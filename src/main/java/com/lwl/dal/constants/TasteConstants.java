package com.lwl.dal.constants;

import java.util.Arrays;
import java.util.List;

public class TasteConstants {
	
	/**
	 * �˶����ʱ��  (����)
	 * Ҳ�ǽ��������Ĵ�ӡʱ��
	 */
	public static final NumberConstants<Integer> unsubscribeIntervalNumber = createNumber(2*60, "2����");
	
	/**
	 * ��ʱ�Ͳͣ��ȼ�Ԥ����ƫ��ʱ��
	 */
	public static final NumberConstants<Integer> MIGRATIONSECENDS = createNumber(40*60, "40����");
	
	/**
	 * �����еö��ͣ���͵�����ʱ���ֹͣ����(����)
	 */
	public static final int inOrderOverseconds = 20 * 60;
	
	/**
	 * ���һ���˵�ͣ���������󳬳���
	 */
	public static final int maxOrderExtendSize = 5;
	
	/**
	 * �𲽾��룬2.5������ȡ3Ԫ���ͷ�
	 */
	public static final double STARTING_DISTANCE = 2.5;
	
	/**
	 * ����2���ﲿ�֣�ÿ�������2Ԫ���ͷ�
	 */
	public static final double CONST_EVERY_MILE= 2;
	
	/**
	 * ���5�����ʷ��¼
	 */
	public static final int recentOrderDays = 5;
	
	/**
	 * �ල�绰
	 */
	public static String supervisionPhone = "400-0571-157";
	
	/**
	 * �ͷ��绰
	 */
	public static String customerPhone = "400-0571-157";
	
	/**
	 * ��վ��ַ
	 */
	public static final String webSite = "www.juwaimai.com";
	
	
	/**
	 * �ʼ���Ϣ
	 */
	//public static final String MAIL_HOST = "smtp.gmail.com";
	//public static final int MAIL_PORT = 465;
	//public static final String MAIL_USERNAME  = "juwaimai@gmail.com";
	//public static final String MAIL_PASSWORD = "517juwaimai";
	public static final String MAIL_HOST = "smtp.163.com";
	public static final int MAIL_PORT =25;
	public static final String MAIL_USERNAME  = "juwaimai@163.com";
	public static final String MAIL_PASSWORD = "517juwaimai";
	
	/**
	 * sina΢����¼��Ϣ
	 */
	public static final String SINA_LOGINURI = "https://api.t.sina.com.cn/oauth2/authorize";
	public static final String SINA_GETUID = "https://api.weibo.com/2/account/get_uid.json";
	public static final String SINA_GETACCESSTOKEN = "https://api.weibo.com/oauth2/access_token";
	public static final String SINA_SHOWUSER = "https://api.weibo.com/2/users/show.json";
	public static final String SINA_CLIENT_ID = "2974140750";
	public static final String SINA_CLIENT_SECRET = "471e4dfb41e0e45991ef7c2a7340aac5";
	public static final String SINA_REDIRECT_URI = "http://www.juwaimai.com/sinaweibo.htm";
	
	
	/**
	 * ip������Ϣ
	 */
	public static final String IP_DATANAME = "qqwry.dat";
	//public static final String IP_DATAPATH = "C:/ip";
	public static final String IP_DATAPATH = "/home/lwl/data";
	
	/**
	 * ʳ���б��ҳ����
	 */
	public static final int PAGESIZE_FOOD = 20;
	
	/**
	 * ����ʳ���б��ҳ����
	 */
	public static final int PAGESIZE_SEARCH_FOOD = 20;
	
	/**
	 * ��ʱ�Ͳ�Ĭ��ʱ����
	 */
	public static final String TIMELY_SEND = "00:00";
	

	/**
	 * Ĭ�Ϸָ��� (�ո�)
	 */
	public static final String SEPARATOR = " ";
	
	public static final String SINAWEIBO_INIT_PASSWORD = "123456";
	
	public static final String MIFAN = "�׷�";
	
	/**
	 * �����ˣ�������,�Ʋ˵�����ʹ��
	 */
	public static final List<String> NO_FOOD_LIST = Arrays.asList("�׷�", "�ɰ���");
	
	public static final String NONE_COVER_IMAGE_PATH = "/statics/images/none.jpg";
	
	public String getSupervisionPhone() {
		return supervisionPhone;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}
	
	/**
	 * ������������
	 * @param <T>
	 * @param value
	 * @param message
	 * @return
	 */
	private static <T> NumberConstants<T> createNumber(T value, String message) {
		NumberConstants<T> number = new NumberConstants<T>();
		number.setMessage(message);
		number.setValue(value);
		return number;
	}
}
