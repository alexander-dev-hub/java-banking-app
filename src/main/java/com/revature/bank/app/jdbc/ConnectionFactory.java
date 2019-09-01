package com.revature.bank.app.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class ConnectionFactory 
{
	private static ConnectionFactory connectionFactory = new ConnectionFactory();

	private static Connection connection;
	
	private ConnectionFactory()
	{
		super();
		this.initializeConnection();
	}
	
	private void initializeConnection()
	{
		Properties properties = new Properties();
		
		try 
		{
			properties.load(new FileReader("jdbc.properties"));
		
			Class.forName(properties.getProperty("driver"));
			
			connection = DriverManager.getConnection(properties.getProperty("url"), 
					properties.getProperty("user"), properties.getProperty("password"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		if(connectionFactory == null)
		{
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
	public Connection getConnection()
	{	
		return connection;
	}
}
