package modelo;

import java.util.Date;
import java.util.List;

public class TicketLeaf extends Ticket {

	public TicketLeaf(int ticketNumber, String type, String description, Client client, Status status, Date creationDate, Date endingDate) {
		super(ticketNumber, type, description, client, status, creationDate, endingDate);
	}


	@Override
	public void addTicket(Ticket ticket) {

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
