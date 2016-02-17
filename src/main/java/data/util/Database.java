package main.java.data.util;

import java.sql.*;

public class Database {
	
	private static Database instance = null;
	
	private final static String URL = "jdbc:mysql://localhost/arieldb";
	private final static String USER = "ariel";
	private final static String PASSWORD = "1234";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	
	private Connection connection;

	private Database(){
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {closeConnection();}));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query){
		try{
			Statement statement = connection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int executeUpdate(String query){
		try{
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public PreparedStatement prepareStatement(String query){
		try{
			return connection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CallableStatement prepareCall(String query){
		try{
			return connection.prepareCall(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public static Database get(){
		if(instance==null) instance = new Database();
		return instance;
	}
	

}
