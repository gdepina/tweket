package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import controller.Application;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;
import modelo.Status;
import modelo.Ticket;
import modelo.TicketComposite;
import modelo.TicketHistorical;
import modelo.TicketLeaf;
import persistence.ConnectionPool;

public class TicketDAO extends Mapper {

	private static int CLOSE_STATE = 4;
    private static TicketDAO instancia;
    Calendar cal = Calendar.getInstance();

    private TicketDAO() {

    }

    public static TicketDAO getInstancia() {
        if (instancia == null)
            instancia = new TicketDAO();
        return instancia;
    }

    public ArrayList<Ticket> getTicketsByType(String type) {

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.Ticket where type=?");
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(new TicketLeaf(
                        rs.getInt("ticket_number"),
                        rs.getString("type"),
                        rs.getString("description"),
                        Application.getInstancia().getClient(rs.getInt("client_id")),
                        getStatus(con, rs.getInt("status_id")),
                        Application.getInstancia().getProduct(rs.getString("product_code")),
                        rs.getDate("creation_date"),
                        rs.getDate("ending_date"),
                        rs.getInt("quantity"),
                        rs.getInt("composite_id")
                ));
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }

        return tickets;
    }
    
    public ArrayList<Ticket> getTicketsByType(String[] args) {
    	String statement = "(";
    	    	
    	for (int i = 0; i < args.length; i++) {
    		if (i+1 == args.length) {
    			statement += "?";	
    		} else {
    			statement += "?,";
    		}
    		
		}
    	statement+= ")";

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.Ticket where status_id !="+CLOSE_STATE+" AND type in "+statement);                      
            for (int i = 0; i < args.length; i++) {
            	ps.setString(i+1, args[i]);							
			}
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(new TicketLeaf(
                        rs.getInt("ticket_number"),
                        rs.getString("type"),
                        rs.getString("description"),
                        Application.getInstancia().getClient(rs.getInt("client_id")),
                        getStatus(con, rs.getInt("status_id")),
                        Application.getInstancia().getProduct(rs.getString("product_code")),
                        rs.getDate("creation_date"),
                        rs.getDate("ending_date"),
                        rs.getInt("quantity"),
                        rs.getInt("composite_id")
                ));
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public Ticket getTicket(int ticketNumber) {

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.Ticket where ticket_number=?");
            ps.setInt(1, ticketNumber);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(new TicketLeaf(
                        rs.getInt("ticket_number"),
                        rs.getString("type"),
                        rs.getString("description"),
                        Application.getInstancia().getClient(rs.getInt("client_id")),
                        getStatus(con, rs.getInt("status_id")),
                        Application.getInstancia().getProduct(rs.getString("product_code")),
                        rs.getDate("creation_date"),
                        rs.getDate("ending_date"),
                        rs.getInt("quantity"),
                        rs.getInt("composite_id")
                ));
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }

        return tickets.get(0);
    }


    private Status getStatus(Connection con, int statusId) {
        Status status = null;
        try {
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.status WHERE id=?");
            ps.setInt(1, statusId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status = new Status(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


    public void addTicket(Ticket tck) {

        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Ticket(type, description, creation_date,client_id, status_id, product_code, quantity) values(?,?,?,?,?,?,?)");
            ps.setString(1, tck.getType());
            ps.setString(2, tck.getDescription());
            ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ps.setInt(4, tck.getClient().getId());
            ps.setInt(5, 1); //Estado uno creado pendiente de revision
            ps.setString(6, tck.getProduct().getProductCode());
            ps.setInt(7, tck.getQuantity());
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
    }

    // ya probado, anda
    public void addTicketComposite(TicketComposite comp) {
        int identity = 0;
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.ticket_composite(creation_date, status_id, client_id) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ps.setInt(2, 1);
            ps.setInt(3, comp.getClient().getId());


            if (ps.executeUpdate() > 0) {
                java.sql.ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    identity = generatedKeys.getInt(1);
                }
            }

            PreparedStatement in = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Ticket(type, description, creation_date,client_id, status_id, product_code, composite_id, quantity) values(?,?,?,?,?,?,?,?)");
            for (Ticket tck : comp.getTickets()) {
                in.setString(1, tck.getType());
                in.setString(2, comp.getDescription());
                in.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                in.setInt(4, comp.getClient().getId());
                in.setInt(5, 1);
                in.setString(6, comp.getProduct().getProductCode());
                in.setInt(7, identity);
                in.setInt(8, comp.getQuantity());
                in.execute();
            }

            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
    }

    public void addTicketHistorical(TicketHistorical history) {

        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Ticket_historical(log, ticket_number, user_id, date) values(?,?,?,?)");
            ps.setString(1, history.getLog());
            ps.setInt(2, history.getTicketNumber());
            ps.setInt(3, history.getUserId());
            ps.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));           
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(int ticketNumber, int statusId, int compositeId) {
    	int paramsSet = 0;
    	String params = "";
    	if (statusId == CLOSE_STATE) {
    		params = ",ending_date=?";
    		paramsSet++;
    	}
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();                                                           
            PreparedStatement pre = con.prepareStatement("update "
                    + super.getDatabase()
                    + ".dbo.Ticket set status_id=? "+params+" where ticket_number=?");
            pre.setInt(1, statusId);
            
            int curr = 1;           
            while(curr <= paramsSet){
            	pre.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            	++curr;
            }
            
            pre.setInt(++curr, ticketNumber);
            pre.execute();
            
            checkAndCloseComposite(compositeId, con);
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
    }

	private void checkAndCloseComposite(int compositeId, Connection con) throws SQLException {
		if (compositeId != 0) {
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.ticket WHERE status_id != ? AND composite_id=?");           
		    ps.setInt(1, CLOSE_STATE);  
		    ps.setInt(2, compositeId);  
		    ResultSet rs = ps.executeQuery();
		    boolean empty = true;
		    while( rs.next() ) {
		        empty = false;
		    }

		    if( empty ) {
		    	PreparedStatement ps1 = con.prepareStatement("update "
		                + super.getDatabase()
		                + ".dbo.ticket_composite set status_id=?, ending_date=? where id=?");
		    	ps1.setInt(1, CLOSE_STATE);
		    	ps1.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		    	ps1.setInt(3, compositeId);
		    	ps1.execute();
		    }
		}
	}
}
