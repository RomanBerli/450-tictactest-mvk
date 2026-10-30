package ch.bbw.m450.tictactoe;

import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;
import ch.bbw.m450.tictactoe.players.GreedyPlayer;
import ch.bbw.m450.tictactoe.players.Maxxer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.WithAssertions;

public class TicTacToeStepDefinitions implements WithAssertions {

    private Stone[] board;
    private int chosenMove;
    private TicTacToePlayer xPlayer;
    private TicTacToePlayer oPlayer;
    private Stone winner;

    @Given("a board with state {string}")
    public void aBoardWithState(String boardString) {
        board = BoardCreator.toBoard(boardString);
    }

    @When("the GreedyPlayer chooses a move for {string}")
    public void theGreedyPlayerChoosesAMoveFor(String symbol) {
        var stone = parseStone(symbol);
        var player = new GreedyPlayer();
        chosenMove = player.play(board, stone);
    }

    @Then("the chosen position should be {int}")
    public void theChosenPositionShouldBe(int expectedPosition) {
        assertThat(chosenMove).isEqualTo(expectedPosition);
    }

    @Given("a game between {string} as X and {string} as O")
    public void aGameBetweenAsXAndAsO(String xPlayerName, String oPlayerName) {
        xPlayer = createPlayer(xPlayerName);
        oPlayer = createPlayer(oPlayerName);
    }

    @When("the game is played to the end")
    public void theGameIsPlayedToTheEnd() {
        winner = TicTacToeMain.play(xPlayer, oPlayer);
    }

    @Then("the winner should be {string}")
    public void theWinnerShouldBe(String expectedSymbol) {
        var expectedStone = parseStone(expectedSymbol);
        assertThat(winner).isEqualTo(expectedStone);
    }

    private Stone parseStone(String symbol) {
        return switch (symbol) {
            case "X" -> Stone.CROSS;
            case "O" -> Stone.CIRCLE;
            default -> throw new IllegalArgumentException("Unknown stone: " + symbol);
        };
    }

    private TicTacToePlayer createPlayer(String name) {
        return switch (name) {
            case "GreedyPlayer" -> new GreedyPlayer();
            case "Maxxer" -> new Maxxer();
            default -> throw new IllegalArgumentException("Unknown player: " + name);
        };
    }
}
