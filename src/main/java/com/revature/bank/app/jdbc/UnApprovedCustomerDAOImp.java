package com.revature.bank.app.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.bank.app.models.AccountApplication;
import com.revature.bank.app.models.Customer;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class UnApprovedCustomerDAOImp implements UnApprovedCustomerDAO
{
	public static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
	
	@Override
	public ResultSet getAllUnApprovedCustomers() throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM UNAPPROVED_CUSTOMER");
		
		return resultSet;
	}

	@Override
	public ResultSet getUnApprovedCustomers(int customerID) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery
				("SELECT * FROM UNAPPROVED_CUSTOMER WHERE CUSTOMERID=" + customerID);
		
		return resultSet;
	}

	@Override
	public void addNewUnApprovedCustomer(Customer customer, AccountApplication application) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("{ call CREATE_UA_CUSTOMER(?,?,?,?,?)");
		
		CallableStatement callableStatement = connection.prepareCall(sqlQuery);
		
		callableStatement.setString(1, customer.getAddress());
		callableStatement.setString(2, customer.getFirstName());
		callableStatement.setString(3, customer.getLastName());
		callableStatement.setString(4, customer.getUsername());
		callableStatement.setString(5, customer.getPassword());
		callableStatement.execute();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT MAX(CUSTOMERID) FROM UNAPPROVED_CUSTOMER");
		
		while(resultSet.next())
		{
			customer.setCustomerID(resultSet.getInt(1));
		}
		
		resultSet = statement.executeQuery("SELECT MAX(APPLICATIONID) FROM ACCOUNT_APPLICATION");
		
		while(resultSet.next())
		{
			application.setApplicationID(resultSet.getInt(1));
		}
	}

	@Override
	public void deleteUnApprovedCustomer(Customer customer) throws SQLException
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("DELETE FROM UNAPPROVED_CUSTOMER WHERE CUSTOMERID=" 
				+ "'" + customer.getCustomerID() + "'");
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		
		preparedStatement.executeUpdate();
	}

}
