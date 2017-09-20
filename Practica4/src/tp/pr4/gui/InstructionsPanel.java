package tp.pr4.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.RobotEngine;
import tp.pr4.Rotation;

/**
 * 
 * @author José Javier Martínez Pagés
 * @author Cristina Valentina Espinosa Victoria
 * 
 * Clase de la interfaz gráfica para el panel de botones con las instrucciones del robot.
 */
public class InstructionsPanel extends JPanel {
	
	private final String LABEL_RIGHT = "RIGHT";
	private final String LABEL_LEFT = "LEFT";
	private final String LABEL_MOVE = "MOVE";
	private final String LABEL_TURN = "TURN";
	private final String LABEL_PICK = "PICK";
	private final String LABEL_DROP = "DROP";
	private final String LABEL_QUIT = "QUIT";
	private final String LABEL_OPERATE = "OPERATE";
	private final String LABEL_INSTRUCTION_TITLE = "Instructions";
	
	private JButton move;
	private JButton turn;
	private JButton pick;
	private JButton drop;
	private JButton quit;
	private JButton operate;
	private JComboBox<String> rotation;
	private JTextField items;
	
	private RobotEngine theRobot;
	
	/**
	 * Constructora por defecto.
	 */
	public InstructionsPanel(){
		this.theRobot = new RobotEngine();
		this.init();
	}
	
	/**
	 * Constructora
	 * @param theRobot el robot
	 */
	public InstructionsPanel(RobotEngine theRobot){
		this.theRobot = theRobot;
		this.init();
	}

	/**
	 * Método privado que inicializa el panel de botones
	 */
	private void init() {
		//Creación
		this.move = new JButton();
		this.turn = new JButton();
		this.pick = new JButton();
		this.drop = new JButton();
		this.quit = new JButton();
		this.operate = new JButton();
		this.rotation = new JComboBox<String>();
		this.items = new JTextField();
		
		//Inicialización
		this.setBorder(new TitledBorder(LABEL_INSTRUCTION_TITLE));
		
		this.move.setText(LABEL_MOVE);
		this.move.setName("jButtonMove");
		this.turn.setText(LABEL_TURN);
		this.turn.setName("jButtonTurn");
		this.pick.setText(LABEL_PICK);
		this.pick.setName("jButtonPick");
		this.drop.setText(LABEL_DROP);
		this.drop.setName("jButtonDrop");
		this.quit.setText(LABEL_QUIT);
		this.quit.setName("jButtonQuit");
		this.operate.setText(LABEL_OPERATE);
		this.operate.setName("jButtonOperate");
		
		this.rotation.addItem(LABEL_LEFT);
		this.rotation.addItem(LABEL_RIGHT);
		this.rotation.setName("jCombTurn");
		this.items.setName("jTextItems");
		
		//Estética
		GridLayout layout = new GridLayout(4,2);
		layout.setHgap(5);
		layout.setVgap(5);
		this.setLayout(layout);
		
		//Añadidos 
		this.add(this.move);
		this.add(this.quit);
		this.add(this.turn);
		this.add(this.rotation);
		this.add(this.pick);
		this.add(this.items);
		this.add(this.drop);
		this.add(this.operate);		
		
	}
	
	/**
	 *  
	 * @return un enumerado con la rotación escogida en el combo box del panel
	 */
	public Rotation getRotation(){
		return Rotation.toRotation((String)this.rotation.getSelectedItem());
	}
	
	/**
	 * 
	 * @return un string con la información escrita por el usuario en el JTextField
	 */
	public String getItemID(){
		return this.items.getText();
	}
	
	/**
	 * Método para fijar los controladores necesarios a cada elemento del panel
	 * @param controlador controlador encargado de definir el comportamiento de los elementos
	 */
	public void fijarControlador(EventListener controlador){
		this.move.addActionListener((ActionListener) controlador);
		this.turn.addActionListener((ActionListener) controlador);
		this.pick.addActionListener((ActionListener) controlador);
		this.drop.addActionListener((ActionListener) controlador);
		this.quit.addActionListener((ActionListener) controlador);
		this.operate.addActionListener((ActionListener) controlador);
		this.rotation.addActionListener((ActionListener) controlador);
		this.items.addActionListener((ActionListener) controlador);
	}
	


}
