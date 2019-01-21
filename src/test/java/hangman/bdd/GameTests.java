package hangman.bdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class GameTests {
    private String word;
    private int difficultyLevel;
    private GameLogic gameLogic;
    private GameLogicResponse gameLogicResponse;

    @Given("I am the user and I am in key_word screen")
    public void userInKeyWordScreen() {

    }

    @When("I type the key_word, choose difficulty and press enter")
    public void typeKeyWord() {
        word = "testing";
        difficultyLevel = 9;
        gameLogic = new GameLogic();
        gameLogicResponse = gameLogic.initialize(word, difficultyLevel);
    }

    @Then("game should start")
    public void gameShouldStart() {
        assertEquals(0, gameLogicResponse.getResponseCode());
    }

    @Then("user should see key_word characters change to „-„")
    public void keyWordLettersChanged() {
        String hiddenWord = word.replaceAll(".", "-");
        assertTrue(gameLogicResponse.getMessage().contains(hiddenWord));
    }

    @Then("user should see HangmanBoard")
    public void hangmanBoard() {
        assertTrue(gameLogicResponse.getMessage().contains("      ________      "));
    }

    @Then("user should see GuessedCharactersBoard")
    public void guessedCharactersBoard() {
        assertTrue(gameLogicResponse.getMessage().contains("Guessed characters: []"));
    }

    @Then("user should see IncorrectCharactersBoard")
    public void incorrectCharactersBoard() {
        assertTrue(gameLogicResponse.getMessage().contains("Incorrect characters: []"));
    }

    @Then("user should see RemainingMistakesBoard")
    public void remainingMistakesBoard() {
        assertTrue(gameLogicResponse.getMessage().contains("Remaining mistakes to use: "));
    }

    /* Another scenario */

    @Given("I am the user and I am in main screen")
    public void userInMainScreen() {
        gameLogic = new GameLogic();
        gameLogic.setWordData(new WordData("testowanie"));
        gameLogic.setDifficultyLevel(this.difficultyLevel);
        gameLogic.setMistakesMade(0);
    }

    @When("I use matching character")
    public void useMatchingCharacter() {
        gameLogicResponse = gameLogic.insertCharacter('e');
    }

    @Then("Winning action should occur")
    public void winningAction() {
        assertEquals("-e-------e", gameLogic.getWordData().getHiddenWord());
        assertEquals(0, gameLogic.getMistakesMade());
        assertTrue(gameLogicResponse.getMessage().contains("Guessed characters: [ e ]"));
    }

    @When("I use non matching character")
    public void useNonMatchingCharacter() {
        gameLogicResponse = gameLogic.insertCharacter('z');
    }

    @Then("Losing action should occur")
    public void losingAction() {
        assertEquals(1, gameLogic.getMistakesMade());
        assertTrue(gameLogicResponse.getMessage().contains("Incorrect characters: [ z ]"));
    }
}
