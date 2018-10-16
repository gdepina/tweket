package modelo;

import dao.TicketDAO;

import java.util.Date;
import java.util.List;

public class TicketLeaf extends Ticket {

	public TicketLeaf(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate);
	}

	public TicketLeaf() {
		super();
	}


	@Override
	public void addTicket(Ticket ticket) {
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
