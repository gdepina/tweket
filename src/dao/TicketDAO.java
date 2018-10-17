package dao;

import controller.Application;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;
import modelo.*;
import persistence.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class TicketDAO extends Mapper {

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
                        rs.getDate("ending_date")
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
                        rs.getDate("ending_date")
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Ticket(type, description, creation_date,client_id, status_id, product_code) values(?,?,?,?,?,?)");
            ps.setString(1, tck.getType());
            ps.setString(2, tck.getDescription());
            ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ps.setInt(4, tck.getClient().getId());
            ps.setInt(5, 1); //Estado uno creado pendiente de revision
            ps.setString(6, tck.getProduct().getProductCode());
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

            PreparedStatement in = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Ticket(type, description, creation_date,client_id, status_id, product_code, composite_id) values(?,?,?,?,?,?,?)");
            for (Ticket tck : comp.getTickets()) {
                in.setString(1, tck.getType());
                in.setString(2, comp.getDescription());
                in.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                in.setInt(4, comp.getClient().getId());
                in.setInt(5, 1);
                in.setString(6, comp.getProduct().getProductCode());
                in.setInt(7, identity);
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Ticket_historical(log, ticket_number, date) values(?,?,?)");
            ps.setString(1, history.getLog());
            ps.setInt(2, history.getTicketNumber());
            ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(int ticketNumber, int statusId) {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement pre = con.prepareStatement("update "
                    + super.getDatabase()
                    + ".dbo.Ticket set status_id=? where ticket_number=?");
            pre.setInt(1, statusId);
            pre.setInt(2, ticketNumber);
            pre.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
    }
//
//
//
//
//	// ya esta probado, anda
//	public void agregarTratamientoEnReclamo(Reclamo rec, Tratamiento tra) {
//		try {
//			Connection con = Conexion.connect();
//			int numero=0;
//			PreparedStatement ps = con.prepareStatement("UPDATE "
//					+ super.getDatabase()
//					+ ".dbo.reclamos SET idTratamiento = ? WHERE idReclamo=?");
//			ps.setInt(1, tra.getIdTratamiento());
//			ps.setInt(2, rec.getIdReclamo());
//			ps.execute();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// ya esta probado, anda

//
//	public int traerUltimoId() {
//		int resu = 0;
//		try {
//			Connection con = Conexion.connect();
//			PreparedStatement ps = con.prepareStatement("SELECT top 1 idReclamo from "+ super.getDatabase()+".dbo.reclamos order by idReclamo desc");
//			ResultSet res = ps.executeQuery();
//			while (res.next())
//				resu = res.getInt("idReclamo");
//			con.close();
//		}
//		catch (SQLException e){
//			e.printStackTrace();
//		}
//		return resu;
//	}

}
