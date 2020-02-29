package com.exam.tictactoe;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.exam.tictactoe.database.DatabaseHandler;

public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		DatabaseHandler.initializeDatabase();
		return application.sources(TictactoeApplication.class);
	}
	
	

}
