package org.unibl.etf.dto;

import java.util.Arrays;
import java.util.Objects;

public class User {

	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String city;
	private byte[] avatar;
	private String mail;
	private boolean isActivated;

	public User() {
		super();
	}

	public User(Integer id, String firstName, String lastName, String userName, String password, String city,
			byte[] avatar, String mail, boolean isActivated) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.city = city;
		this.avatar = avatar;
		this.mail = mail;
		this.isActivated = isActivated;
	}

	public User(Integer id, String firstName, String lastName, String userName, String password, String city,
			byte[] avatar, String mail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.city = city;
		this.avatar = avatar;
		this.mail = mail;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User(String firstName, String lastName, String userName, String password, String city,
			byte[] avatar, String mail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.city = city;
		this.avatar = avatar;
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", city=" + city + ", avatar=" + Arrays.toString(avatar) + ", mail=" + mail
				+ ", isActivated=" + isActivated + "]";
	}

}
