package tp.pr4.items;

import tp.pr4.*;

/**
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase que representa al item garbage. Contiene un int con la cantadidad de material reciclado que aporta. 
 * Sólo puede usarse una vez.
 *
 */
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
			r.printRobotState();
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
	
	@Override
	public boolean equals(Object item){
		return ((super.equals(item)) &&
				(this.recycledMaterial == (((Garbage)item).recycledMaterial)));
	}
	
	@Override
	public int compareTo(Item item){
		return super.compareTo(item);	
	}

}
