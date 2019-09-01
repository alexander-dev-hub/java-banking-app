package com.revature.bank.app.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.bank.app.models.Account;
import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.CustomerManager;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class ApprovedCustomerDAOImp implements ApprovedCustomerDAO
{	
	public static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

	@Override
	public ResultSet getAllApprovedCustomers() throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM APPROVED_CUSTOMER");
		
		return resultSet;
	}

	@Override
	public ResultSet getApprovedCustomers(int customerID) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery
				("SELECT * FROM APPROVED_CUSTOMER WHERE CUSTOMERID=" + customerID);
		
		return resultSet;
	}

	@Override
	public void updateCustomerBalance(Customer customer) throws SQLException 
	{
		double balance = CustomerManager.getAccountOwnershipMap().get(customer).getBalance();
		
		int accountNumber = CustomerManager.getAccountOwnershipMap().get(customer).getAccountNumber();
		
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		String sqlQueary = ("UPDATE BANK_ACCOUNT SET BALANCE=" + balance 
				+ " " + "WHERE ACCOUNT_NUMBER=" + accountNumber);
		
		statement.executeUpdate(sqlQueary);
	}

	@Override
	public void updateCustomerAddress(String address, int customerID) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		String sqlQueary = ("UPDATE APPROVED_CUSTOMER SET ADDRESS=" + "'" + address + "' " 
				+ "WHERE CUSTOMERID=" + "'" + customerID + "'");
		
		statement.executeUpdate(sqlQueary);
	}

	@Override
	public void insertNewCustomer(Customer customer, Account account) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("{ call CREATE_APPROVED_CUSTOMER(?,?,?,?,?)");
		
		CallableStatement callableStatement = connection.prepareCall(sqlQuery);
		
		callableStatement.setInt(1, customer.getCustomerID());
		callableStatement.setString(2, customer.getAddress());
		callableStatement.setString(3, customer.getFirstName());
		callableStatement.setString(4, customer.getLastName());
		callableStatement.setString(5, customer.getUsername());
		callableStatement.execute();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT MAX(CUSTOMERID) FROM APPROVED_CUSTOMER");
		
		while(resultSet.next())
		{
			customer.setCustomerID(resultSet.getInt(1));
		}
		
		resultSet = statement.executeQuery("SELECT MAX(ACCOUNT_NUMBER) FROM BANK_ACCOUNT");
		
		while(resultSet.next())
		{
			account.setAccountNumber(resultSet.getInt(1));
		}
	}

	@Override
	public void deleteCustomer(Customer customer) throws SQLException 
	{
		Connection connection = connectionFactory.getConnection();
		
		String sqlQuery = ("DELETE FROM APPROVED_CUSTOMER WHERE CUSTOMERID=" 
				+ "'" + customer.getCustomerID() + "'");
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		
		preparedStatement.executeUpdate();
	}

	@Override
	public void updateCustomerFirstname(String firstname, int customerID) throws SQLException
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		String sqlQueary = ("UPDATE APPROVED_CUSTOMER SET FIRSTNAME=" + "'" + firstname + "' " + "WHERE CUSTOMERID=" 
				+ "'" + customerID + "'");
		
		statement.executeUpdate(sqlQueary);
	}

	@Override
	public void updateCustomerLastname(String lastname, int customerID) throws SQLException
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		String sqlQueary = ("UPDATE APPROVED_CUSTOMER SET LASTNAME=" + "'" + lastname + "' " + "WHERE CUSTOMERID=" 
				+ "'" + customerID + "'");
		
		statement.executeUpdate(sqlQueary);
	}

}
