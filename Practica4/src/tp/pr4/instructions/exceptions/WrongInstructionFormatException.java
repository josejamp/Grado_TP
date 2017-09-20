package tp.pr4.instructions.exceptions;

import java.io.Serializable;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Excepcion para indicar que una instruccion no es correcta
 */
public class WrongInstructionFormatException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5827964258591386202L;

	public WrongInstructionFormatException() {
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
