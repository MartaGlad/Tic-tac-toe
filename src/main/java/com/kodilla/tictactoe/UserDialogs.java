package com.kodilla.tictactoe;

import java.util.Scanner;

public class UserDialogs {
    static Scanner sc = new Scanner(System.in);

    public static int getBoardSize() {
        int boardSize;

        while (true) {
            String choice = sc.nextLine();
            try {
                boardSize = Integer.parseInt(choice.trim());
            } catch (NumberFormatException e) {
                System.out.println("It's not a number! Choose 3 or 10.");
                continue;
            }
            if (boardSize != 3 && boardSize != 10) {
                System.out.println("Choose 3 or 10!");
                continue;
            }
            break;
        }
        return boardSize;
    }


    public static UserSelection getUserSelection(String message) {
        System.out.println(message);
        while (true) {
            switch (sc.nextLine().trim().toUpperCase()) {
                case "Y":
                    return UserSelection.YES;
                case "N":
                    return UserSelection.NO;
                default:
                    System.out.println("Wrong choice. Choose y or n.");

            }
        }
    }

}



