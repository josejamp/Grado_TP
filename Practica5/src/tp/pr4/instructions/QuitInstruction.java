package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
import tp.pr4.utils.Messages;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase con los métodos necesarios para realizar la instrucción quit, se finaliza el juego.
 */
public class QuitInstruction implements Instruction {
	
	private RobotEngine theEngine;
	
	/**
	 * Constructora por defecto
	 */
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
		//if(this.theEngine.getMainWindow() == null) System.out.print(Messages.COMUNICATION_PROBLEMS);
	}
	
	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theEngine.equals(((QuitInstruction)obj).theEngine))));
	}

}
