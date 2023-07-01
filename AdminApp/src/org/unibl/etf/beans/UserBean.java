package org.unibl.etf.beans;

import java.util.ArrayList;

import org.unibl.etf.dao.UserDAO;
import org.unibl.etf.dto.User;

public class UserBean {

	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean add() {
		return UserDAO.create(user);
	}
	
	public boolean update() {
		return UserDAO.update(user);
	}
	
	public boolean delete() {
		return UserDAO.delete(user);
	}
	
	public ArrayList<User> readAll(){
		return UserDAO.readAll();
	}
	
	public boolean getOne() {
		return (user = UserDAO.selectOne(user)) != null;
	}
	
}
