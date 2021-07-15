package models;

public class Account {
	private String id;
	private String email;
	private String password;
	private String applicationPassword;
	
	public Account(String id, String email, String password, String applicationPassword) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.applicationPassword = applicationPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApplicationPassword() {
		return applicationPassword;
	}

	public void setApplicationPassword(String applicationPassword) {
		this.applicationPassword = applicationPassword;
	}
	
	

}
