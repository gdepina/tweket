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
import excepciones.FKException;
import excepciones.NoFreeConnectionException;
import excepciones.PKDuplicadaException;
import modelo.Product;
import modelo.Ticket;
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
	
	public Product getProductByName (String name) {
		Product pro = null;
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.product WHERE title=?");
			ps.setString(1, name);
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
	
	
    public void addProduct(Product prod) throws PKDuplicadaException {

        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Product(product_code, description, title, price) values(?,?,?,?)");
            ps.setString(1, prod.getProductCode());
            ps.setString(2, prod.getDescription());
            ps.setString(3, prod.getTitle());
            ps.setFloat(4, prod.getPrice());            
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
        	e.printStackTrace();        	
        	throw new PKDuplicadaException("Ocurrio un error, el id ingresado ya existe");           
        }
    }

	public void removeProduct(Product prod) throws FKException {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE " + super.getDatabase() + ".dbo.Product WHERE product_code=?");
            ps.setString(1, prod.getProductCode());                        
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
            throw new FKException("El producto no puede ser eliminar porque es parte de un reclamo");
        }
		
	}

	public void updateProduct(Product prod) {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE " + super.getDatabase() + ".dbo.Product SET product_code=?, description=?, title=?, price=? WHERE product_code=?");
            ps.setString(1, prod.getProductCode());
            ps.setString(2, prod.getDescription());
            ps.setString(3, prod.getTitle());
            ps.setFloat(4, prod.getPrice());
            ps.setString(5, prod.getProductCode());
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
		
	}

}
