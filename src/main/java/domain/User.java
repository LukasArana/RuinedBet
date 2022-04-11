package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	private int age;
	@Id
	private String username;
	private String password;
	private boolean admin;
	private String realName;
	private String surname;
	private String email;
	
	public User(int age, String username, String password, String realName, String surname, String email) {
		this.age = age;
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.surname = surname;
		this.email = email;
	}

	public User(){

	}

	public User(String username, String password, Boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.admin = true;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getUsername() {
		return this.username;
	}
	public boolean isAdmin() {
		return this.admin;
	}
}
