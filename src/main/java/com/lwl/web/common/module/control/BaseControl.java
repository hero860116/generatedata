package com.lwl.web.common.module.control;

import com.alibaba.biz.command.result.ResultCode;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.lwl.biz.ao.BaseSession;

/**
 * 	Control的基本类，实现以下几个功能
 * 	1. 从Context中获取参数
 *  2. 报告错误
 * 
 */
public class BaseControl  extends BaseSession{
	public static final String ERROR_SIGN = "_control_error_";
	
	private static Logger logger = LoggerFactory.getLogger(BaseControl.class);

	protected static Logger getLogger() {
		return logger;
	}

	/**
	 * 报告一个错误
	 * 
	 * 处理方式有几种：
	 * 	1. 抛出一个异常，ControlTool会捕捉错误，并处理，开发模式时，会显示异常堆栈，生产模式时，会显示异常信息
	 *  2. 如果不抛出异常，则需要在vm中兼容空数据
	 *  3. 出错误后的页面，表示，页面数据不完整，可以在客户端输出，但不能缓存内容, 或者缓存两三秒
	 *  4. control 不可以缓存
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