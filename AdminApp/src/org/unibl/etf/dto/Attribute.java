package org.unibl.etf.dto;
import java.util.Objects;

import org.unibl.etf.enums.*;

public class Attribute {
	
	private Integer id;
	private Category category;
	private String name;
	private Type type;
	
	public Attribute() {
		super();
	}

	public Attribute(Integer id, Category category, String name, Type type) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.type = type;
	}

	public Attribute(Category category, String name, Type type) {
		super();
		this.category = category;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		Attribute other = (Attribute) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Attribute [id=" + id + ", category=" + category + ", name=" + name + ", type=" + type + "]";
	}

}
