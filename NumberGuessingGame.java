package oasis_infobyte;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {
    private JTextArea outputTextArea;

    public NumberGuessingGame() {
        outputTextArea = new JTextArea(30, 50);
        outputTextArea.setEditable(false);

        JButton startButton = new JButton("Start New Game");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(startButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void startNewGame() {
        outputTextArea.setText("Welcome to the Number Guessing Game :) \nA random number will be generated between 1 to 100 \nYou'll get 5 attempts to guess the correct answer \n\n");
        playGame();
    }

    public void playGame() {
        Random random = new Random();
        int generatedNum = random.nextInt(1, 101);
        int totalAttempts = 5;
        int score = 0;
        boolean numFound = false;

        while (!numFound && totalAttempts > 0) {
            String input = JOptionPane.showInputDialog("Enter your guess:");
            if (input == null || input.isEmpty()) {
                break;
            }

            int guess = Integer.parseInt(input);

            if (guess == generatedNum) {
                numFound = true;
                outputTextArea.append("Congrats! You found the correct answer\n");
                score += 1;
                break;
            } else if (guess > generatedNum) {
                outputTextArea.append("Too high! Try a lower number\n");
                totalAttempts--;
            } else {
                outputTextArea.append("Too low! Try a higher number\n");
                totalAttempts--;
            }

            if (totalAttempts == 0) {
                outputTextArea.append("\nYou exhausted all your attempts\n"
                        + "The correct number was " + generatedNum + "\n"
                        + "Your score is " + score + "\n");
                JOptionPane.showMessageDialog(this, "Game Over. You used all your attempts.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
