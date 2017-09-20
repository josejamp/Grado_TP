package tp.pr4.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.JTable;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.gui.InstructionsPanel;
import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.PlaceCell;
import tp.pr4.items.ItemContainer;

public class GUIController extends Controller  implements ActionListener,// para los JButton y los jTextField
														ListSelectionListener		{

	private String itemID;
	private Rotation rotation;
	private String tableItem;
	private NavigationPanel navigation;
	private InstructionsPanel instructions;
	
	/**
	 * Constructor por defecto, invoca al constructor por defecto de la superclase,
	 * los atributos privados string los inicializa a la cadena vacía,
	 * la rotacion a null
	 */
	public GUIController() {
		super();
		this.itemID = "";
		this.rotation = Rotation.LEFT;
		this.tableItem = "";
		this.navigation = new NavigationPanel();
	}
	
	/**
	 * Constructor de la clase, invoca al constructor de la superclase
	 * @param robot WALL-E
	 * @param nav El modulo de navegacion de WALL-E
	 * @param inventory El inventario de WALL-E
	 */
	public GUIController(RobotEngine robot, NavigationModule nav, ItemContainer inventory){
		super(robot, nav, inventory);
		this.itemID = "";
		this.rotation = Rotation.LEFT;
		this.tableItem = "";
		this.navigation = new NavigationPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.trataEventoGenerico(e);
	}
	
	public void setNavigationPanel(NavigationPanel nav){
		this.navigation = nav;
	}
	
	public void setInstructionsPanel(InstructionsPanel panel){
		this.instructions = panel;
	}
	
	
	/**
	 * Método privado que modifica la ventana de acuerdo a los eventos que tienen lugar sobre ella
	 * @param fuente el componente que produce el cambio
	 */
	private void cambiarModelo (Component fuente) {
		switch(fuente.getName()) {
			case("jButtonMove"):{
				this.modeloRobot.tryInstruction("MOVE");
			} break;
			case("jButtonTurn"):{
				this.modeloRobot.tryInstruction("Turn " + this.rotation.toString());
			} break;
			case("jButtonPick"):{
				this.modeloRobot.tryInstruction("Pick " + this.instructions.getItemID());
			} break;
			case("jButtonDrop"):{
				String aux = this.tableItem;
				if(aux != ""){
					this.modeloRobot.tryInstruction("Drop " + aux);
				}
				else  MainWindow.showMessage("You have not selected any item");
			} break;
			case("jButtonQuit"):{
				this.quit();
			} break;
			case("jButtonOperate"):{
				String aux = this.tableItem;
				if(aux != ""){
					this.modeloRobot.tryInstruction("Operate " + aux);
				}
				else  MainWindow.showMessage("You have not selected any item");
			} break;
			case("jPlaceCell"):{
				PlaceCell cell = (PlaceCell) fuente;
				if(cell.getVisitado()){
					this.navigation.updateDescription(cell.getPlace());
				}
			} break;
			case("jMenuItemQuit"):{
				this.quit();
			} break;
			case("jCombTurn"):{
				this.setRotation( Rotation.toRotation(  (String)((JComboBox<String>)fuente).getSelectedItem() ) );
			} break;
			case("jTableInventory"):{
				this.setTableItem( this.getSelectedID(fuente) );
			}
		}		
	}
	
	/**
	 * Set de itemID
	 * @param id el nuevo id
	 */
	public void setItemID(String id){
		this.itemID = id;
	}
	
	/**
	 * Set de la rotacion
	 * @param rot la nueva rotacion
	 */
	public void setRotation(Rotation rot){
		this.rotation = rot;
	}
	
	/**
	 * Set del id el item de la tabla de objetos
	 * @param id el nuevo id
	 */
	public void setTableItem(String id){
		this.tableItem = id;
	}
	
	/**
	 * @return el valor almacenado en la celda seleccionada de la tabla
	 */
	private String getSelectedID(Component fuente){
		JTable tabla_aux = ((JTable)fuente);
		String aux = "";
		int col = tabla_aux.getSelectedColumn();  
		int row = tabla_aux.getSelectedRow(); 
		if(tabla_aux.getRowCount() > row)
			aux = (String) (tabla_aux.getModel().getValueAt(row, col));
		return aux;
	}
	
	/**
	 * Método qu ejecuta la instruccion quit
	 */
	private void quit(){
		this.modeloRobot.tryInstruction("QUIT");
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		this.trataEventoGenerico(arg0);
	}
	
	/**
	 * Método para tratar los eventos de forma genérica. 
	 * Se encarga tanto de solicitar la modificación al modelo como de informar a la vista
	 * @param e el evento a tratar
	 */
	private void trataEventoGenerico(EventObject event){
        Component fuente = (Component) event.getSource(); // el que generó el evento
		System.err.println(fuente.getName());
        this.cambiarModelo(fuente);
	}
	

}
