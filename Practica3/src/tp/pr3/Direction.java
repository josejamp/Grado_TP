package tp.pr3;

public enum Direction {
	NORTH, SOUTH, EAST, WEST, UNKNOWN;	
	
	/**
	 * 
	 * @param s String que recibe el metodo
	 * @return La direccion correspondiente al String de entrada
	 */
	public static Direction toDirection(String s){
		Direction dir = Direction.UNKNOWN;
		if(s.equalsIgnoreCase("SOUTH")) dir = Direction.SOUTH;
		else if(s.equalsIgnoreCase("NORTH")) dir = Direction.NORTH;
		else if(s.equalsIgnoreCase("EAST")) dir = Direction.EAST;
		else if(s.equalsIgnoreCase("WEST")) dir = Direction.WEST;
		return dir;
	}
	
	/**
	 * Dada una direccion y una rotacion, rota y devuelve una nueva direccion
	 * @param dir la direccion
	 * @param rot la rotacion
	 * @return nueva direccion
	 */
	public static Direction rotate(Direction dir, Rotation rot){
		Direction nueva;
		switch (dir){
		case NORTH:		
			if(rot.equals(Rotation.RIGHT)) nueva = Direction.EAST;
			else nueva = Direction.WEST;
			break;
			
		case SOUTH:
			if(rot.equals(Rotation.RIGHT)) nueva = Direction.WEST;
			else nueva = Direction.EAST;
			break;
			
		case EAST:
			if(rot.equals(Rotation.RIGHT)) nueva = Direction.SOUTH;
			else nueva = Direction.NORTH;
			break;
			
		case WEST:
			if(rot.equals(Rotation.RIGHT)) nueva = Direction.NORTH;
			else nueva = Direction.SOUTH;
			break;
			
		default:
			nueva = Direction.UNKNOWN;
			break;
		}
		return nueva;
	}
	
	
}
