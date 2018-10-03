package modelo;

import java.util.Date;
import java.util.List;

public abstract class Ticket {
	private int ticketNumber;
	private String type;
	private String description;
	private Client client;
	private Status status;
	private List<TicketHistorical> historical;
	private Date creationDate;
	private Date endingDate;

	public Ticket(int ticketNumber, String type, String description, Client client, Status status,
			List<TicketHistorical> historical, Date creationDate, Date endingDate) {
		super();
		this.ticketNumber = ticketNumber;
		this.type = type;
		this.description = description;
		this.client = client;
		this.status = status;
		this.historical = historical;
		this.creationDate = creationDate;
		this.endingDate = endingDate;
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

	public abstract List<Ticket> getTickets();
	

	public abstract boolean finalized();
	// a este metodo no tendriamos que pasarle un numero de ticket para saber su
	// estado???

	public abstract void finalizeTicket();
	// a este metodo lo mismo, no tendriamos que pasarle un numero de ticket???

}
