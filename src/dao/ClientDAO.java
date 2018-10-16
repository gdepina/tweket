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
import modelo.Client;
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
                        this.getZoneById(con, rs.getInt("zone_code")), rs.getString("phone"), rs.getString("mail"), rs.getString("dni"));

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
                        this.getZoneById(con, rs.getInt("zone_code")), rs.getString("phone"), rs.getString("mail"),
                        rs.getString("dni"));
            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
            e.printStackTrace();
        }
        return cli;
    }

    private ZoneLocation getZoneById(Connection con, int id) {
        int zoneCode = 0;
        String zoneName = null;
        try {
            PreparedStatement ps4 = con
                    .prepareStatement("SELECT TOP 1 * FROM " + super.getDatabase() + ".dbo.Zone where zone_code=?");
            ps4.setInt(1, id);
            ResultSet rs = ps4.executeQuery();

            while (rs.next()) {

                zoneCode = rs.getInt("zone_code");
                zoneName = rs.getString("name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ZoneLocation(zoneCode, zoneName);

    }

}
