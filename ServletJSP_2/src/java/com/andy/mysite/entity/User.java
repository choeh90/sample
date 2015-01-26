package com.andy.mysite.entity;

import java.sql.Date;

public class User {
	private String id;
	private String name;
	private String pass;
	private String email;
	private String phone;

	public User() {
		super();
	}

	public User(String id, String name, String pass, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass + ", email=" + email + ", phone=" + phone + "]";
	}

}
