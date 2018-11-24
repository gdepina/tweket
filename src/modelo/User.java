package modelo;

import java.util.List;

import dao.ClientDAO;
import dao.UserDAO;
import excepciones.FKException;
import excepciones.PKDuplicadaException;
import view.ClientView;
import view.UserView;

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
	
	public User(String name, String pass) {
		this.userName = name;
		this.pass = pass;
	}

	public UserView toView() {
		return new UserView(this.userName, this.id);
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
	public void addRole(Role e) {
		roles.add(e);
	}
	
	public void removeRol(Role e) {
		roles.remove(e);
	}
	
	public void saveRole(Role role) {		
		this.addRole(role);
		UserDAO.getInstancia().saveRoleByUser(role, this);
	}
	
	public void removeRole(Role role) {
		UserDAO.getInstancia().removeRolByUser(role, this);
	}
	
	
	public void add() throws PKDuplicadaException {
		UserDAO.getInstancia().addUser(this);
	}

	public void remove() throws FKException {
		UserDAO.getInstancia().removeUser(this);		
	}
	
	public void update() {
		UserDAO.getInstancia().updateUser(this);		
	}

}
