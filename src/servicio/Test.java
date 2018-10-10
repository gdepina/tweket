package servicio;

import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NoFreeConnectionException;

public class Test {

	public static void main(String[] args){
	
			try {
				ConnectionPool.getInstancia().getConexion();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AccesoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
}
