package com.tledu.zmj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	public static Connection getConnection() {
		try {
			
			Properties properties = PropertiesUtil.getProperties();
			// 根据key 获取对应的value值
			String driver = properties.getProperty("driver");
			String username = properties.getProperty("username");
			String url = properties.getProperty("url");
			String password = properties.getProperty("password");

			Class.forName(driver);
			// 2 创建数据库连接对象
			Connection conn = DriverManager.getConnection(url, username,
					password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(AutoCloseable obj) {
		if (obj != null) {
			try {
				obj.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
