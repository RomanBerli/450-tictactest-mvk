package ch.bbw.m450.tictactoe.players;

import ch.bbw.m450.tictactoe.TicTacToeMain;
import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static ch.bbw.m450.tictactoe.boardCreator.toBoard;

class MaxxerTest implements WithAssertions {

    private Maxxer maxxer;

    @BeforeEach
    void setUp() {
        maxxer = new Maxxer();
    }

    @ParameterizedTest(name = "blocking move for {1} on board {0} should be {2}")
    @CsvSource({
            "OO-|X--|---, CROSS,  2",
            "XX-|-O-|---, CIRCLE, 2",
            "O--|-O-|X--, CROSS,  8",
            "X--|X-O|---, CIRCLE, 6",
            "--X|-X-|--O, CIRCLE, 6"
    })
    void blocksOpponentImmediateWin(String boardStr, Stone stone, int expectedPosition) {
        var board = toBoard(boardStr);
        var move = maxxer.play(board, stone);

        assertThat(move).isEqualTo(expectedPosition);
    }

    @Test
    void doesNotMutateInputBoard() {
        var board = toBoard("X--|-O-|---");
        var snapshot = Arrays.copyOf(board, board.length);

        var move = maxxer.play(board, CROSS);

        assertThat(board).containsExactly(snapshot);
        assertThat(move).isBetween(0, 8);
    }


    @Test
    void twoPerfectPlayersAlwaysDraw() {
        var winner = TicTacToeMain.play(new Maxxer(), new Maxxer());

        assertThat(winner).isNull();
    }

    @Test
    void winsAgainstGreedyPlayerBothAsXAndAsO() {
        var winnerAsX = TicTacToeMain.play(new Maxxer(), new GreedyPlayer());
        var winnerAsO = TicTacToeMain.play(new GreedyPlayer(), new Maxxer());

        assertThat(winnerAsX).isEqualTo(CROSS);
        assertThat(winnerAsO).isEqualTo(CIRCLE);
    }
}
