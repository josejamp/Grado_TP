package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase con los métodos necesarios para realizar la instrucción radar, el robot observa el lugar circundante.
 */
public class RadarInstruction implements Instruction {
	
	private NavigationModule theNavigation;

	/**
	 * Constructora por defecto
	 */
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

	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&	
				(this.theNavigation.equals(((RadarInstruction)obj).theNavigation))));
	}
	
}
