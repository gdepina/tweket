package modelo;

import java.util.Date;

public class TicketHistorical {
	private int id;
	private String log;
	private Date date;

	public TicketHistorical(int id, String log, Date date) {
		super();
		this.id = id;
		this.log = log;
		this.date = date;
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
