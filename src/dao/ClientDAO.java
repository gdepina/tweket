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
import modelo.Client;
import modelo.Product;
import modelo.ZoneLocation;
import persistence.ConnectionPool;

public class ClientDAO extends Mapper {

    private static ClientDAO instancia;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();

    private ClientDAO() {

    }

    public static ClientDAO getInstancia() {
        if (instancia == null)
            instancia = new ClientDAO();
        return instancia;
    }

    // Devuelvo todos los clientes guardados en la base de datos
    public ArrayList<Client> getClients() {
        ArrayList<Client> clientes = new ArrayList<Client>();

        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            Client cli = null;

            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.client");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                cli = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                        this.getZoneById(rs.getInt("zone_code")), rs.getString("phone"), rs.getString("mail"), rs.getString("dni"));

                clientes.add(cli);

            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | NoFreeConnectionException | AccesoException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // busca un cliente determinado en la base de datos solo con el id y lo devuelvo
    public Client getClient(int id) {
        Client cli = null;
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.client WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cli = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                        this.getZoneById(rs.getInt("zone_code")), rs.getString("phone"), rs.getString("mail"),
                        rs.getString("dni"));
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
            e.printStackTrace();
        }
        return cli;
    }
    
    public Client getClientByName(String name) {
        Client cli = null;
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.client WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cli = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                        this.getZoneById(rs.getInt("zone_code")), rs.getString("phone"), rs.getString("mail"),
                        rs.getString("dni"));
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
            e.printStackTrace();
        }
        return cli;
    }

    private ZoneLocation getZoneById(int id) {
        int zoneCode = 0;
        String zoneName = null;
        try {
        	Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps4 = con
                    .prepareStatement("SELECT TOP 1 * FROM " + super.getDatabase() + ".dbo.Zone where zone_code=?");
            ps4.setInt(1, id);
            ResultSet rs = ps4.executeQuery();

            while (rs.next()) {

                zoneCode = rs.getInt("zone_code");
                zoneName = rs.getString("name");

            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
            e.printStackTrace();
        }

        return new ZoneLocation(zoneCode, zoneName);

    }
    
    public ArrayList<ZoneLocation> getZones() {      
    	ArrayList<ZoneLocation> zones = new ArrayList<ZoneLocation>();
        try {
        	Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps4 = con
                    .prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.Zone");            
            ResultSet rs = ps4.executeQuery();

            while (rs.next()) {
            	zones.add(new ZoneLocation(rs.getInt("zone_code"), rs.getString("name")));                        
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
            e.printStackTrace();
        }

        return zones;

    }
    
    public ZoneLocation getZoneByName(String name) {
        int zoneCode = 0;
        String zoneName = null;
        try {
        	Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps4 = con
                    .prepareStatement("SELECT TOP 1 * FROM " + super.getDatabase() + ".dbo.Zone where name=?");
            ps4.setString(1, name);
            ResultSet rs = ps4.executeQuery();

            while (rs.next()) {

                zoneCode = rs.getInt("zone_code");
                zoneName = rs.getString("name");

            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
            e.printStackTrace();
        }

        return new ZoneLocation(zoneCode, zoneName);

    }
    
    public void addClient(Client client) throws PKDuplicadaException {
    	
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Client(name,dni,address,phone,mail,zone_code) values(?,?,?,?,?,?)");
            ps.setString(1, client.getName());
            ps.setString(2, client.getDni());
            ps.setString(3, client.getHomeAddress());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getMail());
            ps.setInt(6, client.getZone().getZoneCode());
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
        	e.printStackTrace();        	
        	throw new PKDuplicadaException("Ocurrio un error, el id ingresado ya existe");           
        }
    }

	public void removeClient(Client client) throws FKException {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE " + super.getDatabase() + ".dbo.Client WHERE id=?");
            ps.setInt(1, client.getId());                        
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
            throw new FKException("El cliente no puede ser eliminado porque es parte de un reclamo");
        }
		
	}
	
	public void updateClient(Client client) {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE " + super.getDatabase() + ".dbo.Client SET name=?, dni=?, address=?, phone=?, mail=?, zone_code=?  WHERE id=?");
            ps.setString(1, client.getName());
            ps.setString(2, client.getDni());
            ps.setString(3, client.getHomeAddress());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getMail());
            ps.setInt(6, client.getZone().getZoneCode());
            ps.setInt(7, client.getId());
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
		
	}

}
