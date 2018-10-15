package modelo;

import dao.TicketDAO;

import java.util.Date;
import java.util.List;

public class ZoneTicket extends TicketLeaf {
	public ZoneTicket(int ticketNumber, String type, String description, Client client, Status status, Date creationDate, Date endingDate) {
		super(ticketNumber, type, description, client, status, creationDate, endingDate);
	}

	// METODOS

	public void process() {

	}

	@Override
	public void changeStatus(Status status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTicket() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean finalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finaizeTicket() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTicket(Ticket ticket) {
		// TODO Auto-generated method stub

	}


	@Override
	public void finalizeTicket() {
		// TODO Auto-generated method stub

	}


}
