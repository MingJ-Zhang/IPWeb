package com.tledu.zmj.util;

public class IPUtil {
	public static void main(String[] args) {
		String ip = "193.0.64.0";
		long ipLong  = ipToLong(ip);
		
		String ipString = longToIp(ipLong);
		System.out.println(ip+" : "+ipString+" : "+ipLong);
	}
	public static long ipToLong(String ipString) {
		long result = 0;
		java.util.StringTokenizer token = new java.util.StringTokenizer(
				ipString, ".");
		result += Long.parseLong(token.nextToken()) << 24;
		result += Long.parseLong(token.nextToken()) << 16;
		result += Long.parseLong(token.nextToken()) << 8;
		result += Long.parseLong(token.nextToken());
		return result;
	}

	public static String longToIp(long ipLong) {
		StringBuilder sb = new StringBuilder();
		sb.append(ipLong >>> 24);
		sb.append(".");
		sb.append(String.valueOf((ipLong & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((ipLong & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(ipLong & 0x000000FF));
		return sb.toString();
	}
}
