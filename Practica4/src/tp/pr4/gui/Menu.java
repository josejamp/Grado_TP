package tp.pr4.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * 
 *@author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase de la interfaz gráfica para el menú superior izquierda
 */
public class Menu extends JMenuBar{
	
	private JMenu file;
	
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
		
		//Inicialización
		this.file.setText("File");
				
		//Añadir
		this.add(this.file);
	}

}
