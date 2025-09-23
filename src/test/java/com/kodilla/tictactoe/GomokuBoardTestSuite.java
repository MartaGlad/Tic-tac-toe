package com.kodilla.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GomokuBoardTestSuite {
    GomokuBoard board = new GomokuBoard(10);
    ResultCounterGomoku resultCounter = new ResultCounterGomoku();

    @Test
    void testOWinsRows(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(1);
        board.writeMove(2);
        board.writeMove(3);
        board.writeMove(4);
        board.writeMove(5);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'O'));

    }

    @Test
    void testOWinsColumns(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(2);
        board.writeMove(12);
        board.writeMove(22);
        board.writeMove(32);
        board.writeMove(42);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(),'O'));

    }

    @Test
    void testOWinsDiagonal1(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(4);
        board.writeMove(15);
        board.writeMove(26);
        board.writeMove(37);
        board.writeMove(48);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'O'));

    }

    @Test
    void testOWinsDiagonal2(){
        //Given
        board.setActualPlayer('O');
        //When
        board.writeMove(6);
        board.writeMove(15);
        board.writeMove(24);
        board.writeMove(33);
        board.writeMove(42);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'O'));

    }

    @Test
    void testXWinsRows(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(1);
        board.writeMove(2);
        board.writeMove(3);
        board.writeMove(4);
        board.writeMove(5);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'X'));

    }

    @Test
    void testXWinsColumns(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(3);
        board.writeMove(13);
        board.writeMove(23);
        board.writeMove(33);
        board.writeMove(43);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'X'));
    }

    @Test
    void testXWinsDiagonal1(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(54);
        board.writeMove(65);
        board.writeMove(76);
        board.writeMove(87);
        board.writeMove(98);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'X'));
    }

    @Test
    void testXWinsDiagonal2(){
        //Given
        board.setActualPlayer('X');
        //When
        board.writeMove(59);
        board.writeMove(68);
        board.writeMove(77);
        board.writeMove(86);
        board.writeMove(95);
        //Then
        assertTrue(resultCounter.isWinner(board.getArray(), 'X'));

    }

    @Test
    void testDraw(){
        //Given
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
                board.getArray()[i][j] = actualPlayer;
            }
        }
        //Then
        assertTrue(resultCounter.isDraw(board.getArray()));

    }

    @Test
    void testWrongMoveException(){
        //Given
        //When
        board.setActualPlayer('X');
        board.writeMove(1);
        //Then
        assertThrows(WrongMoveException.class, () -> board.writeMove(120));
        assertThrows(WrongMoveException.class, () -> board.writeMove(1));

    }
}
