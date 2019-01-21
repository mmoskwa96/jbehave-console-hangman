package hangman.bdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class GameResultTests {
    private GameLogic gameLogic;
    private GameLogicResponse gameLogicResponse;

    @Given("mistakesMade < difficultyLevel")
    public void mistakesMadeLesserThanDiffLevel() {
        gameLogic = new GameLogic();
        gameLogic.setWordData(new WordData("a"));
        gameLogic.setMistakesMade(0);
        gameLogic.setDifficultyLevel(2);
    }

    @When("User guess all letters of the key_word and there is none of „-„ in the key-word")
    public void winningAction() {
        gameLogicResponse = gameLogic.insertCharacter('a');
    }

    @Then("„Word guessed!” screen should appear")
    public void wordGuessed() {
        assertEquals(3, gameLogicResponse.getResponseCode());
        assertTrue(gameLogicResponse.getMessage().contains("guessed!"));
    }

    /* Another scenario */

    @Given("mistakesMade = difficultyLevel-1")
    public void mistakesOneLesserThanDiffLevel() {
        gameLogic = new GameLogic();
        gameLogic.setWordData(new WordData("a"));
        gameLogic.setMistakesMade(0);
        gameLogic.setDifficultyLevel(1);
    }

    @When("User commit losing action.")
    public void losingAction() {
        gameLogicResponse = gameLogic.insertCharacter('c');
    }

    @Then("„Word not guessed!” screen should appear")
    public void wordNotGuessed() {
        assertEquals(1, gameLogicResponse.getResponseCode());
        assertTrue(gameLogicResponse.getMessage().contains("not guessed"));
    }
}
