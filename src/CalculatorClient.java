import java.rmi.Naming;

import view.CalculatorView;

/*class CalculatorClient*/
/**
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class CalculatorClient {

	static String message = "blank";
	static int sum = 0;
	static int difference = 0;
	static int divided = 0;
	static int product = 0;
	static int num1 = 12;
	static int num2 = 14;

	// The HelloWorld object "obj" is the identifier that is
	// used to refer to the remote object that implements
	// the HelloWorld interface.

	static Calculator obj = null;


	public static void main(String args[])
	{

		try {
			CalculatorView frame = new CalculatorView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			obj = (Calculator)Naming.lookup("//"
					+ "localhost"
					+ "/Calculator");
			//message = obj.calculator();
			sum = obj.add(num1, num2);
			System.out.println("Sum of numbers = " + sum);

			difference = obj.subract(num1, num2);
			System.out.println("Difference of numbers = " + difference);

			product = obj.multiply(num1, num2);
			System.out.println("product of numbers = " + product);

			divided = obj.divide(num1, num2);
			System.out.println("divided numbers = " + divided);

			//System.out.println("Message from the RMI-server was: \""
			//+ message + "\"");

		}
		catch (Exception e) {
			System.out.println("CalculatorClient exception: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

}
