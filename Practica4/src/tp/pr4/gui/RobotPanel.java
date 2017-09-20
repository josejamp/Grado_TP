package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuContainer;
import java.awt.image.ImageObserver;
import java.io.Serializable;

import javax.accessibility.Accessible;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tp.pr4.RobotEngine;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase de la interfaz fráfica para el inventario del robot, su fuel y su material reciclado.
 */
public class RobotPanel extends JPanel implements ImageObserver, MenuContainer, Serializable, Accessible{

	private static final long serialVersionUID = 1L;
	private RobotEngine robot;
	private JLabel fuelRecycled;
	private JTable inventory;
	
	/**
	 * Constructora por defecto
	 */
	public RobotPanel(){
		this.robot = new RobotEngine();
		this.init();
	}
	
	/**
	 * Constructora
	 * @param robot el robot
	 */
	public RobotPanel(RobotEngine robot){
		this.robot = robot;
		this.init();
	}
	
	/**
	 * Método privado que inicializa el RobotPanel
	 */
	private void init(){
		//Creación
		this.fuelRecycled = new JLabel();
		InventoryTable table_model = new InventoryTable(this.robot.getInventory());
		this.inventory = new JTable(table_model);
		
		//Inicialización
		this.inventory.setRowSelectionAllowed(true);
		this.fuelRecycled.setText("Fuel: " + this.robot.getFuel() + " Recycled: " + this.robot.getRecycledMaterial());
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
		//this.inventory.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		//this.inventory.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		this.inventory.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
		JPanel robotInfo = new JPanel();
		robotInfo.setBorder(new TitledBorder("Robot info"));
		robotInfo.setLayout(new GridLayout(3,1));
		this.fuelRecycled.setHorizontalAlignment(JLabel.CENTER);
		
		//añadidos
		robotInfo.add(this.fuelRecycled);
		robotInfo.add(this.inventory.getTableHeader(), BorderLayout.NORTH);
		robotInfo.add(this.inventory, BorderLayout.CENTER);
		this.add(robotInfo);
	}

	/**
	 * Método que actualiza el RobotPanel
	 */
	public void update() {
		this.fuelRecycled.setText("Fuel: " + this.robot.getFuel() + " Recycled: " + this.robot.getRecycledMaterial());
		this.updateTable();
	}
	
	/**
	 * @return el valor almacenado en la celda seleccionada de la tabla
	 */
	public String getSelectedID(){
		int col = this.inventory.getSelectedColumn();  
		int row = this.inventory.getSelectedRow();  
		return (String) (this.inventory.getModel().getValueAt(row, col));
	}
	
	/**
	 * Método que actualiza la tabla
	 */
	private void updateTable(){
		int i = 0;
		while(this.robot.getInventory().hasIndex(i)){
			this.inventory.setValueAt(this.robot.getInventory().getId(i), i, 0);
			this.inventory.setValueAt(this.robot.getInventory().getDescription(i), i, 1);
			i++;
		}
		this.inventory.updateUI();
	}

}
