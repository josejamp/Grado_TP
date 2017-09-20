package tp.pr4.cityLoader.cityLoaderExceptions;

import java.io.IOException;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Excepcion para los archivos que contengan ciudades con formato incorrecto
 */
public class WrongCityFormatException extends IOException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongCityFormatException() {
		// TODO Auto-generated constructor stub
	}

	public WrongCityFormatException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongCityFormatException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongCityFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
