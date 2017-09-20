package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class TurnInstruction implements Instruction {
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	private Rotation theRotation;
	
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
			System.out.print(this.lookingAt());
			if(this.theEngine.getFuel() <= 0){
				System.out.print("WALLE·E says: " + "I run out of fuel. I cannot move. Shutting down..." + Interpreter.LINE_SEPARATOR);
				this.theEngine.requestQuit();
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
	
	private String lookingAt(){
		return("WALL·E is looking at direction " + this.theNavigation.getCurrentHeading().toString()+ Interpreter.LINE_SEPARATOR);
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theRotation == ((TurnInstruction)obj).theRotation) &&	
				(this.theNavigation.equals(((TurnInstruction)obj).theNavigation)) &&
				(this.theEngine.equals(((TurnInstruction)obj).theEngine))));
	}

}
