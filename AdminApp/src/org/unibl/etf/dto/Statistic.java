package org.unibl.etf.dto;

import java.util.Objects;

public class Statistic {
	
	private Integer id;
	private String description;
	private String dateTime;
	
	public Statistic() {
		super();
	}

	public Statistic(Integer id, String description, String dateTime) {
		super();
		this.id = id;
		this.description = description;
		this.dateTime = dateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
		Statistic other = (Statistic) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Statistic [id=" + id + ", description=" + description + ", dateTime=" + dateTime + "]";
	}

}
