package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.EmptyStackException;
import java.util.Stack;

import model.Calculator;
import model.ClientModel;
import view.CalculatorView;

/*class CalculatorClient*/
/**
 * 
 * This class is the controller that operates between the CalculatorView and the ClientModel classes.
 * This class is responsible for adding listeners to the CalculcatorView and also connecting to the remote server.
 * 
 * Fields:
 * 	int answer
 * 	int num1
 * 	int num2
 *  Listener listener
 *  ClientModel model
 *  CalculatorView view
 *  
 * Public Methods:
 * 	Constructor: public CalculatorClient(CalculatorView view) 
 *  public int evaluate(String expression)
 *  public void connect()
 *  public static void main(String args[])
 *  public Listener getListener()
 *  public boolean isNumeric(String number)
 *	public void setListener(Listener listener)
 *
 * 
 * Private Class:  
 * 	Listener, This class is responsible to the action listeners associated with the componenets in the Calculator View
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 * 
 */
public class CalculatorClient {

	/******************************************************
	 * 
	 * 						Fields
	 * 
	 ******************************************************/
	
	/**
	 * Variable to store the value of the evaluated calculation. Default value is 0 
	 */
	private int answer = 0;
	
	/**
	 * num1 and num2 are used to store the operands on which an operation will be performed
	 */
	private int num1;
	private int num2;
	
	/**
	 * The Listener variable is used to store an instance of the private Listener class.
	 */
	private Listener listener;

	/**
	 * The ClientModel variable is a reference to an instance of the ClientModel class
	 */
	private ClientModel model;
	
	/**
	 * The CalculatorView variable is a reference to an instance of the CalculatorView class.
	 */
	private CalculatorView view;

	
	
	/******************************************************
	 * 
	 * 						Constructor
	 * 
	 ******************************************************/
	
	/**
	 * The constructor for the CalculatorClient object takes an instance of the CalculatorView class as a parameter.
	 * This instance is assigned to the field view.
	 * A new instance of the ClientModel object is initialised and stored in the model field.
	 * A new instance of the Listener class is created and added to the components in the view class.
	 * @param view
	 */
	public CalculatorClient(CalculatorView view) {
		this.view = view;
		model = new ClientModel();
		this.listener = new Listener();
		this.view.addListeners(this);
	}
	
	
	/******************************************************
	 * 
	 * 						Methods
	 * 
	 ******************************************************/

		/**
		 * The connect() takes no parameters and returns nothing.
		 * Connects to the remote object and stored the reference to the Calculator field in the ClientModel
		 * This method called the remote method calculatorConnected that sends a message to the server to indicate that a client has been connected
		 * In the event of an error when connecting, show a dialog box explianing the error and close the client view
		 */
		public void connect() {
			try {
				model.setObj((Calculator)Naming.lookup("//"
						+ "localhost"
						+ "/Calculator"));
				
				model.getObj().calculatorConnected();

			}
			catch (Exception e) {
				view.displayError("Could not connect to server, please check that the server is running and try again");
				view.dispose();
			}
		}
	
	/**
	 * A method that returns the numerical result of the postfix expression that it has been passed.
	 * An example of the expression it has been passed is "6 3 +"
	 * This method splits the String into an array of strings, split on the presence of whitespace
	 * The character of each element in the array is checked. If the elements are numeric, they are considered to be the operands
	 * If the elements are operators, a calculation is performed on the previous two operands using the operator
	 * The calculations are done by calling methods in the remote server
	 * 
	 * @param String postfix expression
	 * @return An integer representing the value of the evaluated expression.
	 */
	public int evaluate(String expression) {
		
		Stack<Integer> operands = new Stack<>() ; //Create a stack to store the operands
		String[] sections = expression.trim().split("\\s"); //split the postfix expression by where whitespace occurs
		for(int i=0; i<sections.length; i++) { 		//scan all characters of the postfix expression
			String current = sections[i];
			if(current.equals(" ") | current.equals(""))
				continue;
			
			else if(isNumeric(current)) {//if the character is a number, push it to the stack
				operands.push(Integer.parseInt(sections[i])); //convert the String to Integer and push to stack
			}
			
			// If an operator is detected, remove the top two operands from the stack.
			// Perform a calculation on them based on the type of operator
			else { 
				try {
					num1 = operands.pop();
					num2 = operands.pop();
				}
				catch (EmptyStackException e){
					view.displayError("Error retrieving operands from stack");
				}
				
				
				switch(current) {
				case "+":
					try {
						answer = model.getObj().add(num1, num2); //call the add(int, int) method in the server
						System.out.println("Sum of numbers = " + answer);

					} catch (RemoteException e1) {
						view.displayError("Error evaluating sum in rmi");
					}
					break;		
				case "x":
					try {
						answer = model.getObj().multiply(num1, num2); //call the multiply(int, int) method in the server
						System.out.println("product of numbers = " + answer);

					} catch (RemoteException e1) {
						view.displayError("Error evaluating multiplication in rmi");
					}
					break;
				case "-":
					try {
						answer = model.getObj().subtract(num2, num1); //call the subtract(int, int) method in the server
						System.out.println("Difference of numbers = " + answer);

					} catch (RemoteException e1) {
						view.displayError("Error evaluating subtraction in rmi");
					}
					break;
				case "÷":
					try {
						answer = model.getObj().divide(num2, num1); //call the divide(int, int) method in the server
						System.out.println("divided numbers = " + answer);


					} catch (RemoteException e1) {
						view.displayError("Error evaluating division in rmi");
					}
					break;
				}
				operands.push(answer);
				}
				
			}
		return answer;
	}
		
	
	/******************************************************
	 * 
	 * 						Private Class
	 * 
	 ******************************************************/
	/*private class Listener implements ActionListener*/
	/**
	 * This class handles events that occur in the CalculatorView due to user interaction.
	 * @author Emma
	 * @version 1.0
	 */
	private class Listener implements ActionListener {
	
		/**
		 * This method overrides the method from the interface ActionListener
		 * If the C button is pressed, the view display is cleared.
		 * If the Submit button is pressed, the expression is converted from infix to postfix notation and 
		 * the relevant calculation is performed on the operands. 
		 * Otherwise the ActionCommand of the button pressed is appended to the "screen" of the calculator
		 * infixExpression and postfixExpression are both fields of the ClientModel and are updated in this class.
		 */
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	if(e.getActionCommand().equals("C")) {
	    		view.getTextField().setText("");
	    	}
	    	else if(!e.getActionCommand().equals("Submit")) {
	    		view.getTextField().setText(view.getTextField().getText() +  e.getActionCommand());
	    	}
	    	else {
	    		model.setInfixExpression(view.getTextField().getText());
	    		
	    		model.infixToPostfix();	    		
	    		answer = evaluate(model.getPostfixExpression());
	    		view.getTextField().setText(Integer.toString(answer));
	    		view.getMessagesFromServer().setText(view.getMessagesFromServer().getText() + "\n" + "Message from server: " + answer);
	    		model.setInfixExpression("");
	    	}
	      
	    }
	}
	
	/**
	 * Main method to create new instance of CalculatorView and CalculatorClient controller.
	 * Connect to the remote server.
	 * @param String args[]
	 */
	public static void main(String args[])
	{

		try {
			CalculatorView frame = new CalculatorView();
			frame.setVisible(true);
			CalculatorClient client = new CalculatorClient(frame);
			client.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Return a boolean value based on whether the String passed as a parameter can be parsed as an integer or not
	 * @param String representation of a number
	 * @return boolean value 
	 */
	public boolean isNumeric(String number) {
		try {
			Integer.parseInt(number);
		}
		catch(NumberFormatException es){
			try {
				new BigInteger(number);
			}
			catch(Exception e) {
				return false;
			}
			System.out.println("Number too large for computation");
		}
		return true;
	}
	
	
	/******************************************************
	 * 
	 * 				  Getters and Setters
	 * 
	 ******************************************************/
	
	/**
	 * @return the instance of the private Listener class
	 */
	public Listener getListener() {
		return listener;
	}

	/**
	 * Set the value of the Listener field.
	 * @param listener
	 */
	public void setListener(Listener listener) {
		this.listener = listener;
	}

}
