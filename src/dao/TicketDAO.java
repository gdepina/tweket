package dao;

import controller.Application;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;
import modelo.Status;
import modelo.Ticket;
import modelo.TicketLeaf;
import persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		ArrayList<Ticket> tickets = new ArrayList <Ticket>();
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Ticket where type=?");
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				tickets.add(new TicketLeaf(
						rs.getInt("ticket_number"),
						rs.getString("type"),
						rs.getString("description"),
						Application.getInstancia().getClient(rs.getInt("client_id")),
						getStatus(con, rs.getInt("status_id")),
						rs.getDate("creation_date"),
						rs.getDate("ending_date")
				));
			}
			con.close();
		}	
		
		
		catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e){
			e.printStackTrace();
		}
		
		return tickets;
	}

	public Ticket getTicket(int ticketNumber) {

		ArrayList<Ticket> tickets = new ArrayList <Ticket>();
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Ticket where id=?");
			ps.setInt(1, ticketNumber);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				tickets.add(new TicketLeaf(
						rs.getInt("ticket_number"),
						rs.getString("type"),
						rs.getString("description"),
						Application.getInstancia().getClient(rs.getInt("client_id")),
						getStatus(con, rs.getInt("status_id")),
						rs.getDate("creation_date"),
						rs.getDate("ending_date")
				));
			}
			con.close();
		}


		catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e){
			e.printStackTrace();
		}

		return tickets.get(0);
	}



	private Status getStatus(Connection con, int statusId) {
		Status status = null;
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.status id=?");
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



//	public void insertReclamoSimple(ReclamoSimple rec) {
//
//		try {
//			Connection con = Conexion.connect();
//			PreparedStatement ps = con.prepareStatement("INSERT INTO "+ super.getDatabase()+ ".dbo.reclamos(idCliente, descripcion, estado, fechaIngreso, codigoProducto,  idReclamo) values(?,?,?,?,?,?)");
//			ps.setInt(1, rec.getCliente().getIdCliente());
//			ps.setString(2, rec.getDescripcion());
//			ps.setString(3, "ingresado");
//			ps.setString(4, rec.getFechaIngreso());
//			ps.setInt(5, rec.getProducto().getCodigo());
//			ps.setInt(6, rec.getIdReclamo());
//			ps.execute();
//			PreparedStatement pre = con.prepareStatement("select top 1 * from "+ super.getDatabase()+ ".dbo.reclamos order by idReclamo desc");
//			ResultSet res = pre.executeQuery();
//			int resultado = 0;
//			while (res.next()) {
//				resultado = res.getInt("idReclamo");
//			}
//			PreparedStatement in = con.prepareStatement("insert into "+ super.getDatabase()+ ".dbo.reclamo_tipo(idReclamo,tipoReclamo) values (?,?)");
//			in.setInt(1, resultado);
//			in.setString(2, rec.getTipoReclamo());
//			in.execute();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// ya probado, anda
//	public void insertReclamoCompuesto(ReclamoCompuesto comp) {
//		try {
//			Connection con = Conexion.connect();
//			PreparedStatement ps = con.prepareStatement("INSERT INTO "+ super.getDatabase()+ ".dbo.reclamos(idCliente, descripcion, estado, fechaIngreso, codigoProducto, idReclamo) values(?,?,?,?, ?,?)");
//			ps.setInt(1, comp.getCliente().getIdCliente());
//			ps.setString(2, comp.getDescripcion());
//			ps.setString(3, "ingresado");
//			ps.setString(4, comp.getFechaIngreso());
//			ps.setInt(5, comp.getProducto().getCodigo());
//			ps.setInt(6, comp.getIdReclamo());
//
//			ps.execute();
//
//			PreparedStatement in = con.prepareStatement("insert into "+ super.getDatabase()+ ".dbo.reclamo_tipo(idReclamo,tipoReclamo) values (?,?)");
//			for (Reclamo rec : comp.getReclamos()) {
//				in.setInt(1, comp.getIdReclamo());
//				in.setString(2, rec.getTipoReclamo());
//				in.execute();
//			}
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
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
//	public void cerrarReclamo(Reclamo rec) {
//		try {
//			Connection con = Conexion.connect();
//			PreparedStatement pre = con.prepareStatement("update "
//					+ super.getDatabase()
//					+ ".dbo.reclamos set estado=? where idReclamo=?");
//			pre.setString(1, "cerrado");
//			pre.setInt(2, rec.getIdReclamo());
//			pre.execute();
//			PreparedStatement ps = con.prepareStatement("select * from "
//					+ super.getDatabase() + ".dbo.reclamos where idReclamo=?");
//			ps.setInt(1, rec.getIdReclamo());
//			ResultSet res = ps.executeQuery();
//			int resultado = 0;
//			while (res.next()) {
//				resultado = res.getInt("idTratamiento");
//			}
//			PreparedStatement in = con
//					.prepareStatement("update "
//							+ super.getDatabase()
//							+ ".dbo.tratamientos set fechaTratamiento=? where idTratamiento=?");
//			in.setString(1, cal.getTime().toString());
//			in.setInt(2, resultado);
//			in.execute();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
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
