package com.kangda.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PeopleProxy implements InvocationHandler{

	private People p1 = new People();
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我拦下来了，先听我的");
		Object result = method.invoke(p1, args);
		System.out.println("之后听我的");
		return result;
	}

	
}
