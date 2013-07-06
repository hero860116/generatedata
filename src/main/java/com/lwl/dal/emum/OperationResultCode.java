package com.lwl.dal.emum;

import java.util.ArrayList;
import java.util.List;

import com.lwl.common.bean.KeyValue;

public enum OperationResultCode {

	/**
	 * ÿһ�ַ����룬����һ�ִ������ͣ��ٵ��÷����ֻ���ajax������һ�ֲ�ͬ�Ĵ���ͬһ����ʽӦ�û�Ϊͬ�ⷵ����
	 */
	SUCESS(0, "�ɹ�"), OTHER_PEOPLE(1, "�������˲�������"), NOT_EXIST(2, "�������岻���ڣ����ܱ���̨������ɾ����"), OTHER_ERROR(10, "ʧ��");
	
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
	 * ��ýṹ��keyvalue�б�����ǰ�˲���(������)
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
	 * �ж��Ƿ����ָ��ֵ
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
	 * ��ȡָ��ֵ������
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