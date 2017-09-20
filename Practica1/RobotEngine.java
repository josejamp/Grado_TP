package tp.pr1;

import java.util.Scanner;

public class RobotEngine {
	
	private Interpreter interpreter;
	private Place place;
	private Direction direction;
	private Street[] cityMap;
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * @param initialPlace lugar en el que se encuentra WALL-E incialmente
	 * @param direction direccion en la que mira WALL-E inicialmente
	 * @param cityMap array que contiene el mapa con los lugares y las calles que los unen
	 */
	public RobotEngine(Place initialPlace, Direction direction, Street[] cityMap){
		this.interpreter =new Interpreter();
		this.place = initialPlace;
		this.direction = direction;
		this.cityMap = cityMap;
	}
	
	public void startEngine(){
		boolean acabar = false;
		String line = null;
		Instruction instruction = null;
		System.out.println(this.place.toString());
		System.out.println("WALL·E is looking at direction " + this.direction.toString());
		while(!acabar){ //muestra el menu mientras no encuentre la nave o se seleccione QUIT
			System.out.println("WALL·E > ");
			line = RobotEngine.sc.nextLine(); //lee la instruccion
			instruction = this.interpreter.generateInstruction(line); //se genera instruccion a partir de lo leido
			if(!instruction.isValid()){ //si la instruccion generada no es valida
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
			else{ // si es  valida
				if(instruction.getAction() == Action.HELP) System.out.println(Interpreter.interpreterHelp()); //si es HELP muestra la ayuda
				else {
					if(instruction.getAction() == Action.QUIT) { //si es QUIT sale
						System.out.println("I have communication problems. Bye bye");
						acabar = true;
					}
					else{
						if (instruction.getAction() == Action.TURN){ //si es TURN gira y muestra la nueva direccion
							this.direction = this.changeDirection(instruction);
							System.out.println("WALL·E is looking at direction " + this.direction.toString());
						}
						else{ //si es MOVE se mueve en la direccion a  la que esta mirando
							acabar = this.move(instruction);
						}
					}
				}
			}
		}
		System.out.println("Game over"); //Mensaje de final del juego
	}
	
	
	/**
	 * metodo privado que ejecuta la instruccion MOVE
	 * @param instruction instruccion
	 * @return true si es la instruccion MOVE y false en caso contario
	 */
	private boolean move(Instruction instruction){  
		int position = -1;
		boolean acabar = false;
		if (instruction.getAction() == Action.MOVE){
			position = this.lookForStreet(this.direction, this.place);//guardamos en 'position' la posicion del array en la cual esta la calle
			if(position >= 0){
				System.out.println("WALL·E says: moving in direction " + this.direction.toString());
				this.place = this.cityMap[position].nextPlace(this.place); //guardamos la nueva posicion
				System.out.println(this.place.toString());
				System.out.println("WALL·E is looking at direction " + this.direction.toString());
				if (this.place.isSpaceship()){ //si el nuevo lugar es la nave mostramos el mensaje y finalizamos el juego
					System.out.println("WALL·E says: I am at my spaceship. Shutting down... Bye bye");
					acabar = true;
				}
			}
			else System.out.println("WALL·E says: there is no street in direction " + this.direction.toString()); //si no hay calle desde el lugar actual en la direccion dada
		}
		return acabar;
	}
	
	/**
	 * efectua el giro y cambia la direccion segun este
	 * @param instruction instruccion
	 * @return la nueva direccion
	 */
	private Direction changeDirection(Instruction instruction){ 
		Direction direction;
		if (this.direction == Direction.NORTH){
			if(instruction.getRotation() == Rotation.RIGHT) direction = Direction.EAST;
			else direction = Direction.WEST;
		}
		else{
			if(this.direction == Direction.SOUTH){
				if(instruction.getRotation() == Rotation.RIGHT) direction = Direction.WEST;
				else direction = Direction.EAST;
			}
			else{
				if(this.direction == Direction.EAST){
					if(instruction.getRotation() == Rotation.RIGHT) direction = Direction.SOUTH;
					else direction = Direction.NORTH;
				}
				else{
					if(this.direction == Direction.WEST){
						if(instruction.getRotation() == Rotation.RIGHT) direction = Direction.NORTH;
						else direction = Direction.SOUTH;
					}
					else direction = Direction.UNKNOWN;
				}
			}
		}
		return direction;
	}
	
	/**
	 * buscamos los lugares en el cityMap que sean validos
	 * @param direction direccion actual
	 * @param place lugar actual
	 * @return la posicion del array cityMap con la calle valida. En caso de que no exita devuelve -1
	 */
	private int lookForStreet(Direction direction, Place place){ 
		int i = 0;
		boolean enc = false;
		while(!enc && (i < this.cityMap.length)){
			if(this.cityMap[i].comeOutFrom(place, direction)) enc = true;
			else i++;
		}
		if (enc) return i;
		else return -1;
	}

}
