package tp.pr3.items;

import tp.pr3.*;

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
	
	public boolean equals(Item item){
		return ((super.equals(item)) &&
				(this.code.equals(((CodeCard)item).code)));
	}
	
	public int compareTo(Item item){
		return super.compareTo(item);
	}
	
}
