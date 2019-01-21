package hangman.bdd;

import java.util.Scanner;

public class Controller {
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.print("Enter a word to guess: ");
        String word = scanner.nextLine();
        while (!word.matches("[a-zA-Z_]+")) {
            System.out.print("Incorrect word provided (allowed only big and small ASCII letters and _): ");
            word = scanner.nextLine();
        }

        int difficultyLevel;
        System.out.print("Enter a difficulty level (number of incorrect guesses) [1-10]: ");

        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Incorrect difficulty level provided (value must be numeric): ");
                scanner.next();
            }
            difficultyLevel = scanner.nextInt();
            if (difficultyLevel < 1 || difficultyLevel > 10) {
                System.out.print("Incorrect difficulty level provided (must be in range [1, 10]): ");
            }
        } while (difficultyLevel < 1 || difficultyLevel > 10);

        GameLogic gameLogic = new GameLogic();
        GameLogicResponse response = gameLogic.initialize(word, difficultyLevel);
        System.out.print(response.getMessage());

        boolean stop_condition = true;
        while (stop_condition) {
            System.out.print("Enter a character: ");
//            char c = scanner.next(".").charAt(0);
            String character = scanner.next();
            while (!character.matches("[a-zA-Z_]{1}")) {
                System.out.print("Incorrect character provided (allowed single character - big and small ASCII letters and _): ");
                character = scanner.next();
            }

            response = gameLogic.insertCharacter(character.charAt(0));
            while (response.getResponseCode() == 2) {
                System.out.print(response.getMessage());
                character = scanner.next();
                while (!character.matches("[a-zA-Z_]{1}")) {
                    System.out.print("Incorrect character provided (allowed single character - big and small ASCII letters and _): ");
                    character = scanner.next();
                }
//                char c = scanner.next(".").charAt(0);
                response = gameLogic.insertCharacter(character.charAt(0));
            }

            if (response.getResponseCode() == 1 || response.getResponseCode() == 3) {
                stop_condition = false;
            }
            System.out.print(response.getMessage());
        }
    }
}
