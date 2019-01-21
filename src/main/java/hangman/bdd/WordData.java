package hangman.bdd;

import java.util.ArrayList;
import java.util.List;

public class WordData {
    private String word;
    private String hiddenWord;
    private List<Character> guessedCharacters;
    private List<Character> incorrectCharacters;


    public WordData(String word) {
        this.word = word;
        this.hiddenWord = word.replaceAll(".", "-");
        this.guessedCharacters = new ArrayList<>();
        this.incorrectCharacters = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHiddenWord() {
        return this.hiddenWord;
    }

    public List<Character> getGuessedCharacters() {
        return guessedCharacters;
    }

    public List<Character> getIncorrectCharacters() {
        return incorrectCharacters;
    }

    public boolean checkIfCorrectCharacter(Character character) {
        if (this.word.indexOf(character) == -1) {
            this.incorrectCharacters.add(character);
            return false;
        }
        else {
            this.guessedCharacters.add(character);
            generateHiddenWord();
            return true;
        }
    }

    public boolean checkIfGuessedBefore(Character character) {
        return guessedCharacters.contains(character);
    }

    public boolean checkIfIncorrectBefore(Character character) {
        return incorrectCharacters.contains(character);
    }

    public boolean checkIfWordGuessed() {
        return this.word.equals(this.hiddenWord);
    }

    private void generateHiddenWord() {
        char[] hiddenWordCharacters = new char[word.length()];
        char[] wordCharacters = word.toCharArray();

        for (int i = 0; i < wordCharacters.length; i++) {
            if (this.guessedCharacters.contains(wordCharacters[i])) {
                hiddenWordCharacters[i] = wordCharacters[i];
            }
            else {
                hiddenWordCharacters[i] = '-';
            }
        }

        this.hiddenWord = new String(hiddenWordCharacters);
    }
}
