package tp.pr4.items;

import tp.pr4.*;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase abstracta que representa los objetos, de esta heredaran distintos tipos de objetos.
 */
public abstract class Item implements Comparable<Item>{

	protected String id;
	protected String description;
	
	/**
	 * construye item
	 * @param id nombre
	 * @param description descripcion
	 */
	public Item(String id, String description){
		this.id = id;
		this.description = description;
	}
	
	/**
	 * metodo abstracto
	 * @return true si puede seguir usandose, false en caso contrario
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * metodo abstracto, usa el objeto
	 * @param r WALL·E
	 * @return true si lo puede usar
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	/**
	 * 
	 * @return el id
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @return la descripción
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * @return el id y la descripcion del objeto
	 */
	public String toString(){
		return (this.id + ": " + this.description);
	}
	
	@Override
	public boolean equals(Object item){
		return ((this == item) ||
				((item != null) && (this != null) &&
				(item.getClass() == this.getClass()) && 
				this.id.equalsIgnoreCase(((Item)item).id) && 
				this.description.equalsIgnoreCase(((Item)item).description)));
	}
	
	@Override
	public int compareTo(Item item){
		return this.id.compareToIgnoreCase(item.id);
	}
	
}
