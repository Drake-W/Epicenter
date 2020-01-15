/*
 * Epicenter 
 * C308
 * Drake Wood
 * Brian Carlson
 * Daniel Gruza
 */

package com.Epicenter.main;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.applet.AudioClip;
import java.awt.BorderLayout;

public class StartMenu extends JFrame
{
	private File file = new File("Poop in my soup.wav");
	private boolean musicOn = false;
	Clip musicClip;
	ImageIcon volumeon = new ImageIcon( "Volumeon.png" , "volumeon");
	ImageIcon volumeoff = new ImageIcon( "Volumeoff.png" , "volumeoff");
	ImageIcon hangman = new ImageIcon( "Hangman.png" , "hangman");
	
	public StartMenu(){

		JLabel panel;
        SpringLayout layout = new SpringLayout();
		setTitle("Epicenter");
		setPreferredSize(new Dimension(826, 666));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(getSize());
		pack();
		setLocationRelativeTo(null);
		BufferedImage back = null;
		try {
		    back = ImageIO.read(new File("Background.png"));
		} catch (IOException e) {
	}
		
		panel = new JLabel(new ImageIcon(back));
		panel.setLayout(layout);
				
		JButton b1 = new JButton();
		  try {
			    Image checkimg = ImageIO.read(new File("Checkers.png"));
			    b1.setIcon(new ImageIcon(checkimg));
			  } catch (Exception ex) {
			    System.out.println(ex);
			  }
		b1.setPreferredSize(new Dimension(241,114));
		
		JButton b2 = new JButton();
		  try {
			    Image ticimg = ImageIO.read(new File("TicTacToe.png"));
			    b2.setIcon(new ImageIcon(ticimg));
			  } catch (Exception ex) {
			    System.out.println(ex);
			  }
		  b2.setPreferredSize(new Dimension(241,114));
		  
		getContentPane().add(panel);
		setVisible(true);
		
		JButton btnMute = new JButton();
		btnMute.setIcon(volumeoff);
		btnMute.setPreferredSize(new Dimension(114,114));
		
		JButton b3 = new JButton();
		b3.setIcon(hangman);
		b3.setPreferredSize(new Dimension(241,114));
		
		
		panel.add(btnMute);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		
		
		layout.putConstraint(SpringLayout.WEST, b1, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, b1, 175, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, b2, 441, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, b2, 175, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, btnMute, 40, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, btnMute, 475, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, b3, 275, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, b3, 350, SpringLayout.NORTH, panel);
		
	
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(musicOn)
					{
						musicClip.stop();
						musicClip.close();
						btnMute.setIcon(volumeoff);
						musicOn = false;
					}
					else{
						
					musicClip = AudioSystem.getClip();
					
					AudioInputStream ais = AudioSystem.getAudioInputStream(file);
					musicClip.open(ais);
					musicClip.start();
					musicOn = true;
					btnMute.setIcon(volumeon);
					}
					} catch (Exception ex) {
					 System.err.println(ex.getMessage());
				}
			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TicTacToe();
				
			}
		});
				b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Checkers();
			}
		});	
				b3.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						new Hangman();
					}
				});	
	}

	public static void main(String[] args)
	{
		new StartMenu();
	}
}

