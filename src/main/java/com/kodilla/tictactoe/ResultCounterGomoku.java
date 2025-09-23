package com.kodilla.tictactoe;

public class ResultCounterGomoku extends ResultCounterOX {
    private int size = 10;

    @Override
    public boolean isWinner(char[][] array, char c) {
        int required = 5;

        for (int i = 0; i < size; i++) {
            int counter = 0;
            for (int j = 0; j < size; j++) {
                if (array[i][j] == c) {
                    counter++;
                    if(counter == required) {
                        return true;
                    }
                }
                else counter = 0;
            }
        }

        for (int j = 0; j < size; j++) {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                if (array[i][j] == c) {
                    counter++;
                    if(counter == required) {
                        return true;
                    }
                }
                else counter = 0;
            }
        }

        for (int i = 0; i <= size-required; i++) {   //start points in rows and columns, 5 fields must be placed
            for (int j = 0; j <= size-required; j++) {
                int counter = 0;
                for (int k = 0; k < required; k++) {  //4 steps down
                    if (array[i+k][j+k] == c) {
                        counter++;
                        if (counter == required)
                            return true;
                    } else break;
                }
            }
        }

        for (int i = 0; i <= size-required; i++) {
            for (int j = required - 1; j < size; j++) {
                int counter = 0;
                for (int k = 0; k < required; k++) {
                    if (array[i+k][j-k] == c) {
                        counter++;
                        if (counter == required)
                            return true;
                    } else break;
                }
            }
        }
        return false;
    }
}
