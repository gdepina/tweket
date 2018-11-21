package modelo;

import dao.TicketDAO;
import view.TicketView;

import java.util.Date;
import java.util.List;

public abstract class Ticket {
	private int ticketNumber;
	private int quantity;
	private String type;
	private String description;
	private Client client;
	private Status status;
	private Product product;
	private Date creationDate;
	private Date endingDate;
	private int compositeId;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Ticket(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int quantity, int compositeId) {
		this.ticketNumber = ticketNumber;
		this.type = type;
		this.description = description;
		this.client = client;
		this.status = status;
		this.product = product;
		this.creationDate = creationDate;
		this.endingDate = endingDate;
		this.quantity = quantity;
		this.compositeId = compositeId;
	}

	public Ticket() {

	}

	public TicketView toView() {
		TicketView ticketView = new TicketView(this.ticketNumber,
				this.description, this.type, this.status, this.creationDate, this.endingDate, this.client, this.product, this.quantity);
		return ticketView;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	public abstract void addTicket();


	public void changeStatus(int statusId) {
		TicketDAO.getInstancia().changeStatus(this.getTicketNumber(), statusId, this.getCompositeId());
	}

	public int getCompositeId() {
		return compositeId;
	}

	public void setCompositeId(int compositeId) {
		this.compositeId = compositeId;
	}
	
	public boolean isComposite() {
		return this.compositeId != 0;
	}


}
