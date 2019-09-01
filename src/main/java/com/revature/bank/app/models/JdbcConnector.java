package com.revature.bank.app.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.bank.app.jdbc.ApprovedCustomerDAOImp;
import com.revature.bank.app.jdbc.ConnectionFactory;
import com.revature.bank.app.jdbc.UnApprovedCustomerDAOImp;
import com.revature.bank.app.jdbc.UserDAOImp;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class JdbcConnector 
{	
	private static ApprovedCustomerDAOImp aPCustomer = new ApprovedCustomerDAOImp();
	
	private static UnApprovedCustomerDAOImp uACustomer = new UnApprovedCustomerDAOImp();
	
	private static UserDAOImp userDAO = new UserDAOImp();
	
	public static ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
	
	public void importData()
	{
		Connection connection = connectionFactory.getConnection();
		
		try 
		{	
			ResultSet resultSet = aPCustomer.getAllApprovedCustomers();
			
			while(resultSet.next())
			{
				String username = resultSet.getString("USERNAME");
				
				String password = "";

				int accountNumber = resultSet.getInt("ACCOUNT_NUMBER");
				
				int balance = 0;
				
				int customerID = resultSet.getInt("CUSTOMERID");
				
				String address = resultSet.getString("ADDRESS");
				
				String firstname = resultSet.getString("FIRSTNAME");
				
				String lastname = resultSet.getString("LASTNAME");
				
				Statement statement = connection.createStatement();
				
				ResultSet rs = statement.executeQuery("SELECT PASSWORD FROM BANK_USERS WHERE USERNAME=" 
						+ "'" + username + "'");
				
				while(rs.next())
				{
					password = rs.getString("PASSWORD");
				}
				
				rs = statement.executeQuery("SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER=" 
						+ "'" + accountNumber + "'");
				
				while(rs.next())
				{
					balance = rs.getInt("BALANCE");
				}
				
				Customer customer = new Customer(username, password);
				customer.setAddress(address);
				customer.setCustomerID(customerID);
				customer.setFirstName(firstname);
				customer.setLastName(lastname);
				
				Account account = new Account(balance, accountNumber);
				
				CustomerManager.getAccountOwnershipMap().put(customer, account);
				
				AccountManager.getAccountOwnershipMap().put(account, customer);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			ResultSet resultSet2 = uACustomer.getAllUnApprovedCustomers();
			
			while(resultSet2.next())
			{
				String username = resultSet2.getString("USERNAME");
				
				String password = "";
				
				int applicationNumber = resultSet2.getInt("APPLICATIONID");
				
				int customerID = resultSet2.getInt("CUSTOMERID");
				
				String address = resultSet2.getString("ADDRESS");
				
				String firstname = resultSet2.getString("FIRSTNAME");
				
				String lastname = resultSet2.getString("LASTNAME");
				
				Statement statement = connection.createStatement();
				
				ResultSet rs = statement.executeQuery("SELECT PASSWORD FROM BANK_USERS WHERE USERNAME=" 
						+ "'" + username + "'");
				
				while(rs.next())
				{
					password = rs.getString("PASSWORD");
				}
				
				Customer customer = new Customer(username, password);
				customer.setAddress(address);
				customer.setCustomerID(customerID);
				customer.setFirstName(firstname);
				customer.setLastName(lastname);
				
				AccountApplication application = new AccountApplication(customer);
				application.setApplicationID(applicationNumber);
				
				ApplicationManager.getApplicationOwnershipMap().put(application, customer);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			ResultSet resultSet3 = userDAO.getAllEmployees();
			
			while(resultSet3.next())
			{
				String username = resultSet3.getString("USERNAME");
				
				String password = resultSet3.getString("PASSWORD");
				
				Employee employee = new Employee(username, password);
				
				EmployeeManager.getEmployeelist().add(employee);
			}
			
			resultSet3 = userDAO.getAllAdmins();
			
			while(resultSet3.next())
			{
				String username = resultSet3.getString("USERNAME");
				
				String password = resultSet3.getString("PASSWORD");
				
				Administrator admin = new Administrator(username, password);
				
				AdminManager.getAdminList().add(admin);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	

}
