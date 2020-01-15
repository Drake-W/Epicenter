package com.Epicenter.main;

/*
 * Epicenter 
 * C308
 * Drake Wood
 * Brian Carlson
 * Daniel Gruza
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe extends JFrame implements ActionListener {
	private JLabel status = new JLabel("Welcome to TicTacToe X goes first.");
	private static JButton buttons[] = new JButton[9]; // create 9 buttons

	public static final int WIDTH = 400;
	public static final int HEIGHT = 435;
	int whosTurn = 0; // even X, odd O
	int totalSteps = 0;

	public static void main(String[] args) {
		new TicTacToe();
	}

	TicTacToe() {
		super("Tic Tac Toe");
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel biggerPanel = new JPanel();
		biggerPanel.setLayout(new GridLayout(3, 3));

		for (int i = 0; i <= 8; i++) { // placing the buttons onto the board
			buttons[i] = new JButton();
			buttons[i].setText("");
			buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
			buttons[i].setBackground(Color.ORANGE);
			buttons[i].addActionListener(this);
			biggerPanel.add(buttons[i]);
		}
		add(biggerPanel, BorderLayout.CENTER);

		JPanel buttonPanel1 = new JPanel(); // panel for the reset button
		buttonPanel1.setBackground(Color.gray);
		buttonPanel1.setLayout(new FlowLayout());

		JButton resetButton = new JButton("Reset");
		resetButton.setBackground(Color.RED); // create reset button
		resetButton.addActionListener(this);
		buttonPanel1.add(resetButton);

		add(buttonPanel1, BorderLayout.SOUTH);

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());

		labelPanel.add(status);
		add(labelPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();

		if (buttonString.equals("Reset")) {
			for (int i = 0; i <= 8; i++) { // resetting each button
				buttons[i].setText("");
				buttons[i].setEnabled(true);
				status.setText("Welcome to TicTacToe X goes first.");
			}
			whosTurn = 0; // even X, odd O
			totalSteps = 0;
		} else {

			JButton buttonClicked = (JButton) e.getSource();
			if (buttonString.equals("")) { // clicks on an empty button
				if (whosTurn % 2 == 0) {
					buttonClicked.setText("X");
					status.setText("O's turn");
					whosTurn += 1;
					totalSteps++;
				} else {
					buttonClicked.setText("O");
					status.setText("X's turn");
					whosTurn += 1;
					totalSteps++;
				}
			}
		}
		if (checkWin()) {
			if (whosTurn % 2 == 0)
				status.setText("O wins!"); // if o was placed last then o wins
			else
				status.setText("X wins!"); // if x was placed last then x wins

			for (int i = 0; i <= 8; i++) { // placing the button onto the board
				buttons[i].setEnabled(false);
			}
		}

		if (totalSteps == 9) { // if all 9 of the game tiles are changed then it
								// is a tie.
			status.setText("Tie Game!");
			for (int i = 0; i <= 8; i++) { // disables the buttons until reset
											// button is pressed
				buttons[i].setEnabled(false);
			}
		}
	}

	private boolean checkWin() { // check each combination for a win
		if (check(0, 1) && check(1, 2))
			return true;
		else if (check(3, 4) && check(4, 5))
			return true;
		else if (check(6, 7) && check(7, 8))
			return true;
		else if (check(0, 3) && check(3, 6))
			return true;
		else if (check(1, 4) && check(4, 7))
			return true;
		else if (check(2, 5) && check(5, 8))
			return true;
		else if (check(0, 4) && check(4, 8))
			return true;
		else if (check(2, 4) && check(4, 6))
			return true;
		else
			return false;
	}

	public boolean check(int a, int b) { // compare each for a match but not an
											// empty space
		if (buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals(""))
			return true;
		else
			return false;
	}
}