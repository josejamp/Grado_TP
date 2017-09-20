package tp.pr4;

import tp.pr4.items.*;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase que representa las calles que unen los lugares de la ciudad
 */
public class Street {

	private Place source; //lugar de salida
	private Direction direction; //direccion que conecta ambos lugares
	private Place target; //lugar de destino
	private boolean isOpen;
	private String code;
	 
	/**
	 * @param source lugar de salida
	 * @param direction direccion que va de source a target
	 * @param target lugar de destino
	 */
	public Street(Place source, Direction direction, Place target){
		this.source = source;
		this.direction = direction;
		this.target = target;	
		this.isOpen = true;
	}
	
	/**
	 * 
	 * @param source lugar de salida
	 * @param direction direccion que va de source a target
	 * @param target lugar de destino
	 * @param isOpen si la calle esta cerrada por una puerta
	 * @param code codigo con el que se puede abrir dicha puerta
	 */
	public Street(Place source, Direction direction, Place target, boolean isOpen, String code){
		this.source = source;
		this.direction = direction;
		this.target = target;	
		this.isOpen = isOpen;
		this.code = code;
	}
	
	/**
	 * @param place lugar actual
	 * @param whichDirection direccion que sigue WALL-E para ir al lugar siguiente
	 * @return devuelve true si la calle conecta place con source o target en la direccion correspondiente
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){ 
		return (((this.source == place) && (this.direction == whichDirection)) || //si el lugar dado es el de salida y existe calle con la direccion de salida
				(this.target == place) && //si el lugar dado es de destino
				(((this.direction == Direction.NORTH)&& (whichDirection == Direction.SOUTH)) || //si las direcciones son opuestas en todos los casos
				((this.direction == Direction.SOUTH) && (whichDirection == Direction.NORTH)) ||
				((this.direction == Direction.EAST) && (whichDirection == Direction.WEST)) ||
				((this.direction == Direction.WEST) && (whichDirection == Direction.EAST))));
	}
	
	
	/**
	 * @param whereAmI lugar actual
	 * @return devuelve el lugar al final de la calle que sale del lugar en el que estamos en la direccion this.direction
	 */
	public Place nextPlace(Place whereAmI){ 
		Place p = null;
		if (this.source == whereAmI) p = target;
		else if (this.target == whereAmI) p = source;
		return p;
	}
	
	/**
	 * 
	 * @return true si esta abierta, false en caso contrario
	 */
	public boolean isOpen(){
		return this.isOpen;
	}
	
	/**
	 * 
	 * @param card tarjeta con la que se intenta abrir la puerta
	 * @return true si el codigo coincide, false en caso contrario
	 */
	public boolean open(CodeCard card){
		//boolean abrir = card.getCode().equals(this.code);
		this.isOpen = card.getCode().equals(this.code);
		return this.isOpen;
	}
	
	/**
	 * 
	 * @param card tarjeta con la que se intenta cerrar la puerta
	 * @return true si el codigo coincide, false en caso contrario
	 */
	public boolean close(CodeCard card){
		//boolean cerrar = card.getCode().equals(this.code);
		this.isOpen = !card.getCode().equals(this.code);
		return !this.isOpen;
	}
	
	/**
	 * 
	 * @return el codigo de la tarjeta
	 */
	public String getCode(){
		return this.code;
	}
	
	@Override
	public boolean equals(Object obj){
		return (((this == obj)) ||
				((obj != null) && (this != null) && 
				(this.getClass() == obj.getClass()) && 
				this.comeOutFrom(((Street)obj).source, ((Street)obj).direction)));
	}
	
}
