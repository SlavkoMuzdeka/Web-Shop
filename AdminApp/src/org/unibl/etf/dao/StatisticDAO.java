package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.unibl.etf.dto.Statistic;
import org.unibl.etf.pool.ConnectionPool;
import org.unibl.etf.utils.DAOUtil;

public class StatisticDAO {
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM statistics";
	
	public static ArrayList<Statistic> selectAll() {
		ArrayList<Statistic> statistics = new ArrayList<>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object[] values = {};
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
			while (rs.next()) {
				Statistic statistic = new Statistic(rs.getInt(1), rs.getString(2), dateFormat.format(rs.getTimestamp(3)));
				statistics.add(statistic);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionPool.close(c, ps, rs);
		}
		return statistics;
	}

}
