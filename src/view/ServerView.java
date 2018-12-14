package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import controller.CalculatorServer;

/*class ServerView extends JFrame*/
/**
 * Fields:
 *  private JPanel contentPane
 *	private JTextArea displayField
 *	private JPanel panel_1
 *	private JPanel panel
 *	private JButton createClient
 * 	public void addToDisplay(String message)
 *
 * Methods:
 *  Constructor: public ServerView()
 *  private void initComponents() 
 *  public void addListener(CalculatorServer server)
 *  public JTextArea getDisplayField() 
 *  public void displayError(String message)
 *  
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class ServerView extends JFrame{


	/******************************************************
	 * 
	 * 						Fields
	 * 
	 ******************************************************/
	
	/**
	 * JPanels for the UI to hold the components
	 */
	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel;
	
	/**
	 * Components for the UI
	 */
	private JButton createClient;
	private JTextArea displayField;

	/**
	 * UID required because the superclass JFrame is serializable
	 */
	private static final long serialVersionUID = 4L;
	
	
	/******************************************************
	 * 
	 * 					Constructor
	 * 
	 ******************************************************/
	
	/**
	 * call the method to create the frame.
	 */
	public ServerView() {
		initComponents();

	}
	
	/******************************************************
	 * 
	 * 						Methods
	 * 
	 ******************************************************/

	/**
	 * Add components to the GUI
	 */
	private void initComponents() {
		setTitle("Calculator Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Create a JPanel that will hold the textarea
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(0, 0, 390, 358);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		//create the textarea and add it to a JPanel
		displayField = new JTextArea();
		displayField.setBounds(12, 13, 365, 332);
		panel_1.add(displayField);
		displayField.setEditable(false);
		displayField.setColumns(10);
		
		//Create a second panel that will be placed below the first panel and will hold a button
		panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 356, 380, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//create a button and add it to the lower panel
		createClient = new JButton("Create Client");
		createClient.setBounds(32, 13, 316, 122);
		panel.add(createClient);
	}
	
	/**
	 * Add an action listener to the createClient JButton
	 * The listener is stored in the CalculatorServer object.
	 * @param server
	 */
	public void addListener(CalculatorServer server) {
		createClient.addActionListener(server.getListener());
	}
	


	/**
	 * Return the JTextArea that displays messages from the client
	 * @return JTextArea
	 */
	public JTextArea getDisplayField() {
		return displayField;
	}


	/**
	 * Add the message passed as a parameter to the textarea in the GUI
	 * @param message
	 */
	public void addToDisplay(String message) {
		displayField.setText(displayField.getText() + "\n" + message);
	}
	
	/**
	 * Display a dialog box in the event of an exception being thrown and display error message.
	 * @param message to be displayed in the dialog box
	 */
	public void displayError(String message) {
		JOptionPane.showMessageDialog(this,message,"error", JOptionPane.ERROR_MESSAGE);
	}



}
