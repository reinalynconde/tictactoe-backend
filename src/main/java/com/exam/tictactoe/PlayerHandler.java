package com.exam.tictactoe;

public class PlayerHandler {
	private static PlayerHandler instance;
	private Players currentPlayers;
	
	private PlayerHandler() {
		this.currentPlayers = new Players();
	}
	
	public static PlayerHandler getInstance() {
		if(instance == null)
			instance = new PlayerHandler();
		
		return instance;
	}
	
	public void setPlayerNames(String player1, String player2) {
		currentPlayers.setPlayer1Name(player1);
		currentPlayers.setPlayer2Name(player2);
	}
	
	public Players getCurrentPlayers() {
		return this.currentPlayers;
	}
}
