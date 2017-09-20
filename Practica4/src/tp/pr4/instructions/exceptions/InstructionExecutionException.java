package tp.pr4.instructions.exceptions;

import java.io.Serializable;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Excepcion para indicar un mal resultado de ejecutar una instruccion
 */
public class InstructionExecutionException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 944317795401965279L;

	public InstructionExecutionException() {
		// TODO Auto-generated constructor stub
	}

	public InstructionExecutionException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InstructionExecutionException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InstructionExecutionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
