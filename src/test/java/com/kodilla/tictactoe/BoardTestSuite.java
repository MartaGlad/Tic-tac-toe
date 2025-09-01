package com.kodilla.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestSuite {
    @Test
    void testOWinsRows(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('O');
        //When
        game.writeMove(1);
        game.writeMove(2);
        game.writeMove(3);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testOWinsColumns(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('O');
        //When
        game.writeMove(2);
        game.writeMove(5);
        game.writeMove(8);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testOWinsDiagonal1(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('O');
        //When
        game.writeMove(1);
        game.writeMove(5);
        game.writeMove(9);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testOWinsDiagonal2(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('O');
        //When
        game.writeMove(3);
        game.writeMove(5);
        game.writeMove(7);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testXWinsRows(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('X');
        //When
        game.writeMove(4);
        game.writeMove(5);
        game.writeMove(6);
        //Then
        assertTrue(game.isWinner('X'));

    }

    @Test
    void testXWinsColumns(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('X');
        //When
        game.writeMove(3);
        game.writeMove(6);
        game.writeMove(9);
        //Then
        assertTrue(game.isWinner('X'));

    }

    @Test
    void testXWinsDiagonal1(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('X');
        //When
        game.writeMove(1);
        game.writeMove(5);
        game.writeMove(9);
        //Then
        assertTrue(game.isWinner('X'));

    }

    @Test
    void testXWinsDiagonal2(){
        //Given
        Board game = new Board(3);
        game.setActualPlayer('X');
        //When
        game.writeMove(3);
        game.writeMove(5);
        game.writeMove(7);
        //Then
        assertTrue(game.isWinner('X'));

    }

    @Test
    void testDraw(){
        //Given
        Board game = new Board(3);
        //When
        game.setArray(new char[][]{{'X', 'O', 'X'}, {'O', 'O', 'X'}, {'X', 'X', 'O'}});
        //Then
        assertFalse(game.isWinner('X'));
        assertFalse(game.isWinner('O'));
        assertTrue(game.isDraw());
    }

    @Test
    void testWrongMoveException(){
        //Given
        Board game = new Board(3);
        //When
        game.setActualPlayer('X');
        game.writeMove(1);
        //Then
        assertThrows(WrongMoveException.class, () -> game.writeMove(19));
        assertThrows(WrongMoveException.class, () -> game.writeMove(1));
        
    }
}
