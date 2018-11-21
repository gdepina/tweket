package modelo;

import dao.TicketDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketComposite extends Ticket {
	private List<Ticket> tickets;

	public TicketComposite(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, List<Ticket> tickets, int qty, int compositeId) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, qty, compositeId);
		this.tickets = tickets;
	}

	public TicketComposite(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void addTicket() {
		TicketDAO.getInstancia().addTicketComposite(this);
	}


}
