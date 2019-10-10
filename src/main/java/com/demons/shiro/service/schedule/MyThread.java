package com.demons.shiro.service.schedule;

import com.demons.shiro.service.UserService;

public class MyThread implements Runnable{

	private UserService userService;
	
	public MyThread(UserService userService) {
		this.userService=userService;
	}
	@Override
	public void run() {
		System.out.println("通过构造方法传值："+userService);
	}

}
