package com.exam.tictactoe;

public class BoardState {
    private int row;
    private int col;
    private String[][] board;
    private String latestMove;
    private String player1Time;
    private String player2Time;

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String[][] getBoard(){
        return this.board;
    }

    public String getLatestMove(){
        return this.latestMove;
    }

    public String getPlayer1Time() {
        return this.player1Time;
    }

    public String getPlayer2Time() {
        return this.player2Time;
    }
}
