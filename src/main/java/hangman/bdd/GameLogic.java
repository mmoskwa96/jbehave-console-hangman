package hangman.bdd;

public class GameLogic {
    private WordData wordData;
    private int difficultyLevel;
    private int mistakesMade;

    public WordData getWordData() {
        return wordData;
    }

    public void setWordData(WordData wordData) {
        this.wordData = wordData;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getMistakesMade() {
        return mistakesMade;
    }

    public void setMistakesMade(int mistakesMade) {
        this.mistakesMade = mistakesMade;
    }

    public GameLogicResponse initialize(String word, int difficultyLevel) {
        // Response codes: 0 - ok;
//        StringBuilder stringBuilder = new StringBuilder();

        this.difficultyLevel = difficultyLevel;
        this.mistakesMade = 0;

        this.wordData = new WordData(word);

        GameLogicResponse response = new GameLogicResponse(getGameInfo(), 0);
        return response;
    }

    public GameLogicResponse insertCharacter(char c) {
        // Response codes: 0 - proceed; 1 - enough mistakes made; 2 - character used before; 3 - word guessed
        StringBuilder stringBuilder = new StringBuilder();

        // check if char was used before
        if (wordData.checkIfGuessedBefore(c)|| wordData.checkIfIncorrectBefore(c)) {
            stringBuilder.append("Character already provided. Please enter another one: ");
            return new GameLogicResponse(stringBuilder.toString(), 2);
        }

        // check if character guessed
        if (!wordData.checkIfCorrectCharacter(c)) {
            mistakesMade++;
            if (mistakesMade == difficultyLevel) {
                stringBuilder.append("\033[H\033[2J");
                stringBuilder.append(getHangmanDetails(difficultyLevel, mistakesMade));
                stringBuilder.append("\nThe word " + wordData.getWord() + " was not guessed. Game over.\n\n");
                return new GameLogicResponse(stringBuilder.toString(), 1);
            }
        }

        // check if word guessed
        if (wordData.checkIfWordGuessed()) {
            stringBuilder.append("\nThe word was " + wordData.getWord() + " guessed!\n\n");
            return new GameLogicResponse(stringBuilder.toString(), 3);
        }

        return new GameLogicResponse(getGameInfo(), 0);
    }

    public String getGameInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\033[H\033[2J");
        stringBuilder.append("Word: " + wordData.getHiddenWord() + "\n");
        stringBuilder.append("Guessed characters: [ ");
        for (Character element : wordData.getGuessedCharacters()) {
            stringBuilder.append(element.charValue() + " ");
        }
        stringBuilder.append("]\n");
        stringBuilder.append("Incorrect characters: [ ");
        for (Character element : wordData.getIncorrectCharacters()) {
            stringBuilder.append(element.charValue() + " ");
        }
        stringBuilder.append("]\n");
        stringBuilder.append("Remaining mistakes to use: " + (this.difficultyLevel - this.mistakesMade) + "\n");
        stringBuilder.append(getHangmanDetails(difficultyLevel, mistakesMade));

        return stringBuilder.toString();
    }

    public String getHangmanDetails(int difficultyLevel, int mistakesMade) {
        StringBuilder stringBuilder = new StringBuilder();
        int hangmanDetails = 10 - difficultyLevel + mistakesMade;
        switch (hangmanDetails) {
            case 0:
                // do nothing
                break;
            case 1:
                for (int i = 0; i < 9; i++) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append("      ________      \n");
                break;
            case 2:
                stringBuilder.append("\n");
                for (int i = 0; i < 8; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 3:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 8; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 4:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                for (int i = 0; i < 6; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 5:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                stringBuilder.append("          |             O\n");
                for (int i = 0; i < 5; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 6:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                stringBuilder.append("          |             O\n");
                stringBuilder.append("          |             |\n");
                stringBuilder.append("          |             |\n");
                for (int i = 0; i < 3; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 7:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                stringBuilder.append("          |             O\n");
                stringBuilder.append("          |             |---<\n");
                stringBuilder.append("          |             |\n");
                for (int i = 0; i < 3; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 8:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                stringBuilder.append("          |             O\n");
                stringBuilder.append("          |         >---|---<\n");
                stringBuilder.append("          |             |\n");
                for (int i = 0; i < 3; i++) {
                    stringBuilder.append("          |          \n");
                }
                stringBuilder.append("      ____|____      \n");
                break;
            case 9:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                stringBuilder.append("          |             O\n");
                stringBuilder.append("          |         >---|---<\n");
                stringBuilder.append("          |             |\n");
                stringBuilder.append("          |            / \n");
                stringBuilder.append("          |          _/\n");
                stringBuilder.append("          |          \n");
                stringBuilder.append("      ____|____      \n");
                break;
            case 10:
                stringBuilder.append("         ________________\n");
                for (int i = 0; i < 2; i++) {
                    stringBuilder.append("          |             |\n");
                }
                stringBuilder.append("          |             O\n");
                stringBuilder.append("          |         >---|---<\n");
                stringBuilder.append("          |             |\n");
                stringBuilder.append("          |            / \\\n");
                stringBuilder.append("          |          _/   \\_\n");
                stringBuilder.append("          |          \n");
                stringBuilder.append("      ____|____      \n");
                break;
        }
        return stringBuilder.toString();
    }
}
