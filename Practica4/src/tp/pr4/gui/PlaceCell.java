package tp.pr4.gui;

import java.awt.Color;
import java.awt.ItemSelectable;
import java.awt.MenuContainer;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.EventListener;

import javax.accessibility.Accessible;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import tp.pr4.Place;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase para la interfaz gráfica que representa los lugares del mapa.
 */
public class PlaceCell extends JButton implements ImageObserver, ItemSelectable, MenuContainer, Serializable, Accessible, SwingConstants{


	private static final long serialVersionUID = 1L;
	
	private Place thePlace;
	private boolean visitado;
	private boolean actual;
	
	/**
	 * Constructora por defecto
	 */
	public PlaceCell(){
		this.thePlace = null;
		this.visitado = false;
		this.actual = false;
		this.initialize();
	}
	
	/**
	 * Constructora
	 * @param place lugar al que representa el PlaceCell
	 */
	public PlaceCell(Place place){
		super();
		this.thePlace = place;
		this.visitado = false;
		this.actual = false;
		this.initialize();
	}
	
	/**
	 * 
	 * @return el lugar al que representa el PlaceCell
	 */
	public Place getPlace(){
		return this.thePlace;
	}
	
	/**
	 * 
	 * @return true si el lugar ha sido visitado, false en caso contrario
	 */
	public boolean getVisitado(){
		return this.visitado;
	}
	
	/**
	 * 
	 * @return true si es el lugar en el que se encuentra el robot, false en caso contrario
	 */
	public boolean getActual(){
		return this.actual;
	}
	
	/**
	 * Método que establece si el lugar ha sido visitado o no
	 * @param visit true para que el lugar haya sido visitado, false en caso contrario
	 */
	public void setVisitado(boolean visit){
		this.visitado = visit;
	}
	
	/**
	 * Método que estable si es el lugar en el que se cuentra el robot
	 * @param actual true para que sea el lugar actual, false en caso contrario
	 */
	public void setActual(boolean actual){
		this.actual = actual;
	}
	
	/**
	 * Método privado que inicializa el PlaceCell
	 */
	private void initialize(){
		this.setText("");
		this.setName("jPlaceCell");
		//this.setName("jPlaceCell"+ this.thePlace.getName());		
	}
	
	/**
	 * Método que actualiza el PlaceCell según si el lugar al que representa ha sido visitado o es el actual
	 */
	public void update(){
		if(this.visitado){
			this.setText(this.thePlace.getName());
			this.setBackground(Color.GRAY);
		}
		if(this.actual){
			this.setBackground(Color.GREEN);
		}	
	}
	
	/**
	 * Método que fija el controlador del PlaceCell
	 * @param controlador
	 */
	 public void fijarControlador(EventListener controlador) {
		 this.addActionListener((ActionListener)controlador);
	 }
	

}
