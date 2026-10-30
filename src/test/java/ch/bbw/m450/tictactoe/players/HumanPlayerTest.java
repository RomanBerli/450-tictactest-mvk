package ch.bbw.m450.tictactoe.players;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static ch.bbw.m450.tictactoe.BoardCreator.toBoard;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;

class HumanPlayerTest implements WithAssertions {

    private final HumanPlayer humanPlayer = new HumanPlayer();

    @Test
    @StdIo("4")
    void readsMoveFromStandardInputAndPrintsPrompt(StdOut out) {
        var board = toBoard("X--|---|--O");

        var move = humanPlayer.play(board, CROSS);

        assertThat(move).isEqualTo(4);
        assertThat(out.capturedString())
                .contains("where to to put the next CROSS? (0-8):");
    }

    @Test
    @StdIo("8")
    void printsCorrectColorPromptForCircle(StdOut out) {
        var board = toBoard("X--|---|---");

        var move = humanPlayer.play(board, CIRCLE);

        assertThat(move).isEqualTo(8);
        assertThat(out.capturedString())
                .contains("where to to put the next CIRCLE? (0-8):");
    }

    @Test
    @StdIo("not-a-number")
    void throwsNumberFormatExceptionOnInvalidInput() {
        var board = toBoard("---|---|---");

        assertThatThrownBy(() -> humanPlayer.play(board, CROSS))
                .isInstanceOf(NumberFormatException.class);
    }
}
