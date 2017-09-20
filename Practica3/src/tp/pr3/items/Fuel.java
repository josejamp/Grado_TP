package tp.pr3.items;

import tp.pr3.*;

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
			System.out.print("      * My power is " + r.getFuel() + Interpreter.LINE_SEPARATOR);
			System.out.print("      * My recycled material is " + r.getRecycledMaterial() + Interpreter.LINE_SEPARATOR);
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
	
	public boolean equals(Object item){
		return ((super.equals(item)) && 
				(this.power == (((Fuel)item).power)));
	}
	
	public int compareTo(Item item){
		return super.compareTo(item);
	}
}
