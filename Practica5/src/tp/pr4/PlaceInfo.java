package tp.pr4;


/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */


/**
 * Interfaz implementada por Place encargada de mostrar la información del lugar sin que éste pueda ser modificado
 */
public interface PlaceInfo {

	/**
	 * 
	 * @return la descripcion del lugar
	 */
	public String getDescription();

	/**
	 * 
	 * @return el nombre del lugar
	 */
	public String getName();
	
	/**
	 * 
	 * @return true si el lugar es la nave del robot. False en otro caso.
	 */
	public boolean isSpaceship();
	
}
