package org.unibl.etf.beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.unibl.etf.dao.AdminDAO;
import org.unibl.etf.dto.Admin;

public class AdminBean {
	
	private Admin admin = new Admin();
	private boolean isLoggedIn = false;
	
	public Admin retreive(String userName, String password) throws NoSuchAlgorithmException {
		String hashed_password = calculateHash(password);
		if((admin = AdminDAO.retreive(userName, hashed_password)) != null) {
			isLoggedIn = true;
			return admin;
		}
		return admin;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public void logout() {
		admin = new Admin();
		isLoggedIn = false;
	}

	private String calculateHash(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    if (hex.length() == 1) {
		        hexString.append('0');
		    }
		    hexString.append(hex);
		}
		String hexHash = hexString.toString();
		return hexHash;
	}
	
}
