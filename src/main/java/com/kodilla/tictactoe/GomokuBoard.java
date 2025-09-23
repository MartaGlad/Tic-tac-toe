package com.kodilla.tictactoe;


public class GomokuBoard extends Board {

    public GomokuBoard(int size) {
        super(size);
    }

    @Override
    public void boardPatternInfo() {
        StringBuilder sb = new StringBuilder();
        var number = 1;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if(i == 0) {
                    sb.append("   ").append(number++).append("   ");
                }
                else {
                    sb.append("  ").append(number++).append("   ");
                }
                if (j < getSize() - 1) {
                    sb.append("|");
                }
            }
            if (i < getSize() - 1) {
                sb.append("\n-------+-------+-------+-------+-------+-------+-------+-------+-------+-------\n");
            }
        }
        System.out.println(sb);
    }

    @Override
    public void showActualBoard() {
        System.out.println();
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print("   " +getArray()[i][j] + "   ");
                if (j < getSize() - 1) {
                    System.out.print("|");
                }
            }
            if (i < getSize() - 1) {
                System.out.print("\n-------+-------+-------+-------+-------+-------+-------+-------+-------+-------\n");
            }
        }
    }



}
