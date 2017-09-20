package tp.pr3.items;

import tp.pr3.*;

public class Garbage extends Item implements Comparable<Item>{

	private int recycledMaterial;
	private boolean used;

	/**
	 * constructora
	 * @param id nombre
	 * @param description descripcion
	 * @param recycledMaterial cantidad de material reciclado que proporciona
	 */
	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.recycledMaterial = recycledMaterial;
		this.used = true;
	}

	/**
	 * @return true si todavia puede usarse, false en caso contrario
	 */
	public boolean canBeUsed() {
		return this.used;
	}

	/**
	 * recicla el material
	 * @param r WALL·E
	 * @return true si ha podido usarlo, false en caso contrario
	 */
	public boolean use(RobotEngine r, NavigationModule nav){
		boolean ok = false;
		if(this.canBeUsed()){
			this.used = false;
			r.addRecycledMaterial(this.recycledMaterial);
			System.out.print("      * My power is " + r.getFuel() + Interpreter.LINE_SEPARATOR);
			System.out.print("      * My recycled material is " + r.getRecycledMaterial() + Interpreter.LINE_SEPARATOR);
			ok = true;
		}
		return ok;
	}
	
	/**
	 * @return el material reciclado que proporciona el material
	 */
	public String toString(){
		return (super.toString() + "// Recycled material = " + this.recycledMaterial);
	}
	
	public boolean equals(Object item){
		return ((super.equals(item)) &&
				(this.recycledMaterial == (((Garbage)item).recycledMaterial)));
	}
	
	public int compareTo(Item item){
		return super.compareTo(item);	
	}

}
