package view;

public class ClientView {
	private String name;
	private int clientId;
	public ClientView(String name, int clientId) {
		super();
		this.name = name;
	
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return clientId;
	}
	
	
	
}
