package view;

import modelo.Client;
import modelo.Product;
import modelo.Status;

import java.util.Date;

public class TicketView {
	private int ticketNumber;
	private String description;
	private String type;
	private Client client;
	private Product product;
	private Status status;
	private Date creationDate;
	private Date endingDate;
	private int quantity;

	public TicketView(int ticketNumber, String description, String type, Status status, Date creationDate, Date endingDate, Client client, Product product, int qty) {
		this.ticketNumber = ticketNumber;
		this.description = description;
		this.type = type;
		this.client = client;
		this.status = status;
		this.creationDate = creationDate;
		this.endingDate = endingDate;
		this.product = product;
		this.quantity = qty;
		
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public Client getClient() {
		return client;
	}

	public Status getStatus() {
		return status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}
}
