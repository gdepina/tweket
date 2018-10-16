package modelo;

import dao.TicketDAO;

import java.util.Date;
import java.util.List;

public class ZoneTicket extends TicketLeaf {
	public ZoneTicket(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate);
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
