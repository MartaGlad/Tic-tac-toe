package com.kodilla.tictactoe;

import java.util.Scanner;

public class StartSettings {
    public static void main(String[] args) {
        System.out.println("\n #### Kółko i krzyżyk ####");
        System.out.println("----------------------------------");
        System.out.println("Wersja klasyczna (3x3) - wybierz 3");
        System.out.println("Wersja alternatywna - Gomoku (10x10) - wybierz 10");

        Scanner sc = new Scanner(System.in);
        int boardSize;
        while (true) {
            String choice = sc.nextLine();
            try {
                boardSize = Integer.parseInt(choice.trim());
            } catch(NumberFormatException e) {
                System.out.println("To nie jest liczba! Wybierz 3 lub 10.");
                continue;
            }
            if (boardSize != 3 && boardSize != 10 ) {
                System.out.println("Wybierz 3 lub 10!");
                continue;
            }
            break;
        }

        System.out.println("Czy chcesz grać z komputerem? (t/n)");
        String choice;
        while (true) {
            choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("t") && !choice.equalsIgnoreCase("n")) {
                System.out.println("Wybierz t lub n!");
                continue;
            }
            break;
        }
        boolean withComputerPlayer = choice.equalsIgnoreCase("t");

        if(boardSize == 3) {
            new OX().play(withComputerPlayer);
        }
        else {
            new Gomoku().play(withComputerPlayer);
        }

        sc.close();
    }
}
