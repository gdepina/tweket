package modelo;

public class Client {
	private int id;
	private String name;
	private String homeAddress;
	private Zone zone;
	private String phone;
	private String mail;
	private String dni;
	
	public Client(int id, String name, String homeAddress, Zone zone, String phone, String mail, String dni) {
		super();
		this.id = id;
		this.name = name;
		this.homeAddress = homeAddress;
		this.zone = zone;
		this.phone = phone;
		this.mail = mail;
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}


	public void addClient(String name, String homeAddress, int phone, String mail) {

	}

	public void removeClient(int id) {

	}

}
