package calculator;
import java.text.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class InterestGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField principalTextField;
	private JTextField rateTextField;
	private JTextField yearsTextField;
	private JLabel display;
	InterestGUI gui;
	
	public InterestGUI(int frameWidth, int frameHeight) {
		/* Header */
		add(new JLabel("Principal"));
	
		int textFieldLength = 10;
		principalTextField = new JTextField(textFieldLength);
		add(principalTextField);
		
		add(new JLabel("Rate(Percentage)"));
		
		/* Defines textfield for filename */
		int textFieldLength2 = 7;
		rateTextField = new JTextField(textFieldLength2);
		add(rateTextField);
		
		add(new JLabel("Years"));
		
		int textFieldLength3 = 3;
		yearsTextField = new JTextField(textFieldLength3);
		add(yearsTextField);
		
		/* Defines button to trigger file reading */
	JButton computeSimpleInterest = new JButton("Compute Simple Interest");
		add(computeSimpleInterest);
		
		computeSimpleInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//display.setText(Utilities.getFileContents(fileNameTextField.getText()));
				//simple interest amount = principal + (principal * (rate/100) * years)
				DecimalFormat df = new DecimalFormat("#.##");
				Double principal = Double.parseDouble(principalTextField.getText());
				Double rate = Double.parseDouble(rateTextField.getText());
				Double years = Double.parseDouble(yearsTextField.getText());
				Double simpleInterestAmount = principal + (principal * (rate/100) * years);
				//add(new JLabel(simpleInterestAmount.toString()));
				display.setText("Computed Simple Interest is: $" +  df.format(simpleInterestAmount));
				repaint();
			}
		});	
		
		
		/* Defines button to trigger file reading */
		JButton computeCompundInterest = new JButton("Compute Compound Interest");
		add(computeCompundInterest);
		

		display = new JLabel("");
		add(display);
		computeCompundInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Compound Interest Amount = principal * (1 + rate/100)Years 
				//display.setText(Utilities.getFileContents(principalTextField.getText()));
				DecimalFormat df = new DecimalFormat("#.##");
				Double principal = Double.parseDouble(principalTextField.getText());
				Double rate = Double.parseDouble(rateTextField.getText());
				Double years = Double.parseDouble(yearsTextField.getText());
				Double compoundInterestAmount = (1 + rate/100);
				compoundInterestAmount = principal * (java.lang.Math.pow (compoundInterestAmount , years));
				//add(new JLabel(compoundInterestAmount.toString()));
				display.setText("Computed Compound Interest is: $" +  df.format(compoundInterestAmount));
				repaint();
				
			}
		});
	}
	

	private static void createAndShowGUI() {
		int width = 500, height = 150;
		JFrame frame = new JFrame("Interest Calculator");
		frame.setSize(new Dimension(width, height));
		frame.setContentPane(new InterestGUI(width, height)); /* Adds the panel to the frame */

		/* Centralizing the frame */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int upperLeftCornerX = (screenSize.width - frame.getWidth()) / 2;
		int upperLeftCornerY = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(upperLeftCornerX, upperLeftCornerY);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/* Shows the GUI */
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
	
	

