package model;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*Interface Calculator*/
/**
 * This interface names the methods that should be implemented by any class that implements this interface.
 * 
 * Named Methods:
 *  int add(int num1, int num2) throws RemoteException;
	int subtract(int num1, int num2) throws RemoteException;
	int multiply(int num1, int num2) throws RemoteException;
	int divide(int num1, int num2) throws RemoteException;
	void calculatorConnected()
	
	
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public interface Calculator extends Remote{
	int add(int num1, int num2) throws RemoteException; //return the sum of two numbers
	int subtract(int num1, int num2) throws RemoteException; //return the difference of two numbers
	int multiply(int num1, int num2) throws RemoteException; //return the product of two numbers
	int divide(int num1, int num2) throws RemoteException; //return the result of a number divided by another
	void calculatorConnected() throws RemoteException;  //send a message to the server to notify of a client connected.
}
