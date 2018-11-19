package modelo;

import java.util.Date;

import dao.TicketDAO;

public class TicketHistorical {
	private int id;
	private int ticketNumber;
	private String log;
	private Date date;
	private int userId;

	public TicketHistorical(String log, int ticketNumber,int userId) {
		super();
		this.log = log;
		this.ticketNumber = ticketNumber;
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	//METODOS

	public void addLog() {
		TicketDAO.getInstancia().addTicketHistorical(this);
	}

}
