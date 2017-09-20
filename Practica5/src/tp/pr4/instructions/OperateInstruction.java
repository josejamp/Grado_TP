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
 * Clase con los métodos necesarios para realizar la instrucción operate, el robot usa un item.
 */
public class OperateInstruction implements Instruction {
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	private ItemContainer theItemContainer;
	private String theID;

	/**
	 * Constructora por defecto
	 */
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
					this.theEngine.printNoMoreObjects(this.theID);
				}
				if(this.theEngine.getFuel() <= 0){
					this.theEngine.printRunOutOfFuel();
					this.theEngine.requestQuit();
					//this.theEngine.diosposeMainWindow();
				}
			}
			else throw new InstructionExecutionException(Messages.PROBLEMS_USING + this.theID + Interpreter.LINE_SEPARATOR);
		}
		else throw new InstructionExecutionException(Messages.DONT_HAVE_SUCH + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Método que establece el nombre del objeto a utilizar
	 * @param id nombre del objeto a utilizar
	 */
	public void setID(String id){
		this.theID = id;
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
	
	@Override
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
