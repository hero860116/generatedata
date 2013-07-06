package com.lwl.web.common.pipeline;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.template.TemplateNotFoundException;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.alibaba.common.logging.LoggerFactory;
import com.lwl.dal.exception.NoLoginRuntimeException;
import com.lwl.dal.exception.ParamInvalidRuntimeException;
import com.lwl.dal.exception.ProcessNotPassRuntimeException;

/**
 * 对action操作进行csrf校验
 * 
 * @author wb-liweilin
 * 
 */
public class ExceptionValve extends AbstractValve {

    @Resource
    private URIBrokerService uriBrokerService;

    @Resource
    private HttpServletRequest request;

    public void invoke(PipelineContext pipelineContext) throws Exception {
    	
        try {
            pipelineContext.invokeNext();
        } catch (Exception e) {
        	
        	//获取action或scree内的异常，到这里已经被包装过
        	Throwable screeExcetion = e.getCause().getCause();
        	
        	//登录异常
        	if (screeExcetion instanceof NoLoginRuntimeException) {
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker tasteModule = (TurbineURIBroker)uriBrokerService.getURIBroker("tasteModule");
                
                NoLoginRuntimeException noLoginRuntimeException = (NoLoginRuntimeException)screeExcetion;
                
                if (noLoginRuntimeException.isWithTarget()) {
                	String port = request.getServerPort() == 80 ? "" : ":"
        				+ (request.getServerPort());
            		
    	    		String queryString = request.getQueryString() == null ? "" : "?"
    	    				+ request.getQueryString();
    	
    	    		// 当前点击的url
    	    		String targetUri = "http://" + request.getServerName() + port
    	    				+ request.getRequestURI() + queryString;
    	    		rundata.setRedirectLocation(tasteModule.setTarget("login.vm").addQueryData("referer", targetUri).render());
    	    		
                } else {
                    rundata.setRedirectLocation(tasteModule.setTarget("login.vm").render());
                } 
        	} 
        	
        	//流程处理不通提示（如时间到期）
        	else if (screeExcetion instanceof ProcessNotPassRuntimeException) {
        		
        		ProcessNotPassRuntimeException processNotPassRuntimeException = (ProcessNotPassRuntimeException)screeExcetion;
        		  //捕获模块抛出的所有异常，转到模块出错页
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
                rundata.setRedirectLocation(commonModule.setTarget("processNotPass.vm").addQueryData("v_param", "message").addQueryData("message", screeExcetion.getMessage()).addQueryData("v_param", "hasPrevious").addQueryData("hasPrevious", processNotPassRuntimeException.isHasPrevious()).render());
        	}
        	
        	//参数校验异常（如用户手动修改参数，或页面）
        	else if (screeExcetion instanceof ParamInvalidRuntimeException) {
        		
        		  //捕获模块抛出的所有异常，转到模块出错页
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
                rundata.setRedirectLocation(commonModule.setTarget("userError.vm").addQueryData("v_param", "message").addQueryData("message", "很抱歉，您访问的页面不存在!").render());
        	}
        	else if (e.getCause() instanceof TemplateNotFoundException){

                  //捕获模块抛出的所有异常，转到模块出错页
                  TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                  TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
                  rundata.setRedirectLocation(commonModule.setTarget("userError.vm").addQueryData("v_param", "message").addQueryData("message", "很抱歉，您访问的页面不存在!").render());
        	}
        	
        	else {
      		  LoggerFactory.getLogger(ExceptionValve.class).error("exception!", e);

              //捕获模块抛出的所有异常，转到模块出错页
              TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
              TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
              rundata.setRedirectLocation(commonModule.setTarget("systemError.vm").render());
        	}
        }
    }

}
