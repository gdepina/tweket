package view;

public class UserView {
	private String userName;
	private int id;
	public UserView(String name, int id) {
		super();
		this.userName = name;
	
		this.id = id;
	}
	public String getName() {
		return userName;
	}
	public int getId() {
		return id;
	}
	
	
	
}
