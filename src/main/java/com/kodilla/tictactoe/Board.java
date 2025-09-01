package com.kodilla.tictactoe;

import java.util.*;

public class Board {
    private final int SIZE;
    private char[][] array;
    private final char EMPTY = ' ';
    private char actualPlayer;
    private Scanner sc;

    public Board(int size) {
        this.SIZE = size;
        this.array = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            Arrays.fill(this.array[i], EMPTY);
        }
        this.actualPlayer = 'X';
    }


    public char[][] getArray() {
        return array;
    }


    public void setArray(char[][] array) {
        this.array = array;
    }


    public char getActualPlayer() {
        return actualPlayer;
    }


    public void setActualPlayer(char actualPlayer) {
        this.actualPlayer = actualPlayer;
    }


    public void gameInfo() {
        System.out.println("To play, enter the position according to the pattern\n");
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


    public void play(boolean withComputerPlayer) {
        gameInfo();
        int move;
        sc = new Scanner(System.in);

        while (true) {
            showActualPlayBoard();

            if(withComputerPlayer && (actualPlayer == 'O')) {
                move = computerMoveHandle();
                System.out.println("\n\nO (computer) chooses the field number: " + move);
            }
            else {
                System.out.print("\n\n" + actualPlayer + " chooses the field number: ");
                String choice = sc.nextLine();
                try {
                    move = Integer.parseInt(choice.trim());
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number! Please try again.");
                    continue;
                }
                try {
                    writeMove(move);
                } catch (WrongMoveException e) {
                    System.out.println("Wrong move: " + e.getMessage());
                    continue;
                }
            }

            if (isWinner(actualPlayer)) {
                showActualPlayBoard();
                System.out.println("\n\nThe winner is " + actualPlayer + "!");
                break;
            }

            if (isDraw()) {
                showActualPlayBoard();
                System.out.println("\n\nDraw!");
                break;
            }
            changePlayer();
        }
    }


    public void showActualPlayBoard() {
        System.out.println();
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                System.out.print("    " + getArray()[i][j] + "    ");
                if (j < SIZE - 1) {
                    System.out.print("|");
                }
            }
            if (i < SIZE - 1) {
                System.out.print("\n---------+---------+---------\n");
            }
        }
    }


    public int computerMoveHandle() {
        List<Integer> emptyFields = new ArrayList<>();
        int move;

        var emptyFieldNumber = 1;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(getArray()[i][j] == EMPTY) {
                    emptyFields.add(emptyFieldNumber);
                }
                emptyFieldNumber++;
            }
        }

        if(emptyFields.isEmpty()) {
            throw new IllegalStateException("No possibility of movement for O (computer)");
        }

        move = emptyFields.get(new Random().nextInt(emptyFields.size()));
        writeMove(move);

        return move;
    }

    public void writeMove(int move) throws WrongMoveException {
        if (move < 1 || move > (SIZE * SIZE)) {
            throw new WrongMoveException("\n" + "This is not an allowed position! Select a new one from the range 1-" + (SIZE * SIZE));
        }
        var rowIndex = (move - 1) / SIZE;
        var colIndex = (move - 1) % SIZE;
        if (getArray()[rowIndex][colIndex] != EMPTY) {
            throw new WrongMoveException("This field is already taken. Please try again.");
        }
        getArray()[rowIndex][colIndex] = actualPlayer;
    }


    public boolean isWinner(char c) {
        for(int i = 0; i < SIZE; i++) {
            if(array[i][0] == c && array[i][1] == c && array[i][2] == c) return true;
        }
        for(int j = 0; j < SIZE; j++) {
            if (array[0][j] == c && array[1][j] == c && array[2][j] == c) return true;
        }
        if (array[0][0] == c && array[1][1] == c && array[2][2] == c) return true;
        if (array[0][2] == c && array[1][1] == c && array[2][0] == c) return true;

        return false;
    }


    public boolean isDraw() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (getArray()[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public void changePlayer() {
        actualPlayer = (actualPlayer == 'X') ? 'O' : 'X';
    }
}
