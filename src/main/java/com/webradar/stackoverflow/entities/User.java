package com.webradar.stackoverflow.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String usernameOrEmail;
	private String password;
	
	public User() {
	}
	
	public User(Long id, String name, String usernameOrEmail, String password) {
		super();
		this.id = id;
		this.name = name;
		this.usernameOrEmail = usernameOrEmail;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, usernameOrEmail);
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
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(usernameOrEmail, other.usernameOrEmail);
	}
}
