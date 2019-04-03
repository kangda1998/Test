package com.kangda.proxy;

import java.lang.reflect.Proxy;
/*
 * 实现jdk动态代理，必须有接口，真实对象的方法抽在接口里
 * 代理对象实现InvocationHandler接口，并实现invoke方法，private真实对象的一个实例进去
 * 最后用Proxy.newProxyInstance方法得到接口对象
 * 调用实际需要的方法
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
