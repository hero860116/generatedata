package com.lwl.biz.ao;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.common.lang.StringUtil;

public class BaseSession{
	
	@Resource
	private HttpSession session;
	
	/**
	 * ����session
	 */
	public <T, K> void putToSession(K value, String areaName, String prefix, T... keys) {
		session.setAttribute(conectKey(areaName, prefix, keys), value);
	}
	
	/**
	 * ��ȡsession
	 */
	@SuppressWarnings("unchecked")
	public <T, K> K getFromSession(String areaName, String prefix, T... keys) {
		return (K)session.getAttribute(conectKey(areaName, prefix, keys));
	}
	
	/**
	 * ɾ��session
	 */
	public <T> void removeFromSession(String areaName, String prefix, T... keys) {
		session.removeAttribute(conectKey(areaName, prefix, keys));
	}
	
	/**
	 * ƴ��key��������+ǰ׺+keyֵ��ɣ�
	 * �磺cus_phoneorder_mobile_15869016641
	 * @return
	 */
	private <T> String conectKey(String areaName, String prefix, T... keys) {
		return areaName + '_' + prefix + '_' + StringUtil.join(keys, '_');
	}
	
	public HttpSession getSession() {
		return session;
	}
	
	public void setFirstOpenBrowser() {
		session.setAttribute("firstOpenBrowser", true);
	}
	
	public boolean isFirstOpenBrowser() {
		return (Boolean)session.getAttribute("firstOpenBrowser");
	}
	
	public void setUserId(Long userId) {
		session.setAttribute("userId", userId);
	}
	
	public Long getUserId() {
		return (Long)session.getAttribute("userId");
	}
	
	public Integer getAreaId() {
		return (Integer) getSession().getAttribute("areaId");
	}
	
	public void setAreaId(Integer areaId) {
		getSession().setAttribute("areaId", areaId);
	}
	
/*	public String getAreaName() {
		return (String) getSession().getAttribute("areaName");
	}*/
	
	public String getBuildingUnitName() {
		return (String) getSession().getAttribute("buildingUnitName");
	}
	
	public void setBuildingUnitName(String buildingUnitName) {
		getSession().setAttribute("buildingUnitName", buildingUnitName);
	}
	
	public Long getBuildingUnitId() {
		return (Long) getSession().getAttribute("buildingUnitId");
	}
	
	public void setBuildingUnitId(Long buildingUnitId) {
		getSession().setAttribute("buildingUnitId", buildingUnitId);
	}
	
	public String getBuildingUnitType() {
		return (String) getSession().getAttribute("buildingUnitType");
	}
	
	public void setBuildingUnitType(String buildingUnitType) {
		getSession().setAttribute("buildingUnitType", buildingUnitType);
	}
	
	public String getCityName() {
		return (String) getSession().getAttribute("cityName");
	}
	
	public void setCityName(String cityName) {
		getSession().setAttribute("cityName", cityName);
	}
	
	public Integer getCityId() {
		return (Integer) getSession().getAttribute("cityId");
	}
	
	public void setCityId(Integer cityId) {
		getSession().setAttribute("cityId", cityId);
	}
	
	public String getRegionName() {
		return (String) getSession().getAttribute("regionName");
	}
	
	public void setRegionName(String regionName) {
		getSession().setAttribute("regionName", regionName);
	}
	
	public Integer getRegionId() {
		return (Integer) getSession().getAttribute("regionId");
	}
	
	public void setRegionId(Integer regionId) {
		getSession().setAttribute("regionId", regionId);
	}
	
	public void setUserName(String userName) {
		session.setAttribute("userName", userName);
	}
	
	public String getUserName() {
		return (String)session.getAttribute("userName");
	}
	
	public void setUserType(Integer userName) {
		session.setAttribute("userType", userName);
	}
	
	public Integer getUserType() {
		return (Integer)session.getAttribute("userType");
	}
	
	/**
	 * �͵�ʱ��
	 * @return
	 */
	public String getSendTime() {
		return 	(String)session.getAttribute("sendTime");
	}
	
	/**
	 * �͵�ʱ�䱣�浽cookie��
	 * @param sendTime
	 */
	public void setSendTime(String sendTime) {
		session.setAttribute("sendTime", sendTime);
	}
	
	/**
	 * �Ͳͷ�ʽ��Ԥ�����Ǽ�ʱ�Ͳ�
	 * @return
	 */
	public Integer getSendMealType() {
		return 	(Integer)session.getAttribute("sendMealType");
	}
	
	/**
	 * �Ͳͷ�ʽ��Ԥ�����Ǽ�ʱ�Ͳͣ����浽cookie��
	 * @param sendTime
	 */
	public void setSendMealType(Integer sendMealType) {
		session.setAttribute("sendMealType", sendMealType);
	}
	
	/**
	 * ��ǰΪ���ڶ���Id����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getOrderIds() {
		
		List<String> orderIds = (List<String>)session.getAttribute("orderIds");
		if (orderIds == null) {
			orderIds = new LinkedList<String>();
			session.setAttribute("orderIds", orderIds);
		}
		return 	orderIds;
	}
	
	/**
	 * �͵�ʱ�䱣�浽cookie��
	 * @param sendTime
	 */
	public void setOrderIds(List<String> orderIds) {
		session.setAttribute("orderIds", orderIds);
	}
}
