package com.demons.shiro.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.demons.shiro.model.User;

import cn.com.client.DecodeClient;

public class Test {
	
	/*public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "a");
		map.put("b", "b");
//		System.out.println(map);
		synchronized(map) {
			System.out.println(map);
		}
		List<String> list = new ArrayList<String>();
		list.add("23");
		
		String[] s = {"2"};
		List<String> asList = Arrays.asList(s);
		System.out.println(asList);
		String[] strings = asList.toArray(new String[asList.size()]);
	}*/
	public static void main(String[] args) {
		Long da = 2047345523323L;
		Date date = new Date(da);
		System.out.println(new Date().getTime());//获取当前时间的Long类型 数字
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sim.format(date);
		System.out.println(time);
	}
}

