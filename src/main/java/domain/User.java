package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int age;
	private String username;
	private String password;
	private boolean admin;
	
	public User(int age, String username, String password) {
		this.age = age;
		this.username = username;
		this.password = password;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getUsername() {
		return this.username;
	}

}
