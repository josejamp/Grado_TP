package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class ScanInstruction implements Instruction {
	
	private ItemContainer theItemContainer;
	private String theID;
	
	public ScanInstruction(){
		this.theItemContainer = null;
		this.theID = null;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String [] WORD = cad.split(" ");  //separamos las palabras
		if(WORD.length > 0){
			if(WORD[0].equalsIgnoreCase("SCAN") || (WORD[0].equalsIgnoreCase("ESCANEAR"))){
				return this.InterpreterScan(WORD);
			}
			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "   SCAN|ESCANEAR [id]";
	}

	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.theItemContainer = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		if(this.theID != null){
			Item item = this.theItemContainer.getItem(this.theID);
			if(item != null) System.out.print(item.toString() + Interpreter.LINE_SEPARATOR);
			else throw new InstructionExecutionException("You do not have any "+ this.theID + Interpreter.LINE_SEPARATOR);
		}
		else System.out.print("WALL·E says: I am carrying the following items" + Interpreter.LINE_SEPARATOR + this.theItemContainer.toString());
	}
	
	/**
	 * Metodo privado de parse que verifca que la segunda palabra sea correcta, el id es opcional, no tiene que lanzar excepcion.
	 * @param WORD Array de palabras
	 * @return nueva ScanInstruction
	 */
	private ScanInstruction InterpreterScan(String [] WORD){
		ScanInstruction inst = new ScanInstruction();
		if(WORD.length >= 2){ //si hay m�s palabras
				inst.theID = WORD[1];
		}
		else inst.theID = null;
		return inst;
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theID.equalsIgnoreCase(((ScanInstruction)obj).theID)) &&
				(this.theItemContainer.equals(((ScanInstruction)obj).theItemContainer))));
	}

}
