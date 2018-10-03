package modelo;

import java.util.Date;
import java.util.List;

public class TicketComposite extends Ticket {
	private List<Ticket> tickets;

	public TicketComposite(int ticketNumber, String type, String description, Client client, Status status,
			List<TicketHistorical> historical, Date creationDate, Date endingDate, List<Ticket> tickets) {
		super(ticketNumber, type, description, client, status, historical, creationDate, endingDate);
		this.tickets = tickets;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	// METODOS

	public void addTicket(Ticket ticket) {

	}

	public void removeTicket(Ticket ticket) {

	}

	public Ticket getTicket(Ticket ticket) {
		return null;
	}

	public void getChild(int index) {

	}

	public void finalizeTicket() {
		// este no lo hereda de la clase abstracta??? en el diagrama de clases, lo estamos poniendo 2 veces
	}

	public void removeTicket() {

	}
	// este metodo no es el mismo que arriba??? en el diagrama de clases, lo estamos poniendo 2 veces
	//en uno le pasa parametros y en otro no

	public boolean finalized() {
		return false;
	}

	@Override
	public void changeStatus(Status status) {
		// TODO Auto-generated method stub
		
	}



}
