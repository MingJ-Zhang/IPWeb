package com.tledu.zmj.util;

import java.util.Properties;

public class PropertiesUtil {
	private static Properties jdbcProperties = null;

	private PropertiesUtil() {

	}
	public static Properties getProperties() {
		try {
			if (jdbcProperties == null) {
				jdbcProperties = new Properties();
				jdbcProperties.load(PropertiesUtil.class.getClassLoader()
						.getResourceAsStream("jdbc.properties"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jdbcProperties;
	}
}
