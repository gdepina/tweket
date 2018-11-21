package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;
import persistence.ConnectionPool;

public class ConsultaDAO extends Mapper {
	
	
	//Para manejo de fechas
	private static ConsultaDAO instancia;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();	

	//Singleton
	public static ConsultaDAO getInstancia() {
		if (instancia == null)
			instancia = new ConsultaDAO();
		return instancia;
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}

	// busca un producto determinado en la base de datos solo con el codigo de producto y lo devuelvo
	
	public DefaultTableModel getTicketRankLogs () {	
		try {
			String statement = "SELECT name \'Estado\', COUNT(*) \'Cantidad de reclamos\'  "
					+ "FROM " + super.getDatabase() + ".dbo.TICKET INNER JOIN status s on s.id = status_id "
					+ "GROUP BY name";
			
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement(statement);
			
			DefaultTableModel resultModel = buildTableModel(ps.executeQuery());
			ConnectionPool.getInstancia().returnConexion(con);
			return resultModel;
			
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DefaultTableModel getTicketRankByClients () {	
		try {
			String statement = "select TOP 10 client.name 'Cliente', count(*) 'Cantidad de reclamos' "
					+ "from " + super.getDatabase() + ".dbo.TICKET t "
					+ "INNER JOIN " + super.getDatabase() + ".dbo.client on t.client_id = client.id "
					+ "group by client.name "
					+ "ORDER BY COUNT(*) DESC";
			
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement(statement);
			
			DefaultTableModel resultModel = buildTableModel(ps.executeQuery());
			ConnectionPool.getInstancia().returnConexion(con);
			return resultModel;
			
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int getTicketsByMonth(int month) {
		int qty = 0;
		try {

			Connection con = ConnectionPool.getInstancia().getConexion();
		
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) 'qty' FROM " + super.getDatabase() + ".dbo.TICKET  WHERE MONTH(creation_date) = ?");
			ps.setInt(1, month);
			ResultSet rs = ps.executeQuery();	
			while (rs.next()) {

				qty += rs.getInt("qty");				

			}
			ConnectionPool.getInstancia().returnConexion(con);
		}
		catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return qty;
	}
	
	

}
