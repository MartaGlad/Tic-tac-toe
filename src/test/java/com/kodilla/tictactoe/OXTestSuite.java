package com.kodilla.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OXTestSuite {
    @Test
    void testOWinsRows(){
        //Given
        OX game = new OX();
        game.actualPlayer = 'O';
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
        OX game = new OX();
        game.actualPlayer = 'O';
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
        OX game = new OX();
        game.actualPlayer = 'O';
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
        OX game = new OX();
        game.actualPlayer = 'O';
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
        OX game = new OX();
        game.actualPlayer = 'X';
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
        OX game = new OX();
        game.actualPlayer = 'X';
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
        OX game = new OX();
        game.actualPlayer = 'X';
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
        OX game = new OX();
        game.actualPlayer = 'X';
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
        OX game = new OX();
        //When
        game.arrBoard = new char[][]{{'X', 'O', 'X'}, {'O', 'O', 'X'}, {'X', 'X', 'O'}};
        //Then
        assertFalse(game.isWinner('X'));
        assertFalse(game.isWinner('O'));
        assertTrue(game.isDraw());
    }

    @Test
    void testWrongMoveException(){
        //Given
        OX game = new OX();
        //When
        game.actualPlayer = 'X';
        game.writeMove(1);
        //Then
        assertThrows(WrongMoveException.class, () -> game.writeMove(19));
        assertThrows(WrongMoveException.class, () -> game.writeMove(1));
        
    }
}
