package controller;

import java.util.ArrayList;
import java.util.List;

import modelo.Client;
import modelo.Product;
import modelo.Role;
import modelo.Ticket;
import modelo.User;

public class Application {
	public List<Client> clients;
	public List<Product> products;
	public List<User> users;
	public List<Ticket> tickets;
	public static Application instancia;

	public static Application getInstancia() {
		if (instancia == null) {
			instancia = new Application();
		}
		return instancia;
	}
	public Application() {
		this.clients = new ArrayList<Client>();
		this.products = new ArrayList<Product>();
		this.users = new ArrayList<User>();
		this.tickets = new ArrayList<Ticket>();
	
	}
	
	public Role makeRol(String tipoRol) {
		
//		if (tipoRol.compareToIgnoreCase("Zona Entrega") == 0)
//			rol = new Role(tipoRol);
//		else if (tipoRol.compareToIgnoreCase("CallCenter") == 0)
//			rol = new RolCallCenter();
//		else if (tipoRol.compareToIgnoreCase("Distribucion") == 0)
//			rol = new RolDistribucion();
//		else if (tipoRol.compareToIgnoreCase("Consulta") == 0)
//			rol = new RolConsulta();
//		else if (tipoRol.compareToIgnoreCase("Administrador") == 0)
//			rol = new RolAdministrador();
//		else if (tipoRol.compareToIgnoreCase("Facturacion") == 0)
//			rol = new RolFacturacion();
		return null; //new Role(tipoRol);

	}
	public ArrayList<String> getRolesByUser(int userId){
		User user = getUser(userId);
		ArrayList<String> roles= new ArrayList<String>();
		for (Role rol: user.getRoles()){
			roles.add(rol.getType());
		}
		return roles;
	}
	
	public User getUser(int userId){
		User res=null;
		for (User user: users){
			if (user.getId() == userId){
				res=user;
			}
		}
		if (res==null){
			res = User.getUser(userId);
		}
		return res;
	}
	
	public boolean checkUser(int userId, String pass) {	
		User user= getUser(userId);
		return user.getPass().equals(pass);
	}

	
	
}
