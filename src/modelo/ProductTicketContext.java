package modelo;

import java.util.Date;
import java.util.List;

public class ProductTicketContext extends TicketLeaf {
	private int quantity;
	private Product product;
	private StrategyTicket strategy;

	public ProductTicketContext(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int quantity) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, quantity);
		this.quantity = quantity;
	}

	public ProductTicketContext(String strategyType) {
		super();
		this.strategy = new ProductTicketStrategy();
		this.setType(strategyType);
		if (strategyType.toLowerCase().equals("faltante")){
			this.strategy = new MissingTicketStrategy();
		}
		if (strategyType.toLowerCase().equals("cantidad"))  {
			this.strategy = new QuantityTicketStrategy();
		}
		if (strategyType.toLowerCase().equals("croducto")) {
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
	public void addTicket() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizeTicket() {
		// TODO Auto-generated method stub

	}
}
