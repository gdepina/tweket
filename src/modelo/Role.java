package modelo;


public abstract class Role {
	
	protected User user;
	protected String type;
	public void Rol() {
		
	}
	public User getUser() {
		System.out.println("Alan esta es para vos");
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
