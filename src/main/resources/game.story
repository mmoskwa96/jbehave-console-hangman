
Scenario: Start a game.

Given I am the user and I am in key_word screen
When I type the key_word, choose difficulty and press enter
Then game should start
And user should see key_word characters change to „-„
And user should see HangmanBoard
And user should see GuessedCharactersBoard
And user should see IncorrectCharactersBoard
And user should see RemainingMistakesBoard


Scenario: Guess letter of the key_word.

Given I am the user and I am in main screen
When I use matching character
Then Winning action should occur
When I use non matching character
Then Losing action should occur



