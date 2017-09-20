package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuContainer;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.EventListener;
import java.util.List;
import java.util.Observable;

import javax.accessibility.Accessible;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tp.pr4.RobotEngine;
import tp.pr4.RobotEngineObserver;
import tp.pr4.items.InventoryObserver;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase de la interfaz fráfica para el inventario del robot, su fuel y su material reciclado.
 */
public class RobotPanel extends JPanel implements RobotEngineObserver, InventoryObserver {

	private static final long serialVersionUID = 1L;
	private JLabel fuelRecycled;
	private JTable inventory;
	private RobotEngine theRobot;
	
	/**
	 * Constructora por defecto
	 */
	public RobotPanel(){
		this.init();
		this.theRobot = new RobotEngine();
	}
	
	/**
	 * Constructora por defecto
	 */
	public RobotPanel(RobotEngine robot){
		this.theRobot = robot;
		this.init();
	}
	

	
	/**
	 * Método privado que inicializa el RobotPanel
	 */
	private void init(){
		//Creación
		this.fuelRecycled = new JLabel();
		InventoryTable table_model = new InventoryTable( this.theRobot.getInventory());
		this.inventory = new JTable(table_model);
		
		//Inicialización
		this.inventory.setRowSelectionAllowed(true);
		this.inventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.inventory.setName("jTableInventory");
		this.setBorder(new SoftBevelBorder(1));		

		//estética
		this.setLayout(new BorderLayout());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		JTableHeader header = this.inventory.getTableHeader();
		header.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		header.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		header.setPreferredSize(new Dimension(5,5));
		header.revalidate();
		this.inventory.setTableHeader(header);
		this.inventory.getColumnModel().getColumn(0).setHeaderValue("Id");
		this.inventory.getColumnModel().getColumn(1).setHeaderValue("Description");
		
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment( JLabel.LEFT);
		this.inventory.getColumnModel().getColumn(0).setCellRenderer( leftRenderer );
		this.inventory.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
		this.inventory.getTableHeader().setPreferredSize(new Dimension(20, 15));
		this.inventory.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JPanel robotInfo = new JPanel();
		JScrollPane pane = new JScrollPane();
		robotInfo.setBorder(new TitledBorder("Robot info"));
		robotInfo.setLayout(new GridLayout(2,1));
		this.fuelRecycled.setHorizontalAlignment(JLabel.CENTER);
		
		//añadidos
		pane.add(this.inventory);
		pane.setViewportView(this.inventory);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(this.inventory.getSize());
		robotInfo.add(this.fuelRecycled);
		robotInfo.add(pane);
		this.add(robotInfo);
	}

	/**
	 * Método que actualiza el RobotPanel
	 */
	public void update(Observable o, Object arg) {
		RobotEngine modelo = (RobotEngine) o;
		this.fuelRecycled.setText("Fuel: " + modelo.fuelToString() + " Recycled: " + modelo.recycledToString());
		if(arg != null) this.updateTable( (ItemContainer) arg );
	}
	
	/**
	 * @return el valor almacenado en la celda seleccionada de la tabla
	 */
	public String getSelectedID(){
		String aux = "";
		int col = this.inventory.getSelectedColumn();  
		int row = this.inventory.getSelectedRow(); 
		if(this.inventory.getRowCount() > row)
			aux = (String) (this.inventory.getModel().getValueAt(row, col));
		return aux;
	}

	/**
	 * Método que actualiza la tabla
	 */
	public void updateTable(ItemContainer items){
		int i = 0;
		while(items.hasIndex(i)){
			this.inventory.setValueAt(items.getId(i), i, 0);
			this.inventory.setValueAt(items.getDescription(i), i, 1);
			i++;
		}
		this.inventory.updateUI();
	}


	public void updateFuelRecycled(int fuel, int material) {
		this.fuelRecycled.setText("Fuel: " + fuel + " Recycled: " + material);
		
	}

	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		
	}

}
