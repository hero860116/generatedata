package com.lwl.web.common.pipeline;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;

/**
 * ������v_param�������������ֱ�ӷ���context��
 * @author Administrator
 *
 *
 * ���㣺ͨ��request��ȡ�����ķ���url
 */
public class PutToContextValve extends AbstractValve {

	@Resource
	private HttpServletRequest request;
	
	@Resource
	private HttpSession session;
	
	/**
	 * ������Ԫ�أ�session,referer������context�У����߽��ƶ�Ԫ�أ�Ԫ����Ϊv_param��ֵ
	 */
	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {

		TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
		Context context = rundata.getContext();

		//��v_param���Զ���ֵ���ԣ�����context��
		String[] keys = rundata.getParameters().getStrings("v_param");
		
		if (keys != null) {
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				String value = rundata.getParameters().getString(key);
				
				context.put(key, value);
			}
		}
		
		//�����¼������¼id��userName��areaId����
		Long userId = (Long)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		if (userId != null && userName != null) {
			context.put("userId", userId);
			context.put("userName", userName);
		}
		

		pipelineContext.invokeNext();
	}

}