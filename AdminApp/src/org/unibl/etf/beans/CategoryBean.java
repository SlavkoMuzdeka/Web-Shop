package org.unibl.etf.beans;

import java.util.ArrayList;

import org.unibl.etf.dao.CategoryDAO;
import org.unibl.etf.dto.Category;

public class CategoryBean {
	
	private Category category = new Category();
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public ArrayList<Category> readAll(){
		return CategoryDAO.selectAll();
	}
	
	public boolean delete() {
		return CategoryDAO.delete(category);
	}
	
	public boolean add() {
		return CategoryDAO.create(category);
	}
	
}
