package com.demons.shiro.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

public class Encodes {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			// throw ExceptionUtil.getStackTrace(e);
			// return ExceptionUtil.getStackTrace(e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * Html 转码.
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * Html 解码.
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSaltAndPass(String password) {
		// 得到8位盐
		byte[] salts = Digests.generateSalt(8);
		// 将8位byte数组装换为spring
		String salt = Encodes.encodeHex(salts);
		// 将spring数组转化为8位byte数组
		salts = Encodes.decodeHex(salt);
		System.out.println(salts);
		// 原密码
//				String password = "123456";  
		// 对密码加盐进行1024次SHA1加密
		byte[] hashPassword = Digests.sha1(password.getBytes(), salts, 1024);
		// 将加密后的密码数组转换成字符串
		password = Encodes.encodeHex(hashPassword);
		return salt + "," + password;
	}
	   /**
     * @param plainPassword
     * @param password
     * @return boolean 解密判断密码是否正确
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, 1024);
        System.out.println(Encodes.encodeHex(salt)+ Encodes.encodeHex(hashPassword));
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

	public static void main(String[] args) {
		// 得到8位盐
		byte[] salts = Digests.generateSalt(8);
		// 将8位byte数组装换为spring
		String salt = Encodes.encodeHex(salts);
		// 将spring数组转化为8位byte数组
		salts = Encodes.decodeHex(salt);

		// 原密码
		String password = "123456";
		// 对密码加盐进行1024次SHA1加密
		byte[] hashPassword = Digests.sha1(password.getBytes(), salts, 1024);
		// 将加密后的密码数组转换成字符串
		password = Encodes.encodeHex(hashPassword);
		
		System.out.println(validatePassword("123456", "8d0f2a60682b013a43dc3644e91042f58942e710dfc1619f4cc9a704"));
		
	}
}