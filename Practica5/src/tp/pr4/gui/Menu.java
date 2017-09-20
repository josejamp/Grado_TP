package tp.pr4.gui;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase de la interfaz gráfica para el menú superior izquierda
 */
public class Menu extends JMenuBar {
	
	private JMenu file;
	private JMenuItem quit;
	
	/**
	 * Constructora por defecto
	 */
	public Menu(){
		super();
		this.init();
	}
	
	/**
	 * Método privado que inicializa el menú
	 */
	private void init(){
		//Creación
		this.file = new JMenu();
		this.quit = new JMenuItem();
		
		//Inicialización
		this.file.setText("File");
		this.quit.setText("Quit");
		this.quit.setName("jMenuItemQuit");
				
		//Añadir
		this.file.add(this.quit);
		this.add(this.file);
	}
	
	/**
	 * Método que fija el controlador del Menu
	 * @param controlador
	 */
	 public void fijarControlador(EventListener controlador) {
		 this.quit.addActionListener((ActionListener)controlador);
	 }

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
