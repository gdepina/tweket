package modelo;

import java.util.Date;
import java.util.List;

public class ProductTicketContext extends TicketLeaf {
	private int quantity;
	private Product product;
	private StrategyTicket strategy;


	public ProductTicketContext(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int quantity) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate);
		this.quantity = quantity;
	}

	public ProductTicketContext(String strategyType) {
		super();
		this.strategy = new ProductTicketStrategy();
		this.setType(strategyType);
		if (strategyType.equals("faltante")){
			this.strategy = new MissingTicketStrategy();
		}
		if (strategyType.equals("Cantidad"))  {
			this.strategy = new QuantityTicketStrategy();
		}
		if (strategyType.equals("Porducto")) {
			this.strategy = new ProductTicketStrategy();
		}
	}


	public void process() {
		strategy.process();
	}

	public void setStrategy(StrategyTicket strategy) {
		this.strategy = strategy;
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
