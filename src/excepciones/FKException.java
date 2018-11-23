package excepciones;

public class FKException extends Exception {

	private static final long serialVersionUID = 5190329702485382960L;

	public FKException(String mensaje){
		super(mensaje);
	}
}
