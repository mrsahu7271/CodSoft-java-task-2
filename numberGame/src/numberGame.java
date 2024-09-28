import java.util.Random;
import java.util.Scanner;

public class numberGame {

    // Method to generate a random number between the specified range
    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Method to run a single round of the game
    public static int playRound(int maxAttempts, int min, int max) {
        int randomNumber = generateRandomNumber(min, max);
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int guess;

        System.out.println("A random number has been generated between " + min + " and " + max + ". Can you guess it?");

        // Loop until the user guesses the number or runs out of attempts
        while (attempts < maxAttempts) {
            attempts++;
            System.out.print("Attempt " + attempts + ": Enter your guess: ");
            guess = scanner.nextInt();

            if (guess == randomNumber) {
                System.out.println("Congratulations! You've guessed the correct number.");
                return attempts;
            } else if (guess > randomNumber) {
                System.out.println("Your guess is too high.");
            } else {
                System.out.println("Your guess is too low.");
            }
        }

        System.out.println("Sorry! You've used all your attempts. The correct number was: " + randomNumber);
        return attempts;
    }

    // Main game loop
    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        int min = 1;
        int max = 100;
        int maxAttempts = 10;
        boolean playAgain = true;
        int roundsWon = 0;
        int totalRounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        // Main game loop that runs multiple rounds
        while (playAgain) {
            totalRounds++;
            int attemptsUsed = playRound(maxAttempts, min, max);
            totalAttempts += attemptsUsed;

            if (attemptsUsed <= maxAttempts) {
                roundsWon++;
            }

            System.out.println("Your score so far: " + roundsWon + "/" + totalRounds + " rounds won.");
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();

            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Game Over!");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Total Attempts: " + totalAttempts);
        System.out.println("Average Attempts Per Round: " + (double) totalAttempts / totalRounds);
    }

    public static void main(String[] args) {
        startGame();
    }
}
