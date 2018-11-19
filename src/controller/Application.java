package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.ClientDAO;
import dao.ConsultaDAO;
import dao.ProductDAO;
import dao.TicketDAO;
import dao.UserDAO;
import modelo.*;
import observer.Observer;
import view.ClientView;
import view.ProductView;
import view.TicketView;

public class Application extends Observer {
	public List<Client> clients;
	public List<Product> products;
	public Product currentProd;
	public Client currentCli;
	public User currentUser;
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

	public Role makeRol(String type) {
		return new Role(type);

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
		Ticket res = null;
		for (Ticket tck : tickets) {
			if (tck.getTicketNumber() == ticketNumber) {
				res = tck;
			}
		}
		if (res == null)
			res = TicketDAO.getInstancia().getTicket(ticketNumber);
		;
		return res;
	}

	public ArrayList<ClientView> getClients() {
		ArrayList<ClientView> clientesView = new ArrayList<ClientView>();
		for (Client cli : ClientDAO.getInstancia().getClients()) {
			clientesView.add(cli.toView());
		}
		return clientesView;
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

		return res;
	}

	public ArrayList<ProductView> getProducts() {
		ArrayList<ProductView> productViews = new ArrayList<ProductView>();
		for (Product prod : ProductDAO.getInstancia().getProducts()) {
			productViews.add(prod.toView());
		}
		return productViews;
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
		return ClientDAO.getInstancia().getClientByName(name);
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
		return res;
	}

	public boolean checkUser(String userId, String pass) {
		return UserDAO.getInstancia().logIn(userId, pass);
	}

	public Ticket makeTicketByType(String type) {
		Ticket tck = null;

		if (type.equals("Zona Entrega"))
			tck = new ZoneTicket(type);
		else if (type.equals("Faltante") || type.equals("Cantidad") || type.equals("Producto"))
			tck = new ProductTicketContext(type);
		else if (type.equals("Facturacion"))
			tck = new BillingTicket(type);
		return tck;

	}

	public void saveTicket(ArrayList<String> tipoReclamos, int clientId, String descrip, String productCode, int qty) {
		Ticket tck = null;
		if (tipoReclamos.size() == 1) {
			tck = this.makeTicketByType(tipoReclamos.get(0));
			tck.setClient(getClient(clientId));
			tck.setProduct(getProduct(productCode));
			tck.setQuantity(qty);
			tck.setDescription(descrip);
			tck.addTicket();
		} else {
			ArrayList<Ticket> tmpTickets = new ArrayList<Ticket>();
			for (String type : tipoReclamos) {
				tmpTickets.add(this.makeTicketByType(type));
			}
			tck = new TicketComposite(tmpTickets);
			tck.setClient(getClient(clientId));
			tck.setProduct(getProduct(productCode));
			tck.setQuantity(qty);
			tck.setDescription(descrip);
			tck.addTicket();

		}

		tickets.add(tck);
	}
	

	public void addLog(String log, int ticketNumber) {
		new TicketHistorical(log, ticketNumber, currentUser.getId()).addLog();			
	}

	public void changeTicketState(int state, int ticketNumber) {
		this.getTicket(ticketNumber).changeStatus(state);
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
	
	
}
