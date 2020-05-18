package com.tledu.zmj.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static String regexIP = "(([0,1]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([0,1]?\\d?\\d|2[0-4]\\d|25[0-5])";

	/**
	 * 校验IP
	 * @param ip
	 * @return
	 */
	public static boolean isValidIP(String ip) {
		return isValid(regexIP, ip);
	}

	/**
	 * 验证格式
	 * 
	 * @param regexPattern
	 * @param input
	 * @return
	 */
	public static boolean isValid(String regexPattern, String input) {
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	/**
	 * 取数据
	 * 
	 * @param regexPattern
	 * @param input
	 * @param groupIndex
	 * @return
	 */
	public static String getMatchContent(String regexPattern, String input,
			int groupIndex) {
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(groupIndex);
		}

		return null;
	}
}
