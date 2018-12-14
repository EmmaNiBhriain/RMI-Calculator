package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Stack;

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
		
		Stack<Integer> operands = new Stack<>() ;
		String[] sections = expression.trim().split("\\s");
		System.out.println(sections.length);
		//scan all characters of the postfix expression
		for(int i=0; i<sections.length; i++) {
			//char currentChar = );
			String current = sections[i];
			if(current.equals(" ") | current.equals(""))
				continue;
			
			else if(isNumeric(current)) {//if the character is a number, push it to the stack

				//int tempNum = 0;
			
//				while(isNumeric(current)) {
//					tempNum = tempNum * 10 + (currentChar-'0');
//					i++;
//					currentChar = expression.charAt(i);
//				}
//				i--;
				operands.push(Integer.parseInt(sections[i])); //convert the char to int and push to stack
			}
				
			else {
				num1 = operands.pop();
				num2 = operands.pop();
				
				switch(current) {
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
						answer = model.getObj().subract(num2, num1);
						System.out.println("Difference of numbers = " + answer);

					} catch (RemoteException e1) {
						System.out.println("Error evaluating subtraction in rmi");
					}
					break;
				case "÷":
					try {
						answer = model.getObj().divide(num2, num1);
						System.out.println("divided numbers = " + answer);


					} catch (RemoteException e1) {
						System.out.println("Error evaluating division in rmi");
					}
					break;
				}
				operands.push(answer);

				}
				
			

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
			model.getObj().calculatorConnected();

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
	    	if(e.getActionCommand().equals("C")) {
	    		view.getTextField().setText("");
	    	}
	    	else if(!e.getActionCommand().equals("Submit")) {
	    		view.getTextField().setText(view.getTextField().getText() +  e.getActionCommand());
	    	}
	    	else {
	    		model.setInfixExpression(view.getTextField().getText());
	    		
	    		model.infixToPostfix();
	    		System.out.println(model.getPostfixExpression());
	    		
	    		answer = evaluate(model.getPostfixExpression());
	    		view.getTextField().setText(Integer.toString(answer));
	    		view.getMessagesFromServer().setText(view.getMessagesFromServer().getText() + "\n" + "Message from server: " + answer);
	    		System.out.println(answer);
	    		model.setInfixExpression("");
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
	
	public boolean isNumeric(String number) {
		try {
			int num = Integer.parseInt(number);
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

}
