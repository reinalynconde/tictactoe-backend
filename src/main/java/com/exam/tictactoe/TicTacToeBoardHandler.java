package com.exam.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;

import com.exam.tictactoe.database.DatabaseHandler;
import com.exam.tictactoe.database.Highscore;

public class TicTacToeBoardHandler {
    private static int moveCounter = 0;
    private static HashMap<String, String> highScores = new HashMap<>();

    public static String checkBoard(BoardState state){
        if(state.getBoard().length > 0){
            int boardSize = state.getBoard()[0].length;

            moveCounter += 1;

            String verticalCase = checkVerticalCase(state);
            if (verticalCase != null) return verticalCase;

            String horizontalCase = checkHorizontalCase(state);
            if (horizontalCase != null) return horizontalCase;


            String diagonalCase = checkDiagonalCase(state);
            if (diagonalCase != null) return diagonalCase;
            
            String reverseDiagonalCase = checkReverseDiagonalCase(state);
            if(reverseDiagonalCase != null) return reverseDiagonalCase;

            if(moveCounter == (boardSize * boardSize))
                return "draw";

            return "-";
        }


        return "-";
    }

    private static String checkDiagonalCase(BoardState state) {
        String[][] board = state.getBoard();
        String latestMove = state.getLatestMove();
        int boardSize = board[0].length;

        if(state.getRow() == state.getCol()){
            for(int i=0; i<boardSize; i++){
                if(!board[i][i].equals(latestMove))
                    break;
                else if(i == boardSize - 1)
                    return latestMove;
            }
        }
        
        return null;
    }

    private static String checkVerticalCase(BoardState state) {
        String[][] board = state.getBoard();
        String latestMove = state.getLatestMove();
        int boardSize = board[0].length;

        for(int i=0;i<boardSize; i++){
            if(!board[i][state.getCol()].equals(latestMove))
                break;
            else if(i == boardSize - 1)
                return latestMove;
        }
        return null;
    }

    private static String checkHorizontalCase(BoardState state){
        String[][] board = state.getBoard();
        String latestMove = state.getLatestMove();
        int boardSize = board[0].length;

        for(int i=0; i<boardSize; i++){
            if(!board[state.getRow()][i].equals(latestMove))
                break;
            else if(i == boardSize - 1)
                return latestMove;
        }

        return null;
    }
    
    private static String checkReverseDiagonalCase(BoardState state) {
    	String[][] board = state.getBoard();
    	String latestMove = state.getLatestMove();
    	int boardSize = board[0].length;
    	int middle = boardSize - 1;
    	
    	if(state.getRow() + state.getCol() == middle) {
    		for(int i=0; i<boardSize; i++) {
    			if(!board[i][middle - i].equals(latestMove))
    				break;
    			else if(i == middle)
    				return latestMove;
    		}
    	}
    	return null;
    }

    public static void resetMoveCounter(){
        moveCounter = 0;
    }

    public static void storeWinner(String playerName, String playerTime) {
        DatabaseHandler.addHighScore(playerName, playerTime);
    }

    public static ArrayList<Highscore> getHighScores(){
        return DatabaseHandler.get10HighScores();
    }
}
