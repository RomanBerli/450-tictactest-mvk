package ch.bbw.m450.tictactoe;

import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;

public final class BoardCreator {


    public static TicTacToePlayer.Stone[] toBoard(String list) {
        var cleanList = list.replace("|", "").toUpperCase();

        if (cleanList.length() != 9) {
            throw new IllegalArgumentException("list too long/short. Need exact board!");
        }

        return cleanList.chars()
                .mapToObj(c -> switch (c) {
                    case 'X' -> CROSS;
                    case 'O' -> CIRCLE;
                    default -> null;
                })
                .toArray(TicTacToePlayer.Stone[]::new);
    }
}
