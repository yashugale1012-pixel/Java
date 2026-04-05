import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to Number Guessing Game");

        while (playAgain) {

            int number = rand.nextInt(100) + 1;
            int attempts = 7;
            int guess;
            boolean guessedCorrectly = false;

            System.out.println("\nGuess the number between 1 to 100");
            System.out.println("You have " + attempts + " attempts");

            while (attempts > 0) {

                System.out.print("Enter your guess: ");
                guess = sc.nextInt();

                if (guess == number) {
                    System.out.println("Correct! You guessed the number.");
                    score++;
                    guessedCorrectly = true;
                    break;
                }

                else if (guess > number) {
                    System.out.println("Too High");
                }

                else {
                    System.out.println("Too Low");
                }

                attempts--;
                System.out.println("Attempts left: " + attempts);
            }

            if (!guessedCorrectly) {
                System.out.println("You lost. The number was: " + number);
            }

            System.out.println("Your Score: " + score);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nThanks for playing. Final Score: " + score);
        sc.close();
    }
}