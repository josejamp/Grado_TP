package tp.pr4.gui;

import java.util.EventListener;
import java.util.Observer;


/*
* @author José Javier Martínez Pagés
* @author Cristina Valentina Espinosa Victoria
*/

/**
 * Interfaz genérico para que el controlador no tenga que conocer los detalles específicos de como realiza las 
 * presentaciones la vista. Sólo se necesita ordenarla que actualize la información pasándola la nueva información.
 *
 */
public interface MainWindowInterface extends Observer {

	/**
     * Método que se encarga de fijar el controlador en los elementos de la vista de forma adecuada
     * @param controlador contiene el controlador encargado de la visrta.
     */
    public void fijarControlador(EventListener controlador);
    
}
