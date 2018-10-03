package modelo;

import java.util.List;

public class User {

	private int id;
	private String userName;
	private String pass;
	private List<Role> roles;

	public User(int id, String userName, String pass, List<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.pass = pass;
		this.roles = roles;
	}

	public User(int id, String userName) {
		this.id = id;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	// METODOS
	
	public void changePass(String pass) {
		
	}
	
	public void addUser(int id, String userName, String pass) {
		
	}

}
