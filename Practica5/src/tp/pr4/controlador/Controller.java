package tp.pr4.controlador;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.items.ItemContainer;

public abstract class Controller {
	
	protected RobotEngine modeloRobot;
	protected NavigationModule modeloNavigation;
	protected ItemContainer modeloRobotInventory;
	
	/**
	 * Constructor por defecto
	 */
	public Controller(){
		this.modeloRobot = null;
		this.modeloNavigation = null;
		this.modeloRobotInventory = null;
	}
	
	/**
	 * Constructor de la clase
	 * @param robot WALL-E
	 * @param nav El modulo de navegacion de WALL-E
	 * @param inventory El inventario de WALL-E
	 */
	public Controller(RobotEngine robot, NavigationModule nav, ItemContainer inventory){
		this.setModeloRobot(robot);
		this.setModeloNavigation(nav);
		this.setModeloRobotInventory(inventory);
	}
	
	/**
	 * Set de modeloRobot
	 * @param robot robot a fijar
	 */
	public void setModeloRobot(RobotEngine robot){
		this.modeloRobot = robot;
	}
	
	/**
	 * Set de modeloNavigation
	 * @param nav modulo de navegacion a fijar
	 */
	public void setModeloNavigation(NavigationModule nav){
		this.modeloNavigation = nav;
	}
	
	/**
	 * Set de modeloRobotInventory
	 * @param inventory inventario a fijar
	 */
	public void setModeloRobotInventory(ItemContainer inventory){
		this.modeloRobotInventory = inventory;
	}

}
