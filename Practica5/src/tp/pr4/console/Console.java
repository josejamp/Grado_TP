package tp.pr4.console;

import java.util.List;

import tp.pr4.Direction;
import tp.pr4.NavigationObserver;
import tp.pr4.PlaceInfo;
import tp.pr4.RobotEngineObserver;
import tp.pr4.items.InventoryObserver;
import tp.pr4.items.Item;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

public class Console implements RobotEngineObserver, InventoryObserver,	NavigationObserver {
	
	/**
	 * Constructora por defecto
	 */
	public Console(){
		
	}

	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace) {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub

	}

}
