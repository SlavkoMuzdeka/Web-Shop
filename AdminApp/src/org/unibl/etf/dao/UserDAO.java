package org.unibl.etf.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.unibl.etf.dto.User;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class UserDAO {

	private static final String SQL_INSERT = "INSERT INTO users (first_name, last_name, city, user_name, password, avatar, mail, is_activated, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_SELECT_ALL = "SELECT * FROM users WHERE status = 1";

	private static final String SQL_SELECT_ONE = "SELECT * FROM users WHERE status = 1 AND id = ?";

	private static final String SQL_UPDATE = "UPDATE users SET first_name=?, last_name=?, city=?, user_name=?, password=?, avatar=?, mail=? WHERE id=?";

	private static final String SQL_DELETE = "UPDATE users SET status=0 WHERE id = ?";

	public static boolean create(User user) {
		boolean result = false;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { user.getFirstName(), user.getLastName(), user.getCity(), user.getUserName(),
				user.getPassword(), user.getAvatar(), user.getMail(), true, true };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_INSERT, true, values);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (ps.getUpdateCount() > 0) {
				result = true;
			}
			if (rs.next())
				user.setId(rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return result;
	}

	public static ArrayList<User> readAll() {
		ArrayList<User> users = new ArrayList<>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object[] values = {};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while (rs.next()) {
				byte[] data = null;
				Blob blob = rs.getBlob(7);
				if (blob != null)
					data = blob.getBytes(1L, (int) blob.length());
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6),
						rs.getString(4), data, rs.getString(8), rs.getBoolean(9));
				users.add(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return users;
	}

	public static User selectOne(User book) {
		User user = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { book.getId() };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ONE, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				byte[] data = null;
				Blob blob = rs.getBlob(7);
				if (blob != null)
					data = blob.getBytes(1L, (int) blob.length());
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6),
						rs.getString(4), data, rs.getString(8), rs.getBoolean(9));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return user;
	}

	public static boolean update(User user) {
		boolean retVal = false;
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { user.getFirstName(), user.getLastName(), user.getCity(), user.getUserName(),
				user.getPassword(), user.getAvatar(), user.getMail(), user.getId() };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_UPDATE, false, values);
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
			retVal = false;
		} finally {
			ConnectionPool.close(c, ps, null);
		}
		return retVal;
	}

	public static boolean delete(User user) {
		boolean retValue = false;
		Connection c = null;
		PreparedStatement ps = null;
		Object[] values = { user.getId() };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_DELETE, false, values);
			ps.executeUpdate();
			retValue = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			retValue = false;
		} finally {
			ConnectionPool.close(c, ps, null);
		}
		return retValue;
	}

}
