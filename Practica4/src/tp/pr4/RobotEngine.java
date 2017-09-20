package tp.pr4;

import java.util.Scanner;

import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.RobotPanel;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.*;
import tp.pr4.utils.Messages;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase que representa a WALLE y tiene toda su informacion necesaria
 */
public class RobotEngine {

	private NavigationModule module;
	private int fuel;
	private int weight;
	private ItemContainer inventory;
	private boolean acabar;
	private MainWindow theMainWindow;
	private RobotPanel theRobotPanel;
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructora por defecto
	 */
	public RobotEngine() {
		// TODO Auto-generated constructor stub
	}
	
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
		this.acabar = false;
		this.theMainWindow = null;
		this.theRobotPanel = null;
	}
	

	/**
	 * enciende el robot
	 */
	public void startEngine(){
		String line = null;
		if(this.theMainWindow == null){
			this.screenMessage();
		}
		else this.theMainWindow.update();		
		while(!this.acabar){ //muestra el menu mientras no encuentre la nave, se seleccione QUIT y se disponga de fuel
			if(this.theMainWindow == null){
				Instruction instruction = null;
				System.out.print(Messages.WALLE);
				line = RobotEngine.sc.nextLine(); //lee la instruccion
				try{
					instruction = Interpreter.generateInstruction(line); //se genera instruccion a partir de lo leido
				}
				catch(WrongInstructionFormatException e){
					System.out.print(Messages.DONT_UNDERSTAND + Interpreter.LINE_SEPARATOR);
				}
				if(instruction != null){
					instruction.configureContext(this, this.module, this.inventory);
					this.comunicateRobot(instruction);
				}
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
			if(this.theMainWindow == null){
				System.out.print(e.getMessage());
			}
			else this.theMainWindow.showMessage(e.getMessage());
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
	 * 
	 * @return el modulo de navegacion
	 */
	public NavigationModule getNavigationMod(){
		return this.module;
	}
	
	/**
	 * 
	 * @return la ventana principal
	 */
	public MainWindow getMainWindow(){
		return this.theMainWindow;
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
	 * Muestra un mensaje indicando el total consumo del fuel
	 */
	public void printRunOutOfFuel(){
		this.printMessage(Messages.RUN_OUT, true);
	}
	
	/**
	 * Muestra un mensaje indicando la llegada a la nave
	 */
	public void printIamAtMySpaceship(){
		this.printMessage(Messages.SPACESHIP, true);
	}
	
	/**
	 * Muestra un mensaje indicando la direccion a la que mira
	 */
	public void lookingAt(){
		this.printMessage(Messages.lookingAt(this.module.getCurrentHeading()), false);
	}
	
	/**
	 * muestra un mensaje indicando que se ha deshecho correctamente del objeto con identificador id
	 * @param id el identificador del objeto
	 */
	public void printDrop(String id){
		this.printMessage(Messages.printDrop(id), false);
	}
	
	/**
	 *  muestra un mensaje indicando que no tiene más objetos con identificador id
	 * @param id
	 */
	public void printNoMoreObjects(String id){
		this.printMessage(Messages.printNoMoreObjects(id), false);
	}
	
	/**
	 * Muestra un mensaje de felicidad al recoger un objeto de un lugar
	 * @param id el identificador del objeto
	 */
	public void printHappyIhaveTheObject(String id){
		this.printMessage(Messages.printHappyIhaveTheObject(id), false);
	}
	
	/**
	 * muestra el fuel y el material reciclado
	 */
	private void printFuelRecycled(){
		this.printMessage(Messages.printFuel(this.fuel), false);
		this.printMessage(Messages.printRecycledMaterial(this.weight), false);
	}
	
	/**
	 * Metodo privado para saber si hay que mostrar un mensaje por pantalla o no, y mostrarlo si hay que hacerlo
	 * @param message el mensaje a mostrar
	 * @param graphic booleano para indicar si hay que mostrar un mensaje por la interfaz grafica
	 */
	private void printMessage(String message, boolean graphic){
		if(this.theMainWindow == null)
			System.out.print(message + Interpreter.LINE_SEPARATOR);
		else if(graphic) this.theMainWindow.showMessage(message);
	}
	
	@Override
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
	
	/**
	 * Set del navigationPanel
	 * @param navPanel el nuevo navigationPanel
	 */
	public void setNavigationPanel(NavigationPanel navPanel){
		this.module.setNavigationPanel(navPanel);
	}
	
	/**
	 * Set del robotPanel
	 * @param robotPanel el nuevo robotPanel
	 */
	public void setRobotPanel(RobotPanel robotPanel){
		this.theRobotPanel = robotPanel;
	}
	
	/**
	 * Set de la MainWindow
	 * @param mainWindow la nueva MainWindow
	 */
	public void setGUIWindow(MainWindow mainWindow){
		this.theMainWindow = mainWindow;
	}

	/**
	 * Metodo para mostrar el mensaje inicial por pantalla
	 */
	private void screenMessage(){
		this.module.scanCurrentPlace();
		this.lookingAt();
		this.printRobotState();
	}
	
	/**
	 * Metodo para cerrar la ventana si esta existe
	 */
	public void diosposeMainWindow(){
		if(this.theMainWindow != null)
			this.theMainWindow.disposeMainWindow();
	}

	
}
