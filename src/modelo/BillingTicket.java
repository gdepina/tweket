package modelo;

import java.util.Date;
import java.util.List;

public class BillingTicket extends TicketLeaf {
	private int billId;

	public BillingTicket(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int billId) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate);
		this.billId = billId;
	}

	public BillingTicket() {

    }

	public BillingTicket(String type) {
		this.setType(type);
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
	public void addTicket(Ticket ticket) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizeTicket() {
		// TODO Auto-generated method stub

	}

}
