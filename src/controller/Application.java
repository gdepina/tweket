package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.ClientDAO;
import dao.ConsultaDAO;
import dao.ProductDAO;
import dao.RoleDAO;
import dao.TicketDAO;
import dao.UserDAO;
import excepciones.PKDuplicadaException;
import modelo.*;
import observer.Observer;
import view.ClientView;
import view.ProductView;
import view.TicketView;
import view.UserView;

public class Application extends Observer {
	public List<Client> clients;
	public List<Product> products;
	public Product currentProd;
	public Client currentCli;
	public User currentUser;
	public Role currentRole;
	public List<User> users;
	public ArrayList<Ticket> tickets;
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

	public Role makeRol(String type) {
		return new Role(type);

	}

	public ArrayList<TicketView> getTickets() {
		ArrayList<TicketView> ticketView = new ArrayList<TicketView>();
		for (Ticket tck : tickets) {
			ticketView.add(tck.toView());
		}
		return ticketView;
	}
	
	public ArrayList<TicketView> getTickets(String[] types) {
		ArrayList<TicketView> ticketView = new ArrayList<TicketView>();
		for (Ticket tck : TicketDAO.getInstancia().getTicketsByType(types)) {
			ticketView.add(tck.toView());
		}
		return ticketView;
	}

	public ArrayList<String> getRolesByUser(String userName) {
		User user = getUser(userName);
		ArrayList<String> roles = new ArrayList<String>();
		for (Role rol : user.getRoles()) {
			roles.add(rol.getType());
		}
		return roles;
	}

	public Ticket getTicket(int ticketNumber) {		
		return TicketDAO.getInstancia().getTicket(ticketNumber);
	}
	

	public ArrayList<ClientView> getClients() {
		ArrayList<ClientView> clientesView = new ArrayList<ClientView>();
		for (Client cli : ClientDAO.getInstancia().getClients()) {
			clientesView.add(cli.toView());
		}
		return clientesView;
	}
	
	public void saveProduct(String productCode, String title, String desc,float price) throws PKDuplicadaException {	
		new Product(productCode,title,desc,price).save();
		this.notifyObservables();
	}

	public Product getProduct(String code) {
		Product res = null;
		for (Product pro : products) {
			if (pro.getProductCode() == code) {
				res = pro;
			}
		}
		if (res == null)
			res = ProductDAO.getInstancia().getProduct(code);

		return res;
	}
	
	public Product getProductByName(String name) {
		Product res = null;
		for (Product pro : products) {
			if (pro.getTitle() == name) {
				res = pro;
			}
		}
		if (res == null)
			res = ProductDAO.getInstancia().getProductByName(name);
		
		this.currentProd = res;
		return res;
	}

	public ArrayList<ProductView> getProducts() {
		ArrayList<ProductView> productViews = new ArrayList<ProductView>();
		for (Product prod : ProductDAO.getInstancia().getProducts()) {
			productViews.add(prod.toView());
		}
		return productViews;
	}
	
	public ArrayList<ZoneLocation> getZones() {
		return ClientDAO.getInstancia().getZones();
	}
	
	public ZoneLocation getZoneByName(String zoneName) {
		return ClientDAO.getInstancia().getZoneByName(zoneName);
	}
	
	public void saveClient(String name, String dni, String address,String phone, String mail, String zoneName) throws PKDuplicadaException {
		
		new Client(name,dni,address,phone, mail, this.getZoneByName(zoneName)).save();
		this.notifyObservables();
	}

	public Client getClient(int clientId) {
		Client res = null;
		for (Client client : clients) {
			if (client.getId() == clientId) {
				res = client;
			}
		}
		if (res == null) {
			res = ClientDAO.getInstancia().getClient(clientId);
		}
		return res;
	}
	
	public Client getClientByName(String name) {
		Client cli = ClientDAO.getInstancia().getClientByName(name);
		this.currentCli = cli;
		return cli;
	}
	
	public Role getRoleByName(String type) {
		Role role = RoleDAO.getInstancia().getRoleByName(type);
		this.currentRole = role;
		return role;
	}
	
	
	
	public ArrayList<UserView> getUsers() {
		ArrayList<UserView> userViews = new ArrayList<UserView>();
		for (User usr : UserDAO.getInstancia().getUsers()) {
			userViews.add(usr.toView());
		}
		return userViews;
	}

	public User getUser(String userName) {
		User res = null;
		for (User user : users) {
			if (user.getUserName() == userName) {
				res = user;
			}
		}
		if (res == null) {
			res = UserDAO.getInstancia().getUser(userName);
			currentUser = res;
		}
		this.currentUser = res;
		return res;
	}
	
	public void saveUser(String name, String pass) throws PKDuplicadaException {
		new User(name,pass).add();
		this.notifyObservables();
		
	}

	public boolean checkUser(String userId, String pass) {
		return UserDAO.getInstancia().logIn(userId, pass);
	}

	public Ticket makeTicketByType(String type) {
		Ticket tck = null;

		if (type.equals("Zona Entrega"))
			tck = new ZoneTicket(type);
		else if (type.equals("Faltante") || type.equals("Cantidad") || type.equals("Producto"))
			tck = new ProductTicketContext(type); //Patron strategy
		else if (type.equals("Facturacion"))
			tck = new BillingTicket(type);
		return tck;

	}
	
	public void addTicket(String type, int clientId, String des, String productCode, int qty, String billId) {
		Ticket tck = this.makeTicketByType(type);
		tck.setClient(getClient(clientId));
		tck.setProduct(getProduct(productCode));
		tck.setQuantity(qty);
		tck.setDescription(des);
		tck.setBillId(billId);
		tickets.add(tck);
		this.notifyObservables();
	}
	
	public void removeTicket(String type) {		
		ListIterator listIterator = tickets.listIterator();
	      while (listIterator.hasNext()) 
	        { 
	           Ticket tck = (Ticket) listIterator.next(); 
	            if (tck.getType() == type) 
	            	listIterator.remove(); 
	        } 		
		this.notifyObservables();
	}
	
	public boolean checkTicketTypeExist(String type) {				
		for (Ticket tck : tickets) {
			return tck.getType() == type;
		}	
		return false;
	}
	

	public void saveTicketComposite(int clientId) {	
		TicketComposite tck = new TicketComposite(tickets);
		tck.setClient(getClient(clientId));		
		tck.addTicket();
		this.notifyObservables();
	}

	public void saveTicket(ArrayList<String> tipoReclamos, int clientId, String descrip, String productCode, int qty, String billId) {
		Ticket tck = null;
		if (tipoReclamos.size() == 1) {
			tck = this.makeTicketByType(tipoReclamos.get(0));
			tck.setClient(getClient(clientId));
			tck.setProduct(getProduct(productCode));
			tck.setQuantity(qty);
			tck.setDescription(descrip);
			tck.setBillId(billId);
			tck.addTicket();
		}
	
		this.notifyObservables();
	}
	

	public void changeTicketState(int state, String stateName, int ticketNumber, String log) {	
		this.getTicket(ticketNumber).changeStatus(state);
		new TicketHistorical(log+" > se cambia estado a "+stateName , ticketNumber, currentUser.getId()).addLog();
		this.notifyObservables();
	}
	
	public DefaultTableModel getTicketHistorical(int ticketNumber) {
		return TicketDAO.getInstancia().getTicketHistorical(ticketNumber);
	}
	
	public DefaultTableModel getTicketRankLogs() {
		return ConsultaDAO.getInstancia().getTicketRankLogs();
	}
	
	public DefaultTableModel getTicketRankByClients() {
		return ConsultaDAO.getInstancia().getTicketRankByClients();
	}
	public int getTicketsByMonth(int month) {
		return ConsultaDAO.getInstancia().getTicketsByMonth(month);
	}

	public DefaultTableModel getAvgResponseTime() {
		return ConsultaDAO.getInstancia().getAvgResponseTime();
	}

	public List<Role> getAvailableRoles() {
		return RoleDAO.getInstancia().getAvailableRoles(this.currentUser.getId());		
	}

}
