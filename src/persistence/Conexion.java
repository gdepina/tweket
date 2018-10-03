package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	public static Connection connect() {
		Connection con = null;
		try {
            String userName = "loginInteractivas";
            String password = "interactivas";
            String url = "jdbc:sqlserver://GRUPO7\\SQLEXPRESS:1433;database=tpInteractivas";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection (url, userName, password);
        } catch (Exception e) {
            System.err.println ("No se puede conectar al server de la base de datos.");
            System.exit(1);
        }
		return con;
	}
}
