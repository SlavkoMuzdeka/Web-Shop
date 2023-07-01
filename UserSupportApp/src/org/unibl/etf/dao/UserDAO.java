package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.unibl.etf.dto.User;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class UserDAO {
	
	private static final String SELECT_ONE = "SELECT * FROM users WHERE id = ?";
	
	public static User findById(Integer id) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		Object[] values = { id };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SELECT_ONE, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(8));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			ConnectionPool.close(c, ps, rs);
		}
		return user;
	}
	

}
