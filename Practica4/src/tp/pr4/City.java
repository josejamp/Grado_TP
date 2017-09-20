package tp.pr4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 *Clase que contiene una lista de calles, formando la ciudad por la que se mueve WALL·E
 */
public class City {
	
	private List<Street> cityMap;
	
	/**
	 * constructora por defecto
	 */
	public City(){
		this.cityMap = null;
	}
	
	/**
	 * constructora
	 * @param cityMap las calles de la ciudad en ArrayList
	 */
	public City(ArrayList<Street> cityMap){
		this.cityMap = cityMap;
	}
	
	/**
	 * constructora
	 * @param cityMap las calles de la ciudad en Array
	 */
	public City(Street[] cityMap){
		this.cityMap = Arrays.asList(cityMap);
	}
	
	/**
	 * 
	 * @param currentPlace lugar de WALL·E
	 * @param currentHeading direccion a la que mira WALL·E
	 * @return la calle que parte de la posicion de WALL·E en la direccion que mira si esta existe, si no null
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading){	
		boolean enc = false;
		Street s = null;
		if(this.cityMap !=null){ //no hace nada si no hay ciudad
			Iterator<Street> it = this.cityMap.iterator();
			while(!enc && it.hasNext()){ //busqueda de la calle en la ciudad
				s = it.next();
				if(s.comeOutFrom(currentPlace, currentHeading)) enc = true;
			}
			if (!enc) s = null;
		}
		return s;
	}
	
	/**
	 * Añade una calle a la lista de calles
	 * @param street calle a añadir
	 */
	public void addStreet(Street street){
		this.cityMap.add(street);
	}

	@Override
	public boolean equals(Object obj){
		boolean ok = true;
		if((obj == this) ||
			((obj != null) && (this != null) &&
			(this.getClass() == obj.getClass()) &&
			((City)obj).cityMap.size() == this.cityMap.size()))
		{
			Iterator<Street> it = this.cityMap.iterator();
			Iterator<Street> itObj = ((City)obj).cityMap.iterator();
			while(it.hasNext() && itObj.hasNext() && ok){
				ok = it.next().equals(itObj.next());
			}
		}
		return ok;
	}
	
}
