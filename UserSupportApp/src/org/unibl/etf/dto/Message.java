package org.unibl.etf.dto;

import java.util.Objects;

public class Message {

	private Integer id;
	private String title;
	private String content;
	private Boolean status;
	private Integer userId;
	private User user;
	
	public Message() {
		super();
	}

	public Message(Integer id, String title, String content, Boolean status, Integer userId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.userId = userId;
	}

	public Message(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Message other = (Message) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + ", userId="
				+ userId + ", user=" + user + "]";
	}
	
}
