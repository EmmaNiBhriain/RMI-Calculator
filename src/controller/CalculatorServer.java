package controller;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.Calculator;
import view.ServerView;

/*class CalculatorServer*/
/**
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class CalculatorServer extends UnicastRemoteObject implements Calculator{
	private ArrayList<CalculatorClient> clients = new ArrayList();
	private int answer;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServerView view;

	public CalculatorServer() throws RemoteException {
		super();
		this.view = new ServerView();
		view.setVisible(true);
	}

	public static void main(String args[]) {
		try {

			// Create an object of the HelloWorldServer class.

			CalculatorServer obj = new CalculatorServer();

			// Bind this object instance to the name "HelloServer".
			Registry registry = LocateRegistry.createRegistry( 1099 );

			// and replace the Naming.rebind() with the next line
			registry.rebind("Calculator", obj );

			System.out.println("Calculator bound in registry");
		}
		catch (Exception e) {
			System.out.println("CalculatorServer error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void calculatorConnected() {
		try {
			System.out.println("Client connected at IP address: " + getClientHost());
			view.addToDisplay("Client connected at IP address: " + getClientHost());
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int add(int num1, int num2) throws RemoteException {
		try{
			System.out.println("Addition request from client with IP address: " + getClientHost()); // display message
			view.addToDisplay("Addition request from client with IP address: " + getClientHost() + "\n" + num1 + " + " + num2);
		}catch(Exception e){}
		answer = num1+num2;
		view.addToDisplay("Answer returned to client: " + answer);

		return answer;
	}

	@Override
	public int subract(int num1, int num2) throws RemoteException {
		try {
			System.out.println("Subtraction request from client with IP address: " + getClientHost());
			view.addToDisplay("Subtraction request from client with IP address: " + getClientHost() + "\n" + num1 + " - " + num2);
		} catch (ServerNotActiveException e) {

		} // display message
		answer = num1-num2;
		view.addToDisplay("Answer returned to client: " + answer);

		return answer;
	}

	@Override
	public int multiply(int num1, int num2) throws RemoteException {
		try {
			System.out.println("Multiplication request from client with IP address: " + getClientHost());
			view.addToDisplay("Multiplication request from client with IP address: " + getClientHost() + "\n" + num1 + " x " + num2);

		} catch (ServerNotActiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // display message
		answer =  num1*num2;
		view.addToDisplay("Answer returned to client: " + answer);

		return answer;
	}

	@Override
	public int divide(int num1, int num2) throws RemoteException {
		try {
			System.out.println("Division request from client with IP address: " + getClientHost());
			view.addToDisplay("Division request from client with IP address: " + getClientHost() + "\n" + num1 + " ÷ " + num2);

		} catch (ServerNotActiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // display message
		answer = num1/num2;
		view.addToDisplay("Answer returned to client: " + answer);
		return answer;
	}

}
