package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase con los métodos necesarios para realizar la instrucción turn, el robot gira en una determinada direccion.
 */
public class TurnInstruction implements Instruction {
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	private Rotation theRotation;
	
	/**
	 * Constructora por defecto
	 */
	public TurnInstruction(){
		this.theNavigation = null;
		this.theRotation = Rotation.UNKNOWN;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String [] WORD = cad.split(" ");  //separamos las palabras
		if(WORD.length > 0){ //si efectivamente hay palabras que separar
			if(WORD[0].equalsIgnoreCase("TURN") || (WORD[0].equalsIgnoreCase("GIRAR"))){
				 return this.interpreterTURN(WORD);
			}
			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();	
	}

	@Override
	public String getHelp() {
		return "   TURN | GIRAR < LEFT|RIGHT >";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;
		this.theNavigation = navigation;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		if(this.theRotation != Rotation.UNKNOWN){
			this.theNavigation.rotate(this.theRotation);
			this.theEngine.addFuel(-5);
			this.theEngine.lookingAt();
			if(this.theEngine.getFuel() <= 0){
				this.theEngine.printRunOutOfFuel();
				this.theEngine.requestQuit();
				this.theEngine.diosposeMainWindow();
			}
			else this.theEngine.printRobotState();
		}
		else throw new InstructionExecutionException();
	}
	
	/**
	 * Metodo privado de parse que verifca que la segunda palabra sea correcta.
	 * @param WORD Array de palabras
	 * @return nueva TurnInstruction
	 * @throws WrongInstructionFormatException Si la palabra no es correcta
	 */
	private TurnInstruction interpreterTURN(String [] WORD) throws WrongInstructionFormatException{
		TurnInstruction inst = new TurnInstruction();
		if(WORD.length >= 2){ //si hay mas palabras
			if(WORD[1].equalsIgnoreCase("RIGHT")){ //mira si la segunda es right
				inst.theRotation = Rotation.RIGHT;
			}
			else if(WORD[1].equalsIgnoreCase("LEFT")){ //mira si la segunda es left
				inst.theRotation = Rotation.LEFT;
			}
			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();
		return inst;
	}
	
	/**
	 * Método que establece el sentido del giro
	 * @param rotation izquierda o derecha
	 */
	public void setRotation(Rotation rotation){
		this.theRotation = rotation;
	}
	
	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theRotation == ((TurnInstruction)obj).theRotation) &&	
				(this.theNavigation.equals(((TurnInstruction)obj).theNavigation)) &&
				(this.theEngine.equals(((TurnInstruction)obj).theEngine))));
	}

}
