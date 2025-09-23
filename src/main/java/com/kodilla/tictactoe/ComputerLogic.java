package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerLogic {
    public static int computerMoveHandle(int size, char[][] array) {
        List<Integer> emptyFields = new ArrayList<>();
        int move;

        var emptyFieldNumber = 1;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(array[i][j] == ' ') {
                    emptyFields.add(emptyFieldNumber);
                }
                emptyFieldNumber++;
            }
        }

        if(emptyFields.isEmpty()) {
            throw new IllegalStateException("No possibility of movement for O (computer)");
        }
        move = emptyFields.get(new Random().nextInt(emptyFields.size()));

        return move;
    }
}
