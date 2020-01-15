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
    import javax.swing.*;


public class HangmanGUI {

    static JFrame frame;
    static JTextField textField;
    static JLabel guessesRemainingLabel;
    static JLabel lettersGuessedLabel;
    static JLabel wordLabel;

    // buildGUI - builds the GUI
    static void buildGUI(){
        SwingUtilities.invokeLater(
                new Runnable(){
                    @Override
                    public void run(){
                        frame = new JFrame("Hangman");

                        // JLabels
                        guessesRemainingLabel = new JLabel("Guesses remaining: " + String.valueOf(Hangman.guessesRemaining));
                        lettersGuessedLabel = new JLabel("Already guessed: ");
                        wordLabel = new JLabel();

                        // TextField & checkButton
                        textField = new JTextField();
                        JButton checkButton = new JButton("Guess");
                        GuessListener guessListener = new GuessListener();
                        checkButton.addActionListener(guessListener);
                        textField.addActionListener(guessListener);

                        // Panel for all the labels
                        JPanel labelPanel = new JPanel();
                        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
                        labelPanel.add(guessesRemainingLabel);
                        labelPanel.add(lettersGuessedLabel);
                        labelPanel.add(wordLabel);

                        // User panel
                        JPanel userPanel = new JPanel(new BorderLayout());
                        userPanel.add(BorderLayout.CENTER, textField);
                        userPanel.add(BorderLayout.EAST, checkButton);
                        labelPanel.add(userPanel);

                        // Add everything to frame
                        frame.add(BorderLayout.CENTER, labelPanel);
                        frame.setSize(500, 250);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
        );
    }

    // drawGuessesRemaining - Outputs the guesses remaining
    static void drawGuessesRemaining(){
        final String guessesMessage = "Guesses remaining: " + String.valueOf(Hangman.guessesRemaining);
        SwingUtilities.invokeLater(
                new Runnable(){
                    @Override
                    public void run(){
                        guessesRemainingLabel.setText(guessesMessage);
                        guessesRemainingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    }
                }
        );
    }

    // drawLettersGuessed - Outputs the letters guessed
    static void drawLettersGuessed(){
        StringBuilder lettersBuilder = new StringBuilder();
        for (Character el : Hangman.lettersGuessed) {
        	String s = el + " ";
            lettersBuilder.append(s);
        }

        final String letters = lettersBuilder.toString();
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        lettersGuessedLabel.setText("Letters guessed: " + letters);
                    }
                }
        );
    }

    // drawWord - draws the secret word with dashes & etc for user to use to guess the word with
    static void drawWord(){
        StringBuilder word = new StringBuilder();
        for(int i=0; i<Hangman.lettersRevealed.length; i++){

            if (Hangman.lettersRevealed[i]) {
                String s = Hangman.targetWord.charAt(i) + " ";
                word.append(s);
            } 
            
            else {
                word.append("_ ");
            }
        }

        final String w = word.toString();
        SwingUtilities.invokeLater(
                new Runnable(){
                    @Override
                    public void run() {
                        wordLabel.setText(w);
                    }
                }
        );
    }

    //playAgainDialog - shows the confirm w
    static int playAgainDialog(String m){
        return JOptionPane.showConfirmDialog(frame, m, "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    // GETTERS
    private static String getText(){
        return textField.getText();
    }

    // SETTERS
    static void setText(final String t){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        textField.setText(t);
                    }
                }
        );
    }

    // ActionListener
    private static class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev){
            String guess = getText();

            if(Hangman.checkUserGuess(guess)) {
                Hangman.updateWord(guess);
                drawWord();

                if(Hangman.lettersGuessed.size() != 0)      // No letters have been guessed by the user at the beginning
                    drawLettersGuessed();

                // Checks if the user has won or lost
                if (Hangman.checkIfWon())
                    Hangman.winGame();
                
                else if (Hangman.guessesRemaining == 0)
                    Hangman.loseGame();
            }
        }
    }
}