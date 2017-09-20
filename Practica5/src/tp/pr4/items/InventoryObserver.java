/**
 * 
 */
package tp.pr4.items;

import java.util.List;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * Interfaz encargada de informar sobre los cambios producidos en el inventario del robot
 */
public interface InventoryObserver {

	/**
	 * Informa cuando el inventario ha sido modificado
	 * @param inventory inventario del robot actualizado
	 */
	public void inventoryChange(List<Item> inventory);
	
	/**
	 * Informa cuando se solicita la instruccion scan del inventario
	 * @param inventoryDescription descripcion del inventario
	 */
	public void inventoryScanned(String inventoryDescription);
	
	/**
	 * Informa cuando un objeto está agotado y se borra del inventario
	 * No se encarga de actualizar el inventario una vez eliminado el objeto,
	 * de esto debe encargarse inventoryChange
	 * @param itemName
	 */
	public void itemEmpty(String itemName);
	
	/**
	 * Informa cuando se solicita la instruccion scan de un objeto del inventario
	 * @param description descripcion del objeto
	 */
	public void itemScanned(String description);
	
}
