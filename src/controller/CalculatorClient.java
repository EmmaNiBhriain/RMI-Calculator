package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import model.Calculator;
import model.ClientModel;
import view.CalculatorView;

/*class CalculatorClient*/
/**
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class CalculatorClient {

	static String message = "";
	static int answer = 0;
	static int num1;
	static int num2;
	private String expression;
	private Listener listener;


	// The HelloWorld object "obj" is the identifier that is
	// used to refer to the remote object that implements
	// the HelloWorld interface.

	private ClientModel model;
	private CalculatorView view;

	public CalculatorClient(CalculatorView view) {
		this.view = view;
		model = new ClientModel();
		this.listener = new Listener();
		this.view.addListeners(this);
	}
	
	public int evaluate(String expression) {
		num1 = (int) expression.charAt(0) - '0';
		num2 = (int) expression.charAt(2) - '0';
		answer = 0;
		expression = Character.toString(expression.charAt(1));
		switch(expression) {
		case "+":
			try {
				answer = model.getObj().add(num1, num2);
				System.out.println("Sum of numbers = " + answer);

			} catch (RemoteException e1) {
				System.out.println("Error evaluating sum in rmi");
			}
			break;		
		case "x":
			try {
				answer = model.getObj().multiply(num1, num2);
				System.out.println("product of numbers = " + answer);

			} catch (RemoteException e1) {
				System.out.println("Error evaluating multiplication in rmi");
			}
			break;
		case "-":
			try {
				answer = model.getObj().subract(num1, num2);
				System.out.println("Difference of numbers = " + answer);

			} catch (RemoteException e1) {
				System.out.println("Error evaluating subtraction in rmi");
			}
			break;
		case "÷":
			try {
				answer = model.getObj().divide(num1, num2);
				System.out.println("divided numbers = " + answer);


			} catch (RemoteException e1) {
				System.out.println("Error evaluating division in rmi");
			}
			break;
		}
		return answer;
	}
	
	public void connect() {
		try {
			model.setObj((Calculator)Naming.lookup("//"
					+ "localhost"
					+ "/Calculator"));
			
			//System.out.println("Message from the RMI-server was: \""
			//+ message + "\"");

		}
		catch (Exception e) {
			System.out.println("CalculatorClient exception: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	private class Listener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {	    	
	    	if(!e.getActionCommand().equals("Submit")) {
	    		view.getTextField().setText(view.getTextField().getText() +  e.getActionCommand());
	    	}
	    	else {
	    		expression = view.getTextField().getText();
	    		answer = evaluate(expression);
	    		System.out.println(answer);
	    	}
	      
	    }
	}
	
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

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

}
