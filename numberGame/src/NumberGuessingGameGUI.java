import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private Random random = new Random();
    private int randomNumber;
    private int maxAttempts = 10;
    private int attempts;
    private int roundsWon = 0;
    private int totalRounds = 0;

    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JButton newGameButton;
    private JLabel scoreLabel;

    public NumberGuessingGameGUI() {
        // Initialize the game window
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Add instruction label
        instructionLabel = new JLabel("Guess the number between 1 and 100:");
        add(instructionLabel);

        // Add input field for the user to enter the guess
        guessField = new JTextField();
        add(guessField);

        // Add button to submit the guess
        guessButton = new JButton("Guess");
        add(guessButton);

        // Add label for feedback
        feedbackLabel = new JLabel("");
        add(feedbackLabel);

        // Add label for score display
        scoreLabel = new JLabel("Rounds Won: 0 | Total Rounds: 0");
        add(scoreLabel);

        // Add button for a new game
        newGameButton = new JButton("New Game");
        newGameButton.setEnabled(false);
        add(newGameButton);

        // Add action listeners
        guessButton.addActionListener(new GuessButtonListener());
        newGameButton.addActionListener(new NewGameButtonListener());

        // Start the game
        startNewRound();
    }

    // Method to start a new round
    private void startNewRound() {
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        newGameButton.setEnabled(false);
    }

    // Action listener for guessing
    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessText = guessField.getText();
            try {
                int guess = Integer.parseInt(guessText);
                attempts++;

                if (guess == randomNumber) {
                    feedbackLabel.setText("Correct! You guessed the number.");
                    roundsWon++;
                    endRound();
                } else if (guess > randomNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Too low! Try again.");
                }

                if (attempts >= maxAttempts) {
                    feedbackLabel.setText("Out of attempts! The correct number was: " + randomNumber);
                    endRound();
                }

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    // Method to end the round
    private void endRound() {
        totalRounds++;
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
        newGameButton.setEnabled(true);
        updateScore();
    }

    // Update the score display
    private void updateScore() {
        scoreLabel.setText("Rounds Won: " + roundsWon + " | Total Rounds: " + totalRounds);
    }

    // Action listener for starting a new game
    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewRound();
        }
    }

    public static void main(String[] args) {
        // Create and display the game window
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI game = new NumberGuessingGameGUI();
            game.setVisible(true);
        });
    }
}
