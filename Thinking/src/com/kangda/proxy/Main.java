package com.kangda.proxy;

import java.lang.reflect.Proxy;
/*
 * ʵ��jdk��̬���������нӿڣ���ʵ����ķ������ڽӿ���
 * �������ʵ��InvocationHandler�ӿڣ���ʵ��invoke������private��ʵ�����һ��ʵ����ȥ
 * �����Proxy.newProxyInstance�����õ��ӿڶ���
 * ����ʵ����Ҫ�ķ���
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(Main.class.getClassLoader() == People.class.getClassLoader());
		PeopleProxy pp = new PeopleProxy();
		Peo peo = (Peo) Proxy.newProxyInstance(People.class.getClassLoader(), new Class[] { Peo.class }, pp);
		peo.dance();
		peo.eat();
	}
}
