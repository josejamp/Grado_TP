package tp.pr3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp.pr3.instructions.DropInstruction;
import tp.pr3.instructions.HelpInstruction;
import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.MoveInstruction;
import tp.pr3.instructions.OperateInstruction;
import tp.pr3.instructions.PickInstruction;
import tp.pr3.instructions.QuitInstruction;
import tp.pr3.instructions.RadarInstruction;
import tp.pr3.instructions.ScanInstruction;
import tp.pr3.instructions.TurnInstruction;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class Interpreter {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final List<Instruction> instructionList = Interpreter.initList();
	//private static final int NUM_INSTRUCTIONS = 9;
	
	/**
	 * metodo que inicializa la lista de instrucciones
	 * @return el nuevo listado
	 */
	public static ArrayList<Instruction> initList(){
		ArrayList<Instruction> list = new ArrayList<Instruction>();
		list.add(new DropInstruction());
		list.add(new HelpInstruction());
		list.add(new MoveInstruction());
		list.add(new OperateInstruction());
		list.add(new PickInstruction());
		list.add(new QuitInstruction());
		list.add(new RadarInstruction());
		list.add(new ScanInstruction());
		list.add(new TurnInstruction());
		return list;
	}
			
			
	/**
	 * crea una instruccion a partir de la orden del usuario
	 * @param line almacena la orden escrita por el usuario
	 * @return una instruccion valida para WALL-E
	 */
	public static Instruction generateInstruction(String line) throws WrongInstructionFormatException{ 
		Instruction dec = null;
		boolean enc = false;
		Iterator<Instruction> it = Interpreter.instructionList.iterator();
		while (it.hasNext() && !enc){ //recorre el array de instrucciones hasta que encuentra una instruccion valida
			try{
				dec = it.next().parse(line);
				enc = true;
			}
			catch(WrongInstructionFormatException e){
			}
		}
		if(dec != null) return dec;
		else throw new WrongInstructionFormatException();
	}

	/**
	 * muestra las instrucciones  validas para WALL-E
	 */
	public static String interpreterHelp(){ 		
		String cad = "The valid instructions for WALL·E are: " + Interpreter.LINE_SEPARATOR;
		Iterator<Instruction> it = Interpreter.instructionList.iterator();
		while(it.hasNext()){
			cad = cad + it.next().getHelp() + Interpreter.LINE_SEPARATOR;
		}
		return cad;
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				this.getClass() == obj.getClass()));
	}

	
}
