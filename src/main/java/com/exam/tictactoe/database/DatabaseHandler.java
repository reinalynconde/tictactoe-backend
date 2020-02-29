package com.exam.tictactoe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class DatabaseHandler {
    private static Connection connect = null;
    private static final String DB_IP = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "tictactoe";
    private static final int QUERY_TIMEOUT = 10;
    
    public static Connection getConnection(String database) {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + database 
	                        + "?user=root&password=");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static void initializeDatabase() {
    	createDatabase();
    	createTables();
    }
    
    private static void createDatabase() {
    	PreparedStatement preparedStatement = null;
    	connect = getConnection("");
    	
    	if(connect != null) {
    		try {
    			System.out.println("Creating database...");
				preparedStatement = connect.prepareStatement("CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";");
				preparedStatement.setQueryTimeout(QUERY_TIMEOUT);
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		finally {
    			close(preparedStatement);
    			
    		}
    	}
    }
    
    private static void createTables() {
    	PreparedStatement preparedStatement = null;
    	connect = getConnection(DB_NAME);
    	
    	if(connect != null) {
    		try {
    			System.out.println("Creating tables...");
				preparedStatement = connect.prepareStatement("CREATE TABLE IF NOT EXISTS HIGHSCORES("
						+ "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
						+ "PLAYERNAME VARCHAR(50),"
						+ "DURATION TIME)");
				preparedStatement.setQueryTimeout(QUERY_TIMEOUT);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		finally {
    			close(preparedStatement);
    		}
    	}
    }
    
    public static int addHighScore(String playerName, String duration) {
    	PreparedStatement preparedStatement = null;
    	connect = getConnection(DB_NAME);
    	
    	if(connect != null) {
    		try {
    			System.out.printf("Adding new highscore with playerName %s and time %s\n", playerName, duration);
    			preparedStatement = connect.prepareStatement("INSERT INTO HIGHSCORES(PLAYERNAME, DURATION) VALUES(?, ?);");
    			preparedStatement.setString(1, playerName);
    			preparedStatement.setString(2, duration);
    			preparedStatement.setQueryTimeout(QUERY_TIMEOUT);
    			
    			return preparedStatement.executeUpdate();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    		finally {
    			close(preparedStatement);
    		}
    	}
    	return 0;
    }
    
    public static ArrayList<Highscore> get10HighScores(){
    	ArrayList<Highscore> highscores = new ArrayList<>();
    	PreparedStatement preparedStatement = null;
    	ResultSet result = null;
    	connect = getConnection(DB_NAME);
    	
    	if(connect != null) {
    		try {
    			preparedStatement = connect.prepareStatement("SELECT * FROM HIGHSCORES ORDER BY DURATION ASC LIMIT 10;");
    			preparedStatement.setQueryTimeout(QUERY_TIMEOUT);
    			result = preparedStatement.executeQuery();
    			
    			while(result.next()) {
    				highscores.add(new Highscore(result.getString("PLAYERNAME"), result.getString("DURATION")));
    			}
    			
    			return highscores;
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	return null;
    }

	private static void close(PreparedStatement preparedStatement) {
		try {
			if(preparedStatement != null)
				preparedStatement.close();
			
			if(connect != null)
				connect.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
    
    
}
