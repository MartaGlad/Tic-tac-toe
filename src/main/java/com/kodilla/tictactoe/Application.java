package com.kodilla.tictactoe;

public class Application {

    public static void main(String[] args) {
        ResultCounter resultCounterOX = new ResultCounterOX();
        ResultCounter resultCounterGomoku = new ResultCounterGomoku();

        System.out.println("\n####  Tick-tack-toe  ####");
        System.out.println("----------------------------------");
        System.out.println("Classic version (3x3) - choose 3");
        System.out.println("Alternative version - Gomoku (10x10) - choose 10");

        int boardSize = UserDialogs.getBoardSize();

        String question = "Do you want to play with the computer? (y/n)";
        UserSelection userSelectionIfComputer = UserDialogs.getUserSelection(question);

        if(boardSize == 3) {
            new Board(3).play(resultCounterOX, userSelectionIfComputer);
        }
        else {
            new GomokuBoard(10).play(resultCounterGomoku, userSelectionIfComputer);
        }
    }
}
