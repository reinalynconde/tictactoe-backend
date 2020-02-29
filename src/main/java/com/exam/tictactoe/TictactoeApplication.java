package com.exam.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.exam.tictactoe.database.DatabaseHandler;

@SpringBootApplication
public class TictactoeApplication {

	public static void main(String[] args) {		
		SpringApplication.run(TictactoeApplication.class, args);
	}
}
