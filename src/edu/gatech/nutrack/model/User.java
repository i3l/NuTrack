package edu.gatech.nutrack.model;

public class User {
	private String username, password, email;
	private int type;  // 0 is physician
	
	public User(String username, String password, String email, int type) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
	}
	
	public User() {
		this("", "", "", 0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
