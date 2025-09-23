package com.kodilla.tictactoe;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestSuite {
    Board board = new Board(3);
    ResultCounter resultCounter = new ResultCounterOX();


    @Test
    void testOWinsRows(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(1);
        board.writeMove(2);
        board.writeMove(3);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'O'));

    }

    @Test
    void testOWinsColumns(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(2);
        board.writeMove(5);
        board.writeMove(8);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'O'));

    }

    @Test
    void testOWinsDiagonal1(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(1);
        board.writeMove(5);
        board.writeMove(9);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'O'));

    }

    @Test
    void testOWinsDiagonal2(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(3);
        board.writeMove(5);
        board.writeMove(7);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'O'));

    }

    @Test
    void testXWinsRows(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(4);
        board.writeMove(5);
        board.writeMove(6);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'X'));

    }

    @Test
    void testXWinsColumns(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(3);
        board.writeMove(6);
        board.writeMove(9);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'X'));

    }

    @Test
    void testXWinsDiagonal1(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(1);
        board.writeMove(5);
        board.writeMove(9);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'X'));

    }

    @Test
    void testXWinsDiagonal2(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(3);
        board.writeMove(5);
        board.writeMove(7);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'X'));

    }

    @Test
    void testDraw(){
        //Given
        //When
        board.setArray(new char[][]{{'X', 'O', 'X'}, {'O', 'O', 'X'}, {'X', 'X', 'O'}});
        //Then
        assertFalse(resultCounter.isWinner(board.getArray(),'X'));
        assertFalse(resultCounter.isWinner(board.getArray(),'O'));
        assertTrue(resultCounter.isDraw(board.getArray()));
    }

    @Test
    void testWrongMoveException(){
        //Given
        //When
        board.setActualPlayer('X');
        board.writeMove(1);
        //Then
        assertThrows(WrongMoveException.class, () -> board.writeMove(19));
        assertThrows(WrongMoveException.class, () -> board.writeMove(1));
        
    }
}
