package tp.pr1;

public class Place {
	
	private String name; //nombre del lugar
	private boolean isSpaceShip; //nos dira si es la nave para acabar el juego, true si es la nave
	private String description; //descripcion del lugar

	/**
	 * @param name nombre del lugar
	 * @param isSpaceShip true si es la nave de WALL-E, false en caso contrario
	 * @param description descripcion del lugar
	 */
	public Place(String name, boolean isSpaceShip, String description){
		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
	}
	
	/**
	 * @return devuelve true si es la nave
	 */
	public boolean isSpaceship(){ 
		return this.isSpaceShip;
	}
	
	/**
	 * Sobreescribe el metodo toString para mostrar el nombre del lugar y su descripcion
	 */
	public String toString(){ 
		return (this.name + Interpreter.LINE_SEPARATOR + this.description);
	}



	
	
	
	
	
	

}
