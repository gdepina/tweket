package modelo;

import java.util.Date;
import java.util.List;

public abstract class TicketLeaf extends Ticket {

	public TicketLeaf(int ticketNumber, String type, String description, Client client, Status status,
			List<TicketHistorical> historical, Date creationDate, Date endingDate) {
		super(ticketNumber, type, description, client, status, historical, creationDate, endingDate);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void changeStatus(Status status);
	
	public abstract void removeTicket();
	
	public abstract boolean finalized();
	
	public abstract void finaizeTicket();
	

}
