package model;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*Interface Calculator*/
/**
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public interface Calculator extends Remote{
	int add(int num1, int num2) throws RemoteException;
	int subract(int num1, int num2) throws RemoteException;
	int multiply(int num1, int num2) throws RemoteException;
	int divide(int num1, int num2) throws RemoteException;
}
