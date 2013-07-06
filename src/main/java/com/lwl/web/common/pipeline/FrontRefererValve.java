package com.lwl.web.common.pipeline;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;

/**
 * 将包含v_param参数的请求参数直接放入context中
 * @author Administrator
 *
 *
 * 烦点：通过request获取完整的访问url
 */
public class FrontRefererValve extends AbstractValve {

	@Resource
	private HttpServletRequest request;
	
	@Resource
	private HttpSession session;
	
	@Resource
	private URIBrokerService uriBrokerService;
	
	/**
	 * 将公用元素（session,referer）放入context中，或者将制定元素，元素名为v_param的值
	 */
	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		
		TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
		
		String port = request.getServerPort() == 80 ? "" : ":"
				+ (request.getServerPort());
		String queryString = request.getQueryString() == null ? "" : "?"
				+ request.getQueryString();

		// 当前点击的url
		String inUri = "http://" + request.getServerName() + port
				+ request.getRequestURI() + queryString;

		// 当前refererurl
		String referUri = request.getHeader("referer");
		if (referUri == null) {
			referUri = getTurbineURIBroker("tasteModule").render();
		}

		// session中保存的url
		@SuppressWarnings("unchecked")
		LinkedList<String> referers = (LinkedList<String>) session
				.getAttribute("frontReferers");

		//是否增加referer
		String skipreferer = rundata.getParameters().getString("skipreferer");
		if ("true".equals(skipreferer)) {

		}
		//提交当前页面
		else if (inUri.equals(referUri)) {

		}
		else {

			if (referers == null) {
				referers = new LinkedList<String>();
			}

			referers.addLast(referUri);

/*			// 将上一个referer加入后，检查连接是否在原来的记录中，然后进行重置
			if (referers != null && referers.contains(inUri)) {
				int index = referers.indexOf(inUri);

				LinkedList<String> subReferers = new LinkedList<String>();
				for (int i = 0; i < index; i++) {
					subReferers.add(referers.get(i));
				}

				referers = subReferers;
			}*/
		}

		//是否删除referer
		if (referers != null && referers.size() > 0
				&& inUri.equals(referers.getLast())) {
			referers.removeLast();

		}

		Context context = rundata.getContext();

		session.setAttribute("frontReferers", referers);
		if (referers != null && referers.size() > 0) {
			context.put("referer", referers.getLast());
			rundata.getParameters().add("referer", referers.getLast());
		}

		pipelineContext.invokeNext();
	}
	
	
	//返回模块地址
	private TurbineURIBroker getTurbineURIBroker(String turbineUriName) {
		return (TurbineURIBroker)uriBrokerService.getURIBroker(turbineUriName);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private <T extends URIBroker> T getURIBroker(String uriName) {
		return (T)uriBrokerService.getURIBroker(uriName);
	}

}