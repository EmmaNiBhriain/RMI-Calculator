package controller;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import model.Calculator;

/*class CalculatorServer*/
/**
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class CalculatorServer extends UnicastRemoteObject implements Calculator{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculatorServer() throws RemoteException {
		super();
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
	public int add(int num1, int num2) throws RemoteException {
		try{
		    System.out.println("Addition request from client with IP address: " + getClientHost()); // display message
		}catch(Exception e){}
		return num1+num2;
	}

	@Override
	public int subract(int num1, int num2) throws RemoteException {
	    try {
			System.out.println("Subtraction request from client with IP address: " + getClientHost());
		} catch (ServerNotActiveException e) {

		} // display message
		return num1-num2;
	}

	@Override
	public int multiply(int num1, int num2) throws RemoteException {
	    try {
			System.out.println("Multiplication request from client with IP address: " + getClientHost());
		} catch (ServerNotActiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // display message

		return num1*num2;
	}

	@Override
	public int divide(int num1, int num2) throws RemoteException {
	    try {
			System.out.println("Division request from client with IP address: " + getClientHost());
		} catch (ServerNotActiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // display message
		return num1/num2;
	}

}
