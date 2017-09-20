package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class OperateInstruction implements Instruction {
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	private ItemContainer theItemContainer;
	private String theID;

	public OperateInstruction(){
		this.theEngine = null;
		this.theNavigation = null;
		this.theItemContainer = null;
		this.theID = null;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String [] WORD = cad.split(" ");  //separamos las palabras
		if(WORD.length > 0){
			if(WORD[0].equalsIgnoreCase("OPERATE") || (WORD[0].equalsIgnoreCase("OPERAR"))){
				return this.InterpreterOperate(WORD);
			}
			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return ("   OPERATE|OPERAR <id>");
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
			if (item.use(this.theEngine, this.theNavigation)){
				if(!item.canBeUsed()) {
					this.theItemContainer.pickItem(this.theID);
					System.out.println("What a pity! I have no more " + this.theID + " in my inventory" + Interpreter.LINE_SEPARATOR);
				}
			}
			else throw new InstructionExecutionException("I have problems using the object " + this.theID + Interpreter.LINE_SEPARATOR);
		}
		else throw new InstructionExecutionException("WALL·E says: " + "I have not such object" + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Metodo privado de parse que verifca que la segunda palabra sea correcta.
	 * @param WORD Array de palabras
	 * @return nueva OperateInstruction
	 * @throws WrongInstructionFormatException Si la palabra no es correcta
	 */
	private OperateInstruction InterpreterOperate(String [] WORD) throws WrongInstructionFormatException{
		OperateInstruction inst = new OperateInstruction();
		if(WORD.length >= 2){ //si hay mas palabras
			inst.theID = WORD[1];
		}
		else throw new WrongInstructionFormatException();
		return inst;
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.theID.equalsIgnoreCase(((OperateInstruction)obj).theID)) &&
				(this.theItemContainer.equals(((OperateInstruction)obj).theItemContainer)) &&		
				(this.theNavigation.equals(((OperateInstruction)obj).theNavigation)) &&
				(this.theEngine.equals(((OperateInstruction)obj).theEngine))));
	}

}
