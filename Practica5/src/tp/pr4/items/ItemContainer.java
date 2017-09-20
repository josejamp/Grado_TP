package tp.pr4.items;

import tp.pr4.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase que representa el inventario del robot, la lista está ordenada
 */
public class ItemContainer extends Observable<InventoryObserver>{

	private List<Item>items;
	
	/**
	 * constructora por defecto
	 */
	public ItemContainer(){
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * 
	 * @return el numero de objetos en el array
	 */
	public int numberOfItems(){
		return this.items.size();
	}
	
	/**
	 * 
	 * @param item objeto a insertar
	 * @return true si se ha insertado, false en caso contrario
	 */
	public boolean addItem(Item item){
		boolean ok = (item != null) && !this.containsItem(item.id);
		if(ok){
			int pos = Collections.binarySearch(this.items, item);
			this.items.add(-pos-1, item);
		}
		return ok;
	}
	
	/**
	 * busca un objeto en la lista segun su id
	 * @param id nombre del objeto
	 * @return el objeto si se ha encontrado, null en caso contrario
	 */
	public Item getItem(String id){
		Item aux = new Fuel(id, " ", 0, 0);
	//	Iterator<Item> it = this.items.iterator();
	//	boolean enc = false;
	//	while(!enc && it.hasNext()){
	//		item = it.next();
	//		if(item.getId().equalsIgnoreCase(id)) enc = true;
	//	}
		int pos = Collections.binarySearch(this.items, aux);
		boolean enc = pos > -1;
		Item item = null;
		if(enc) item = this.items.get(pos);
		return item;
	}
	
	/**
	 * 
	 * @param id nombre del objeto
	 * @return true si el objeto esta en el contenedor, false en caso contrario
	 */
	public boolean containsItem(String id){
		return (this.getItem(id) != null);
	}
	
	/**
	 * 
	 * @param id nombre del objeto
	 * @return el objeto que ha cogido
	 */
	public Item pickItem(String id){
		Item item = this.getItem(id);
		if (item != null) this.items.remove(item);
		return item;
	}
	
	/**
	 * @return un string con los objetos de la lista 
	 */
	public String toString(){
		String cadena = "";
		Iterator<Item> it = this.items.iterator();
		while(it.hasNext()){
			Item aux = it.next();
			cadena += ("   " + aux.getId() + Interpreter.LINE_SEPARATOR);
		}
		return cadena;
	}
	
	/**
	 * 
	 * @return el array de objetos
	 */
	public List<Item> getItems(){
		return this.items;
	}
	
	/**
	 * Devuelve el id del objeto de la lista que ocupla la posición pos
	 * @param pos posicion de la lista
	 * @return su ID
	 */
	public String getId(int pos){
		return this.items.get(pos).getId();
	}
	
	/**
	 * Devuelve la descripcion del objeto de la lista que ocupla la posición pos
	 * @param pos posicion de la lista
	 * @return su descripcion
	 */
	public String getDescription(int pos){
		return this.items.get(pos).getDescription();
	}
	
	/**
	 * Método que comprueba si la posición solicitada existe en la lista de items
	 * @param pos posición solicitada
	 * @return true si es menor al tamaño de la lista (si la posición existe), false en caso contrario
	 */
	public boolean hasIndex(int pos){
		return ((pos >= 0) && (this.items.size() > pos));
	}
	
	
	@Override
	public boolean equals(Object obj){
		boolean ok = true;
		if((obj == this) ||
			((obj != null)  && (this != null) &&
			(this.getClass() == obj.getClass()) &&
			((ItemContainer)obj).items.size() == this.items.size()))
		{	
				Iterator<Item> it = this.items.iterator();
				Iterator<Item> itObj = ((ItemContainer)obj).items.iterator();
				while(it.hasNext() && itObj.hasNext() && ok){
					ok = it.next().equals(itObj.next());
				}
		}
		else ok = false;
		return ok;
	}
	
	@Override
	public ItemContainer clone(){
		ItemContainer nuevo = new ItemContainer();
		Iterator<Item> it = this.items.iterator();
		while(it.hasNext()){
			nuevo.addItem( it.next().clone() );
		}
		return nuevo;
	}
	
}
