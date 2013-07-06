package com.lwl.dal.emum;

import java.util.ArrayList;
import java.util.List;

import com.lwl.common.bean.KeyValue;

public enum OperationResultCode {

	/**
	 * 每一种返回码，代表一种错误类型，再调用方（手机，ajax）代表一种不同的处理，同一处理方式应该划为同意返回码
	 */
	SUCESS(0, "成功"), OTHER_PEOPLE(1, "被其他人操作过了"), NOT_EXIST(2, "操作主体不存在，可能被后台其他人删除了"), OTHER_ERROR(10, "失败");
	
	private Integer code;
	private String message;
	
	OperationResultCode(Integer code, String message){
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	/**
	 * 获得结构的keyvalue列表，便于前端操作(下拉框)
	 * @return
	 */
	public static List<KeyValue> getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
		
		for (OperationResultCode operationResultCode : OperationResultCode.values()) {
			KeyValue keyValue = new KeyValue(operationResultCode.getMessage(), operationResultCode.getCode());
			keyValues.add(keyValue);
		}
		
		return keyValues;
	}
	
	/**
	 * 判断是否包含指定值
	 * @param status
	 * @return
	 */
	public static boolean containsType(Integer status) {
		boolean ret = false;
		for (OperationResultCode operationResultCode : OperationResultCode.values()){
			if (operationResultCode.getCode().equals(status)) {
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	/**
	 * 获取指定值的描述
	 * @param status
	 * @return
	 */
	public static String getMessage(Integer status) {
		String message = "";
		for (OperationResultCode operationResultCode : OperationResultCode.values()) {
			if (operationResultCode.getCode().equals(status)) {
				message = operationResultCode.getMessage();
				break;
			}
		}
		return message;
	}
	
}