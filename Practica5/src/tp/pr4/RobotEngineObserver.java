package tp.pr4;

import java.util.Observable;
import java.util.Observer;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * Interfaz encargada de informar sobre los eventos del RobotEngine
 */
public interface RobotEngineObserver{
	
	public void communicationCompleted();
	
	public void communicationHelp(String help);
	
	public void engineOff(boolean atShip);
	
	/**
	 * Informa de que ha habido un error
	 * @param msg mensaje de error
	 */
	public void raiseError(String msg);
	
	/**
	 * Informa cuando el robot dice algo
	 * @param message lo que dice el robot
	 */
	public void robotSays(String message);
	
	/**
	 * Informa cuando la cantidad de fuel o de material reciclado cambia
	 * @param fuel cantidad de fuel actual
	 * @param recycledMaterial cantidad de material reciclado actual
	 */
	public void robotUpdate(int fuel, int recycledMaterial);
	
}
