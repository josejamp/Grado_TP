package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class MoveInstruction implements Instruction {
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	
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
		System.out.print(Interpreter.LINE_SEPARATOR);
		this.theEngine.addFuel(-5);
		this.theEngine.printRobotState();
		this.lookingAt();
		if(this.theNavigation.atSpaceship()){
			System.out.print("WALL·E says: " + "I am at my spaceship. Bye bye" + Interpreter.LINE_SEPARATOR);
			this.theEngine.requestQuit();		
		}
		else if(this.theEngine.getFuel() <= 0){
			System.out.print("WALL·E says: " + "I run out of fuel. I cannot move. Shutting down..." + Interpreter.LINE_SEPARATOR);
			this.theEngine.requestQuit();
		}
	}
	
	private String lookingAt(){
		return("WALL·E is looking at direction " + this.theNavigation.getCurrentHeading().toString()+ Interpreter.LINE_SEPARATOR);
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theEngine.equals(((MoveInstruction)obj).theEngine)) &&	
				(this.theNavigation.equals(((MoveInstruction)obj).theNavigation))));
	}

}
