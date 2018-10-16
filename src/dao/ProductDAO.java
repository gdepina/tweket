package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;
import modelo.Product;
import persistence.ConnectionPool;

public class ProductDAO extends Mapper {
	
	
	//Para manejo de fechas
	private static ProductDAO instancia;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();

	//Singleton
	public static ProductDAO getInstancia() {
		if (instancia == null)
			instancia = new ProductDAO();
		return instancia;
	}

	// devuelvo todos los Productos guardados en la base de datos
	
	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();

		try {

			Connection con = ConnectionPool.getInstancia().getConexion();
			Product pro = null;

			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.Product");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				pro = new Product(rs.getString ("product_code"), rs.getString("title"), rs.getString("description"),
						rs.getFloat("price"));

				products.add(pro);

			}
			ConnectionPool.getInstancia().returnConexion(con);
		}
		catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return products;
	}

	// busca un producto determinado en la base de datos solo con el codigo de producto y lo devuelvo
	
	public Product getProduct (String productCode) {
		Product pro = null;
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.product WHERE product_code=?");
			ps.setString(1, productCode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pro = new Product(rs.getString ("product_code"), rs.getString("title"), rs.getString("description"),
						rs.getFloat("price"));
			}
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return pro;
	}

}
