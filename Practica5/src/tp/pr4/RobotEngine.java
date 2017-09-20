package tp.pr4;

import java.util.Scanner;


import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.*;
import tp.pr4.utils.Messages;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase que representa a WALLE y tiene toda su informacion necesaria
 */
public class RobotEngine extends Observable<RobotEngineObserver>{

	private NavigationModule module;
	private int fuel;
	private int weight;
	private ItemContainer inventory;
	private boolean acabar;
	/*eliminar(?) private MainWindow theMainWindow; */
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
		//this.theMainWindow = null;
	}
	

	/**
	 * enciende el robot
	 */
	public void startEngine(){
		//this.informarObservadores();
		String line = null;
		/* if(this.theMainWindow == null){
			this.screenMessage();
		} */
	//	else this.theMainWindow.update(this, null);		
		while(!this.acabar){ //muestra el menu mientras no encuentre la nave, se seleccione QUIT y se disponga de fuel
			//if(this.theMainWindow == null){
				System.out.print(Messages.WALLE);
				line = RobotEngine.sc.nextLine(); //lee la instruccion
				this.tryInstruction(line);
			//}
		}
	}
	
	/**
	 * Intenta generar una instruccion a partir del string line
	 * @param line string que parsea para generar una instruccion
	 */
	public void tryInstruction(String line){
		Instruction instruction = null;
		try{
			instruction = Interpreter.generateInstruction(line); //se genera instruccion a partir de lo leido
		}
		catch(WrongInstructionFormatException e){
			this.printMessage(Messages.DONT_UNDERSTAND + Interpreter.LINE_SEPARATOR, true);
		}
		if(instruction != null){
			instruction.configureContext(this, this.module, this.inventory);
			this.comunicateRobot(instruction);
		}
	}
	
			
	/**
	 * Ejecuta una instruccion
	 * @param c instruccion a ejecutar
	 */
	public void comunicateRobot(Instruction c){
		try{			
			c.execute();
			/*
			this.theMainWindow.avisaGiro(this.module.getCurrentHeading());
			this.theMainWindow.cambioFuelMaterial(this.fuel, this.weight);
			this.theMainWindow.newPlace(this.module.getCurrentPlace(), this.module.getCurrentHeading());
			this.theMainWindow.addItem(this.inventory);
			*/
			//this.informarObservadores();
		}
		catch(InstructionExecutionException e){
		/*	if(this.theMainWindow == null){
				System.out.print(e.getMessage());
			}
			else this.theMainWindow.showMessage(e.getMessage()); */
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
		//this.informarObservadores();
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
	/*public MainWindow getMainWindow(){
		return this.theMainWindow;
	}
	*/
	
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
		//if(this.theMainWindow == null)
			System.out.print(message + Interpreter.LINE_SEPARATOR);
		//else if(graphic) this.theMainWindow.showMessage(message);
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
	 * Set de la MainWindow
	 * @param mainWindow la nueva MainWindow
	 */
	/*
	public void setGUIWindow(MainWindow mainWindow){
		this.theMainWindow = mainWindow;
	}
	*/

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
	/*
	public void diosposeMainWindow(){
		if(this.theMainWindow != null)
			this.theMainWindow.disposeMainWindow();
	}
	*/

	/**
	 * metodo que devuelve el nivel de fuel en forma de String
	 * @return string con el fuel
	 */
	public String fuelToString(){
		return ("" + this.fuel);
	}
	
	/**
	 * metodo que devuelve el material reciclado del robot en forma de String
	 * @return string con el material reciclado
	 */
	public String recycledToString(){
		return ("" + this.weight);
	}
	
	/**
	 * metodo que devuelve el id del objeto i-esimo del inventario del robot
	 * @param i posicion del objeto en el inventario del robot
	 * @return id del objeto que ocupa la posicion i-esima
	 */
	public String idToString(int i){
		return this.inventory.getId(i);
	}
	
	/**
	 * metodo que devuelve la descripcion del objeto i-esimo del inventario del robot
	 * @param i posicion del objeto en el inventario del robot
	 * @return descripcion del objeto que ocupa la posicion i-esima
	 */
	public String descriptionToString(int i){
		return this.inventory.getDescription(i);
	}
	
	/**
	 * método para saber si dado un número, el número de objetos del inventario
	 * del robot es mayor o igual
	 * @param i numero de objetos
	 * @return true si el robot tiene mas objetos, false en caso contrario
	 */
	public boolean inventoryHasIndex(int i){
		return this.inventory.hasIndex(i);
	}
	
	/**
	 * metodo para conocer el lugar en el que se encuentra el robot actualmente
	 */
	public Place actualPlace(){
		return this.module.getCurrentPlace();
	}
	
	/**
     * Indicar a los observadores que se ha actualizado el modelo.
     */
    /*private void informarObservadores(){
        this.setChanged(); // establece que ha habido un cambio.
        this.notifyObservers(this.inventory.clone()); // notifica a los observadores.
    }*/
	
	
	public void addEngineObserver(RobotEngineObserver observer){
		
	}
	
	public void  addItemContainerObserver(InventoryObserver c){
		
	}
	
	public void addNavigationObserver(NavigationObserver robotObserver){
		
	}
	
	public boolean  isOver(){
		
		return false;		
	}
	
	public void  requestError(String msg){
		
	}
	
	public void requestStart(){
		
	}
	
	public void saySomething(String message){
		
	}
	
}
