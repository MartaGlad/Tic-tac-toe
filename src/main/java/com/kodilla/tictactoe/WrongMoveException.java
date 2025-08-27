package com.kodilla.tictactoe;

public class WrongMoveException extends RuntimeException {
    public WrongMoveException(String message) {
        super(message);
    }
}
