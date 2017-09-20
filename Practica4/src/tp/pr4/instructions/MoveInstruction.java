package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

/**
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 *
 *Clase con los métodos necesarios para realizar la instrucción move, el robot se mueve por la ciudad.
 */
public class MoveInstruction implements Instruction {
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	
	/**
	 * Constructora por defecto
	 */
	public MoveInstruction(){
		this.theEngine = null;
		this.theNavigation = null;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if((cad.equalsIgnoreCase("MOVE")) || (cad.equalsIgnoreCase("MOVER"))) return new MoveInstruction();
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "   MOVE | MOVER";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;
		this.theNavigation = navigation;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		this.theNavigation.move();
		this.theNavigation.scanCurrentPlace();
		this.theEngine.addFuel(-5);
		this.theEngine.printRobotState();
		if(this.theNavigation.atSpaceship()){
			this.theEngine.printIamAtMySpaceship();
			this.theEngine.requestQuit();
			this.theEngine.diosposeMainWindow();
		}
		else if(this.theEngine.getFuel() <= 0){
			this.theEngine.printRunOutOfFuel();
			this.theEngine.requestQuit();
			this.theEngine.diosposeMainWindow();
		}
	}
	
	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theEngine.equals(((MoveInstruction)obj).theEngine)) &&	
				(this.theNavigation.equals(((MoveInstruction)obj).theNavigation))));
	}

}
