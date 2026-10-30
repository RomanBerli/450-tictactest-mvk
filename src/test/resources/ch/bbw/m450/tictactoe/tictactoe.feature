Feature: TicTacToe Game Logic
  As a TicTacToe player
  I want the game to follow standard TicTacToe rules
  So that a winner or a draw is determined accurately

  Scenario: GreedyPlayer selects the first free top-left position
    Given a board with state "XO-|---|---"
    When the GreedyPlayer chooses a move for "X"
    Then the chosen position should be 2

  Scenario: Perfect player Maxxer never loses against GreedyPlayer
    Given a game between "GreedyPlayer" as X and "Maxxer" as O
    When the game is played to the end
    Then the winner should be "O"
