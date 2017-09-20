package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Inherited;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;

import tp.pr4.Direction;
import tp.pr4.NavigationObserver;
import tp.pr4.Place;
import tp.pr4.RobotEngine;
import tp.pr4.RobotEngineObserver;
import tp.pr4.Rotation;
import tp.pr4.controlador.Controller;
import tp.pr4.controlador.GUIController;
import tp.pr4.instructions.*;
import tp.pr4.items.InventoryObserver;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase con toda la ventana de la interfaz gráfica.
 */
public class MainWindow implements RobotEngineObserver{

	private final String LABEL_MAIN_TITLE = "WALL·E The garbage collector";
	
	
	private JFrame main;
	private NavigationPanel navigation;
	private RobotPanel robotPanel;
	private JPanel thePanel;
	private Menu file;
	private InstructionsPanel instructions;
	private JSplitPane splitPanel;
	private TopDownLabel theTopDwonLabel;
	
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
	public MainWindow(RobotEngine robot, GUIController controlador){
		this.theRobot = robot;
		this.init();
		this.fijarControlador(controlador);
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
		this.theTopDwonLabel = new TopDownLabel();

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
		this.main.add(this.theTopDwonLabel, BorderLayout.SOUTH);
		this.main.setJMenuBar(this.file);
		
		this.main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.main.setVisible(true);
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
	 * @return el RobotPanel de MainWindow
	 */
	public RobotPanel getRobotPanel(){
		return this.robotPanel;
	}
	
	public InstructionsPanel getInstructionPanel(){
		return this.instructions;
	}

	/**
	 * Método que actualiza la ventana
	 */
	public void update(Observable arg0, Object arg1) {
		System.out.println("EOOOOOO");
		this.navigation.update(arg0, arg1);
		this.robotPanel.update(arg0, arg1);	
	}
	
	/**
	 * Método que muestra un mensaje en una ventana aparte
	 * @param message mensaje a mostrar
	 */
	public static void showMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Método que cierra la ventana
	 */
	public void disposeMainWindow(){
		this.main.dispose();
	}
	
	/**
	 * Método qu ejecuta la instruccion quit
	 */
	private void quit(){
		this.theRobot.tryInstruction("QUIT");
	}

	
	public void fijarControlador(EventListener controlador) {
		this.instructions.fijarControlador(controlador);
		this.navigation.fijarControlador(controlador);
		this.file.fijarControlador(controlador);
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
