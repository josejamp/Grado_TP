package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class RadarInstruction implements Instruction {
	
	private NavigationModule theNavigation;

	public RadarInstruction(){
		this.theNavigation = null;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("RADAR")) return new RadarInstruction();
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "   RADAR";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theNavigation = navigation;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		this.theNavigation.scanCurrentPlace();
	}

	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&	
				(this.theNavigation.equals(((RadarInstruction)obj).theNavigation))));
	}
	
}
