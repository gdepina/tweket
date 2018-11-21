package modelo;

import java.util.Date;

public class BillingTicket extends TicketLeaf {
	public BillingTicket(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int qty, int compositeId) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, qty, compositeId);		
	}

	public BillingTicket() {

    }

	public BillingTicket(String type) {
		this.setType(type);
	}


}
