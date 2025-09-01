package com.kodilla.tictactoe;

import java.util.Scanner;

public class StartSettings {

    public static void main(String[] args) {

        System.out.println("\n####  Tick-tack-toe  ####");
        System.out.println("----------------------------------");
        System.out.println("Classic version (3x3) - choose 3");
        System.out.println("Alternative version - Gomoku (10x10) - choose 10");

        Scanner sc = new Scanner(System.in);
        int boardSIZE;

        while (true) {
            String choice = sc.nextLine();
            try {
                boardSIZE = Integer.parseInt(choice.trim());
            } catch(NumberFormatException e) {
                System.out.println("It's not a number! Choose 3 or 10.");
                continue;
            }
            if (boardSIZE != 3 && boardSIZE != 10 ) {
                System.out.println("Choose 3 or 10!");
                continue;
            }
            break;
        }

        System.out.println("Do you want to play with the computer? (t/n)");
        String choice;
        while (true) {
            choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("t") && !choice.equalsIgnoreCase("n")) {
                System.out.println("Choose t or n!");
                continue;
            }
            break;
        }
        boolean withComputerPlayer = choice.equalsIgnoreCase("t");

        if(boardSIZE == 3) {
            new Board(3).play(withComputerPlayer);
        }
        else {
            new BiggerBoard(10).play(withComputerPlayer);
        }

        sc.close();
    }
}
