package modelo;


public class Role {
		
	protected String type;
	private int id;

	
	public Role(String type) {
		super();
		this.type = type;
	}
	public Role(int id, String type) {
		this.id = id;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
