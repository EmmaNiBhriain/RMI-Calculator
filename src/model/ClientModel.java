package model;

import java.util.HashMap;
import java.util.Stack;

/*class Client Model*/
/**
 * This class stores the data associated with the client. 
 * The conversion from infix to postfix notation also occurs in this class.
 * 
 * Fields:
 *  Calculator obj
 *  String infixExpression
 *  String postfixExpression
 *  static HashMap<Character,Integer> operatorsMap;
 *  
 *  Methods:
 *   public void infixToPostfix()
 *   public boolean isOperator(char currentChar)
 *   public int precedence(char character)
 *   public Calculator getObj() 
 *   public void setObj(Calculator obj)
 *   public String getInfixExpression()
 *   public void setInfixExpression(String infixExpression) 
 *   public String getPostfixExpression() 
 *   public void setPostfixExpression(String postfixExpression)
 *   
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class ClientModel {
	
	/******************************************************
	 * 
	 * 						Fields
	 * 
	 ******************************************************/
	
	/**
	 * Variable to hold an instance of the Calculator interface
	 */
	private Calculator obj = null;
			
	/**
	 * Variable to store the infix notation of the expression to be evaluated
	 */
	private String infixExpression;
	
	/**
	 * Variable to store the postfix notation of the expression to be evaluated 
	 */
	private String postfixExpression;
	
	/**
	 * Map that stores the operators as a key and its precedence value as a value. 
	 */
	private static HashMap<Character, Integer>operatorsMap = new HashMap<Character, Integer>();

	/**
	 * Populate the operatorsMap HashMap
	 */
	static {
		operatorsMap.put('(', 1);
		operatorsMap.put(')', 1);
		operatorsMap.put('+', 2);
		operatorsMap.put('-', 2);
		operatorsMap.put('x', 3);
		operatorsMap.put('÷', 3);
	}

	
	/******************************************************
	 * 
	 * 						Methods
	 * 
	 ******************************************************/
	/**
	 * Change the format of the expression from infix to postfix notation. 
	 * This involves moving the operator from between operands to after operands
	 * Example: infix notation = 2*3   postfix notation of the same expression = 2 3 *
	 */
	public void infixToPostfix() {
		boolean negValue = true;
		postfixExpression = "";
		Stack<Character> operators = new Stack<Character>(); //Stack to store operators
		char current = ' ';

		for(int i=0; i<infixExpression.length(); i++) { //scan the infix expression character by character
			current = infixExpression.charAt(i);

			if(!isOperator(current)){ //if the current character is NOT an operator, add it to the postfix expression
				postfixExpression += current;
				//if(i<infixExpression.length()-1 && isOperator(infixExpression.charAt(i+1))) //if the next character is an operator, add a space in the postfix expression
					//postfixExpression += " ";

			}
			else {
				if(current == '(') { //add the open bracket to the stack
					operators.push(current);
				}
				else if(current == ')') {
					while(operators.peek() != '(') {
						postfixExpression = postfixExpression + " " + operators.pop() + " "; //remove operators from the stack until the open bracket is encountered
					}
					operators.pop(); //remove the open bracket from the stack without adding it to the postfix expression
				}
				else {
					if(current == '-' && infixExpression.charAt(i+1) != ' ')
						postfixExpression += current;
					else {
						while(!operators.isEmpty() &&  precedence(operators.peek()) >= precedence(current)) {
							postfixExpression += operators.pop() + " "; //add the operator to the stack
						}
						negValue = true;
						operators.push(current);
					}
					
				}
			}
		}

		//remove any remaining operator
		while(!operators.isEmpty()) {
			postfixExpression += " " + operators.pop() + " ";
		}

	}

	/**
	 * If the current character of the expression is an operator, return true
	 * Otherwise return false
	 * @param currentChar
	 * @return boolean value
	 */
	public boolean isOperator(char currentChar) {
		if(operatorsMap.containsKey(currentChar)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Retrieve the precedence value that corresponds to the operator in the operatorsMap HashMap
	 * @param character
	 * @return integer representing level of precedence
	 */
	public int precedence(char character) {
		if(operatorsMap.containsKey(character)) {
			return operatorsMap.get(character);
		}
		else {
			return 0;
		}
	}

	public boolean isNegative(String expression) {
		if(expression.isEmpty())
			return true;
		else if(expression.length()>2) {
			if(expression.charAt(expression.length()-1)==' ')
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	
	/******************************************************
	 * 
	 * 				Getters and Setters
	 * 
	 ******************************************************/
	
	/**
	 * Return the instance of the Calculator interface stored in the obj variable
	 * @return (Calculator) obj
	 */
	public Calculator getObj() {
		return obj;
	}

	/**
	 * Set the value of the variable obj which stores an instance of the Calculator interface
	 * @param (Calculator) obj
	 */
	public void setObj(Calculator obj) {
		this.obj = obj;
	}

	/**
	 * Return the expression to be evaluated in infix notation
	 * @return String infixExpression
	 */
	public String getInfixExpression() {
		return infixExpression;
	}

	/**
	 * Set the value of the String which stores the expression to be evaluated using infix notation
	 * @param infixExpression
	 */
	public void setInfixExpression(String infixExpression) {
		this.infixExpression = infixExpression;
	}

	/**
	 * Return the expression to be evaluated in postfix notation
	 * @return String postfixExpression
	 */
	public String getPostfixExpression() {
		return postfixExpression;
	}

	/**
	 *  Set the value of the String which stores the expression to be evaluated using postfix notation
	 * @param postfixExpression
	 */
	public void setPostfixExpression(String postfixExpression) {
		this.postfixExpression = postfixExpression;
	}



}
