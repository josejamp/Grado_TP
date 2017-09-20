package tp.pr4.items;

import tp.pr4.*;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase que representa al item fuel. Contiene un int indicando la energía que aporta y otro int indicando el número
 * de veces que puede utilizarse.
 */
public class Fuel extends Item implements Comparable<Item>{
	
	private int power;
	private int times;
	
	/**
	 * constructora
	 * @param id nombre
	 * @param description descripcion
	 * @param power cantidad de energia que proporciona
	 * @param times numero de veces que puede usarse
	 */
	public Fuel(String id, String description, int power, int times) {
		super(id, description);
		this.power = power;
		this.times = times;
	}

	/**
	 * @return true si el objeto todavia puede utilizarse, false en caso contrario
	 */
	public boolean canBeUsed() {
		return (this.times > 0);
	}

	/**
	 * usa el fuel
	 * @param r WALL·E
	 * @return true si ha podido usarlo, false en caso contrario
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean ok = false;
		if(this.canBeUsed()){
			this.times--;
			r.addFuel(this.power);
			r.printRobotState();
			ok = true;
		}
		return ok;
	}
	
	/**
	 * @return la energia que proporciona y el numero de veces que puede usarse
	 */
	public String toString(){
		return (super.toString() + "// power = " + this.power + ", times = " + this.times);
	}
	
	@Override
	public Fuel clone(){
		return new Fuel(this.id, this.description, this.power, this.times);
	}
	
}
