package com.test.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class teset {
	public static void main(String[] args) {
		HashSet<String> se = new HashSet<>();
		se.add("a");
		se.add("b");
		se.add("b");
		for (Iterator iterator = se.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		String str = "我223sds";
		System.out.println(str.subSequence(0,1));
	}
	
}