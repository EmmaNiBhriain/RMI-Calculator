package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
	private JPanel panel_2;
	private JTextField textField;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_3;
	private JButton button_4;
	private JButton button_8;
	private JButton button_9;


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
		textField.setBounds(12, 13, 285, 95);
		panel.add(textField);
		textField.setColumns(10);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(0, 124, 309, 264);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		button = new JButton("1");
		button.setBounds(49, 13, 46, 49);
		panel_1.add(button);

		button_1 = new JButton("2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(111, 13, 54, 49);
		panel_1.add(button_1);

		button_2 = new JButton("3");
		button_2.setBounds(177, 13, 54, 49);
		panel_1.add(button_2);

		button_5 = new JButton("7");
		button_5.setBounds(49, 133, 46, 49);
		panel_1.add(button_5);

		button_6 = new JButton("8");
		button_6.setBounds(111, 133, 54, 49);
		panel_1.add(button_6);

		button_7 = new JButton("9");
		button_7.setBounds(177, 133, 54, 49);
		panel_1.add(button_7);

		button_3 = new JButton("4");
		button_3.setBounds(49, 75, 46, 49);
		panel_1.add(button_3);

		button_4 = new JButton("5");
		button_4.setBounds(111, 71, 54, 49);
		panel_1.add(button_4);

		button_8 = new JButton("6");
		button_8.setBounds(177, 73, 54, 49);
		panel_1.add(button_8);

		button_9 = new JButton("0");
		button_9.setBounds(111, 202, 54, 49);
		panel_1.add(button_9);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(0, 388, 309, 143);
		contentPane.add(panel_2);
	}
}
