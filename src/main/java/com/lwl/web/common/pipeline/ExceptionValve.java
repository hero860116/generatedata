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
 * ��action��������csrfУ��
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
        	
        	//��ȡaction��scree�ڵ��쳣���������Ѿ�����װ��
        	Throwable screeExcetion = e.getCause().getCause();
        	
        	//��¼�쳣
        	if (screeExcetion instanceof NoLoginRuntimeException) {
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker tasteModule = (TurbineURIBroker)uriBrokerService.getURIBroker("tasteModule");
                
                NoLoginRuntimeException noLoginRuntimeException = (NoLoginRuntimeException)screeExcetion;
                
                if (noLoginRuntimeException.isWithTarget()) {
                	String port = request.getServerPort() == 80 ? "" : ":"
        				+ (request.getServerPort());
            		
    	    		String queryString = request.getQueryString() == null ? "" : "?"
    	    				+ request.getQueryString();
    	
    	    		// ��ǰ�����url
    	    		String targetUri = "http://" + request.getServerName() + port
    	    				+ request.getRequestURI() + queryString;
    	    		rundata.setRedirectLocation(tasteModule.setTarget("login.vm").addQueryData("referer", targetUri).render());
    	    		
                } else {
                    rundata.setRedirectLocation(tasteModule.setTarget("login.vm").render());
                } 
        	} 
        	
        	//���̴���ͨ��ʾ����ʱ�䵽�ڣ�
        	else if (screeExcetion instanceof ProcessNotPassRuntimeException) {
        		
        		ProcessNotPassRuntimeException processNotPassRuntimeException = (ProcessNotPassRuntimeException)screeExcetion;
        		  //����ģ���׳��������쳣��ת��ģ�����ҳ
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
                rundata.setRedirectLocation(commonModule.setTarget("processNotPass.vm").addQueryData("v_param", "message").addQueryData("message", screeExcetion.getMessage()).addQueryData("v_param", "hasPrevious").addQueryData("hasPrevious", processNotPassRuntimeException.isHasPrevious()).render());
        	}
        	
        	//����У���쳣�����û��ֶ��޸Ĳ�������ҳ�棩
        	else if (screeExcetion instanceof ParamInvalidRuntimeException) {
        		
        		  //����ģ���׳��������쳣��ת��ģ�����ҳ
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
                rundata.setRedirectLocation(commonModule.setTarget("userError.vm").addQueryData("v_param", "message").addQueryData("message", "�ܱ�Ǹ�������ʵ�ҳ�治����!").render());
        	}
        	else if (e.getCause() instanceof TemplateNotFoundException){

                  //����ģ���׳��������쳣��ת��ģ�����ҳ
                  TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                  TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
                  rundata.setRedirectLocation(commonModule.setTarget("userError.vm").addQueryData("v_param", "message").addQueryData("message", "�ܱ�Ǹ�������ʵ�ҳ�治����!").render());
        	}
        	
        	else {
      		  LoggerFactory.getLogger(ExceptionValve.class).error("exception!", e);

              //����ģ���׳��������쳣��ת��ģ�����ҳ
              TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
              TurbineURIBroker commonModule = (TurbineURIBroker)uriBrokerService.getURIBroker("commonModule");
              rundata.setRedirectLocation(commonModule.setTarget("systemError.vm").render());
        	}
        }
    }

}
