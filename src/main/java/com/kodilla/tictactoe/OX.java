package com.kodilla.tictactoe;

import java.util.*;

public class OX {
    final static int size = 3;
    protected static char[][] arrBoard;

    final static char EMPTY = ' ';

    protected char actualPlayer = 'X';
    Scanner sc;

    public OX() {
        arrBoard = new char[size][size];
        for(int i = 0; i < size; i++) {
            Arrays.fill(arrBoard[i], EMPTY);
        }
    }

    void gameInfo() {
        System.out.println("Aby grać wpisuj pozycję według wzoru\n");
        StringBuilder sb = new StringBuilder();
        var number = 1;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                sb.append("    ").append(number++).append("    ");
                if (j < size - 1) {
                    sb.append("|");
                }
            }
            if (i < size - 1) {
                sb.append("\n---------+---------+---------\n");
            }
        }
        System.out.println(sb);
    }


    void play(boolean withComputerPlayer) {
        gameInfo();
        int move;
        sc = new Scanner(System.in);

        while (true) {
            showActualPlayBoard();

            if(withComputerPlayer && (actualPlayer == 'O')) {
                move = computerMoveHandle();
                System.out.println("\n\nO (komputer) wybiera pole: " + move);
            }
            else {
                System.out.print("\n\n" + actualPlayer + " wybiera pole: ");
                String choice = sc.nextLine();
                try {
                    move = Integer.parseInt(choice.trim());
                } catch (NumberFormatException e) {
                    System.out.println("To nie jest liczba! Spróbuj ponownie.");
                    continue;
                }
                try {
                    writeMove(move);
                } catch (WrongMoveException e) {
                    System.out.println("Błędny ruch: " + e.getMessage());
                    continue;
                }
            }

            if (isWinner(actualPlayer)) {
                showActualPlayBoard();
                System.out.println("\n\nWygrał " + actualPlayer + "!");
                break;
            }

            if (isDraw()) {
                showActualPlayBoard();
                System.out.println("\n\nRemis!");
                break;
            }
            changePlayer();
        }
    }


    void showActualPlayBoard() {
        System.out.println();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print("    " + arrBoard[i][j] + "    ");
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            if (i < size - 1) {
                System.out.print("\n---------+---------+---------\n");
            }
        }
    }

    int computerMoveHandle() {
        List<Integer> emptyFields = new ArrayList<>();
        int move;

        var emptyFieldNumber = 1;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(arrBoard[i][j] == EMPTY) {
                    emptyFields.add(emptyFieldNumber);
                }
                emptyFieldNumber++;
            }
        }

        if(emptyFields.isEmpty()) {
            throw new IllegalStateException("Brak możliwosci ruchu dla O (komputera)");
        }

        move = emptyFields.get(new Random().nextInt(emptyFields.size()));
        writeMove(move);

        return move;
    }

    void writeMove(int move) throws WrongMoveException {
        if (move < 1 || move > size * size) {
            throw new WrongMoveException("To nie jest dozwolona pozycja! Wybierz nową z zakresu 1-" + (size * size));
        }
        var rowIndex = (move - 1) / size;
        var colIndex = (move - 1) % size;
        if (arrBoard[rowIndex][colIndex] != EMPTY) {
            throw new WrongMoveException("To pole jest już zajęte. Spróbuj jeszcze raz.");
        }
        arrBoard[rowIndex][colIndex] = actualPlayer;
    }


    boolean isWinner(char c) {
        for(int i = 0; i < size; i++) {
            if(arrBoard[i][0] == c && arrBoard[i][1] == c && arrBoard[i][2] == c) return true;
        }
        for(int j = 0; j < size; j++) {
            if (arrBoard[0][j] == c && arrBoard[1][j] == c && arrBoard[2][j] == c) return true;
        }
        if (arrBoard[0][0] == c && arrBoard[1][1] == c && arrBoard[2][2] == c) return true;
        if (arrBoard[0][2] == c && arrBoard[1][1] == c && arrBoard[2][0] == c) return true;

        return false;
    }

    boolean isDraw() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (arrBoard[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    void changePlayer() {
        actualPlayer = (actualPlayer == 'X') ? 'O' : 'X';
    }
}
