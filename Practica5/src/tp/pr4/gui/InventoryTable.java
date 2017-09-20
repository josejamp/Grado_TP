package tp.pr4.gui;

import java.util.Observable;

import javax.swing.table.AbstractTableModel;

import tp.pr4.items.ItemContainer;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase de la interfaz gráfica para el inventario del robot.
 */
public class InventoryTable extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int numColumns;
	private ItemContainer inventory;
	
	
	/**
	 * Constructora por defecto
	 */
	public InventoryTable(){
		this.inventory = new ItemContainer();
		this.numColumns = 2;
	}
	
	/**
	 * Constructora
	 * @param inventory inventario del robot
	 */
	public InventoryTable(ItemContainer inventory){
		this.inventory = inventory;
		this.numColumns = 2;
	}


	@Override
	public int getColumnCount() {
		return this.numColumns;
	}

	@Override
	public int getRowCount() {
		return this.inventory != null? this.inventory.numberOfItems():0 ;
	}

	@Override
	public Object getValueAt(int row, int column) {
		String r="";
		switch(column){
		case 0:
			r = this.inventory.getId(row);
			break;
		case 1:
			r = this.inventory.getDescription(row);
			break;	
		}
		return r;
	}
	
	 @Override
	 public boolean isCellEditable(int row, int column) {
	     //all cells false
	     return false;
	 }
	 
	 /**
	  * Set del inventario
	  * @param container ItemConatiner a fijar
	  */
	public void setInventory(ItemContainer container){
		this.inventory = container;
	}

}
