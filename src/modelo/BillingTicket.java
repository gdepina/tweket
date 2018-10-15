package modelo;

import java.util.Date;
import java.util.List;

public class BillingTicket extends TicketLeaf {
	private int billId;

	public BillingTicket(int ticketNumber, String type, String description, Client client, Status status, Date creationDate, Date endingDate, int billId) {
		super(ticketNumber, type, description, client, status, creationDate, endingDate);
		this.billId = billId;
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
