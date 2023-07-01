package org.unibl.etf.dto;

import java.util.Objects;

public class Category {
	
	private Integer id;
	private String name;
	private Category superCategory;
	
	public Category() {
		super();
	}

	public Category(Integer id, String name, Category superCategory) {
		super();
		this.id = id;
		this.name = name;
		this.superCategory = superCategory;
	}

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(Integer id) {
		super();
		this.id = id;
	}
	
	public Category(String name, Category superCategory) {
		super();
		this.name = name;
		this.superCategory = superCategory;
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
