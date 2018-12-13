package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ServerView extends JFrame{

	private JPanel contentPane;
	private JTextArea displayField;
	private JPanel panel_1;


	/**
	 * Create the frame.
	 */
	public ServerView() {
		initComponents();
	}


	private void initComponents() {
		setTitle("Calculator Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(0, 0, 399, 358);
		contentPane.add(panel_1);
		panel_1.setLayout(null);


		displayField = new JTextArea();
		displayField.setBounds(12, 13, 375, 332);
		panel_1.add(displayField);
		displayField.setEditable(false);
		displayField.setColumns(10);
	}


	public JTextArea getDisplayField() {
		return displayField;
	}


	public void setDisplayField(JTextArea displayField) {
		this.displayField = displayField;
	}

	public void addToDisplay(String message) {
		displayField.setText(displayField.getText() + "\n" + message);
	}



}
