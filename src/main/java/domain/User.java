package domain;

public class User {
	private int age;
	private String username;
	private String password;
	
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
