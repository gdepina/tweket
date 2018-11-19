package modelo;

import dao.TicketDAO;

import java.util.Date;

public class TicketLeaf extends Ticket {

	public TicketLeaf(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int qty) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, qty);
	}

	public TicketLeaf() {
		super();
	}


	@Override
	public void addTicket() {
		TicketDAO.getInstancia().addTicket(this);

	}

	@Override
	public void removeTicket() {

	}

	@Override
	public void changeStatus(Status status) {

	}

	@Override
	public boolean finalized() {
		return false;
	}

	@Override
	public void finalizeTicket() {

	}

	

}
