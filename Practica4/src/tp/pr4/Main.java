package tp.pr4;

import java.io.FileInputStream;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.InputStream;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.gui.MainWindow;

/**
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 *
 */
public class Main {

	/**
	 * Creates the city, the engine and finally
	 * starts the simulation
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/*
		int i = 0;
		while(i < args.length){
			System.out.print(args[i] + Interpreter.LINE_SEPARATOR);
			System.err.print(args[i] + Interpreter.LINE_SEPARATOR);
			i++;
		}
		*/
		CommandLineParser parser = new GnuParser();
		Options options = new Options();
		options.addOption( OptionBuilder.withLongOpt( "help" )
										.withDescription( "Shows this help message" )
						                .create("h") );

		options.addOption( OptionBuilder.withLongOpt( "interface" )
										.withDescription( "The type of interface: console or swing" )
						                .hasArg()
						                .withArgName("type")
						                .create("i") );
		
		options.addOption( OptionBuilder.withLongOpt( "map" )
										.withDescription( "File with the description of the city" )
						                .hasArg()
						                .withArgName("mapfile")
						                .create("m") );
		try {
			CommandLine cmd = parser.parse( options, args);
			if(cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				System.out.println("Execute this assignment with these parameters:");
				formatter.printHelp( "tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]", options );
			}
			else if(cmd.hasOption("i") && cmd.hasOption("m") && ( cmd.getOptionValue("i").equals("console") || cmd.getOptionValue("i").equals("swing") ) ){
		    	try{
					InputStream file = new FileInputStream(cmd.getOptionValue("m"));
					CityLoaderFromTxtFile map = new CityLoaderFromTxtFile();
					RobotEngine engine = new RobotEngine(map.loadCity(file), map.getInitialPlace(),Direction.NORTH);
					if(cmd.getOptionValue("i").equals("console")) {
						engine.startEngine();
					}
					else {
						MainWindow main = new MainWindow(engine);
						engine.setGUIWindow(main);
						engine.setNavigationPanel(main.getNavigationPanel());
						engine.setRobotPanel(main.getRobotPanel());
						engine.startEngine();
					}
					System.exit(0);
				}
				catch(IOException e){
					Main.badParams();
					System.exit(2);
				}   
			}
			else {
				Main.switchError(cmd);
				System.exit(1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private static void switchError(CommandLine cmd){
		if(cmd.getOptionValue("m") == null)
			System.err.print("Map file not specified" + Interpreter.LINE_SEPARATOR);
		else if(cmd.getOptionValue("i") == null)
			System.err.print("Interface not specified" + Interpreter.LINE_SEPARATOR);
		else if(!( cmd.getOptionValue("i").equals("console") || cmd.getOptionValue("i").equals("swing") ) )
			System.err.print("Wrong type of interface" + Interpreter.LINE_SEPARATOR);
	}
	
	private static void noParams(){
		System.err.print(("Bad params.Usage: java tp.pr3.Main <mapfile><mapfile> : file with the description of the city.") + Interpreter.LINE_SEPARATOR);
	}
	
	private static void badParams(){
		System.err.print("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)" + Interpreter.LINE_SEPARATOR);
	}
}
