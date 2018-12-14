package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import model.Calculator;
import view.ServerView;

/*class CalculatorServer*/
/**
 * This class is the controller that communicates with the ServerView class.
 * The server also has methods that are called remotely from the Calculator client controller.
 * This class is responsible for adding actionListeners to the ServerView.
 * 
 * Fields:
 * 	private double answer
 *  private ServerListener listener
 *  private static int portNumber
 *  private static final long serialVersionUID;
	private ServerView view;
 *  
 * Public Methods:
 * 	Constructor: public CalculatorServer()throws RemoteException
 *  public void calculatorConnected()
 *  public double add(double num1, double num2) throws RemoteException
 *  public double subtract(double num1, double num2) throws RemoteException
 *  public double multiply(double num1, double num2) throws RemoteException
 *  public double divide(double num1, double num2) throws RemoteException
 *  public ServerListener getListener()
 *  public void setListener(ServerListener listener)
 *  
 * Private Class:  
 * 	ServerListener, This class is responsible to the action listeners associated with the components in the ServerView
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class CalculatorServer extends UnicastRemoteObject implements Calculator{	

	/******************************************************
	 * 
	 * 						Fields
	 * 
	 ******************************************************/
	
	/**
	 * Variable to hold the result of calculations returned from the add, subtract, multiply and divide methods
	 */
	private double answer;
	
	/**
	 * A variable to reference an instance of the ServerListener class
	 */
	private ServerListener listener;
	
	/**
	 * Port number used to create a registry
	 */
	private static int portNumber = 1099;

	/**
	 * serialVersionUID required as the class extends the UnicastRemoteObject class which is serializable
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Reference to an instance of the ServerView class.
	 */
	private ServerView view;

	
	/******************************************************
	 * 
	 * 						Constructor
	 * 
	 ******************************************************/
	
	/**
	 * Create a new instance of the ServerView and ServerListener classes.
	 * Add the ServerListener to the button component in the ServerView.
	 * @throws RemoteException
	 */
	public CalculatorServer() throws RemoteException {
		super();
		this.view = new ServerView();
		view.setVisible(true);
		this.listener = new ServerListener();
		view.addListener(this);
	}

	
	
	/******************************************************
	 * 
	 * 						Methods
	 * 
	 ******************************************************/
	

	/**
	 * This method displays the IP address of the connected client to the ServerView
	 */
	@Override
	public void calculatorConnected() {
		try {
			view.addToDisplay("Client connected at IP address: " + getClientHost());
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Override the add method from the Calculator interface.
	 * Return the sum of two doubles passed as parameters.
	 * Display request from client in the ServerView
	 * @return double result of the calculation
	 * @param two doubles to be used in the calculation
	 */
	@Override
	public double add(double num1, double num2) throws RemoteException {
		try{
			view.addToDisplay("Addition request from client with IP address: " + getClientHost() + "\n" + num1 + " + " + num2);
		}catch(Exception e){
			view.displayError("Could not get client ip address");
		}
		answer = num1+num2;
		view.addToDisplay("Answer returned to client: " + answer);
		return answer;
	}

	
	/**
	 * Override the subtract method from the Calculator interface.
	 * Return the difference of two doubles passed as parameters.
	 * Display request from client in the ServerView
	 * @return double result of the calculation
	 * @param two doubles to be used in the calculation
	 */
	@Override
	public double subtract(double num1, double num2) throws RemoteException {
		try {
			System.out.println("Subtraction request from client with IP address: " + getClientHost());
			view.addToDisplay("Subtraction request from client with IP address: " + getClientHost() + "\n" + num1 + " - " + num2);
		} catch (ServerNotActiveException e) {
			view.displayError("Could not get client ip address");
		} // display message
		answer = num1-num2;
		view.addToDisplay("Answer returned to client: " + answer);

		return answer;
	}

	
	/**
	 * Override the multiply method from the Calculator interface.
	 * Return the product of two doubles passed as parameters.
	 * Display request from client in the ServerView
	 * @return double result of the calculation
	 * @param two doubles to be used in the calculation
	 */
	@Override
	public double multiply(double num1, double num2) throws RemoteException {
		try {
			System.out.println("Multiplication request from client with IP address: " + getClientHost());
			view.addToDisplay("Multiplication request from client with IP address: " + getClientHost() + "\n" + num1 + " x " + num2);

		} catch (ServerNotActiveException e) {
			view.displayError("Could not get client ip address");
		} // display message
		answer =  num1*num2;
		view.addToDisplay("Answer returned to client: " + answer);

		return answer;
	}

	
	/**
	 * Override the multiply method from the Calculator interface.
	 * Return the result of two doubles divided by each other passed as parameters.
	 * Display request from client in the ServerView
	 * @return double result of the calculation
	 * @param two doubles to be used in the calculation
	 */
	@Override
	public double divide(double num1, double num2) throws RemoteException {
		try {
			System.out.println("Division request from client with IP address: " + getClientHost());
			view.addToDisplay("Division request from client with IP address: " + getClientHost() + "\n" + num1 + " ÷ " + num2);

		} catch (ServerNotActiveException e) {
			view.displayError("Could not get client ip address");
		} // display message
		answer = num1/num2;
		view.addToDisplay("Answer returned to client: " + answer);
		return answer;
	}
	
	
	/******************************************************
	 * 
	 * 				    Private Class
	 * 
	 ******************************************************/
	
	/*private class ServerListener implements ActionListener*/
	/**
	 * This class is responsible for handling events in the server GUI based on user input
	 * @author Emma
	 * @version 1.0
	 */
	private class ServerListener implements ActionListener {
		
		/**
		 * Override the actionPerformed method from the ActionListener interface.
		 * If the "Create Client" button is pressed, run the Calculator Client program. 
		 */
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	if(e.getActionCommand().equals("Create Client")) {
	    		CalculatorClient.main(null);
	    		
	    	}
	      
	    }
	}
	
	
	
	/******************************************************
	 * 
	 * 				   Getters and Setters
	 * 
	 ******************************************************/

	/**
	 * Return the instance of the ServerListener class stored in the listener variable.
	 * @return ServerListener
	 */
	public ServerListener getListener() {
		return listener;
	}

	/**
	 * Set the value of the listener variable
	 * @param listener, instance of the ServerListener class 
	 */
	public void setListener(ServerListener listener) {
		this.listener = listener;
	}
	
	
	
	/******************************************************
	 * 
	 * 						Main Method
	 * 
	 ******************************************************/
	/**
	 * This method is used to start the program.
	 * It creates a new CalculatorServer object and binds the object to the registry created at the specified port number
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		try {

			// Create an object of the CalculatorServer class.
			CalculatorServer obj = new CalculatorServer();
			Registry registry = LocateRegistry.createRegistry( portNumber );
			registry.rebind("Calculator", obj );
		}
		catch (Exception e) {
			ServerView view = new ServerView();
			view.displayError("Cannot create new server registry. Port already in use");
			view.dispose();
		}
	}

}
