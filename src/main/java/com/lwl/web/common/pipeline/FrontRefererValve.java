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
 * ������v_param�������������ֱ�ӷ���context��
 * @author Administrator
 *
 *
 * ���㣺ͨ��request��ȡ�����ķ���url
 */
public class FrontRefererValve extends AbstractValve {

	@Resource
	private HttpServletRequest request;
	
	@Resource
	private HttpSession session;
	
	@Resource
	private URIBrokerService uriBrokerService;
	
	/**
	 * ������Ԫ�أ�session,referer������context�У����߽��ƶ�Ԫ�أ�Ԫ����Ϊv_param��ֵ
	 */
	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {
		
		TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
		
		String port = request.getServerPort() == 80 ? "" : ":"
				+ (request.getServerPort());
		String queryString = request.getQueryString() == null ? "" : "?"
				+ request.getQueryString();

		// ��ǰ�����url
		String inUri = "http://" + request.getServerName() + port
				+ request.getRequestURI() + queryString;

		// ��ǰrefererurl
		String referUri = request.getHeader("referer");
		if (referUri == null) {
			referUri = getTurbineURIBroker("tasteModule").render();
		}

		// session�б����url
		@SuppressWarnings("unchecked")
		LinkedList<String> referers = (LinkedList<String>) session
				.getAttribute("frontReferers");

		//�Ƿ�����referer
		String skipreferer = rundata.getParameters().getString("skipreferer");
		if ("true".equals(skipreferer)) {

		}
		//�ύ��ǰҳ��
		else if (inUri.equals(referUri)) {

		}
		else {

			if (referers == null) {
				referers = new LinkedList<String>();
			}

			referers.addLast(referUri);

/*			// ����һ��referer����󣬼�������Ƿ���ԭ���ļ�¼�У�Ȼ���������
			if (referers != null && referers.contains(inUri)) {
				int index = referers.indexOf(inUri);

				LinkedList<String> subReferers = new LinkedList<String>();
				for (int i = 0; i < index; i++) {
					subReferers.add(referers.get(i));
				}

				referers = subReferers;
			}*/
		}

		//�Ƿ�ɾ��referer
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
	
	
	//����ģ���ַ
	private TurbineURIBroker getTurbineURIBroker(String turbineUriName) {
		return (TurbineURIBroker)uriBrokerService.getURIBroker(turbineUriName);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private <T extends URIBroker> T getURIBroker(String uriName) {
		return (T)uriBrokerService.getURIBroker(uriName);
	}

}