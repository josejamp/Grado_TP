package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class PickInstruction implements Instruction {
	
	private NavigationModule theNavigation;
	private ItemContainer theItemContainer;
	private String theID;
	
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
		this.theNavigation = navigation;
		this.theItemContainer = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = this.theNavigation.pickItemFromCurrentPlace(this.theID);
		if(item != null){
			if(!this.theItemContainer.addItem(item)) throw new InstructionExecutionException("WALL·E says: " + "I am stupid! I had already the object " + this.theID.toLowerCase() + Interpreter.LINE_SEPARATOR);
			else System.out.print("WALL·E says: " + "I am happy! Now I have " + this.firstToUpper(item.getId().toLowerCase()) + Interpreter.LINE_SEPARATOR);
		}
		else throw new InstructionExecutionException("WALL·E says: " + "Oops, this place has not the object "+ this.theID.toLowerCase() + Interpreter.LINE_SEPARATOR);
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
	
	/**
	 * 
	 * @param id el id del objeto
	 * @return el String con la primera letra en mayuscula
	 */
	private String firstToUpper(String id){
		return id.replaceFirst((id.charAt(0) + ""), Character.toUpperCase(id.charAt(0)) + "");
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theID.equalsIgnoreCase(((PickInstruction)obj).theID)) &&
				(this.theItemContainer.equals(((PickInstruction)obj).theItemContainer)) &&		
				(this.theNavigation.equals(((PickInstruction)obj).theNavigation))));
	}
	
}
