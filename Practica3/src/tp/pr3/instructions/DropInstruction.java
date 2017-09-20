package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class DropInstruction implements Instruction {
	
	private NavigationModule theNavigation;
	private ItemContainer theItemContainer;
	private String theID;
	
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
		this.theNavigation = navigation;
		this.theItemContainer = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = this.theItemContainer.getItem(this.theID);
		if(item != null){
			if(this.theNavigation.findItemAtCurrentPlace(theID)) throw new InstructionExecutionException("WALL·E says: the object is already at the place" + Interpreter.LINE_SEPARATOR);
			else{
				this.theNavigation.dropItemAtCurrentPlace(item);
				this.theItemContainer.pickItem(theID);
				System.out.print("Great! I have dropped " + this.theID + Interpreter.LINE_SEPARATOR);
			}
		}
		else throw new InstructionExecutionException("You do not have any "+ this.theID + Interpreter.LINE_SEPARATOR);
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
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theID.equalsIgnoreCase(((DropInstruction)obj).theID)) &&
				(this.theItemContainer.equals(((DropInstruction)obj).theItemContainer)) &&		
				(this.theNavigation.equals(((DropInstruction)obj).theNavigation))));
	}

}
