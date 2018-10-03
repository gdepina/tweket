package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import controller.Application;
import modelo.Role;
import modelo.User;
import persistence.Conexion;


public class UserDAO extends Mapper {
	private static UserDAO instancia;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	
	private UserDAO(){
		
	}
	

	public static UserDAO getInstancia(){
		if (instancia == null)
			instancia = new UserDAO();
		return instancia;
	}
	
	
	public ArrayList<User> getUsers() { 
		ArrayList<User> usuarios = new ArrayList<User>();
	
		try{
			Connection con = Conexion.connect();		
			Role rol=null;		
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.users");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				ArrayList <Role> roles= new ArrayList<Role>();
				
				
				
				PreparedStatement ps1 = con.prepareStatement("SELECT * FROM "+ super.getDatabase() + ".dbo.rol_user WHERE userId=?");
				ps1.setInt(1, rs.getInt("id"));
				
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {
					rol = Application.getInstancia().makeRol(rs1.getString("type"));
					roles.add(rol);
				}
						
						
				usuarios.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), roles));
			}
			
			con.close();
		}
		
		catch (SQLException e){
			e.printStackTrace();
		}
		return usuarios;
	}
	
	
	public void saveRoleByUser(Role role, User user){
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.rol_user(userId, type) values(?,?)");
			ps.setInt(1,user.getId());
			ps.setString(2, role.getType());
			ps.execute();
			con.close();
		}
			catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	//ya esta probado, anda
	public void removeRolByUser(Role rol, User user ){
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("delete from "+super.getDatabase()+".dbo.rol_user where userId=? and type=?");
			ps.setInt(1, user.getId());
			ps.setString(2, rol.getType());
			ps.execute();
			con.close();
		}
			catch (SQLException e){
			e.printStackTrace();
		}
	}
	public User getUser(int id) {
		User user = null;
		ArrayList<Role> roles = new ArrayList<Role>();
		Role rol=null;
		try {			
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ super.getDatabase() + ".dbo.user WHERE userId=?");
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new Usuario();
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setUserPassword(rs.getString("userPassword"));
		
			}
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM "+ super.getDatabase() + ".dbo.rol_user WHERE idUsuario=?");
			ps1.setInt(1, idUsuario);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
					rol = Controlador.getInstancia().generarTipoRol(rs1.getString("tipoRol"));
					roles.add(rol);
				}
			user.setRoles(roles);
			PreparedStatement ps3 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Tratamientos where idUsuario=?");
			ps3.setInt(1,user.getIdUsuario());
			ResultSet rs3 = ps3.executeQuery();
			
			while (rs3.next()){
				tra = new Tratamiento(rs3.getString("detalleRealizado"));
				tra.setIdTratamiento(rs3.getInt("idTratamiento"));
				tra.setFechaTratamiento(rs3.getString("fechaTratamiento"));
				tratamientos.add(tra);
			}
			user.setTratamientos(tratamientos);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public Usuario buscarUsuarioPorLogin(String nombreUsuario, String contra){
		Usuario user = null;
		try {
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ super.getDatabase() +".dbo.Usuarios WHERE userLogin=? and userPassword=?");
			ps.setString(1, nombreUsuario);
			ps.setString(2, contra);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new Usuario();
				user.setIdUsuario(rs.getInt("idUsuario"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public ArrayList<Rol> obtenerRoles(Usuario user){
		ArrayList<Rol> roles = new ArrayList<Rol>();
		Rol rol=null;
		try {
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ super.getDatabase() + ".dbo.rol_user WHERE idUsuario=?");
			ps.setInt(1, user.getIdUsuario());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rol = Controlador.getInstancia().generarTipoRol(rs.getString("tipoRol"));
				roles.add(rol);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
}
