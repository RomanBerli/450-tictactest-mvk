package ch.bbw.m450.tictactoe.players;

import ch.bbw.m450.tictactoe.TicTacToePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ch.bbw.m450.tictactoe.TicTacToeMain.isWin;

public class Maxxer implements TicTacToePlayer {
    private int maxDepth;
    private int savedTurn;

    @Override
    public int play(Stone[] board, Stone colorToPlay) {
        maxDepth = Arrays.stream(board).filter(Objects::isNull).toList().size();
        miniMax(board, colorToPlay, maxDepth);
        return savedTurn;
    }

    private int miniMax(Stone[] board, Stone colorToPlay, int tiefe) {
        if (tiefe == 0) {
            return 0;
        }
        if (isWin(board, colorToPlay.opponent())) {
            return -1;
        }

        int maxWert = -2;
        for (var i : alleLeerenFelder(board)) {
            board[i] = colorToPlay;
            int wert = -miniMax(board, colorToPlay.opponent(), tiefe - 1);
            board[i] = null;

            if (wert > maxWert) {
                maxWert = wert;
                if (tiefe == maxDepth) {
                    savedTurn = i;
                }
            }
        }
        return maxWert;
    }

    private List<Integer> alleLeerenFelder(Stone[] board) {
        List<Integer> leereFelder = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] == null) {
                leereFelder.add(i);
            }
        }
        return leereFelder;
    }

}


