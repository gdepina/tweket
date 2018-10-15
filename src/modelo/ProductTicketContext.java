package modelo;

import java.util.Date;
import java.util.List;

public class ProductTicketContext extends TicketLeaf {
	private int quantity;
	private Product product;

	public ProductTicketContext(int ticketNumber, String type, String description, Client client, Status status, Date creationDate, Date endingDate, int quantity, Product product, StrategyTicket strategy) {
		super(ticketNumber, type, description, client, status, creationDate, endingDate);
		this.quantity = quantity;
		this.product = product;
		this.strategy = strategy;
	}

	private StrategyTicket strategy;

	// METODOS

	public void process() {

	}

	public void setStrategy() {

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
