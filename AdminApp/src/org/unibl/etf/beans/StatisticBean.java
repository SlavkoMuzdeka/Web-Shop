package org.unibl.etf.beans;

import java.util.ArrayList;

import org.unibl.etf.dao.StatisticDAO;
import org.unibl.etf.dto.Statistic;

public class StatisticBean {

	public ArrayList<Statistic> readAll(){
		return StatisticDAO.selectAll();
	}

}
