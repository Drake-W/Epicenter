/*
 * Epicenter 
 * C308
 * Drake Wood
 * Brian Carlson
 * Daniel Gruza
 */
package com.Epicenter.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Checkers extends JFrame implements ActionListener{ 
	private static JButton buttons[] = new JButton[64]; // create 64 buttons
	
	ImageIcon b = new ImageIcon( "b.png" , "b");
	ImageIcon kb = new ImageIcon( "kb.png" , "kb");
	ImageIcon kr = new ImageIcon( "kr.png" , "kr");
	ImageIcon r = new ImageIcon( "r.png" , "r");
	ImageIcon r2 = new ImageIcon( "r.png" , "r2");
	ImageIcon rb = new ImageIcon( "rb.png" , "rb");
	ImageIcon rr = new ImageIcon( "rr.png" , "rr");


   public Checkers() {
	   //button numbers that need to start at each color
	   	int[] arr = {1,3,5,7,10,12,14,16,17,19,21,23};
	   	int[] arb = {42,44,46,48,49,51,53,55,58,60,62,64};
	   	int[] ar = {26,28,30,32,33,35,37,39};
	   	
	    //setVisible(true);
	    JFrame checkerBoard = new JFrame(); 
	    checkerBoard.setSize(800, 800); 
	    checkerBoard.setVisible(true);
	    checkerBoard.setTitle("CheckerBoard"); 
	    checkerBoard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		checkerBoard.setResizable(false);
		checkerBoard.setLocationRelativeTo(null);
	    int row = 8; 
	    int col = 8; 

	    Container pane = checkerBoard.getContentPane(); 
	    pane.setLayout(new GridLayout(row, col)); 
	
	    for (int i = 0; i <64; i++){
	    	buttons[i] = new JButton();
			pane.add(buttons[i]);
			buttons[i].addActionListener(this);
	    }

	  pack();
	    for (int i = 0; i <64; i++){
	    	buttons[i].setIcon(b);
	    }
	    for (int i = 0; i <12; i++){
	    	buttons[arr[i]-1].setIcon(rr);
	    	buttons[arb[i]-1].setIcon(rb);
	    }
	    for (int i = 0; i <8; i++){
	    	buttons[ar[i]-1].setIcon(r);
	    }
   }


public static void main(String[] args)
   {
	   new Checkers();
   }


@Override
public void actionPerformed(ActionEvent e) {
	//cycles through possible button images on press
	JButton buttonClicked = (JButton) e.getSource();
	if (buttonClicked.getIcon() == rb){
		buttonClicked.setIcon(r);
	}else
	if (buttonClicked.getIcon() == r){
		buttonClicked.setIcon(rr);
	}else
	if (buttonClicked.getIcon() == rr){
		buttonClicked.setIcon(r2);
	}else
	if (buttonClicked.getIcon() == r2){
		buttonClicked.setIcon(rb);
	}
}
}

