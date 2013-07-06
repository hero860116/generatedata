package com.lwl.web.admin.pipeline;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.turbine.util.TurbineUtil;

/**
 * ���û��½��ת����½ҳ��
 * 
 * @author liweilin
 * 
 */
public class LoginValve extends AbstractValve {

    @Resource
    private URIBrokerService uriBrokerService;

    @Resource
    private HttpServletRequest request;
    
    @Resource
    private HttpSession session;

    public void invoke(PipelineContext pipelineContext) throws Exception {
    	
    	//Ϊajax��ѯ����
    	if (request.getRequestURI().contains("admin/success")) {
    		pipelineContext.invokeNext();
    		return;
    	}
    	
    	if ((Long)session.getAttribute("userId") == null) {
            TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
            TurbineURIBroker tasteModule = (TurbineURIBroker)uriBrokerService.getURIBroker("tasteModule");
            rundata.setRedirectLocation(tasteModule.setTarget("login.vm").render());
            
            return;
    	}
    	
        pipelineContext.invokeNext();
    }

}
