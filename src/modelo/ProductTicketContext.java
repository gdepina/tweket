package modelo;

import java.util.Date;

public class ProductTicketContext extends TicketLeaf {
	private StrategyTicket strategy;

	public ProductTicketContext(int ticketNumber, String type, String description, Client client, Status status, Product product, Date creationDate, Date endingDate, int quantity,int compositeId) {
		super(ticketNumber, type, description, client, status, product, creationDate, endingDate, quantity, compositeId);
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
		if (strategyType.toLowerCase().equals("producto")) {
			this.strategy = new ProductTicketStrategy();
		}
	}


	public void process() {
		strategy.process();
	}

	public void setStrategy(StrategyTicket strategy) {
		this.strategy = strategy;
	}
}
