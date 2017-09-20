package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.MenuContainer;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.Observable;

import javax.accessibility.Accessible;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.City;
import tp.pr4.Direction;
import tp.pr4.NavigationModule;
import tp.pr4.NavigationObserver;
import tp.pr4.Place;
import tp.pr4.PlaceInfo;

/*
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 */

/**
 * 
 * Clase de la interfaz gráfica para el menú con el mapa, la ilustración de WALLE y las descripciones
 */
public class NavigationPanel extends JPanel implements NavigationObserver{

	private static final long serialVersionUID = 1L;

	private final int INIT_PLACE = 60;
	
	private NavigationModule theNav;
	private ArrayList<PlaceCell> matriz;
	private JLabel robotHeading;
	private JTextArea description;
	private int actualPlace;
	private boolean actualizar;
	
	
	/**
	 * Constructora por defecto
	 */
	public NavigationPanel(){
		this.theNav = new NavigationModule();
		this.actualPlace = this.INIT_PLACE;
		this.actualizar = false;
		this.init();
	}
	
	/**
	 * Constructora
	 * @param nav el módulo de navegación
	 */
	public NavigationPanel(NavigationModule nav){
		this.theNav = nav;
		this.actualPlace = this.INIT_PLACE;
		this.actualizar = false;
		this.init();
	}
	
	/**
	 * Método privado que inicializa en NavigationPanel
	 */
	private void init(){
		//creacion
		this.matriz = new ArrayList<PlaceCell>();
		this.robotHeading = new JLabel();
		this.description = new JTextArea();
		
		//inicialización
		this.description.setText("");
		this.description.setRows(6);
		this.description.setColumns(5);	
		this.description.setEditable(false);
		this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleNorth.png"));
		
		//estética
		this.setLayout(new BorderLayout());		
		JPanel city = new JPanel (new GridLayout(11,11));
		city.setBorder(new TitledBorder("City map"));
		
		JPanel log = new JPanel();
		log.setLayout(new BorderLayout());
		log.setBorder(new TitledBorder("Log"));
		
		JScrollPane scrollPane = new JScrollPane(this.description);	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Añadidos
		for(int i = 0; i<121; i++) {
			this.matriz.add(new PlaceCell());
		}	
		
	//	this.initCell(this.theNav.getCurrentPlace(), this.theNav.getCity(), this.INIT_PLACE);
		
		for(int i = 0; i<121; i++) {
			city.add(this.matriz.get(i));
		}
		
		this.description.revalidate();
		log.add(scrollPane, BorderLayout.CENTER);
		this.add(this.robotHeading, BorderLayout.WEST);
		this.add(city, BorderLayout.CENTER);
		this.add(log, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Método privado que inicializa el array de PlaceCell almacenando cada lugar en su posición correspondiente
	 * @param p lugar inicial
	 * @param city ciudad por la que se mueve el robot
	 * @param pos posición inicial
	 */
	private void initCell(Place p, City city, int pos){
		if((this.matriz.get(pos).getPlace() == null)){//Si el lugar no se ha almacenado ya
			PlaceCell auxCell = new PlaceCell(p);
			this.matriz.set(pos, auxCell);
		
			if(city.lookForStreet(p, Direction.NORTH ) != null){
				this.initCell(city.lookForStreet(p, Direction.NORTH).nextPlace(p), city, pos-11);
			}
			if(city.lookForStreet(p, Direction.SOUTH) != null){
				this.initCell(city.lookForStreet(p, Direction.SOUTH).nextPlace(p), city, pos+11);
			}
			if(city.lookForStreet(p, Direction.WEST) != null){
				this.initCell(city.lookForStreet(p, Direction.WEST).nextPlace(p), city, pos-1);
			}
			if(city.lookForStreet(p, Direction.EAST) != null ){
				this.initCell(city.lookForStreet(p, Direction.EAST).nextPlace(p), city, pos+1);
			}
			
		}
		return;
	}
	
	/**
	 * Método que actualiza el NavigationPanel
	 */
	public void update(Observable o, Object arg){
		Place newPlace = this.theNav.getCurrentPlace();
		System.out.println("HOLAA");
		//Actualiza la dirección a la que mira el robot
		if(this.theNav.getCurrentHeading() == Direction.NORTH){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleNorth.png"));
			if(this.actualizar){
				this.updateOld();
				this.actualPlace -= 11;
			}
		}
		else if(this.theNav.getCurrentHeading() == Direction.SOUTH){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleSouth.png"));
			if(this. actualizar){
				this.updateOld();
				this.actualPlace += 11;
			}
		}
		else if(this.theNav.getCurrentHeading() == Direction.EAST){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleEast.png"));
			if(this.actualizar){
				this.updateOld();
				this.actualPlace++;
			}
		}
		else if(this.theNav.getCurrentHeading() == Direction.WEST){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleWest.png"));
			if(this.actualizar){
				this.updateOld();
				this.actualPlace--;
			}
		}
		
		//Actualiza la descripción del lugar que se muestra
		this.description.setText(newPlace.toString());
		
		//Actualiza el estado del lugar actual 
		//this.actualPlace = this.searchPlace(newPlace);
		this.matriz.get(this.actualPlace).setPlace(newPlace);
		this.matriz.get(this.actualPlace).setVisitado(true);		
		this.matriz.get(this.actualPlace).setActual(true);
		this.matriz.get(this.actualPlace).update(null, null);
		this.setActualizar(false);
		
		this.update(newPlace);
	}
	
	/**
	 * Dado un lugar, este método actualiza la descripción mostrada
	 * @param place lugar cuya descripción se muestra
	 */
	public void update(Place place) {
		this.description.setText(this.theNav.getCurrentPlace().toString());		
	}
	
	public void setActualizar(boolean actualizar){
		this.actualizar = actualizar;
	}
	
	/**
	 * Método privado que busca un lugar dentro del array de PlaceCell
	 * @param place lugar a buscar
	 * @return la posición del array donde se encuentra el lugar buscado
	 */
	private int searchPlace(Place place){
		int i = 0;
		boolean enc = false;
		Iterator<PlaceCell> it = this.matriz.iterator();
		while(it.hasNext() && !enc){
			PlaceCell p = it.next();
			if((p.getPlace() == null) || !p.getPlace().equals(place)) i++;
			else enc = true;
		}
		if(!enc) i = -1;
		return i;
	}
	
	/**
	 * Actualiza el lugar anterior
	 */
	private void updateOld(){
		this.matriz.get(this.actualPlace).setActual(false);
		this.matriz.get(this.actualPlace).update(null, null);
	}

	/**
	 * Método que fija el controlador en los componentes del NavigationPanel que lo necesiten
	 * @param mainWindow el controlador
	 */
	public void fijarControlador(EventListener mainWindow) {
		for(int i = 0; i<121; i++) {
			this.matriz.get(i).fijarControlador(mainWindow);
		}
	}

	public void updateDirection(Direction dir) {
		if(dir == Direction.NORTH){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleNorth.png"));
		}
		else if(dir == Direction.SOUTH){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleSouth.png"));
		}
		else if(dir  == Direction.EAST){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleEast.png"));
		}
		else if(dir == Direction.WEST){
			this.robotHeading.setIcon(new ImageIcon("src/tp/pr4/gui/images/walleWest.png"));
		}
		
	}

	public void updateCurrentPlace(Place p, Direction dir) {
		if(dir == Direction.NORTH){
			if(this.actualizar){
				this.updateOld();
				this.actualPlace -= 11;
			}
		}
		else if(dir == Direction.SOUTH){
			if(this. actualizar){
				this.updateOld();
				this.actualPlace += 11;
			}
		}
		else if(dir == Direction.EAST){
			if(this.actualizar){
				this.updateOld();
				this.actualPlace++;
			}
		}
		else if(dir == Direction.WEST){
			if(this.actualizar){
				this.updateOld();
				this.actualPlace--;
			}
		}
		
		this.matriz.get(this.actualPlace).setPlace(p);
		this.matriz.get(this.actualPlace).setVisitado(true);		
		this.matriz.get(this.actualPlace).setActual(true);
		this.matriz.get(this.actualPlace).update(null, null);
		this.setActualizar(false);
		this.updateDescription(p);
		
	}

	public void updateDescription(PlaceInfo place) {
		this.description.setText(place.toString());		
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

	
	
}
