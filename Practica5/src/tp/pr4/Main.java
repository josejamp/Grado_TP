package tp.pr4;

import java.io.FileInputStream;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.InputStream;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.controlador.Controller;
import tp.pr4.controlador.GUIController;
import tp.pr4.gui.MainWindow;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * Clase main
 */
public class Main {

	/**
	 * Creates the city, the engine and finally
	 * starts the simulation
	 * @param args
	 */
	public static void main(String[] args) {
		ArgChecker checker = new  ArgChecker(args);
		if(checker.check()){
			if(checker.helpRequest()) {
				checker.showHelp();
			}
			else {
		    	try{
					InputStream file = new FileInputStream(checker.getMapValue());
					CityLoaderFromTxtFile map = new CityLoaderFromTxtFile();
					RobotEngine engine = new RobotEngine(map.loadCity(file), map.getInitialPlace(),Direction.NORTH);
					if(checker.consoleRequest()) {
						engine.startEngine();
					}
					else {
						GUIController controlador = new GUIController(engine, engine.getNavigationMod(), engine.getInventory());
						MainWindow main = new MainWindow(engine, controlador);
						controlador.setNavigationPanel(main.getNavigationPanel());
						controlador.setInstructionsPanel(main.getInstructionPanel());
						//engine.setGUIWindow(main);
						engine.setNavigationPanel(main.getNavigationPanel());
						//engine.addObserver(main);
						engine.startEngine();
					}
					System.exit(0);
				}
				catch(IOException e){
					Main.badParams();
					System.exit(2);
				}   
			}
		}
		else {
			checker.error();
			System.exit(1);
		}
	}
	
	/**
	 * Muestra el mensaje de error correspondiente a la inexistencia del archivo con el mapa
	 */
	private static void badParams(){
		System.err.print("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)" + Interpreter.LINE_SEPARATOR);
	}
}
