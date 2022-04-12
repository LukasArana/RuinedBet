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
	private Float availableMoney;

	public User(int age, String username, String password, String realName, String surname, String email) {
		this.age = age;
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.surname = surname;
		this.email = email;
		this.availableMoney = (float) 0.0;
	}

	public User(){
		this.availableMoney = (float) 0.0;

	}

	public User(String username, String password, Boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.availableMoney = (float) 0.0;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.admin = true;
		this.availableMoney = (float) 0.0;
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
	public void updateAvailableMoney(float money){this.availableMoney += money;}

    public float getAvailableMoney() {
		return this.availableMoney;
    }
}
