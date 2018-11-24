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
import modelo.Product;
import modelo.Role;
import modelo.User;
import persistence.ConnectionPool;

public class UserDAO extends Mapper {
	private static UserDAO instancia;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();

	private UserDAO() {

	}

	public static UserDAO getInstancia() {
		if (instancia == null)
			instancia = new UserDAO();
		return instancia;
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();

		try {
			Connection con = ConnectionPool.getInstancia().getConexion();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.users");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {			
				users.add(new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("pass"), this.getRolesByUser(con,rs.getInt("id"))));
			}
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void saveRoleByUser(Role role, User user) {
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.role_user(user_id, role_id) values(?,?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, role.getId());
			ps.execute();
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
	}

	public void removeRolByUser(Role rol, User user) {
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con
					.prepareStatement("delete from " + super.getDatabase() + ".dbo.role_user where user_id=? and role_id=?");
			ps.setInt(1, user.getId());
			ps.setInt(2, rol.getId());
			ps.execute();
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
	}

	public User getUser(String userName) {
		User user = null;	
		try {
			
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.users WHERE user_name=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("pass"), this.getRolesByUser(con,rs.getInt("id")));
			}
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean logIn(String userName, String pw) {
		User user = null;
		try {
			Connection con = ConnectionPool.getInstancia().getConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM " + super.getDatabase() + ".dbo.users WHERE user_name=? and pass=?");
			ps.setString(1, userName);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("pass"), this.getRolesByUser(con,rs.getInt("id") ));
			}
			ConnectionPool.getInstancia().returnConexion(con);
		} catch (SQLException | NoFreeConnectionException | ConexionException | AccesoException e) {
			e.printStackTrace();
		}
		return user != null;
	}

	private List<Role> getRolesByUser(Connection con, int userId) {
		ArrayList<Role> roles = new ArrayList<Role>();
		Role role = null;
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + super.getDatabase() + ".dbo.role_user"
							+ " INNER JOIN role on role_id = id WHERE user_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				role = Application.getInstancia().makeRol(rs.getString("type"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
    public void addUser(User user) throws PKDuplicadaException {

        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".dbo.Users(user_name, pass) values(?,?)");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPass());                       
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
        	e.printStackTrace();        	
        	throw new PKDuplicadaException("Ocurrio un error, el id ingresado ya existe");           
        }
    }

	public void removeUser(User user) throws FKException {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE " + super.getDatabase() + ".dbo.USERS WHERE user_name=?");
            ps.setString(1, user.getUserName());                        
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
            throw new FKException("El usuario no puede ser eliminado porque tiene roles asignados");
        }
		
	}
	
	public void updateUser(User user) {
        try {
            Connection con = ConnectionPool.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE " + super.getDatabase() + ".dbo.Users SET user_name=?, pass=? WHERE id=?");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPass());    
            ps.setInt(3, user.getId());    
            ps.execute();
            ConnectionPool.getInstancia().returnConexion(con);
        } catch (SQLException | ConexionException | AccesoException | NoFreeConnectionException e) {
            e.printStackTrace();
        }
		
	}

}
