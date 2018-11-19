package modelo;

import java.util.Date;

public class ZoneTicket extends TicketLeaf {
	public ZoneTicket(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int quantity) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, quantity);
	}

	public ZoneTicket() {
		super();
	}

	public ZoneTicket(String type) {
		this.setType(type);
	}

	// METODOS

	public void process() {

	}

}
