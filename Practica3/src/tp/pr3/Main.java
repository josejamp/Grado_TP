package tp.pr3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import tp.pr3.cityLoader.CityLoaderFromTxtFile;

public class Main {

	/**
	 * Creates the city, the engine and finally
	 * starts the simulation
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 0){
			Main.noParams();
			System.exit(1);
		}
		else{
			try{
			InputStream file = new FileInputStream(args[0]);
			CityLoaderFromTxtFile map = new CityLoaderFromTxtFile();
			RobotEngine engine = new RobotEngine(map.loadCity(file), map.getInitialPlace(),Direction.NORTH);
			engine.startEngine(); // plays
			System.exit(0);
			}
			catch(IOException e){
				Main.badParams();
				System.exit(2);
			}
		}
	}
	
	private static void noParams(){
		System.err.print(("Bad params.Usage: java tp.pr3.Main <mapfile><mapfile> : file with the description of the city.") + Interpreter.LINE_SEPARATOR);
	}
	
	private static void badParams(){
		System.err.print("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)" + Interpreter.LINE_SEPARATOR);
	}
}
