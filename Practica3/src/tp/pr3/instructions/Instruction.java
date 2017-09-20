package tp.pr3.instructions;

import tp.pr3.instructions.exceptions.*;
import tp.pr3.items.*;
import tp.pr3.*;

public interface Instruction {
	
	/**
	 * Mira si el tipo de Instruction al que se refiere la cadena, , si no es correcta se lanza una excepcion
	 * @param cad cadena a comprobar
	 * @return la instruccion correspondiente
	 * @throws WrongInstructionFormatException
	 */
	abstract public Instruction parse(String cad) throws WrongInstructionFormatException;
	
	/**
	 * 
	 * @return El comando de la instruccion
	 */
	abstract public String getHelp();
	
	/**
	 * Configura el entorno
	 * @param engine el robot
	 * @param navigation el sistema de navegacion
	 * @param robotContainer el inventario del robot
	 */
	abstract public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	
	/**
	 * Ejecuta la iinstruccion, si no es posible, lanza una excepcion
	 * @throws InstructionExecutionException
	 */
	abstract public void execute() throws InstructionExecutionException;

}
