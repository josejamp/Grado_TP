package tp.pr3;

import tp.pr3.items.*;

public class Place {

	private String name; //nombre del lugar
	private boolean isSpaceShip; //nos dira si es la nave para acabar el juego, true si es la nave
	private String description; //descripcion del lugar
	private ItemContainer items;

	/**
	 * @param name nombre del lugar
	 * @param isSpaceShip true si es la nave de WALL-E, false en caso contrario
	 * @param description descripcion del lugar
	 */
	public Place(String name, boolean isSpaceShip, String description){
		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
		this.items = new ItemContainer();
	}
	
	/**
	 * @return devuelve true si es la nave
	 */
	public boolean isSpaceship(){ 
		return this.isSpaceShip;
	}
	
	/**
	 * intenta añadir un objeto
	 * @param it objeto a añadir
	 * @return true si puede añadirlo, false en caso contrario
	 */
	public boolean addItem(Item it){
		return this.items.addItem(it);
	}
	
	/**
	 * coge un objeto del lugar
	 * @param id nombre del objeto
	 * @return el objeto que ha cogido
	 */
	public Item pickItem(String id){
		return this.items.pickItem(id);
	}
	
	/**
	 * Mira si el objeto esta en el lugar
	 * @param id nombre del objeto
	 * @return true si el objeto esta, false en caso contrario
	 */
	public boolean existItem(String id){
		return (this.items.containsItem(id));
	}
	
	/**
	 * Suelta un objeto en el lugar
	 * @param it Objeto a soltar
	 * @return false si el objeto ya estaba en el lugar, true en caso contrario
	 */
	public boolean dropItem(Item it){
		boolean ok = false;
		if(!this.existItem(it.getId())){
			ok = this.addItem(it);
		}
		return ok;
	}
	
	/**
	 * 
	 * @return el array con los objetos del lugar
	 */
	public ItemContainer getItems(){
		return this.items;
	}
	
	/**
	 * Sobreescribe el metodo toString para mostrar el nombre del lugar y su descripcion
	 */
	public String toString(){ 
		String s;
		if(this.items.numberOfItems() > 0) s = (this.name + Interpreter.LINE_SEPARATOR + this.description + Interpreter.LINE_SEPARATOR + "The place contains these objects: " + Interpreter.LINE_SEPARATOR + this.items.toString());
		else s = (this.name + Interpreter.LINE_SEPARATOR + this.description + Interpreter.LINE_SEPARATOR + "The place is empty. There are no objects to pick" + Interpreter.LINE_SEPARATOR);
		return s;
	}
	
	public boolean equals(Object p){
		return ((this == p) || 
				(p != null) && (this != null) &&
				(this.getClass() == p.getClass()) &&
				(this.name.equalsIgnoreCase(((Place)p).name)) &&
				(this.description.equalsIgnoreCase(((Place)p).description)) &&
				(((Place)p).isSpaceShip == this.isSpaceShip));
	}

}
