package tp.pr3.cityLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import tp.pr3.City;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.CodeCard;
import tp.pr3.items.Fuel;
import tp.pr3.items.Garbage;
import tp.pr3.Direction;

public class CityLoaderFromTxtFile {
	
	private ArrayList<Place> places;
	private ArrayList<Street> streets;

	/**
	 * Constructora, crea los arrays vacios
	 */
	public CityLoaderFromTxtFile(){
		this.places = new ArrayList<Place>();
		this.streets = new ArrayList<Street>();
	}
	
	/**
	 * Crea una ciudad a partir de un archivo de texto, lanza una excepcion si la lectura falla o el formato es incorreccto
	 * @param file archivo a leer
	 * @return la ciduad creada a partir de la informacion del archivo de texto
	 * @throws IOException
	 */
	public City loadCity(InputStream file) throws IOException{
		Scanner sc = null;
		sc = new Scanner(file, "UTF-8").useDelimiter("\\A");
		if(sc.nextLine().equals("BeginCity")){
			sc = this.readPlaces(sc);
			sc = this.readStreets(sc);
			sc = this.readItems(sc);
			if(sc.hasNextLine() && sc.nextLine().equals("EndCity")){
				sc.close();
				return new City(streets);
			}
			else {
				sc.close();
				throw new WrongCityFormatException();
			}
		}
		else {
			sc.close();
			throw new WrongCityFormatException();
		}
	}
	
	/**
	 * 
	 * @return el lugar inicial
	 */
	public Place getInitialPlace(){
		return this.places.get(0);
	}
	
	/**
	 * Lee la parte respectiva a Places del fichero, lanza una excepcion si el formato es incorrecto
	 * @param sc Scanner de lectura
	 * @return el Scanner actualizado
	 * @throws WrongCityFormatException
	 */
	private Scanner readPlaces(Scanner sc) throws WrongCityFormatException{
		boolean ok = true;
		if(sc.hasNextLine() && sc.nextLine().equals("BeginPlaces")){ //si hay siguiente linea y si la linea es correcta
			if(sc.hasNextLine()){ //si hay siguiente linea
				ok = this.buclePlaces(sc); //ejecuta el bucle de lectura
			}
			else ok = false;
		}
		else ok = false;
		if(!ok) throw new WrongCityFormatException(); //lanza la exxcepcion si el formato es incorrecto
		return sc;
	}
	
	/**
	 * Lee la parte respectiva a Streets del fichero, lanza una excepcion si el formato es incorrecto
	 * @param sc Scanner de lectura
	 * @return el Scanner actualizado
	 * @throws WrongCityFormatException
	 */
	private Scanner readStreets(Scanner sc) throws WrongCityFormatException{
		boolean ok = true;
		if(sc.hasNextLine() && sc.nextLine().equals("BeginStreets")){ //si hay siguiente linea y si la linea es correcta
			if(sc.hasNextLine()){ //si hay siguiente linea
				ok = this.bucleStreets(sc); //ejecuta el bucle de lectura
			}
			else ok = false;
		}
		else ok = false;
		if(!ok) throw new WrongCityFormatException(); //lanza la exxcepcion si el formato es incorrecto
		return sc;
	}
	
	/**
	 * Lee la parte respectiva a Items del fichero, lanza una excepcion si el formato es incorrecto
	 * @param sc Scanner de lectura
	 * @return el Scanner actualizado
	 * @throws WrongCityFormatException
	 */
	private Scanner readItems(Scanner sc) throws WrongCityFormatException{
		boolean ok = true;
		if(sc.hasNextLine() && sc.nextLine().equals("BeginItems")){ //si hay siguiente linea y si la linea es correcta
			if(sc.hasNextLine()){ //si hay siguiente linea
				ok = this.bucleItems(sc); //ejecuta el bucle de lectura
			}
			else ok = false;
		}
		else ok = false;
		if(!ok) throw new WrongCityFormatException(); //lanza la exxcepcion si el formato es incorrecto
		return sc;
	}
	
	/**
	 * Recorre cada linea de lectura de Places
	 * @param sc Scanner de lectura
	 * @return true si el formato es correcto, false en caso contrario
	 */
	private boolean buclePlaces(Scanner sc){
		boolean ok = true;
		String cad = sc.nextLine(); //Guarda la linea en un String
		int i = 0;
		while(!(cad.equals("EndPlaces")) && ok){ //Mientras no sea el final de Places y vaya todo bien					
			String[] WORD = cad.split(" "); //Partimos el String por los espacios
			ok = this.correctPlaces(WORD, i); //Si el formato es correcto
			i++;
			if(sc.hasNextLine()) cad = sc.nextLine(); //Se lee la siguiente linea
			else ok = false;
		}
		return ok;
	}
	
	/**
	 * Recorre cada linea de lectura de Streets
	 * @param sc Scanner de lectura
	 * @return true si el formato es correcto, false en caso contrario
	 */
	private boolean bucleStreets(Scanner sc){
		boolean ok = true;
		String cad = sc.nextLine(); //Guarda la linea en un String
		int i = 0;
		while((!cad.equals("EndStreets")) && ok){	//Mientras no sea el final de Streets y vaya todo bien	
			String[] WORD = cad.split(" "); //Partimos el String por los espacios
			ok = this.correctStreets(WORD, i); //Si el formato es correcto
			i++;
			if(sc.hasNextLine()) cad = sc.nextLine(); //Se lee la siguiente linea
			else ok = false;
		}
		return ok;
	}
	
	/**
	 * Recorre cada linea de lectura de Items
	 * @param sc Scanner de lectura
	 * @return true si el formato es correcto, false en caso contrario
	 */
	private boolean bucleItems(Scanner sc){
		boolean ok = true;
		String cad = sc.nextLine(); //Guarda la linea en un String
		int i = 0;
		while(!(cad.equals("EndItems")) && ok){ //Mientras no sea el final de Items y vaya todo bien	
			String[] WORD = cad.split(" "); //Partimos el String por los espacios
			ok = this.correctItems(WORD, i); //Si el formato es correcto
			i++;
			if(sc.hasNextLine()) cad = sc.nextLine(); //Se lee la siguiente linea
			else ok = false;
		}
		return ok;
	}
	
	/**
	 * Mira si cada palabra es correcta hasta que vuelve a leer "place"
	 * @param WORD array de palabras
	 * @param i indice de las calles
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctStreets(String[] WORD, int i){
		boolean ok = true;
		if((WORD.length >= 8) && WORD[0].equals("street") && this.isInteger(WORD[1]) && (Integer.parseInt(WORD[1]) == i) && (WORD[2].equals("place")) &&
			(WORD[4].equals("south") || WORD[4].equals("north") || WORD[4].equals("east") || WORD[4].equals("west"))){ //si la longitud es mayor o igual a ocho
				ok = this.correctStreectSecondPart(WORD);
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * Mira si cada palabra es correcta despues de ver por segunda vez "place"
	 * @param WORD array de palabras
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctStreectSecondPart(String[] WORD){
		boolean ok = true;
		if(WORD[5].equals("place") && this.isInteger(WORD[3]) && this.isInteger(WORD[6]) &&
				(Integer.parseInt(WORD[3]) < this.places.size() && Integer.parseInt(WORD[6])<this.places.size())){
			if(WORD[7].equals("open")){ //crea la calle abierta
				this.streets.add(new Street(this.places.get(Integer.parseInt(WORD[3])), Direction.toDirection(WORD[4]), this.places.get(Integer.parseInt(WORD[6]))));
				}
			else if(WORD[7].equals("closed") && (WORD.length == 9)){ //crea la calle cerrada
				this.streets.add(new Street(this.places.get(Integer.parseInt(WORD[3])), Direction.toDirection(WORD[4]), this.places.get(Integer.parseInt(WORD[6])), false, WORD[8]));
			}
			else ok = false;
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * Mira si cada palabra es correcta
	 * @param WORD array de palabras
	 * @param i indice de los lugares
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctPlaces(String[] WORD, int i){
		boolean ok = true;
		if((WORD.length == 5) && WORD[0].equals("place") && this.isInteger(WORD[1]) && (Integer.parseInt(WORD[1]) == i) && //si las siguientes palabras son correcctas y si la longitud del array es igual a cinco
			((WORD[4].equals("noSpaceShip")) || WORD[4].equals("spaceShip"))){
				this.places.add(new Place(WORD[2], (WORD[4].equals("spaceShip")? true:false), this.changeDescription(WORD[3])));
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * Mira si cada palabra es correcta hasta tener que distinguir el tipo de Item
	 * @param WORD array de palabras
	 * @param i indice de los objetos
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctItems(String[] WORD, int i){
		return ((WORD.length >= 7) &&  (WORD[0].equals("fuel") || WORD[0].equals("codecard") || WORD[0].equals("garbage")) &&
				this.isInteger(WORD[1]) && (Integer.parseInt(WORD[1]) == i)) && //si la longitud del array es mayor o igual a siete y si las palabras son correctas
				(this.correctFuel(WORD) || this.correctGarbage(WORD) || this.correctCodeCard(WORD));
	}
	
	/**
	 * Mira si es Fuel
	 * @param WORD array de palabras
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctFuel(String[] WORD){
		boolean ok = true;
		if(WORD[0].equals("fuel") && WORD[6].equals("place") && (WORD.length == 8) && this.isInteger(WORD[7]) && (Integer.parseInt(WORD[7]) < this.places.size())
				&& this.isInteger(WORD[4]) && this.isInteger(WORD[5])){ //si es fuel y todo es correcto
			this.places.get(Integer.parseInt(WORD[7])).addItem(new Fuel(WORD[2], this.changeDescription(WORD[3]), Integer.parseInt(WORD[4]), Integer.parseInt(WORD[5])));
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * Mira si es Garbage
	 * @param WORD array de palabras
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctGarbage(String[] WORD){
		boolean ok = true;
		if(WORD[0].equals("garbage") && WORD[5].equals("place") && (WORD.length == 7) && this.isInteger(WORD[6]) && (Integer.parseInt(WORD[6]) < this.places.size())
				&& this.isInteger(WORD[4])){ //si es garbage y todo es correcto
			this.places.get(Integer.parseInt(WORD[6])).addItem(new Garbage(WORD[2], this.changeDescription(WORD[3]), Integer.parseInt(WORD[4])));
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * Mira si es CodeCard
	 * @param WORD array de palabras
	 * @return true si todo es correcto, false cuando no lo es
	 */
	private boolean correctCodeCard(String[] WORD){
		boolean ok = true;
		if(WORD[0].equals("codecard") && WORD[5].equals("place") && this.isInteger(WORD[6])){ //si es CodeCard y todo es correcto
			this.places.get(Integer.parseInt(WORD[6])).addItem(new CodeCard(WORD[2], this.changeDescription(WORD[3]), WORD[4]));
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * Elimina "_" de las descripciones de los objetos y lo sustituye por espacios
	 * @param description descripcion del objeto inicial (con "_")
	 * @return la descripcion con " " en vez de "_"
	 */
	private String changeDescription(String description){
		String[] WORD = description.split("_");
		String cad = WORD[0];
		for(int i = 1; i < WORD.length; i++){
			cad = cad + " " + WORD[i];
		}
		return cad;
	}
	
	/**
	 * Metodo para saber si un String es un numero
	 * @param s String a comprobar
	 * @return true si es un numero, false en caso contrario
	 */
	public boolean isInteger(String s) {
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
	
}
