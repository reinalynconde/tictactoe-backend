package com.exam.tictactoe.controller;

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

@Controller
@CrossOrigin
public class MainController {
	private Gson gson = new Gson();
	
	@PostMapping("/registerPlayers")
	public ResponseEntity<String> registerPlayers(@RequestBody Players players){
		if(players.getPlayer1Name() != null && players.getPlayer2Name() != null)
			PlayerHandler.getInstance().setPlayerNames(players.getPlayer1Name(), players.getPlayer2Name());
		else
			return new ResponseEntity<String>("Error while trying to register players", HttpStatus.OK);
		
		return new ResponseEntity<String>("Successfully registered players", HttpStatus.OK);
		
	}
	
	@GetMapping("/getPlayers")
	public ResponseEntity<String> getCurrentPlayers(){
		return new ResponseEntity<String>(gson.toJson(PlayerHandler.getInstance().getCurrentPlayers()), HttpStatus.OK);
	}
}
