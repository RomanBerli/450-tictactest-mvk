package ch.bbw.m450.tictactoe;

import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;
import ch.bbw.m450.tictactoe.players.GreedyPlayer;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.concurrent.atomic.AtomicInteger;

import static ch.bbw.m450.tictactoe.BoardCreator.toBoard;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;

class TicTacToeMainTest implements WithAssertions {
    private void assertIsWin(String board, TicTacToePlayer.Stone color) {
        assertThat(TicTacToeMain.isWin(toBoard(board), color)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"OOO|XX-|---", "---|OOO|XX-", "---|XX-|OOO"})
    void detectRowWinForO(String board) {
        assertIsWin(board, CIRCLE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"X0-|X--|X0-", "-X-|OXO|-X-", "--X|--X|OOX"})
    void detectColumnWinForX(String board) {
        assertIsWin(board, CROSS);
    }

    @ParameterizedTest(name = "diagonal win for {1} on {0}")
    @CsvSource({
            "X--|OX-|--X, CROSS",
            "--X|OX-|X--, CROSS",
            "O--|XO-|--O, CIRCLE",
            "--O|XO-|O--, CIRCLE"
    })
    void detectsDiagonalWins(String board, Stone winner) {
        assertIsWin(board, winner);
    }

    @ParameterizedTest(name = "no winner on board {0}")
    @ValueSource(strings = {
            "---|---|---",
            "XOX|XOO|OXX",
            "XXO|OOX|XOX",
            "XO-|OX-|---",
            "X-X|-O-|X-X"
    })
    void detectsNoWinOnIncompleteOrDrawnBoards(String board) {
        assertThat(TicTacToeMain.isWin(toBoard(board), CROSS)).isFalse();
        assertThat(TicTacToeMain.isWin(toBoard(board), CIRCLE)).isFalse();
    }

    @Test
    void stoneOpponentReturnsOppositeColor() {
        assertThat(CROSS.opponent()).isEqualTo(CIRCLE);
        assertThat(CIRCLE.opponent()).isEqualTo(CROSS);
    }

    @Test
    void playThrowsWhenBothPlayersAreSameInstance() {
        var samePlayer = new GreedyPlayer();

        assertThatThrownBy(() -> TicTacToeMain.play(samePlayer, samePlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("players must differ");
    }

    @Test
    @StdIo
    void playPrintsWinnerAnnouncementOnWin(StdOut out) {
        var xMoves = new int[]{0, 1, 2};
        var oMoves = new int[]{3, 4};
        var xIndex = new AtomicInteger();
        var oIndex = new AtomicInteger();

        var winner = TicTacToeMain.play(
                (b, c) -> xMoves[xIndex.getAndIncrement()],
                (b, c) -> oMoves[oIndex.getAndIncrement()]
        );

        assertThat(winner).isEqualTo(CROSS);
        assertThat(out.capturedString()).contains("...and the winner is: CROSS");
    }
}
