package tp.pr4.items;

import tp.pr4.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase que representa el inventario del robot
 */
public class ItemContainer {

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
		boolean ok = false;
		if((item != null) && !this.containsItem(item.id)){
			this.items.add(item);
			Collections.sort(this.items);
			ok = true;
		}
		else ok = false;
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
		for(int i = 0; i < this.items.size(); i++){
			cadena += ("   " + this.items.get(i).getId() + Interpreter.LINE_SEPARATOR);
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
		return this.items.size() > pos;
	}
	
	
	/**
	 * amplia el array dejando un hueco en la posicion i
	 * @param i posicion en la que hay que dejar un hueco (para mas tarde insertar)
	 */
	private void ampliar(int i){
		this.items.add(this.items.get(this.items.size()-1));
		for(int j = this.items.size()-2; j >= i; j--){
			this.items.set(j+1, this.items.get(j));
		}
	}
	
	/**
	 * inserta un objeto en el array de manera ordenada sin distincion entre mayusculas y minusculas
	 * @param item objeto a insertar
	 */
	private void insert(Item item){
		int i = 0;
		boolean enc = false;
		while(!enc && (i < this.items.size())){
			if((this.items.get(i).getId().toLowerCase().compareTo(item.getId().toLowerCase())) > 0){
				this.ampliar(i);
				this.items.set(i, item);
				enc = true;
			}
			else i++;
		}
		if(!enc) this.items.add(item);
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
	
}
