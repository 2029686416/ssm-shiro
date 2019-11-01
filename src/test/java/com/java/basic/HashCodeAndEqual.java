package com.java.basic;

import java.util.HashMap;

/**
 * @author liuhao
 * @date 2019-10-25-13:39
 */
public class HashCodeAndEqual {
    /**
     * HashMap里的hashcode方法和equal方法什么时候需要重写？
     * @param args
     */
    public static void main(String[] args) {
        Key key1 = new Key(1);
        Key key2 = new Key(1);
//		Object
        HashMap<Key, String> map = new HashMap<Key, String>();
        map.put(key1, "这是key1");
        //用key2对象来取
        //结果 1：当没有在Key中定义hashcode,equal方法时，默认使用的是Object中的方法
        System.out.println(map.get(key2));
    }
}
class Key {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Key(Integer id) {
        this.id=id;
    }

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj==null || !(obj instanceof Key)) {
            return false;
        }else {
            return this.getId().equals(((Key)obj).getId());
        }
    }


}
