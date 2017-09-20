package tp.pr1;

public class Street {
	
	private Place source; //lugar de salida
	private Direction direction; //direccion que conecta ambos lugares
	private Place target; //lugar de destino
	 
	/**
	 * @param source lugar de salida
	 * @param direction direccion que va de source a target
	 * @param target lugar de destino
	 */
	public Street(Place source, Direction direction, Place target){
		this.source = source;
		this.direction = direction;
		this.target = target;	
	}
	
	
	/**
	 * @param place lugar actual
	 * @param whichDirection direccion que sigue WALL-E para ir al lugar siguiente
	 * @return devuelve true si la calle conecta place con source o target en la direccion correspondiente
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){ 
		boolean enc = false;
		if ((this.source == place) && (this.direction == whichDirection)) enc = true; //si el lugar dado es el de salida y existe calle con la direccion de salida
		else {
			if (this.target == place){ //si el lugar dado es de destino
				if ((this.direction == Direction.NORTH)&& (whichDirection == Direction.SOUTH)) enc = true; //si las direcciones son opuestas en todos los casos
				else {
					if ((this.direction == Direction.SOUTH) && (whichDirection == Direction.NORTH)) enc = true;
					else{
						if ((this.direction == Direction.EAST) && (whichDirection == Direction.WEST)) enc = true;
						else{
							if ((this.direction == Direction.WEST) && (whichDirection == Direction.EAST)) enc = true;
						}
					}
				}
			}
		}	
		return enc;
	}
	
	
	/**
	 * @param whereAmI lugar actual
	 * @return devuelve el lugar al final de la calle que sale del lugar en el que estamos en la direccion this.direction
	 */
	public Place nextPlace(Place whereAmI){ 
		if (this.source == whereAmI) return target;
		else {
			if (this.target == whereAmI) return source;
			else return null;
		}
	}
	

}
