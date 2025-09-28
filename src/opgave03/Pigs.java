package opgave03;

import java.util.Scanner;
import examples.RollTwoDice;

public class Pigs {
    public static void main(String[] args) {
        printRules();
        playPigs();
    }

    public static void playPigs() {
        Scanner scanner = new Scanner(System.in);

        int targetScore = chooseTargetScore(scanner);
        int player1Score = 0;
        int player2Score = 0;
        boolean player1Turn = true;

        while (player1Score < targetScore && player2Score < targetScore) {
            if (player1Turn) {
                player1Score = takeTurn("Player 1", player1Score, scanner);
            } else {
                player2Score = takeTurn("Player 2", player2Score, scanner);
            }
            player1Turn = !player1Turn;
        }

        System.out.println("=====================================================");
        System.out.println("Final scores: Player 1 = " + player1Score + ", Player 2 = " + player2Score);
        if (player1Score >= targetScore) {
            System.out.println("🎉 Player 1 wins!");
        } else {
            System.out.println("🎉 Player 2 wins!");
        }
        System.out.println("=====================================================");

        scanner.close();
    }

    public static int takeTurn(String playerName, int currentScore, Scanner scanner) {
        System.out.println(playerName + "'s turn.");
        int roundResult = playerTurn(scanner);

        if (roundResult == -1) {
            System.out.println("💀 " + playerName + " rolled (1,1) and lost ALL points!");
            currentScore = 0;
        } else {
            currentScore += roundResult;
            System.out.println(playerName + "'s total score: " + currentScore);
        }
        return currentScore;
    }

    public static int chooseTargetScore(Scanner scanner) {
        System.out.print("Enter the target score to play to (default is 100): ");
        String input = scanner.nextLine().trim();
        try {
            int targetScore = Integer.parseInt(input);
            if (targetScore > 0) {
                return targetScore;
            } else {
                System.out.println("Invalid input. Using default target score of 100.");
            }
        } catch (NumberFormatException e) {
            if (!input.isEmpty()) {
                System.out.println("Invalid input. Using default target score of 100.");
            }
        }
        return 100;
    }

    public static int playerTurn(Scanner scanner) {
        int roundScore = 0;
        while (true) {
            System.out.print("Roll the dice? (yes/no): ");
            String answer = scanner.nextLine().trim();
            if (!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))) {
                break;
            }

            int[] dice = RollTwoDice.rollDice();
            int die1 = dice[0];
            int die2 = dice[1];

            System.out.println("You rolled: (" + die1 + ", " + die2 + ")");

            if (die1 == 1 && die2 == 1) {
                return -1;
            } else if (die1 == 1 || die2 == 1) {
                System.out.println("Rolled a 1! No points this round.");
                roundScore = 0;
                break;
            } else {
                roundScore += die1 + die2;
                System.out.println("Current round score: " + roundScore);
            }
        }
        return roundScore;
    }

    public static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Welcome to the game of Pigs with TWO dice!");
        System.out.println("Rules:");
        System.out.println("Two players take turns rolling two dice.");
        System.out.println(" - If both dice show 1 (snake eyes), the player loses ALL points accumulated so far.");
        System.out.println(" - If either die shows 1, the player loses only the points from this round.");
        System.out.println(" - Otherwise, the sum of the dice is added to the round score.");
        System.out.println("A player can stop rolling anytime to keep their round score.");
        System.out.println("The first player to reach the target score wins.");
        System.out.println("=====================================================");
    }
}
