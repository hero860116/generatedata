package com.lwl.web.common.module.control;

import com.alibaba.biz.command.result.ResultCode;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.lwl.biz.ao.BaseSession;

/**
 * 	Control�Ļ����࣬ʵ�����¼�������
 * 	1. ��Context�л�ȡ����
 *  2. �������
 * 
 */
public class BaseControl  extends BaseSession{
	public static final String ERROR_SIGN = "_control_error_";
	
	private static Logger logger = LoggerFactory.getLogger(BaseControl.class);

	protected static Logger getLogger() {
		return logger;
	}

	/**
	 * ����һ������
	 * 
	 * ����ʽ�м��֣�
	 * 	1. �׳�һ���쳣��ControlTool�Ჶ׽���󣬲���������ģʽʱ������ʾ�쳣��ջ������ģʽʱ������ʾ�쳣��Ϣ
	 *  2. ������׳��쳣������Ҫ��vm�м��ݿ�����
	 *  3. ��������ҳ�棬��ʾ��ҳ�����ݲ������������ڿͻ�������������ܻ�������, ���߻���������
	 *  4. control �����Ի���
	 * 
	 */
	public void reportError(TurbineRunData rundata, ResultCode resultCode) {
		rundata.getRequest().setAttribute(ERROR_SIGN, true);
		rundata.getResponse().setHeader("Cache-Control", "no-cache");
	}
	
	public String getString(Context context, String key) {
		return getString(context, key, null);
	}
	
	public String getString(Context context, String key, String defaultValue) {
		Object value = context.get(key);
		if (value == null) {
			return defaultValue;
		}
		String val = String.valueOf(value).trim();
		if (val.equals("") || val.equals("null")) {
			return defaultValue;
		}
		return val;
	}
	
	public int getInt(Context context, String key) {
		return getInt(context, key, 0);
	}
	
	public int getInt(Context context, String key, int defaultValue) {
		Object value = context.get(key);
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		} else if (value instanceof Long) {
				return ((Long) value).intValue();
		} else {
	        try {
	            return Integer.parseInt(String.valueOf(value));
	        } catch (NumberFormatException nfe) {
	            return defaultValue;
	        }
		}
	}

}