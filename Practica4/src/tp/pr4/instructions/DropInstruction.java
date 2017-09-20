package tp.pr4.instructions;

import tp.pr4.Interpreter;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;
import tp.pr4.utils.Messages;

/**
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase con los métodos necesarios para realizar la instrucción drop, el robot se deshace de un objeto.
 */
public class DropInstruction implements Instruction {
	
	private NavigationModule theNavigation;
	private ItemContainer theItemContainer;
	private RobotEngine theEngine;
	private String theID;
	
	/**
	 * Constructora por defecto
	 */
	public DropInstruction(){
		this.theNavigation = null;
		this.theItemContainer = null;
		this.theID = null;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String [] WORD = cad.split(" ");  //separamos las palabras
		if(WORD.length > 0){
			if(WORD[0].equalsIgnoreCase("DROP") || WORD[0].equalsIgnoreCase("SOLTAR")){
				return this.InterpreterDrop(WORD);
			}
			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "   DROP|SOLTAR <id>";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theEngine = engine;
		this.theNavigation = navigation;
		this.theItemContainer = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = this.theItemContainer.getItem(this.theID);
		if(item != null){
			if(this.theNavigation.findItemAtCurrentPlace(theID)) throw new InstructionExecutionException(Messages.OBJECT_ALREADY_PLACE + Interpreter.LINE_SEPARATOR);
			else{
				this.theNavigation.dropItemAtCurrentPlace(item);
				this.theItemContainer.pickItem(theID);
				this.theEngine.printDrop(this.theID);
			}
		}
		else throw new InstructionExecutionException(Messages.DONT_HAVE_ANY + this.theID + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Método que establece el nombre del objeto 
	 * @param id nombre del objeto
	 */
	public void setID(String id){
		this.theID = id;
	}
	
	/**
	 * Metodo privado de parse que verifca que la segunda palabra sea correcta.
	 * @param WORD Array de palabras
	 * @return nueva DropInstruction
	 * @throws WrongInstructionFormatException Si la palabra no es correcta
	 */
	private DropInstruction InterpreterDrop(String [] WORD) throws WrongInstructionFormatException{
		DropInstruction inst = new DropInstruction();
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
				(this.theID.equalsIgnoreCase(((DropInstruction)obj).theID)) &&
				(this.theItemContainer.equals(((DropInstruction)obj).theItemContainer)) &&		
				(this.theNavigation.equals(((DropInstruction)obj).theNavigation))));
	}

}
