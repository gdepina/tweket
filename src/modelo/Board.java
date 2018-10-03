package modelo;

import java.util.List;

public class Board {

	private User user;
	private String currentRole;
	private List<Ticket> tickets;

	public Board(User user, String currentRole, List<Ticket> tickets) {
		super();
		this.user = user;
		this.currentRole = currentRole;
		this.tickets = tickets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	//METODOS
	
	public List<Ticket> getTicketsByRole() {//este metodo no deberia pasarle un role y que me devuelva sus tickets??
		return tickets;
	}

}
