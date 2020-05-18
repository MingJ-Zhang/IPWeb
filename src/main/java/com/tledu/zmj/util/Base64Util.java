package com.tledu.zmj.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

	// 编码
	public static String encode(String input)
			throws UnsupportedEncodingException {
		return Base64.getEncoder().encodeToString(input.getBytes("utf-8"));
	}

	// 解码
	public static String decode(String baseValue)
			throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(baseValue), "utf-8");
	}
}
