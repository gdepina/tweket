package excepciones;

public class NoFreeConnectionException extends Exception {

	private static final long serialVersionUID = 3197334867086748501L;

	public NoFreeConnectionException(String mensaje){
		super(mensaje);
	}
}
