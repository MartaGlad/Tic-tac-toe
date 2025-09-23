package com.kodilla.tictactoe;

public interface ResultCounter {
    boolean isWinner(char[][] array, char c);
    boolean isDraw(char[][] array);
}
