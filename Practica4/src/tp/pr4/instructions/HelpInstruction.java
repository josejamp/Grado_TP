package tp.pr4.instructions;

import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.RobotEngine;
import tp.pr4.NavigationModule;
import tp.pr4.items.ItemContainer;

/**
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase que contiene los métodos necesarios para realizar la instrucción help, la cual muestra la ayuda del juego.
 */
public class HelpInstruction implements Instruction {
	
	private RobotEngine theEngine;
	
	/**
	 * Constructora por defecto
	 */
	public HelpInstruction() {
		this.theEngine = null;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		if (cad.equalsIgnoreCase("HELP") || cad.equalsIgnoreCase("AYUDA")) return new HelpInstruction();
		else throw new WrongInstructionFormatException();
	}
	
	@Override
	public String getHelp(){
		return "   HELP|AYUDA";
	}
	
	@Override
	public void execute() throws InstructionExecutionException{
		this.theEngine.requestHelp();
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;
	}

	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theEngine.equals(((HelpInstruction)obj).theEngine))));
	}
	
}
