package com.tledu.zmj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tledu.zmj.dao.IIPLocationDao;
import com.tledu.zmj.util.DBUtil;

public class IPLocationDao implements IIPLocationDao{

	@SuppressWarnings("null")
	@Override
	public String load(String ip) {
		
		String location = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select location from ip where INET_ATON(?) between  startIPLong and endIPLong";
			statement = connection.prepareStatement(sql);
			statement.setString(1,ip);
			rs = statement.executeQuery();
			while(rs.next()){
				 location = rs.getString("location");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return location;
	}

}
