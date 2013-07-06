package com.lwl.web.common.module.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.turbine.util.TurbineUtil;


public abstract class BaseAction {

	@Resource
	private URIBrokerService uriBrokerService;
	
	@Resource
	private HttpServletRequest request;
	
	
	/**
	 * ת���û�У�����ҳ
	 * @param message
	 */
	protected void redirectToUserError(String message) {
		TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
		rundata.setRedirectLocation(getTurbineURIBroker("commonModule").setTarget("userError.vm").addQueryData("v_param", "message").addQueryData("message", message).render());
	}
	
	/**
	 * ת���û�У�����ҳ
	 * @param message
	 */
	protected void redirectToUserError() {
		redirectToUserError("�ܱ�Ǹ�������ʵ�ҳ�治����!");
	}
	
	/**
	 * ת��ϵͳ����ҳ��
	 * @param message
	 */
	protected void redirectToSystemError(String message) {
		TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
		rundata.setRedirectLocation(getTurbineURIBroker("commonModule").setTarget("systemError.vm").render());
	}
	
	/**
	 * ת����ʾ��Ϣҳ��
	 * @param message
	 */
	protected void redirectToTipInfo(String message) {
		TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
		rundata.setRedirectLocation(getTurbineURIBroker("commonModule").setTarget("info.vm").addQueryData("v_param", "message").addQueryData("message", message).render());
	}
	
	/**
	 * ת���ɹ���Ϣҳ��
	 * @param message
	 */
	protected void redirectToSuccess(String message) {
		TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
		rundata.setRedirectLocation(getTurbineURIBroker("commonModule").setTarget("success").addQueryData("v_param", "message").addQueryData("message", message).render());
	}
	
	//����ģ���ַ
	protected TurbineURIBroker getTurbineURIBroker(String turbineUriName) {
		return (TurbineURIBroker)uriBrokerService.getURIBroker(turbineUriName);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends URIBroker> T getURIBroker(String uriName) {
		return (T)uriBrokerService.getURIBroker(uriName);
	}
}
