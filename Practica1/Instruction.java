package tp.pr1;

public class Instruction {

	private Action action;
	private Rotation rotation;
	
	/**
	 * construye el objeto si se desconocen la accion y la rotacion
	 */
	public Instruction(){ 
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNOWN;
	}
	
	/**
	 * construye el objeto si se desconoce la rotacion
	 * @param action instruccion
	 */
	public Instruction(Action action){ 
		this.action = action;
		this.rotation = Rotation.UNKNOWN;
	}
	
	/**
	 * ambos valores son conocidos
	 * @param action instruccion
	 * @param rotation hacia la izquierda o la derecha
	 */
	public Instruction(Action action, Rotation rotation) { 
		this.action = action;
		this.rotation = rotation;
	}
	
	/**
	 * comprueba que la instruccion sea valida segun los valores de action y turn
	 * @return true si la instruccion es valida
	 */
	public boolean isValid(){ 
		boolean valid = true;
		if ((this.action == Action.UNKNOWN)||((this.action == Action.TURN) && (this.rotation == Rotation.UNKNOWN))) valid = false;
		return valid;
	}
	
	/**
	 * @return la accion de la instruccion
	 */
	public Action getAction(){ 
		return this.action;
	}
	
	/**
	 * @return la rotacion de la instruccion
	 */
	public Rotation getRotation(){ 
		return this.rotation;
	}
	
}
