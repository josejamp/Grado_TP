package tp.pr4.items;

import tp.pr4.*;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase que representa las tarjetas que usa WALL·E para abrir calles cerradas.
 */
public class CodeCard extends Item implements Comparable<Item>{

	private String code;
	
	/**
	 * constructora
	 * @param id nombre
	 * @param description descripción
	 * @param code código de la tarjeta
	 */
	public CodeCard(String id, String description, String code) {
		super(id, description);
		this.code = code;
	}

	/**
	 * @return true, siempre puede ser usada
	 */
	public boolean canBeUsed() {
		return true;
	}

	/**
	 * intenta utilizar la tarjeta
	 * @param r WALL·E
	 * @return true si ha podido usarla en la puerta correspondiente
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean ok = false;
		Street street = nav.getHeadingStreet();
		if(street != null){
			if(street.isOpen()) {
				ok = street.close(this);
			}
			else{
				if(!street.isOpen()){
					ok = street.open(this);
				}
				else ok = false;
			}
		}
		else ok = false;
		return ok;
	}
	
	/**
	 * 
	 * @return el codigo de la tarjeta
	 */
	public String getCode(){
		return this.code;
	}
	
	@Override
	public CodeCard clone(){
		return new CodeCard(this.id, this.description, this.code);
	}
	
	
}
