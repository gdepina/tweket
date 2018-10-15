package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;

public class ConnectionPool {

	private String connectionUrl = "jdbc:sqlserver://tweket.database.windows.net:1433;database=tweket;user=api@tweket;password=Neverpony02;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	private Connection con = null;
	private Map<Connection, Boolean> conexiones;
	private static ConnectionPool instancia;

	private ConnectionPool() throws ConexionException, AccesoException {
		conexiones = new HashMap<Connection, Boolean>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			crearPool();
		} catch (ClassNotFoundException e) {
			throw new ConexionException("Error de configuracion");
		}
	}

	public static ConnectionPool getInstancia() throws ConexionException, AccesoException {
		if (instancia == null)
			instancia = new ConnectionPool();
		return instancia;
	}

	private void crearPool() throws AccesoException {
		for (int i = 0; i < 10; i++) {
			try {
				con = DriverManager.getConnection(connectionUrl);
				conexiones.put(con, false);
			} catch (SQLException e) {
				throw new AccesoException("No pude acceder al servidor");
			}
		}
	}

	public synchronized Connection getConexion() throws NoFreeConnectionException {
		Set<Connection> claves = conexiones.keySet();
		for (Connection c : claves) {
			if (!conexiones.get(c)) {
				conexiones.put(c, true);
				return c;
			}
		}
		throw new NoFreeConnectionException("No hay mas conexiones disponibles");
	}

	public synchronized void returnConexion(Connection conexion) {
		conexiones.put(conexion, false);
	}
}
