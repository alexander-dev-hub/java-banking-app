package com.revature.bank.app.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.bank.app.jdbc.ApprovedCustomerDAOImp;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class CustomerManager implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -586381320477593170L;
	
	private static TransactionLogger logger = new TransactionLogger();
	
	private static Map<Customer, Account> accountOwnershipMap 
		= new HashMap<Customer, Account>(); 
	
	private static ApprovedCustomerDAOImp customerDAO = new ApprovedCustomerDAOImp();
	
	public void deposit(Customer user, double amount)
	{
		accountOwnershipMap.get(user).deposit(amount);
		logger.logDeposit(user.getUsername(), amount);
		
		try 
		{
			customerDAO.updateCustomerBalance(user);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void withdraw(Customer user, double amount)
	{
		accountOwnershipMap.get(user).withdraw(amount);
		logger.logWithdrawl(user.getUsername(), amount);
		
		try 
		{
			customerDAO.updateCustomerBalance(user);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void transfer(Customer user1, Customer user2, double amount)
	{
		accountOwnershipMap.get(user1).withdraw(amount);
		accountOwnershipMap.get(user2).deposit(amount);
		logger.logTransfer(user1.getUsername(), user2.getUsername(), amount);
		
		try 
		{
			customerDAO.updateCustomerBalance(user1);
			customerDAO.updateCustomerBalance(user2);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void addNewUser(Customer newUser, Account account)
	{
		accountOwnershipMap.put(newUser, account);
	}
	
	public void removeUser(Customer user)
	{
		accountOwnershipMap.remove(user);
	}
	
	public Customer findCustomerWithUserName(String username)
	{
		Customer foundCustomer = null;
		
		for(Customer customer : this.accountOwnershipMap.keySet())
		{
			if(customer.getUsername().equals(username))
			{
				foundCustomer = customer;
				break;
			}
		}
		
		return foundCustomer;
	}

	public static void setAccountOwnershipMap(Map<Customer, Account> accountOwnershipMap) {
		CustomerManager.accountOwnershipMap = accountOwnershipMap;
	}

	public static Map<Customer, Account> getAccountOwnershipMap() 
	{
		return accountOwnershipMap;
	}
}
