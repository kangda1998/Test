package com.kangda.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PeopleProxy implements InvocationHandler{

	private People p1 = new People();
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("���������ˣ������ҵ�");
		Object result = method.invoke(p1, args);
		System.out.println("֮�����ҵ�");
		return result;
	}

	
}
