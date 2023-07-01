package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.unibl.etf.dto.Attribute;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class AttributeDAO {

	private static final String SQL_INSERT = "INSERT INTO attributes(CATEGORY_id, name, type) VALUES (?, ?, ?)";

	public static boolean create(Attribute attribute) {
		boolean result = false;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object obj = attribute.getType().toString() == "STRING"? "string": "number";
		System.out.println(attribute.getType().toString());
		Object values[] = { attribute.getCategory().getId(), attribute.getName(), obj };
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_INSERT, true, values);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (ps.getUpdateCount() > 0) {
				result = true;
			}
			if (rs.next())
				attribute.setId(rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return result;
	}

}
