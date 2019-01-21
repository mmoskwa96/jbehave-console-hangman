
Scenario: Show winning result.

Given mistakesMade < difficultyLevel
When User guess all letters of the key_word and there is none of „-„ in the key-word
Then „Word guessed!” screen should appear


Scenario: Show losing result.

Given mistakesMade = difficultyLevel-1
When User commit losing action.
Then „Word not guessed!” screen should appear