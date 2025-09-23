package com.kodilla.tictactoe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Board {
    private int size;
    private char[][] array;
    private final char EMPTY = ' ';
    private char actualPlayer = 'X';
    private Scanner sc;

    public Board(int size) {
        this.size = size;
        this.array = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(this.array[i], EMPTY);
        }
    }

    public char[][] getArray() {
        return array;
    }

    public void setArray(char[][] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }

    public void setActualPlayer(char actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public void boardPatternInfo() {
        StringBuilder sb = new StringBuilder();
        var number = 1;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                sb.append("    ").append(number++).append("    ");
                if (j < getSize() - 1) {
                    sb.append("|");
                }
            }
            if (i < getSize() - 1) {
                sb.append("\n---------+---------+---------\n");
            }
        }
        System.out.println(sb);
    }

    public void play(ResultCounter resultCounter, UserSelection ifComputer) {
        System.out.println("To play, enter the position according to the pattern:\n");
        boardPatternInfo();
        int move;
        sc = new Scanner(System.in);
        FileWriter fw = new FileWriter();
        FileReader fr = new FileReader();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            showActualBoard();

            if (ifComputer.equals(UserSelection.YES) && (actualPlayer == 'O')) {
                move = ComputerLogic.computerMoveHandle(getSize(), getArray());
                System.out.println("\n\nO (computer) chooses the field number: " + move);
                writeMove(move);
            } else {
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
            if (resultCounter.isWinner(getArray(), actualPlayer)) {
                showActualBoard();
                System.out.println("\n\nThe winner is " + actualPlayer + "!");
                fw.writeFile(LocalDateTime.now().format(formatter) + ": The winner was " + actualPlayer + "\n");
                fr.readFile();
                break;
            }
            if (resultCounter.isDraw(getArray())) {
                showActualBoard();
                System.out.println("\n\nDraw!");
                fw.writeFile(LocalDateTime.now().format(formatter) + "Draw\n");
                fr.readFile();
                break;
            }
            changePlayer();
        }
    }

    public void showActualBoard() {
        System.out.println();
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print("    " + getArray()[i][j] + "    ");
                if (j < getSize() - 1) {
                    System.out.print("|");
                }
            }
            if (i < getSize() - 1) {
                System.out.print("\n---------+---------+---------\n");
            }
        }
    }

    public void writeMove(int move) throws WrongMoveException {
        if (move < 1 || move > (getSize() * getSize())) {
            throw new WrongMoveException("\nThis is not an allowed position! Select a new one from the range 1-"
                    + (getSize() * getSize()));
        }
        var rowIndex = (move - 1) / getSize();
        var colIndex = (move - 1) % getSize();
        if (getArray()[rowIndex][colIndex] != EMPTY) {
            throw new WrongMoveException("This field is already taken. Please try again.");
        }
        getArray()[rowIndex][colIndex] = actualPlayer;
    }

    public void changePlayer() {
        actualPlayer = (actualPlayer == 'X') ? 'O' : 'X';
    }

}

