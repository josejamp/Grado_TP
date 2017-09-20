package tp.pr1;

public class Interpreter {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * crea una instruccion a partir de la orden del usuario
	 * @param line almacena la orden escrita por el usuario
	 * @return una instruccion valida para WALL-E
	 */
	public Instruction generateInstruction(String line){ 
		Instruction dec = null;
		if(line.equalsIgnoreCase("MOVE")){ //moverse en la direccion en la que WALL-E mira
			Instruction inst = new Instruction(Action.MOVE);
			dec = inst;
		}
		else if(line.equalsIgnoreCase("HELP")){ //muestra los comandos
			Instruction inst = new Instruction(Action.HELP);
			dec = inst;
		}
		else if(line.equalsIgnoreCase("QUIT")){	 //sale del juego		
			Instruction inst = new Instruction(Action.QUIT);
			dec = inst;
		}
		else{ //en caso de que sea la instruccion turn
			String [] WORD = line.split(" ");  //separamos las palabras
			if(WORD.length > 0){ //si efectivamente hay palabras que separar
				if(WORD[0].equalsIgnoreCase("TURN")){ //si la primera es turn
					if(WORD.length >= 2){ //si hay m�s palabras
						if(WORD[1].equalsIgnoreCase("RIGHT")){ //mira si la segunda es right
							Instruction inst = new Instruction(Action.TURN, Rotation.RIGHT);
							dec = inst;
						}
						else if(WORD[1].equalsIgnoreCase("LEFT")){ //mira si la segunda es left
							Instruction inst = new Instruction(Action.TURN, Rotation.LEFT);
							dec = inst;
						}
						else{ //en caso de que la segunda palabra no sea valida
							Instruction inst = new Instruction(Action.TURN);
							dec = inst;
						}
					}
					else{ //en caso de que no haya segunda palabra
						Instruction inst = new Instruction(Action.TURN);
						dec = inst;
					}
				}
				else{ //en caso de que la primera palabra no se entienda
					Instruction inst = new Instruction();
					dec = inst;
				}
			}
			else{ //en caso de que no haya primera palabra (puede ser un espacio)
				Instruction inst = new Instruction();
				dec = inst;
			}
		}
	 return dec;
	}

	/**
	 * muestra las instrucciones  validas para WALL-E
	 */
	public  static String interpreterHelp(){ 
		
		return ("The valid instructions for WALL·E are:" + Interpreter.LINE_SEPARATOR + "  MOVE" + Interpreter.LINE_SEPARATOR +"  TURN <LEFT | RIGHT>" + Interpreter.LINE_SEPARATOR + "  HELP" + Interpreter.LINE_SEPARATOR + "  QUIT");
	}
}
