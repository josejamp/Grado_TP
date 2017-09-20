package tp.pr4;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Enumerado para los giros
 */
public enum Rotation {
	LEFT, RIGHT, UNKNOWN;
	
	
	/**
	 * Método estático que dado un string devuelve su enumerado correspondiente
	 * @param s string a pasar a enumerado
	 * @return el enumerado correspondiente a la rotación indicada en el string
	 */
	public static Rotation toRotation(String s){
		Rotation rotation = Rotation.UNKNOWN;
		if(s.equalsIgnoreCase("LEFT")){
			rotation = Rotation.LEFT;
		}
		else if(s.equalsIgnoreCase("RIGHT")){
			rotation = Rotation.RIGHT;
		}
		
		return rotation;
	}
}
