package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import controller.CalculatorClient;

/*class CalculatorView*/
/**
 * 
 * @author Emma Ni Bhriain
 * @version 1.0
 */
public class CalculatorView extends JFrame{
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField textField;
	private JButton button_1;
	private JButton button_2;
	private JButton button_6;
	private JButton button_7;
	private JButton button_4;
	private JButton button_8;
	private JButton button_9;
	private JButton btnX;
	private JButton button_5;
	private JButton button_decimal;
	private JButton button_3;
	private JButton button_0;
	private JButton button_divide;
	private JButton button_subtract;
	private JButton button_plus;
	private JButton button_Submit;
	private JButton button_clear;
	private JButton button_closeBracket;
	private JButton button_openBracket;
	
	private JPanel panel_2;
	private JTextArea messagesFromServer;
	private JScrollPane scrollPane;
	
	
	/**
	 * Create the frame.
	 */
	public CalculatorView() {
		initComponents();
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(0, 0, 309, 124);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(12, 13, 285, 95);
		panel.add(textField);
		textField.setColumns(10);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(0, 124, 309, 268);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		button_2 = new JButton("2");
		button_2.setPreferredSize(new Dimension(50, 50));

		button_2.setBounds(74, 13, 54, 49);
		panel_1.add(button_2);

		button_3 = new JButton("3");
		button_3.setPreferredSize(new Dimension(50, 50));
		button_3.setBounds(129, 13, 54, 49);
		panel_1.add(button_3);

		button_7 = new JButton("7");

		button_7.setPreferredSize(new Dimension(50, 50));
		button_7.setBounds(18, 133, 54, 49);
		panel_1.add(button_7);

		button_9 = new JButton("9");
		button_9.setPreferredSize(new Dimension(50, 50));
		button_9.setBounds(129, 133, 54, 49);
		panel_1.add(button_9);

		button_5 = new JButton("5");
		button_5.setPreferredSize(new Dimension(50, 50));
		button_5.setBounds(74, 73, 54, 49);
		panel_1.add(button_5);

		button_6 = new JButton("6");
		button_6.setPreferredSize(new Dimension(50, 50));
		button_6.setBounds(129, 75, 54, 49);
		panel_1.add(button_6);

		button_0 = new JButton("0");
		button_0.setPreferredSize(new Dimension(50, 50));

		button_0.setBounds(12, 195, 63, 49);
		panel_1.add(button_0);

		button_divide = new JButton("\u00F7");
		button_divide.setPreferredSize(new Dimension(50, 50));

		button_divide.setBounds(197, 13, 54, 49);
		panel_1.add(button_divide);

		btnX = new JButton("x");
		btnX.setPreferredSize(new Dimension(50, 50));
		btnX.setBounds(197, 73, 54, 49);
		panel_1.add(btnX);

		button_plus = new JButton("+");
		button_plus.setPreferredSize(new Dimension(50, 50));
		button_plus.setBounds(195, 133, 54, 49);
		panel_1.add(button_plus);

		button_subtract = new JButton("-");
		button_subtract.setPreferredSize(new Dimension(50, 50));
		button_subtract.setBounds(197, 195, 54, 49);
		panel_1.add(button_subtract);

		button_decimal = new JButton(".");
		button_decimal.setPreferredSize(new Dimension(50, 50));
		button_decimal.setBounds(255, 195, 54, 49);
		panel_1.add(button_decimal);

		button_8 = new JButton("8");
		button_8.setPreferredSize(new Dimension(50, 50));
		button_8.setBounds(74, 133, 54, 49);
		panel_1.add(button_8);

		button_4 = new JButton("4");
		button_4.setPreferredSize(new Dimension(50, 50));
		button_4.setBounds(18, 73, 54, 49);
		panel_1.add(button_4);

		button_1 = new JButton("1");
		button_1.setPreferredSize(new Dimension(50, 50));
		button_1.setBounds(18, 13, 54, 49);
		panel_1.add(button_1);
		
		button_Submit = new JButton("Submit");
		button_Submit.setPreferredSize(new Dimension(50, 50));
		button_Submit.setBounds(74, 195, 116, 49);
		panel_1.add(button_Submit);
		textField.setBounds(12, 13, 285, 95);
		
		button_clear = new JButton("C");
		button_clear.setPreferredSize(new Dimension(50, 50));
		button_clear.setBounds(255, 133, 54, 49);
		panel_1.add(button_clear);
		
		button_closeBracket = new JButton(")");
		button_closeBracket.setPreferredSize(new Dimension(50, 50));
		button_closeBracket.setBounds(255, 73, 54, 49);
		panel_1.add(button_closeBracket);
		
		button_openBracket = new JButton("(");
		button_openBracket.setPreferredSize(new Dimension(50, 50));
		button_openBracket.setBounds(255, 13, 54, 49);
		panel_1.add(button_openBracket);
		textField.setBounds(12, 13, 285, 95);


//		panel_2 = new JPanel();
////		panel_2.setLayout(null);
//		panel_2.setBackground(new Color(230, 230, 250));
//		panel_2.setBounds(0, 392, 309, 139);
//		contentPane.add(panel_2);
//
//		messagesFromServer = new JTextArea();
//		//messagesFromServer.setBounds(12, 13, 262, 113);
//		
//		scrollPane = new JScrollPane(messagesFromServer);
//		scrollPane.setBounds(12, 13, 262, 113);
//		panel_2.add(scrollPane);

		panel_2 = new JPanel();
//		panel_2.setLayout(null);
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(0, 392, 309, 139);
		contentPane.add(panel_2);
		//messagesFromServer.setBounds(12, 13, 262, 113);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 262, 113);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		messagesFromServer = new JTextArea();
		scrollPane.setViewportView(messagesFromServer);
		panel_2.setLayout(gl_panel_2);


		//panel_2.add(messagesFromServer);
		
	}
	
	public void addListeners(CalculatorClient client) {
		//Add action listeners
		//Listener actionListener = client.getListener();
		button_1.addActionListener(client.getListener());
		button_2.addActionListener(client.getListener());
		button_6.addActionListener(client.getListener());
		button_7.addActionListener(client.getListener());
		button_4.addActionListener(client.getListener());
		button_8.addActionListener(client.getListener());
		button_9.addActionListener(client.getListener());
		btnX.addActionListener(client.getListener());
		button_5.addActionListener(client.getListener());
		button_decimal.addActionListener(client.getListener());
		button_3.addActionListener(client.getListener());
		button_0.addActionListener(client.getListener());
		button_divide.addActionListener(client.getListener());
		button_subtract.addActionListener(client.getListener());
		button_plus.addActionListener(client.getListener());
		button_Submit.addActionListener(client.getListener());
		button_clear.addActionListener(client.getListener());
		button_openBracket.addActionListener(client.getListener());
		button_closeBracket.addActionListener(client.getListener());

	}
	
	
	public JTextArea getMessagesFromServer() {
		return messagesFromServer;
	}


	public void setMessagesFromServer(JTextArea messagesFromServer) {
		this.messagesFromServer = messagesFromServer;
	}


	public JTextField getTextField() {
		return textField;
	}


	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}




