package com.test.test;

import java.util.HashSet;
import java.util.Iterator;

public class teset {
	public static void main(String[] args) {
		HashSet<String> se = new HashSet<>();
		se.add("a");
		se.add("b");
		se.add("b");

		for (Iterator iterator = se.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
			as();
		}
		String str = "我223sds";
		System.out.println(str.subSequence(0,1));
	}
	public static void as(){
		System.out.println("11");

	}	/*public static void main(String[] args) throws Exception{
		User user = new User();
		user.getId();
		File file = new File("D:\\aaa/a.txt");
		InputStream is = new FileInputStream(file);
		byte[] b = new byte[1024];
		int len = 0;
		System.out.println("!2");
		while ((len=is.read(b))!=-1){
			System.out.println(new String(b,0,len));
		}

	}*/


	
}