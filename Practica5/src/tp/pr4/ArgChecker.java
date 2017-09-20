package tp.pr4;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/*
* @author José Javier Martínez Pagés
* @author Cristina Valentina Espinosa Victoria
*/

/**
 * Clase encargada de comprobar que los argumentos de inicio del programa sean correctos
 *
 */
public class ArgChecker {
	
	private String args[];
	private CommandLine cmd;
	private Options options;
	
	/**
	 * constructor por defecto, inicializa args, cmd y options a null
	 */
	public ArgChecker(){
		this.args = null;
		this.cmd = null;
		this.options = null;
	}
	
	/**
	 * constructor de la clase
	 * @param args argumentos de inicio del programa
	 */
	public ArgChecker(String[] args){
		this.args = args;
		this.parse();
	}
	
	
	/**
	 * Parsea los argumentos e inicializa el resto de atributos privados de la clase
	 */
	@SuppressWarnings("static-access")
	private void parse(){
		CommandLineParser parser = new GnuParser();
		this.options = new Options();
		this.options.addOption( OptionBuilder.withLongOpt( "help" )
										.withDescription( "Shows this help message" )
						                .create("h") );

		this.options.addOption( OptionBuilder.withLongOpt( "interface" )
										.withDescription( "The type of interface: console or swing" )
						                .hasArg()
						                .withArgName("type")
						                .create("i") );
		
		this.options.addOption( OptionBuilder.withLongOpt( "map" )
										.withDescription( "File with the description of the city" )
						                .hasArg()
						                .withArgName("mapfile")
						                .create("m") );
		try {
			this.cmd = parser.parse( this.options, this.args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprueba la correccion de los argumentos
	 * @return true si son correctos, false si no lo son
	 */
	public boolean check(){
		return (this.cmd.hasOption("h") || 
				(this.cmd.hasOption("i") && this.cmd.hasOption("m") && 
				( this.cmd.getOptionValue("i").equals("console") || this.cmd.getOptionValue("i").equals("swing") )));
	}
	
	/**
	 * Distingue entre el motivo del error e informa al usuario mediante System.err()
	 */
	public void error(){
		if(this.cmd.getOptionValue("m") == null)
			System.err.print("Map file not specified" + Interpreter.LINE_SEPARATOR);
		else if(this.cmd.getOptionValue("i") == null)
			System.err.print("Interface not specified" + Interpreter.LINE_SEPARATOR);
		else if(!( this.cmd.getOptionValue("i").equals("console") || this.cmd.getOptionValue("i").equals("swing") ) )
			System.err.print("Wrong type of interface" + Interpreter.LINE_SEPARATOR);
	}
	
	/**
	 * Comprueba si se ha pedido ayuda para los parametros
	 * @return true si se ha reconocido la opcion "h", false en caso contrario
	 */
	public boolean helpRequest(){
		return cmd.hasOption("h");
	}
	
	/**
	 * Comprueba si se ha pedido iniciar la aplicacion en modo consola
	 * @return true si el valor de la opcion "i" es "console", false en caso contrario
	 */
	public boolean consoleRequest(){
		return cmd.getOptionValue("i").equals("console");
	}
	
	/**
	 * Mustra la ayuda
	 */
	public void showHelp(){
		HelpFormatter formatter = new HelpFormatter();
		System.out.println("Execute this assignment with these parameters:");
		formatter.printHelp( "tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]", this.options );
	}
	
	/**
	 * Devuelve el valor asociado al nombre del mapa
	 * @return string con el nombre del archivo del mapa
	 */
	public String getMapValue(){
		return this.cmd.getOptionValue("m");
	}

}
