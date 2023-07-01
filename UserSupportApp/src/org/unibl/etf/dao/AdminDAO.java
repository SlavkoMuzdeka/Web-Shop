package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.unibl.etf.dto.Admin;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class AdminDAO{
	
	private static final String SELECT_ADMIN_FOR_USER_SUPPORT = "SELECT * FROM admins WHERE is_admin = 0";

	public static Admin retreive(String userName, String password) {
		Admin administrator = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object[] values = {};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SELECT_ADMIN_FOR_USER_SUPPORT, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(userName.equals(rs.getString(4)) && password.equals(rs.getString(5)))
					administrator = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			ConnectionPool.close(c, ps, rs);
		}
		return administrator;
	}

}
