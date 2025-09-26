package examples;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int totalSum = 0;
    private static int doubleCount = 0;
    private static int maxSum = 0;
    private static int[] faceCounts = new int[6];

    public static void main(String[] args) {
        System.out.println("Welcome to the game, roll two dice.");
        printRules();
        System.out.println();

        playTwoDice();

        System.out.println();
        System.out.println("Thanks for playing, roll two dice.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Rules for Roll Two Dice");
        System.out.println("The player rolls two dice as long as they want.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Roll two dice? ('yes/no') ");
        String answer = scanner.nextLine();

        while (!answer.equals("no")) {
            int[] dice = rollDice();
            int sum = dice[0] + dice[1];

            System.out.println("You rolled: " + dice[0] + " and " + dice[1] + " (sum: " + sum + ")");
            System.out.println();

            updateStatistics(sum, dice);

            System.out.print("Roll two dice? ('yes/no') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    public static int[] rollDice() {
        int[] dice = new int[2];
        dice[0] = (int) (Math.random() * 6 + 1);
        dice[1] = (int) (Math.random() * 6 + 1);
        return dice;
    }

    private static void updateStatistics(int sum, int[] dice) {
        rollCount++;
        totalSum += sum;

        if (dice[0] == dice[1]) {
            doubleCount++;
        }
        if (sum > maxSum) {
            maxSum = sum;
        }

        faceCounts[dice[0] - 1]++;
        faceCounts[dice[1] - 1]++;
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%20s %4d\n", "Number of rolls:", rollCount);
        System.out.printf("%20s %4d\n", "Total sum:", totalSum);
        System.out.printf("%20s %4d\n", "Number of doubles:", doubleCount);
        System.out.printf("%20s %4d\n", "Highest sum:", maxSum);

        System.out.println("\nFace distribution:");
        for (int i = 0; i < faceCounts.length; i++) {
            System.out.printf("%10d’s: %4d\n", i + 1, faceCounts[i]);
        }
    }
}