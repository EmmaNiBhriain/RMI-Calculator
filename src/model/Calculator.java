package model;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*Interface Calculator*/
/**
 * This interface names the methods that should be implemented by any class that implements this interface.
 * 
 * Named Methods:
 *  double add(double num1, double num2) throws RemoteException;
	double subtract(double num1, double num2) throws RemoteException;
	double multiply(double num1, double num2) throws RemoteException;
	double divide(double num1, double num2) throws RemoteException;
	void calculatorConnected()
	
	
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public interface Calculator extends Remote{
	double add(double num1, double num2) throws RemoteException; //return the sum of two numbers
	double subtract(double num1, double num2) throws RemoteException; //return the difference of two numbers
	double multiply(double num1, double num2) throws RemoteException; //return the product of two numbers
	double divide(double num1, double num2) throws RemoteException; //return the result of a number divided by another
	void calculatorConnected() throws RemoteException;  //send a message to the server to notify of a client connected.
}
