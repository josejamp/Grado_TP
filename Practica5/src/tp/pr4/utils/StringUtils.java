package tp.pr4.utils;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase de utilidades para Strings
 */
public class StringUtils {

	/**
	 * 
	 * @param id el id del objeto
	 * @return el String con la primera letra en mayuscula
	 */
	public static String firstToUpper(String id){
		return id.replaceFirst((id.charAt(0) + ""), Character.toUpperCase(id.charAt(0)) + "");
	}
	
	/**
	 * Metodo para saber si un String es un numero
	 * @param s String a comprobar
	 * @return true si es un numero, false en caso contrario
	 */
	public static boolean isInteger(String s) {
		boolean ok = true;
	    if(!s.isEmpty()){
	    	int i = 0;
	    	if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) ok = false;
	            else i++;
	        }
		    while((i < s.length()) && ok) {
		    	if(i == 0 && s.charAt(i) == '-') {
		            if(s.length() == 1) ok = false;
		        }
		        if(Character.digit(s.charAt(i),10) < 0) ok = false;
		        else i++;
		    }
	    }
	    else ok = false;
	    return ok;
	}
	
	/**
	 * Elimina "_" de las descripciones de los objetos y lo sustituye por espacios
	 * @param description descripcion del objeto inicial (con "_")
	 * @return la descripcion con " " en vez de "_"
	 */
	public static String ingoreUnderLines(String string){
		String[] WORD = string.split("_");
		String cad = WORD[0];
		for(int i = 1; i < WORD.length; i++){
			cad = cad + " " + WORD[i];
		}
		return cad;
	}
	
}
