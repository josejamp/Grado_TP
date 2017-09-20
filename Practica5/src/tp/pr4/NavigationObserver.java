package tp.pr4;

import java.util.Observer;


/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * Interfaz encargada de informar sobre los eventos del NavigationModule
 *
 */
public interface NavigationObserver{

	/**
	 * Informa cuando el robot cambia de direccion
	 * @param newHeading nueva direccion en la que mira el robot
	 */
	public void headingChanged(Direction newHeading);
	
	/**
	 * Informa cuando el NavigationModule ha sido inicializado
	 * @param initialPlace lugar en el que esta el robot inicialmente
	 */
	public void initNavigationModule(PlaceInfo initialPlace);
	
	/**
	 * Informa cuando el "inventario" del lugar en el que se encuentra el robot ha cambiado 
	 * (se añade o se quita un objeto del lugar)
	 * @param placeDescription nueva descripcion del lugar con los objetos actualizados
	 */
	public void placeHasChanged(PlaceInfo placeDescription);
	
	/**
	 * Informa cuando se ejecuta la instruccion radar sobre un lugar
	 * @param placeDescription descripcion del lugar
	 */
	public void placeScanned(PlaceInfo placeDescription);
	
	/**
	 * Informa cuando el robot cambia de lugar (se mueve)
	 * @param heading direccion en la que mira el robot
	 * @param place informacion del nuevo lugar
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place);
}
