package ch.bbw.m450.tictactoe;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static ch.bbw.m450.tictactoe.boardCreator.toBoard;

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
}
