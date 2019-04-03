package com.kangda.proxy;

public class People implements Peo {
	/* (non-Javadoc)
	 * @see com.kangda.proxy.Peo#eat()
	 */
	@Override
	public void eat() {
		System.out.println("I often eat some beef");
	}
	/* (non-Javadoc)
	 * @see com.kangda.proxy.Peo#dance()
	 */
	@Override
	public void dance() {
		System.out.println("I love dance");
	}

}
