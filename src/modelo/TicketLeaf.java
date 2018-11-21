package modelo;

import dao.TicketDAO;

import java.util.Date;

public class TicketLeaf extends Ticket {

	public TicketLeaf(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int qty, int compositeId) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, qty, compositeId);
	}

	public TicketLeaf() {
		super();
	}


	@Override
	public void addTicket() {
		TicketDAO.getInstancia().addTicket(this);

	}

}
