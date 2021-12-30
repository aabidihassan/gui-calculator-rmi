/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.*;
import java.rmi.*;
import services.ICalculatorRemote;

/**
 *
 * @author hassa
 */
public class CalculatorClient extends JPanel implements ActionListener{
    
    public static final int width = 320;
	public static final int height = 480;

	//creating the layout and buttons, I use an array as it saves more space and is easily implemented.
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private JButton numButtons[];
	private JButton opButtons[];
	private JTextField text;
	
	//the numbers to be displayed on screen
	public double num1, num2, ans, operation;

	//setting up the numbers on the GUI and the positions
	private int[][] numCons = new int[][] {
		{0,5,2,1},//{gridx, gridy, gridwidth, gridheight}
		{0,4,1,1},
		{1,4,1,1},
		{2,4,1,1},
		{0,3,1,1},
		{1,3,1,1},
		{2,3,1,1},
		{0,2,1,1},
		{1,2,1,1},
		{2,2,1,1},
	};
	//Setting up the operations
	private int[][] opCons = new int[][] {
		{2,5,1,1},
		{3,4,1,2},
		{3,3,1,1},
		{3,2,1,1},
		{2,1,1,1},
		{1,1,1,1},
		{3,1,1,1},
	};
    
    public CalculatorClient(){//Setting up the screen sizes
	
	//Constructor for Client
		//Setting up the layout
		setPreferredSize(new Dimension(width, height));
		layout = new GridBagLayout();
		layout.columnWidths = new int[] {80,80,80,80};
		layout.rowHeights = new int[] {80,80,80,80,80,80};
		setLayout(layout);
		gbc = new GridBagConstraints();
		//creating the buttons and adding the constraints
		numButtons = new JButton[10];
		for(int i = 0; i < numButtons.length; i++){
			numButtons[i] = new JButton(""+i);
			numButtons[i].addActionListener(this);
			gbc.gridx = numCons[i][0];
			gbc.gridy = numCons[i][1];
			gbc.gridwidth = numCons[i][2];
			gbc.gridheight = numCons[i][3];
			gbc.fill = GridBagConstraints.BOTH;
			add(numButtons[i], gbc);
		}
		//Creating OP buttons
		opButtons = new JButton[7];
		opButtons[0] = new JButton(".");
		opButtons[1] = new JButton("=");
		opButtons[2] = new JButton("+");
		opButtons[3] = new JButton("-");
		opButtons[4] = new JButton("*");
		opButtons[5] = new JButton("/");
		opButtons[6] = new JButton("C");
		
		for(int i = 0; i < opButtons.length; i++){			
			gbc.gridx = opCons[i][0];
			gbc.gridy = opCons[i][1];
			gbc.gridwidth = opCons[i][2];
			gbc.gridheight = opCons[i][3];
			gbc.fill = GridBagConstraints.BOTH;
			opButtons[i].addActionListener(this);
			add(opButtons[i], gbc);
		}
		//creating text field
		text = new JTextField();
		text.setEnabled(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		add(text, gbc);
                text.setFont(new Font("Serif", Font.BOLD, 30));
                text.setForeground(Color.BLACK);
	}
	//Action listnener
    	public void actionPerformed(ActionEvent AE)
    	{
    		//Adds pressing of numbers to GUI
    		for(int i = 0; i < numButtons.length; i++){
    			if(AE.getSource()==numButtons[i])
    			{
    				text.setText(text.getText()+i);
    			}
    		}
    		// Clear button
    		if(AE.getSource()==opButtons[6])
			{
				text.setText("");
			}
    		//+ operation
    		if(AE.getSource()==opButtons[2])
			{
				num1 = Double.parseDouble(text.getText());
				operation = 1;
				text.setText("");
			} //- operation
    		if(AE.getSource()==opButtons[3])
			{
    			num1 = Double.parseDouble(text.getText());
				operation = 2;
				text.setText("");
			}//* operation
    		if(AE.getSource()==opButtons[4])
			{
    			num1 = Double.parseDouble(text.getText());
				operation = 3;
				text.setText("");
			}//divide operation
    		if(AE.getSource()==opButtons[5])
			{
    			num1 = Double.parseDouble(text.getText());
				operation = 4;
				text.setText("");
			}//= equals operation, depending on which operation has been set it will go to that option
    		if(AE.getSource()==opButtons[1])
			{
    			num2 = Double.parseDouble(text.getText());
    			if(operation == 1){
    				sum();
    			} else if(operation == 2){
    				subt();
    			} else if(operation == 3){
    				mult();
    			} else if(operation == 4){
    				div();
    			}
    			operation = 0;
    			text.setText(""+ans);
			}
		}
    	//add method which connects to the interface
		public void sum()
		{
			try
			{
				ICalculatorRemote calculator=(ICalculatorRemote)Naming.lookup("rmi://localhost:1099/calculator");
				ans=calculator.addition(num1,num2);
			}
			catch(Exception ex)
			{
				System.out.println("Exception:"+ex);
			}
		}
		//Subtract method connects to interface
		public void subt()
		{
			try
			{
				ICalculatorRemote calculator=(ICalculatorRemote)Naming.lookup("rmi://localhost:1099/calculator");
				ans=calculator.soustraction(num1,num2);
			}
			catch(Exception ex)
			{
				System.out.println("Exception:"+ex);
			}
		}
		//Multiply method connects to interface
		public void mult()
		{
			try
			{
				ICalculatorRemote calculator=(ICalculatorRemote)Naming.lookup("rmi://localhost:1099/calculator");
				ans=calculator.multiplication(num1,num2);
			}
			catch(Exception ex)
			{
				System.out.println("Exception:"+ex);
			}
		}
		//Div method which links to the interface
		public void div()
		{
			try
			{
				ICalculatorRemote calculator=(ICalculatorRemote)Naming.lookup("rmi://localhost:1099/calculator");
				ans=calculator.division(num1,num2);
			}
			catch(Exception ex)
			{
				System.out.println("Exception:"+ex);
			}
		}
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(new CalculatorClient(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        
    }
    
}
