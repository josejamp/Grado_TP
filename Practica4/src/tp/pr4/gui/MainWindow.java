package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Inherited;
import java.util.EventObject;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;

import tp.pr4.RobotEngine;
import tp.pr4.instructions.*;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase con toda la ventana de la interfaz gráfica.
 */
public class MainWindow implements ActionListener{

	private final String LABEL_MAIN_TITLE = "WALL·E The garbage collector";
	
	
	private JFrame main;
	private NavigationPanel navigation;
	private RobotPanel robotPanel;
	private JPanel thePanel;
	private Menu file;
	private InstructionsPanel instructions;
	private JSplitPane splitPanel;
	
	private RobotEngine theRobot;
	
	/**
	 * Constructora por defecto
	 */
	public MainWindow(){
		this.init();
	}
	
	/**
	 * Constructora
	 * @param robot el RobotEngine que recibe las instrucciones 
	 */
	public MainWindow(RobotEngine robot){
		this.theRobot = robot;
		this.init();	
	}

	/**
	 * Método privado que inicializa la ventana
	 */
	private void init(){
		//creacion
		this.main = new JFrame();
		this.navigation = new NavigationPanel(this.theRobot.getNavigationMod());
		this.robotPanel = new RobotPanel(this.theRobot);
		this.thePanel = new JPanel();
		this.file = new Menu();
		this.instructions = new InstructionsPanel(this.theRobot);
		this.splitPanel = new JSplitPane();

		//inicialización
		this.main.setTitle(LABEL_MAIN_TITLE);
		this.splitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.thePanel.setBorder(new SoftBevelBorder(1));
		
		
		//estética
		this.main.setLayout(new BorderLayout());
		this.main.setSize(800, 600);
		
		this.thePanel.setLayout(new BorderLayout());
				
		//Añadidos
		
		this.thePanel.add(this.instructions, BorderLayout.CENTER);
		
		this.splitPanel.setDividerLocation(270);
		this.splitPanel.setLeftComponent(this.thePanel);
		this.splitPanel.setRightComponent(this.robotPanel);
		
		
		this.main.add(splitPanel, BorderLayout.NORTH);
		this.main.add(this.navigation, BorderLayout.CENTER);
		this.main.setJMenuBar(this.file);
		
		this.main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.main.setVisible(true);
		
		this.instructions.fijarControlador(this);
		this.navigation.fijarControlador(this);
	}
	
	/**
	 * 
	 * @return el NavigationPanel de MainWindow
	 */
	public NavigationPanel getNavigationPanel(){
		return this.navigation;
	}
	
	/**
	 * 
	 * @return el RobotPanel de MainWindoq
	 */
	public RobotPanel getRobotPanel(){
		return this.robotPanel;
	}
	
	
	/**
	 * Método privado que modifica la ventana de acuerdo a los eventos que tienen lugar sobre ella
	 * @param fuente el componente que produce el cambio
	 */
	private void cambiarModelo (Component fuente) {
		switch(fuente.getName()) {
			case("jButtonMove"):{
				MoveInstruction move = new MoveInstruction();
				move.configureContext(this.theRobot, this.theRobot.getNavigationMod(), this.theRobot.getInventory());
				this.theRobot.comunicateRobot(move);
				this.update();			
			} break;
			case("jButtonTurn"):{
				TurnInstruction turn = new TurnInstruction();
				turn.setRotation(this.instructions.getRotation());
				turn.configureContext(this.theRobot, this.theRobot.getNavigationMod(), this.theRobot.getInventory());
				this.theRobot.comunicateRobot(turn);
				this.update();
			} break;
			case("jButtonPick"):{
				PickInstruction pick = new PickInstruction();
				pick.setID(this.instructions.getItemID());
				pick.configureContext(this.theRobot, this.theRobot.getNavigationMod(), this.theRobot.getInventory());
				this.theRobot.comunicateRobot(pick);
				this.update();
			} break;
			case("jButtonDrop"):{
				DropInstruction drop = new DropInstruction();
				drop.setID(this.robotPanel.getSelectedID());
				drop.configureContext(this.theRobot, this.theRobot.getNavigationMod(), this.theRobot.getInventory());
				this.theRobot.comunicateRobot(drop);
				this.update();
			} break;
			case("jButtonQuit"):{
				QuitInstruction quit = new QuitInstruction();
				quit.configureContext(this.theRobot, this.theRobot.getNavigationMod(), this.theRobot.getInventory());
				this.theRobot.comunicateRobot(quit);
				//this.main.dispose();
				//this.update();
			} break;
			case("jButtonOperate"):{
				OperateInstruction operate = new OperateInstruction();
				operate.setID(this.robotPanel.getSelectedID());
				operate.configureContext(this.theRobot, this.theRobot.getNavigationMod(), this.theRobot.getInventory());
				this.theRobot.comunicateRobot(operate);
				this.update();
			} break;
			case("jPlaceCell"):{
				PlaceCell cell = (PlaceCell) fuente;
				if(cell.getVisitado()){
					this.navigation.update(cell.getPlace());
				}
			} break;
		}
		
	}
	
	/**
	 * Método que trata los eventos ocurridos sobre la ventana
	 * @param event el evento ocurrido
	 */
	private void trataEventoGenerico(EventObject event){
        Component fuente = (Component) event.getSource(); // el que generó el evento
		//System.err.println(fuente.getName());
        this.cambiarModelo(fuente);
	}
	

	public void stateChanged(ChangeEvent arg0) {
		//System.err.print("stateChanged: ");
		this.trataEventoGenerico(arg0);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//System.err.print("actionPerformed: ");
        this.trataEventoGenerico(ae);
	}

	/**
	 * Método que actualiza la ventana
	 */
	public void update() {		
		this.navigation.update();
		this.robotPanel.update();
		
	}
	
	/**
	 * Método que muestra un mensaje en una ventana aparte
	 * @param message mensaje a mostrar
	 */
	public void showMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Método que cierra la ventana
	 */
	public void disposeMainWindow(){
		this.main.dispose();
	}
	
	/*public static void main(String[] args) {
		MainWindow w = new MainWindow();
		
		
	}*/
	
}
