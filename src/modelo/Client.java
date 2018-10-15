package modelo;

import dao.ClientDAO;
import view.ClientView;

import java.util.ArrayList;

public class Client {
	private int id;
	private String name;
	private String homeAddress;
	private ZoneLocation zone;
	private String phone;
	private String mail;
	private String dni;

	public Client(int id, String name, String homeAddress, ZoneLocation zone, String phone, String mail, String dni) {
		super();
		this.id = id;
		this.name = name;
		this.homeAddress = homeAddress;
		this.zone = zone;
		this.phone = phone;
		this.mail = mail;
		this.dni = dni;
	}

	public ClientView toView() {
		return new ClientView(this.name, this.id);
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

	public ZoneLocation getZone() {
		return zone;
	}

	public void setZone(ZoneLocation zone) {
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

	public static Client getClient(int clientId) {
		return ClientDAO.getInstancia().getClient(clientId);
	}
	public static ArrayList<Client> getClients() {
		return ClientDAO.getInstancia().getClients();
	}

}
