package com.demons.shiro.test;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

/**
*@author:liuhao
*@version:2020年4月8日下午3:49:12
*@description:
*/
public class Test {
	
	@PostConstruct
	void started() {
		System.out.println("12345");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	} 
	
	public static void add(){
		adf
		
	}
	
	public static void main(String[] args) {
		System.out.println("args:"+args.toString());
		for (String arg : args) {
			System.out.println(arg); 
		}
		System.out.println(args.length);

	}
}
