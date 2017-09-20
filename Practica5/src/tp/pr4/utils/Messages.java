package tp.pr4.utils;

import tp.pr4.Direction;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase de utilidades para los mensajes.
 */
public class Messages {
	
	public static final String WALLE = "WALL·E> ";
	public static final String WALLE_SAYS = "WALL·E says: ";
	public static final String RUN_OUT = "WALLE·E says: " + "I run out of fuel. I cannot move. Shutting down...";
	public static final String SPACESHIP = Messages.WALLE_SAYS + "I am at my spaceship. Bye bye";
	public static final String DONT_UNDERSTAND = Messages.WALLE_SAYS + "I do not understand, please repeat";
	public static final String OBJECT_ALREADY_PLACE = Messages.WALLE_SAYS + "the object is already at the place";
	public static final String DONT_HAVE_ANY = "You do not have any ";
	public static final String DONT_HAVE_SUCH = Messages.WALLE_SAYS + "I have not such object";
	public static final String PROBLEMS_USING = "I have problems using the object ";
	public static final String STUPID = Messages.WALLE_SAYS + "I am stupid! I had already the object ";
	public static final String PLACE_DOESNT_HAVE_OBJECT = Messages.WALLE_SAYS + "Oops, this place has not the object ";
	public static final String COMUNICATION_PROBLEMS = Messages.WALLE_SAYS + "I have communication problems. Bye bye";
	public static final String CARRYING_ITEMS = Messages.WALLE_SAYS + "I am carrying the following items";
	
	
	/**
	 * Devuelve un mensaje indicando la direccion a la que mira
	 */
	public static String lookingAt(Direction dir){
		return ("WALL·E is looking at direction " + dir.toString());
	}
	
	/**
	 * Devuelve un mensaje indicando que se ha deshecho correctamente del objeto con identificador id
	 * @param id el identificador del objeto
	 */
	public static String printDrop(String id){
		return ("Great! I have dropped " + id);
	}
	
	/**
	 *  devuelve un mensaje indicando que no tiene más objetos con identificador id
	 * @param id
	 */
	public static String printNoMoreObjects(String id){
		return ("What a pity! I have no more " + id + " in my inventory");
	}
	
	/**
	 * Devuelve un mensaje de felicidad al recoger un objeto de un lugar
	 * @param id el identificador del objeto
	 */
	public static String printHappyIhaveTheObject(String id){
		return ("WALL·E says: " + "I am happy! Now I have " + StringUtils.firstToUpper(id.toLowerCase()));
	}
	
	/**
	 * devuelve un mensaje con la cantidad de fuel
	 */
	public static String printFuel(int fuel){
		return ("      * My power is " + fuel);
	}
	
	/**
	 * devuelve un String con la cantidad de material reciclado
	 */
	public static String printRecycledMaterial(int weight){
		return ("      * My recycled material is " + weight);
	}

}
