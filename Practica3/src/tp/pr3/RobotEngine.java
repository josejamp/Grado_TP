package tp.pr3;

import java.util.Scanner;

import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.*;

public class RobotEngine {

	private NavigationModule module;
	private int fuel;
	private int weight;
	private ItemContainer inventory;
	private boolean acabar;
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * @param initialPlace lugar en el que se encuentra WALL-E incialmente
	 * @param dir direccion en la que mira WALL-E inicialmente
	 * @param cityMap array que contiene el mapa con los lugares y las calles que los unen
	 */
	public RobotEngine(City cityMap, Place initialPlace, Direction dir){
		this.module = new NavigationModule(cityMap, initialPlace);
		this.module.initHeading(dir);
		this.fuel = 100;
		this.weight = 0;
		this.inventory = new ItemContainer();
		acabar = false;
	}
	
	/**
	 * enciende el robot
	 */
	public void startEngine(){
		String line = null;
		this.module.scanCurrentPlace();
		System.out.print(this.lookingAt());
		this.printRobotState();
		while(!this.acabar){ //muestra el menu mientras no encuentre la nave, se seleccione QUIT y se disponga de fuel
			Instruction instruction = null;
			this.walle();
			line = RobotEngine.sc.nextLine(); //lee la instruccion
			try{
				instruction = Interpreter.generateInstruction(line); //se genera instruccion a partir de lo leido
			}
			catch(WrongInstructionFormatException e){
				System.out.print(this.doNotUnderstand());
			}
			if(instruction != null){
				instruction.configureContext(this, this.module, this.inventory);
				this.comunicateRobot(instruction);
			}
		}
	}
	
	/**
	 * Ejecuta una instruccion
	 * @param c instruccion a ejecutar
	 */
	public void comunicateRobot(Instruction c){
		try{
			c.execute();
		}
		catch(InstructionExecutionException e){
			System.out.print(e.getMessage());
		}
	}
	
	/**
	 * suma (o resta) una cantidad de fuel a las reservas
	 * @param fuel cantidad de fuel que hay que sumar
	 */
	public void addFuel(int fuel){
		this.fuel = this.fuel + fuel;
	}
	
	/**
	 * 
	 * @return el fuel restante de WALL·E
	 */
	public int getFuel(){
		return this.fuel;
	}
	
	/**
	 * suma una cantidad de material reciclado a las reservas
	 * @param weight la cantidad de material reciclado que hay que sumar
	 */
	public void addRecycledMaterial(int weight){
		this.weight = this.weight + weight;
	}
	
	/**
	 * 
	 * @return la cantidad de material reciclado
	 */
	public int getRecycledMaterial(){
		return this.weight;
	}
	
	/**
	 * 
	 * @return el inventario del robot
	 */
	public ItemContainer getInventory(){
		return this.inventory;
	}
	
	/**
	 * Solicita mostrar el mensaje de ayuda
	 */
	public void requestHelp(){
		System.out.print(Interpreter.interpreterHelp() + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Solicita acabar
	 */
	public void requestQuit(){
		this.acabar = true;
	}
	
	/**
	 * Muestra el combustible y el material reciclado del robot
	 */
	public void printRobotState(){
		this.printFuelRecycled();
	}
	
	/**
	 * 
	 * @return ""WALL·E says: I do not understand, please repeat"
	 */
	private String doNotUnderstand(){
		return (this.walleSays() + "I do not understand, please repeat" + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * muestra "WALL·E > "
	 */
	private void walle(){
		System.out.print("WALL·E> ");
	}
	
	/**
	 * 
	 * @return "WALL·E says: "
	 */
	private String walleSays(){
		return ("WALL·E says: ");
	}
	
	/**
	 * muestra la cantidad de fuel
	 */
	private void printFuel(){
		System.out.print("      * My power is " + this.fuel + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * muestra la cantidad de material reciclado
	 */
	private void printRecycledMaterial(){
		System.out.print("      * My recycled material is " + this.weight + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * muestra el fuel y el material reciclado
	 */
	private void printFuelRecycled(){
		this.printFuel();
		this.printRecycledMaterial();
	}
	
	/**
	 * 
	 * @return "WALL·E is looking at direction " + direccion
	 */
	private String lookingAt(){
		return("WALL·E is looking at direction " + this.module.getCurrentHeading().toString() + Interpreter.LINE_SEPARATOR);
	}
	
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null) && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				(this.fuel == ((RobotEngine)obj).fuel) &&
				(this.weight == ((RobotEngine)obj).weight) &&
				(this.inventory.equals(((RobotEngine)obj).inventory)) &&
				(this.module.equals(((RobotEngine)obj).module)) &&
				(this.acabar == ((RobotEngine)obj).acabar)));
	}
	
}
