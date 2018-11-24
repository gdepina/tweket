package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.Application;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.FKException;
import excepciones.NoFreeConnectionException;
import excepciones.PKDuplicadaException;
import modelo.Client;
import modelo.Product;
import modelo.Role;
import modelo.ZoneLocation;
import persistence.ConnectionPool;

public class RoleDAO extends Mapper {

    private static RoleDAO instancia;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();

    private RoleDAO() {

    }

    public static RoleDAO getInstancia() {
        if (instancia == null)
            instancia = new RoleDAO();
        return instancia;
    }

    public ArrayList<Role> getAvailableRoles(int userId) {
        ArrayList<Role> roles = new ArrayList<Role>();

        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            Role role = null;

            PreparedStatement ps = con.prepareStatement("select id, type from " + super.getDatabase() + ".dbo.role "
            		+ "EXCEPT "
            		+ "select id, type from " + super.getDatabase() + ".dbo.role "
            		+ "INNER JOIN " + super.getDatabase() + ".dbo.role_user r on r.role_id = id "
            		+ "where r.user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

            	role = new Role(rs.getString("type"));

            	roles.add(role);

            }
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | NoFreeConnectionException | AccesoException e) {
            e.printStackTrace();
        }
        return roles;
    }

	public Role getRoleByName(String type) {
		ArrayList<Role> roles = new ArrayList<Role>();
		Role role = null;
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.role"
							+ " WHERE type=?");
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {		
				role = new Role(rs.getInt("id"), rs.getString("type"));
				roles.add(role);
			}
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return roles.get(0);
	}

}
