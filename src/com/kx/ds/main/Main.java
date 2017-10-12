package com.kx.ds.main;


import com.kx.ds.service.managers.ThreadManager;
import com.kx.ds.utils.ServiceBean;

public class Main {
	public static void main(String[] args) {
		
		ThreadManager threadManager = ServiceBean.getBean("threadManager");
		threadManager.startThread();

	}
}
