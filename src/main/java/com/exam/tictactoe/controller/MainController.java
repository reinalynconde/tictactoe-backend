package com.exam.tictactoe.controller;

import com.exam.tictactoe.BoardState;
import com.exam.tictactoe.TicTacToeBoardHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.exam.tictactoe.PlayerHandler;
import com.exam.tictactoe.Players;
import com.google.gson.Gson;

import javax.swing.text.html.parser.Entity;

@Controller
@CrossOrigin
public class MainController {
	private Gson gson = new Gson();
	
	@PostMapping("/players")
	public ResponseEntity<String> registerPlayers(@RequestBody Players players){
		if(players.getPlayer1Name() != null && players.getPlayer2Name() != null)
			PlayerHandler.getInstance().setPlayerNames(players.getPlayer1Name(), players.getPlayer2Name());
		else
			return new ResponseEntity<String>("Error while trying to register players", HttpStatus.OK);

		TicTacToeBoardHandler.resetMoveCounter();
		return new ResponseEntity<String>("Successfully registered players", HttpStatus.OK);
		
	}
	
	@GetMapping("/players")
	public ResponseEntity<String> getCurrentPlayers(){
		return new ResponseEntity<String>(gson.toJson(PlayerHandler.getInstance().getCurrentPlayers()), HttpStatus.OK);
	}

	@PostMapping("/move")
	public ResponseEntity<String> checkBoard(@RequestBody BoardState board){
		String winner = TicTacToeBoardHandler.checkBoard(board);

		if(winner.equals("X"))
			TicTacToeBoardHandler.storeWinner(PlayerHandler.getInstance().getCurrentPlayers().getPlayer1Name(), board.getPlayer1Time());
		else if(winner.equals("O"))
			TicTacToeBoardHandler.storeWinner(PlayerHandler.getInstance().getCurrentPlayers().getPlayer2Name(), board.getPlayer2Time());

		return new ResponseEntity<String>(winner, HttpStatus.OK);
	}

	@GetMapping("/highscores")
	public ResponseEntity<String> getHighScores(){
		return new ResponseEntity<String>(gson.toJson(TicTacToeBoardHandler.getHighScores()), HttpStatus.OK);
	}
}
