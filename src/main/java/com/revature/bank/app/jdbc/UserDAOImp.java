package com.revature.bank.app.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class UserDAOImp implements UserDAO
{
	public static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
	
	@Override
	public void addNewUser(String username, String password, String type) throws SQLException
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = "INSERT INTO BANK_USERS VALUES(?, ?, ?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, type);
		
		preparedStatement.executeUpdate();
	}

	@Override
	public void deleteUser(String username) throws SQLException
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("DELETE FROM BANK_USERS WHERE USERNAME=" + "'" + username + "'");
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		
		preparedStatement.executeUpdate();
	}

	@Override
	public ResultSet getAllEmployees() throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM BANK_USERS WHERE TYPE='employee'");
		
		return resultSet;
	}

	@Override
	public ResultSet getAllAdmins() throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM BANK_USERS WHERE TYPE='administrator'");
		
		return resultSet;
	}

	@Override
	public void updateUsername(String newUsername, String oldUsername) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("UPDATE BANK_USERS SET USERNAME=" + "'" + newUsername + "'" 
				+ " WHERE USERNAME=" + "'" + oldUsername + "'");
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		
		preparedStatement.executeUpdate();
	}

	@Override
	public void updatePassword(String newPassword, String username) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("UPDATE BANK_USERS SET PASSWORD=" + "'" + newPassword + "'" 
				+ " WHERE USERNAME=" + "'" + username + "'");
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		
		preparedStatement.executeUpdate();
	}

}
