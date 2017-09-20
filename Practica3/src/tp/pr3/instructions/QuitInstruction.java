package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class QuitInstruction implements Instruction {
	
	private RobotEngine theEngine;
	
	public QuitInstruction(){
		this.theEngine = null;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("QUIT") || cad.equalsIgnoreCase("SALIR")) return new QuitInstruction();
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "   QUIT|SALIR";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;

	}

	@Override
	public void execute() throws InstructionExecutionException {
		this.theEngine.requestQuit();
		System.out.print("WALL·E says: " + "I have communication problems. Bye bye");
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theEngine.equals(((QuitInstruction)obj).theEngine))));
	}

}
