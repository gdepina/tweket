package excepciones;

public class PKDuplicadaException extends Exception {

	private static final long serialVersionUID = 5190329702485382960L;

	public PKDuplicadaException(String mensaje){
		super(mensaje);
	}
}
