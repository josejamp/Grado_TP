package tp.pr4.instructions;

import tp.pr4.Interpreter;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;
import tp.pr4.utils.Messages;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 *
 * Clase con los métodos necesarios para realizar la instrucción pick, el robot coge un objeto.
 */
public class PickInstruction implements Instruction {
	
	public RobotEngine theEngine;
	private NavigationModule theNavigation;
	private ItemContainer theItemContainer;
	private String theID;
	
	/**
	 * Constructora por defecto
	 */
	public PickInstruction(){
		this.theNavigation = null;
		this.theItemContainer = null;
		this.theID = null;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String [] WORD = cad.split(" ");  //separamos las palabras
		if(WORD.length > 0){
			if(WORD[0].equalsIgnoreCase("PICK") || (WORD[0].equalsIgnoreCase("COGER")))
				return this.InterpreterPick(WORD);

			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {	
		return "   PICK|COGER <id>";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;
		this.theNavigation = navigation;
		this.theItemContainer = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = this.theNavigation.pickItemFromCurrentPlace(this.theID);
		if(item != null){
			if(!this.theItemContainer.addItem(item)) throw new InstructionExecutionException(Messages.STUPID + this.theID.toLowerCase() + Interpreter.LINE_SEPARATOR);
			else this.theEngine.printHappyIhaveTheObject(item.getId());
		}
		else throw new InstructionExecutionException(Messages.PLACE_DOESNT_HAVE_OBJECT + this.theID.toLowerCase() + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Método que establece el nombre del objeto a coger
	 * @param id nombre del objeto a coger
	 */
	public void setID(String id){
		this.theID = id;
	}

	/**
	 * Metodo privado de parse que verifca que la segunda palabra sea correcta.
	 * @param WORD Array de palabras
	 * @return nueva PickInstruction
	 * @throws WrongInstructionFormatException Si la palabra no es correcta
	 */
	private PickInstruction InterpreterPick(String [] WORD) throws WrongInstructionFormatException{
		PickInstruction inst = new PickInstruction();
		if(WORD.length >= 2){ //si hay m�s palabras
				inst.theID = WORD[1];
		}
		else throw new WrongInstructionFormatException();
		return inst;
	}
	
	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theID.equalsIgnoreCase(((PickInstruction)obj).theID)) &&
				(this.theItemContainer.equals(((PickInstruction)obj).theItemContainer)) &&		
				(this.theNavigation.equals(((PickInstruction)obj).theNavigation))));
	}
	
}
