package com.kodilla.tictactoe;

import java.util.*;

public class Gomoku  {
    final static int size = 10;
    protected static char[][] arrBoard;

    final static char EMPTY = ' ';

    protected char actualPlayer = 'X';
    Scanner sc;

    public Gomoku() {
        arrBoard = new char[size][size];
        for(int i = 0; i < size; i++) {
            Arrays.fill(arrBoard[i], EMPTY);
        }
    }

    void gameInfo() {
        System.out.println("Aby grać wpisuj pozycję według wzoru\n");
        StringBuilder sb = new StringBuilder();
        var number = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == 0) {
                    sb.append("   ").append(number++).append("   ");
                }
                else {
                    sb.append("  ").append(number++).append("   ");
                }
                if (j < size - 1) {
                    sb.append("|");
                }
            }
            if (i < size - 1) {
                sb.append("\n-------+-------+-------+-------+-------+-------+-------+-------+-------+-------\n");
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("   " +arrBoard[i][j] + "   ");
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            if (i < size - 1) {
                System.out.print("\n-------+-------+-------+-------+-------+-------+-------+-------+-------+-------\n");
            }
        }
    }


    boolean isWinner(char c) {
        int required = 5;

        for (int i = 0; i < size; i++) {
            int counter = 0;
            for (int j = 0; j < size; j++) {
                if (arrBoard[i][j] == c) {
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
                if (arrBoard[i][j] == c) {
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
                    if (arrBoard[i+k][j+k] == c) {
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
                    if (arrBoard[i+k][j-k] == c) {
                        counter++;
                        if (counter == required)
                            return true;
                    } else break;
                }
            }
        }
        return false;
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

   void writeMove(int move) {
        if (move < 1 || move > size * size) {
            throw new WrongMoveException("To nie jest dozwolona pozycja! Wybierz nową z zakresu 1-" + size * size);
        }

        var rowIndex = (move - 1) / size;
        var colIndex = (move - 1) % size;
        if (arrBoard[rowIndex][colIndex] != EMPTY) {
            throw new WrongMoveException("To pole jest już zajęte. Spróbuj jeszcze raz.");
        }
        arrBoard[rowIndex][colIndex] = actualPlayer;
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
