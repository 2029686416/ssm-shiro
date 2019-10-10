package com.demons.shiro.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author hbj @2017 2017年10月16日 下午10:57:13
 * @version 1.0
 *          <p>
 *          Title:R
 *          </p>
 *          <p>
 * @Description: 结果返回
 *               </p>
 *               <p>
 *               Company:
 *               </p>
 */
public class R extends HashMap<String, Object> implements Serializable{

	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
	}

	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("success", false);
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("success", true);
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.put("success", true);
		r.putAll(map);
		return r;
	}

	public static R ok() {
		R r = new R();
		r.put("success", true);
		return r;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}