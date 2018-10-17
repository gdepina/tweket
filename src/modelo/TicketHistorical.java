package modelo;

import dao.TicketDAO;

import java.util.Calendar;
import java.util.Date;

public class TicketHistorical {
	private int id;
	private int ticketNumber;
	private String log;
	private Date date;

	public TicketHistorical(String log, int ticketNumber) {
		super();
		this.log = log;
		this.ticketNumber = ticketNumber;
	}

	public static void addHistory(TicketHistorical tckHistory) {
		TicketDAO.getInstancia().addTicketHistorical(tckHistory);
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

	public void addLog(String log) {

	}

}
