package tp.pr3.instructions;

import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.RobotEngine;
import tp.pr3.NavigationModule;
import tp.pr3.items.ItemContainer;

public class HelpInstruction implements Instruction {
	
	private RobotEngine theEngine;
	
	public HelpInstruction() {
		this.theEngine = null;
	}
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		if (cad.equalsIgnoreCase("HELP") || cad.equalsIgnoreCase("AYUDA")) return new HelpInstruction();
		else throw new WrongInstructionFormatException();
	}
	
	public String getHelp(){
		return "   HELP|AYUDA";
	}
	
	public void execute() throws InstructionExecutionException{
		this.theEngine.requestHelp();
	}

	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;
	}

	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theEngine.equals(((HelpInstruction)obj).theEngine))));
	}
	
}
