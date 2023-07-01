package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.unibl.etf.dto.Category;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class CategoryDAO {

	private static final String SQL_INSERT = "INSERT INTO categories(name, CATEGORIES_id) VALUES (?, ?)";

	private static final String SQL_SELECT_ALL = "SELECT * FROM categories";

	private static final String SQL_SELECT_ONE = "SELECT * FROM categories WHERE id=?";

	private static final String SQL_DELETE = "DELETE FROM categories WHERE id=?";

	public static boolean create(Category category) {
		boolean result = false;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = {category.getName(), category.getSuperCategory() != null ? category.getSuperCategory().getId(): null};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_INSERT, true, values);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (ps.getUpdateCount() > 0) {
				result = true;
			}
			if (rs.next())
				category.setId(rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return result;
	}

	public static ArrayList<Category> selectAll() {
		ArrayList<Category> categories = new ArrayList<>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object[] values = {};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2), selectOne(new Category(rs.getInt(3))));
				categories.add(category);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return categories;
	}

	public static Category selectOne(Category category) {
		Category cat = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { category.getId() };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ONE, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				cat = new Category(rs.getInt(1), rs.getString(2));
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return cat;
	}

	public static boolean delete(Category category) {
		boolean retValue = false;
		Connection c = null;
		PreparedStatement ps = null;
		Object[] values = { category.getId() };
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
