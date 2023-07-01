package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.dto.Message;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class MessageDAO{

	private static final String SELECT_ALL = "SELECT * FROM messages ORDER BY id DESC";
	
	private static final String UPDATE_ONE = "UPDATE messages SET status = 1 WHERE id = ?";

	public static List<Message> findAll() {
		List<Message> messages = new ArrayList<>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object[] values = {};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while (rs.next()) {
				Message message = 
						new Message(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
				message.setUser(UserDAO.findById(message.getUserId()));
				messages.add(message);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			ConnectionPool.close(c, ps, rs);
		}
		return messages;
	}

	public static Message update(Message message) {
		Connection c = null;
		PreparedStatement ps = null;
		Object[] values = {message.getId()};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, UPDATE_ONE, false, values);
			ps.executeUpdate();
			message.setStatus(true);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			ConnectionPool.close(c, ps, null);
		}
		return message;
	}

}
