package tp.pr4.gui;

import javax.swing.table.AbstractTableModel;

import tp.pr4.items.ItemContainer;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase de la interfaz gráfica para el inventario del robot.
 */
public class InventoryTable extends AbstractTableModel {
	
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
		return this.inventory.numberOfItems();
	}

	@Override
	public Object getValueAt(int row, int column) {
		String r="";
		switch(column){
		case 0:
			r = this.inventory.getItems().get(row).getId();
			break;
		case 1:
			r = this.inventory.getItems().get(row).getDescription();
			break;
				
		}
		return r;
	}
	
	 @Override
	 public boolean isCellEditable(int row, int column) {
	     //all cells false
	     return false;
	 }

}
