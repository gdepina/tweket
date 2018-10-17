package modelo;

import dao.TicketDAO;
import view.TicketView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Ticket {
	private int ticketNumber;
	private String type;
	private String description;
	private Client client;
	private Status status;
	private Product product;
	private List<TicketHistorical> historical;
	private Date creationDate;
	private Date endingDate;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Ticket(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate) {
		this.ticketNumber = ticketNumber;
		this.type = type;
		this.description = description;
		this.client = client;
		this.status = status;
		this.product = product;
		this.creationDate = creationDate;
		this.endingDate = endingDate;
	}

	public Ticket() {

	}

	public TicketView toView() {
		TicketView ticketView = new TicketView(this.ticketNumber,
				this.description, this.type, this.status, this.creationDate, this.endingDate, this.client);
		return ticketView;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<TicketHistorical> getHistorical() {
		return historical;
	}

	public void setHistorical(List<TicketHistorical> historical) {
		this.historical = historical;
	}

	public void addHistorical(TicketHistorical history) {
		this.historical.add(history);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	
	//METODOS
	public abstract void addTicket(Ticket ticket);

	public abstract void removeTicket();

	public abstract void changeStatus(Status status);
	

	public abstract boolean finalized();
	// a este metodo no tendriamos que pasarle un numero de ticket para saber su
	// estado???

	public abstract void finalizeTicket();
	// a este metodo lo mismo, no tendriamos que pasarle un numero de ticket???

//	public ArrayList<Ticket> getTickets() {
//		return TicketDAO.getInstancia().getTickets();
//	};

	public static ArrayList<Ticket> getTickets(String type) {
		return TicketDAO.getInstancia().getTicketsByType(type);
	};

	public static Ticket getTicket(int id) {
		return TicketDAO.getInstancia().getTicket(id);
	};

	public static void changeStatus(int ticketNumber, int statusId) {
		TicketDAO.getInstancia().changeStatus(ticketNumber, statusId);
	};




}
