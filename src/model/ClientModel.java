package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ClientModel {
	private Calculator obj = null;
	private String infixExpression;
	private String postfixExpression;
	private static HashMap<Character, Integer>operatorsMap = new HashMap<Character, Integer>();

	static {
		operatorsMap.put('(', 1);
		operatorsMap.put(')', 1);
		operatorsMap.put('+', 2);
		operatorsMap.put('-', 2);
		operatorsMap.put('x', 3);
		operatorsMap.put('÷', 3);
	}

	/**
	 * Change the format of the expression from infix to postfix notation. This involves moving the operator from between operands to after operands
	 * Example: infix notation = 2*3   postfix notation of the same expression = 2 3 *
	 */
	public void infixToPostfix() {
		boolean negValue = true;
		postfixExpression = "";
		Stack<Character> operators = new Stack<Character>();
		//char removed;
		char current = ' ';

		for(int i=0; i<infixExpression.length(); i++) {
			current = infixExpression.charAt(i);

			if(!isOperator(current)){

				postfixExpression += current;
				if(i<infixExpression.length()-1 && isOperator(infixExpression.charAt(i+1)))
					postfixExpression += " ";

			}
			else {
				if(current == '(') {
					operators.push(current);
				}
				else if(current == ')') {
					while(operators.peek() != '(') {
						postfixExpression = postfixExpression + " " + operators.pop() + " ";
					}
					operators.pop();
				}
				else {

					while(!operators.isEmpty() &&  precedence(operators.peek()) >= precedence(current)) {
						postfixExpression += operators.pop() + " ";
					}
					negValue = true;
					operators.push(current);
					//i++;
				}
				//current = infixExpression.charAt(i);
			}
			//			else if(current == '-' && negValue == true) {
			//				//i+=2; //skip the space
			//				//current = infixExpression.charAt(i);
			//				negValue = false;
			//			}

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
	 * @return
	 */
	public boolean isOperator(char currentChar) {
		if(operatorsMap.containsKey(currentChar)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int precedence(char character) {
		if(operatorsMap.containsKey(character)) {
			return operatorsMap.get(character);
		}
		else {
			return 0;
		}
	}

	public Calculator getObj() {
		return obj;
	}

	public void setObj(Calculator obj) {
		this.obj = obj;
	}

	public String getInfixExpression() {
		return infixExpression;
	}

	public void setInfixExpression(String infixExpression) {
		this.infixExpression = infixExpression;
	}

	public String getPostfixExpression() {
		return postfixExpression;
	}

	public void setPostfixExpression(String postfixExpression) {
		this.postfixExpression = postfixExpression;
	}



}
