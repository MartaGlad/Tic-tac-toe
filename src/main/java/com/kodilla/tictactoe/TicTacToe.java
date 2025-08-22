package com.kodilla.tictactoe;


import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3;
    private static final char EMPTY = ' ';

    private final char[][] arrBoard = new char[SIZE][SIZE];
    private char actualPlayer = 'X';
    private Scanner sc = new Scanner(System.in);

    private void play() {
        welcomeInfo();
        createEmptyArrBoard();

        while (true) {
            showActualPlayBoard();
            handleGivenChoiceNumber();

            if (isWinner(actualPlayer)) {
                showActualPlayBoard();
                System.out.println("\nWygrał " + actualPlayer + "!");
                break;
            }

            if (isDraw()) {
                showActualPlayBoard();
                System.out.println("Remis!");
                break;
            }
            changePlayer();
        }
    }


    private void welcomeInfo() {
        System.out.println("\n #### Kóło i krzyżyk ####");
        System.out.println("Aby grać wpisuj pozycję według wzoru");

        StringBuilder sb = new StringBuilder();
        var number = 1;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                sb.append("    ").append(number++).append("    ");
                if (j < SIZE - 1) {
                    sb.append("|");
                }
            }
            if (i < SIZE - 1) {
                sb.append("\n---------+---------+---------\n");
            }
        }
        System.out.println(sb);
    }


    private void createEmptyArrBoard() {
        for(int i = 0; i < SIZE; i++) {
            Arrays.fill(arrBoard[i], EMPTY);
        }
    }


    private void showActualPlayBoard() {
        System.out.println("\n");
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                System.out.print("    " + arrBoard[i][j] + "    ");
                if (j < SIZE - 1) {
                    System.out.print("|");
                }
            }
            if (i < SIZE - 1) {
                System.out.print("\n---------+---------+---------\n");
            }
        }
    }


    private void handleGivenChoiceNumber() {
        int rowIndex;
        int colIndex;

        while (true) {
            System.out.print("\n" + actualPlayer + " wybiera pole: ");
            String choice = sc.nextLine();
            int number;
            try {
                number = Integer.parseInt(choice.trim());
            } catch (NumberFormatException e) {
                System.out.println("To nie jest liczba! Spróbuj ponownie.");
                continue;
            }
            if (number < 1 || number > 9) {
                System.out.println("To nie jest dozwolona pozycja! Wybierz nową z zakresu 1-9.");
                continue;
            }
            rowIndex = (number-1) / SIZE;
            colIndex = (number-1) % SIZE;
            if (arrBoard[rowIndex][colIndex] != EMPTY) {
                System.out.println("To pole jest już zajęte. Spróbuj jeszcze raz.");
                continue;
            }
            break;
        }
        arrBoard[rowIndex][colIndex] = actualPlayer;
    }


    private boolean isWinner(char c) {
        for(int i = 0; i < SIZE; i++) {
            if(arrBoard[i][0] == c && arrBoard[i][1] == c && arrBoard[i][2] == c) return true;
        }
        for(int j = 0; j < SIZE; j++) {
            if (arrBoard[0][j] == c && arrBoard[1][j] == c && arrBoard[2][j] == c) return true;
        }
        if (arrBoard[0][0] == c && arrBoard[1][1] == c && arrBoard[2][2] == c) return true;
        if (arrBoard[0][2] == c && arrBoard[1][1] == c && arrBoard[2][0] == c) return true;

        return false;

    }


    private boolean isDraw() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (arrBoard[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    private void changePlayer() {
        actualPlayer = (actualPlayer == 'X') ? 'O' : 'X';
    }


    public static void main(String[] args) {
        new TicTacToe().play();
    }

}
