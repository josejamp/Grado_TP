package tp.pr4;


/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * Clase abstracta con la funcionalidad basica comun a ConsoleController y GUIController
 */
public abstract class Controller {
	
	public RobotEngine engine;
	
	public Controller(RobotEngine engine){
		this.engine = engine;	
	}
	
	public void registerEngineObserver(){
		
	}
	
	public void registerItemContainerObserver(){
		
	}
	
	public void registeRobotObserver(){
		
	}
	
	public void startController(){
		
	}
	
	

}
