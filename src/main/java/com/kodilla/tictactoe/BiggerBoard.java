package com.kodilla.tictactoe;

import java.util.*;

public class BiggerBoard extends Board {
    private final int SIZE;
    private char[][] array;
    private final char EMPTY = ' ';
    private char actualPlayer;
    private Scanner sc;


    public BiggerBoard(int size) {
        super(size);
        this.SIZE = size;
        this.array = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            Arrays.fill(this.array[i], EMPTY);
        }
        this.actualPlayer = 'X';
    }


    @Override
    public char[][] getArray() {
        return this.array;
    }


    @Override
    public void setArray(char[][] array) {
        this.array = array;
    }


    @Override
    public void gameInfo() {
        System.out.println("To play, enter the position according to the pattern:\n");
        StringBuilder sb = new StringBuilder();
        var number = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(i == 0) {
                    sb.append("   ").append(number++).append("   ");
                }
                else {
                    sb.append("  ").append(number++).append("   ");
                }
                if (j < SIZE - 1) {
                    sb.append("|");
                }
            }
            if (i < SIZE - 1) {
                sb.append("\n-------+-------+-------+-------+-------+-------+-------+-------+-------+-------\n");
            }
        }
        System.out.println(sb);
    }

    @Override
    public void showActualPlayBoard() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("   " +array[i][j] + "   ");
                if (j < SIZE - 1) {
                    System.out.print("|");
                }
            }
            if (i < SIZE - 1) {
                System.out.print("\n-------+-------+-------+-------+-------+-------+-------+-------+-------+-------\n");
            }
        }
    }


    @Override
    public boolean isWinner(char c) {
        int required = 5;

        for (int i = 0; i < SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (array[i][j] == c) {
                    counter++;
                    if(counter == required) {
                        return true;
                    }
                }
                else counter = 0;
            }
        }

        for (int j = 0; j < SIZE; j++) {
            int counter = 0;
            for (int i = 0; i < SIZE; i++) {
                if (array[i][j] == c) {
                    counter++;
                    if(counter == required) {
                        return true;
                    }
                }
                else counter = 0;
            }
        }

        for (int i = 0; i <= SIZE-required; i++) {   //start points in rows and columns, 5 fields must be placed
            for (int j = 0; j <= SIZE-required; j++) {
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

        for (int i = 0; i <= SIZE-required; i++) {
            for (int j = required - 1; j < SIZE; j++) {
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
