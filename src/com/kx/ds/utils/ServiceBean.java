package com.kx.ds.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceBean<T> {
	
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("KXDCT.xml");
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String key){
		return (T) ctx.getBean(key);
	}
	
	
}
