package tp.pr4;


import tp.pr4.gui.NavigationPanel;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.Item;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase que representa el módulo de navegacion por el que se guía el robot
 */
public class NavigationModule extends Observable<NavigationObserver>{
	
	private City city;
	private Place place;
	private Direction direction;
	/*eliminar(?)*/private NavigationPanel theNavPanel;
	
	/**
	 * Constructora por defecto
	 */
	public NavigationModule() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructora
	 * @param aCity ciudad para la navegacion
	 * @param initialPlace lugar donde empieza WALL·E
	 */
	public NavigationModule (City aCity, Place initialPlace){
		this.city = aCity;
		this.place = initialPlace;
		this.theNavPanel = null;
		this.direction = null;
	}
	
	/**
	 * Constructora
	 * @param aCity ciudad para la navegacion
	 * @param initialPlace lugar donde empieza WALL·E
	 * @param navPanel representacion grafica de la ciudad
	 */
	public NavigationModule(City aCity, Place initialPlace, NavigationPanel navPanel){
		this.city = aCity;
		this.place = initialPlace;
		this.theNavPanel = navPanel;
		this.direction = null;
	}
	

	/**
	 * Inicializa la direcccion hacia donde mira WALL·E
	 * @param heading direccion inicial
	 */
	public void initHeading(Direction heading){
		this.direction = heading;
	}
	
	/**
	 * Mira si el lugar es la nave espacial
	 * @return true si es la nave espacial, false en caso contrario
	 */
	public boolean atSpaceship(){
		return this.place.isSpaceship();
	}
	
	/**
	 * Rota la direccion
	 * @param rotation hacia donde gira WALL·E
	 */
	public void rotate(Rotation rotation){
		this.direction = Direction.rotate(this.direction, rotation);
		//this.informarObservadores();
	}
	
	/**
	 * Mueve a WALL·E, si es posible, si no lanza la excepcion
	 * @throws InstructionExecutionException
	 */
	public void move() throws InstructionExecutionException{
		if(this.theNavPanel == null)
			System.out.print("WALL·E says: " + "Moving in direction " + this.direction.toString() + Interpreter.LINE_SEPARATOR + Interpreter.LINE_SEPARATOR);
		Street newStreet = this.getHeadingStreet();//guardamos en 'newStreet' la posicion del array en la cual esta la calle
		if(newStreet != null){
			if(newStreet.isOpen()){
				this.place = newStreet.nextPlace(this.place); //guardamos la nueva posicion
				if(this.theNavPanel != null) this.theNavPanel.setActualizar(true);
				//this.informarObservadores();
			}
			else throw new InstructionExecutionException("Arrggg, there is a street but it is closed!"+ Interpreter.LINE_SEPARATOR);
		}
		else throw new InstructionExecutionException("there is no street in direction " + this.direction.toString()+ Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Coge un objeto
	 * @param id nombre del objeto a coger
	 * @return el objeto recibido
	 */
	public Item pickItemFromCurrentPlace(String id){
		return this.place.pickItem(id);
	}
	
	/**
	 * Deja un objeto en el lugar
	 * @param it el objeto a dejar
	 */
	public void dropItemAtCurrentPlace(Item it){
		this.place.addItem(it);
	}
	
	/**
	 * Mira si hay un objeto en el lugar
	 * @param id nombre del objeto
	 * @return true si el objeto está en el lugar, false en caso contrario
	 */
	public boolean findItemAtCurrentPlace(String id){
		return (this.place.getItems().getItem(id) != null);
	}
	
	/**
	 * Muestra la informacion del lugar
	 */
	public void scanCurrentPlace(){
		if(this.theNavPanel == null){
			System.out.print(this.place.toString());
		}
	}
	
	/**
	 * Devuelve la calle a la que mira el robot
	 * @return la calle a la que mira el robot
	 */
	public Street getHeadingStreet(){
		return this.city.lookForStreet(this.place, this.direction);
	}
	
	/**
	 * Devuelve la direccion a la que mira el robot
	 * @return la direccion a la que mira el robot
	 */
	public Direction getCurrentHeading(){
		return this.direction;
	}
	
	/**
	 * Devuelve el lugar en el cual esta el robot
	 * @return el lugar en el cual esta el robot
	 */
	public Place getCurrentPlace(){
		return this.place;
	}
	
	/**
	 * método que devuelve la ciudad por la que se mueve el robot
	 * @return la ciudad por la que se mueve el robot
	 */
	public City getCity(){
		return this.city;
	}
	
	
	@Override
	public boolean equals(Object obj){
		return ((this == obj) ||
				((obj != null)  && (this != null) &&
				(this.getClass() == obj.getClass()) &&
				this.city.equals(((NavigationModule)obj).city) &&
				this.direction == ((NavigationModule)obj).direction &&
				this.place.equals(((NavigationModule)obj).place)));
	}
	
	/**
	 * Método que establece el atributo privado navPanel
	 * @param navPanel nueva referencia del atributo privado navPanel
	 */
	public void setNavigationPanel(NavigationPanel navPanel){
		this.theNavPanel = navPanel;
	}
	
	/**
     * Indicar a los observadores que se ha actualizado el modelo.
     */
	/*
    private void informarObservadores(){
        setChanged(); // establece que ha habido un cambio.
        notifyObservers(); // notifica a los observadores.	
        // notifyObservers(new Double(alto)); // se podría informa mandandoles un Objeto con la información necesaria
    }
	*/
		
}
