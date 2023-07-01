package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.unibl.etf.dto.Admin;
import org.unibl.etf.pool.ConnectionPool;

public class AdminDAO{
	
	private static final String SELECT_ADMIN_FOR_ADMIN_APP = "SELECT * FROM admins WHERE is_admin = 1";

	public static Admin retreive(String userName, String password) {
		Admin administrator = null;
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getInstance().checkOut();
			s = c.createStatement();
			rs = s.executeQuery(SELECT_ADMIN_FOR_ADMIN_APP);
			if(rs.next()) {
				if(userName.equals(rs.getString(4)) && password.equals(rs.getString(5)))
					administrator = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			ConnectionPool.close(c, s, rs);
		}
		return administrator;
	}

}
