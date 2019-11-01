package com.java.basic;

import java.util.LinkedList;
import java.util.List;

/**
 * ArrayList和LinkedList
 *
 * @author liuhao
 * @date 2019-10-25-15:03
 */
public class ArrayList {
    public static void main(String[] args) {
        List<String> arrayList = new java.util.ArrayList<>();
        arrayList.add("a");

        List<String> linkList = new LinkedList<>();
        linkList.add("b");
        System.out.println(linkList);
    }
}
