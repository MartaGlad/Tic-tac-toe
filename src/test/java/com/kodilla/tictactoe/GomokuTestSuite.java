package com.kodilla.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GomokuTestSuite {
    @Test
    void testOWinsRows(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'O';
        //When
        game.writeMove(1);
        game.writeMove(2);
        game.writeMove(3);
        game.writeMove(4);
        game.writeMove(5);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testOWinsColumns(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'O';
        //When
        game.writeMove(2);
        game.writeMove(12);
        game.writeMove(22);
        game.writeMove(32);
        game.writeMove(42);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testOWinsDiagonal1(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'O';
        //When
        game.writeMove(4);
        game.writeMove(15);
        game.writeMove(26);
        game.writeMove(37);
        game.writeMove(48);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testOWinsDiagonal2(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'O';
        //When
        game.writeMove(6);
        game.writeMove(15);
        game.writeMove(24);
        game.writeMove(33);
        game.writeMove(42);
        //Then
        assertTrue(game.isWinner('O'));

    }

    @Test
    void testXWinsRows(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'X';
        //When
        game.writeMove(1);
        game.writeMove(2);
        game.writeMove(3);
        game.writeMove(4);
        game.writeMove(5);
        //Then
        assertTrue(game.isWinner('X'));

    }

    @Test
    void testXWinsColumns(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'X';
        //When
        game.writeMove(3);
        game.writeMove(13);
        game.writeMove(23);
        game.writeMove(33);
        game.writeMove(43);
        //Then
        assertTrue(game.isWinner('X'));
    }

    @Test
    void testXWinsDiagonal1(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'X';
        //When
        game.writeMove(54);
        game.writeMove(65);
        game.writeMove(76);
        game.writeMove(87);
        game.writeMove(98);
        //Then
        assertTrue(game.isWinner('X'));
    }

    @Test
    void testXWinsDiagonal2(){
        //Given
        Gomoku game = new Gomoku();
        game.actualPlayer = 'X';
        //When
        game.writeMove(59);
        game.writeMove(68);
        game.writeMove(77);
        game.writeMove(86);
        game.writeMove(95);
        //Then
        assertTrue(game.isWinner('X'));

    }

    @Test
    void testDraw(){
        //Given
        Gomoku game = new Gomoku();
        //When
        String[] drawTab = {
 //filled example table, the winner is checked earlier in the loop in method play,
 //if the winner is determined, draw isn't considered
                "XOXOXOXOXO",
                "OXOXOXOXOX",
                "XOXOXOXOXO",
                "OXOXOXOXOX",
                "XOXOXOXOXO",
                "OXOXOXOXOX",
                "XOXOXOXOXO",
                "OXOXOXOXOX",
                "XOXOXOXOXO",
                "OXOXOXOXOX"
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char actualPlayer = drawTab[i].charAt(j);
                game.arrBoard[i][j] = actualPlayer;
            }
        }
        //Then
        assertTrue(game.isDraw());
    }

    @Test
    void testWrongMoveException(){
        //Given
        Gomoku game = new Gomoku();
        //When
        game.actualPlayer = 'X';
        game.writeMove(1);
        //Then
        assertThrows(WrongMoveException.class, () -> game.writeMove(120));
        assertThrows(WrongMoveException.class, () -> game.writeMove(1));

    }
}
