package com.kodilla.tictactoe;


public class ResultCounterOX implements ResultCounter {
    private int size = 3;

    private final char EMPTY = ' ';


    @Override
    public boolean isWinner(char[][] array, char c) {
        for(int i = 0; i < size; i++) {
            if(array[i][0] == c && array[i][1] == c && array[i][2] == c) return true;
        }
        for(int j = 0; j < size; j++) {
            if (array[0][j] == c && array[1][j] == c && array[2][j] == c) return true;
        }
        if (array[0][0] == c && array[1][1] == c && array[2][2] == c) return true;
        if (array[0][2] == c && array[1][1] == c && array[2][0] == c) return true;

        return false;
    }

    @Override
    public boolean isDraw(char[][] array) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (array[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

}
