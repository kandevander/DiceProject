package example;

import java.util.Scanner;
import examples.RollTwoDice;

public class Craps {
    public static void main(String[] args) {
        playCraps();
    }

    public static void playCraps() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game of Craps.");

        int wins = 0;
        int losses = 0;
        String playAgain;

        do {
            System.out.print("Roll two dice? ('yes/no') ");
            String answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("yes")) {
                break;
            }

            int[] dice = RollTwoDice.rollDice();
            int sum = dice[0] + dice[1];

            System.out.println("You rolled: " + dice[0] + " and " + dice[1] + " (sum: " + sum + ")");
            System.out.println();

            if (sum == 7 || sum == 11) {
                System.out.println("You win!");
                wins++;
            } else if (sum == 2 || sum == 3 || sum == 12) {
                System.out.println("You lose!");
                losses++;
            } else {
                System.out.println("Your point is: " + sum);
                boolean pointResult = rollforPoint(sum);
                if (pointResult) {
                    System.out.println("You rolled your point! You win!");
                    wins++;
                } else {
                    System.out.println("You rolled a 7 ! You lose!");
                    losses++;
                }
            }

            System.out.print("Do you want to play again? ('yes/no') ");
            playAgain = scanner.nextLine();
            System.out.println();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        System.out.println("Total wins: " + wins);
        System.out.println("Total losses: " + losses);
        scanner.close();
    }

    private static boolean rollforPoint(int point) {
        int sum = point;
        while (true) {
            int[] dice = RollTwoDice.rollDice();
            int newSum = dice[0] + dice[1];
            System.out.println("You rolled: " + dice[0] + " and " + dice[1] + " (sum: " + newSum + ")");
            if (newSum == sum) {
                return true;
            } else if (newSum == 7) {
                return false;
            }
        }
    }
}
