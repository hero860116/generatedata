package com.lwl.common.bean;

/**
 * ��װ����<2���԰�>
 * ��ʱ����Ҫ�������������������԰�װ��һ����Ϊһ�飬Ȼ�����ж�����д��ݴ���
 * @author Administrator
 * ����ֵ����Ϊnull  �������Ҫ�ٿ����ݴ���ǰ����Ϊnull
 * @param <A>
 * @param <B>
 */
public class PackAttrBean<A, B> {
	private A a;
	private B b;
	
	
	public PackAttrBean(A a, B b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	
	
	public PackAttrBean() {
		super();
	}



	public A getA() {
		return a;
	}
	public void setA(A a) {
		this.a = a;
	}
	public B getB() {
		return b;
	}
	public void setB(B b) {
		this.b = b;
	}
	
	public boolean equals(PackAttrBean<A,B> obj) {
		if (a.equals(obj.getA()) && b.equals(obj.getB())) {
			return true;
		} else {
			return false;
		}
	}
}
