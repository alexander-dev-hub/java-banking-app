package com.revature.bank.app.views;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bank.app.jdbc.ApprovedCustomerDAOImp;
import com.revature.bank.app.models.Account;
import com.revature.bank.app.models.AccountManager;
import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.CustomerManager;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class CustomerDeleteView implements View
{
	private static ApprovedCustomerDAOImp customerDAO =  new ApprovedCustomerDAOImp();
	
	@Override
	public void show(ViewController controller) 
	{
		CustomerManager customerManager = new CustomerManager();
		
		AccountManager accountManager = new AccountManager();
		
		Scanner scanner = ViewController.scanner;
		
		String username = "";
		
		Customer customer = null;
		
		String command = "";
		
		for(Customer c : customerManager.getAccountOwnershipMap().keySet())
		{
			System.out.println(c.getUsername() 
					+ " " + customerManager.getAccountOwnershipMap().get(c).getAccountNumber());
		}
		
		System.out.println("-------------------------------");
		
		while(true)
		{
			System.out.println("Enter the username associated to the account you want to delete");
			
			username = scanner.nextLine();
			
			if(customerManager.findCustomerWithUserName(username) != null)
			{
				customer = customerManager.findCustomerWithUserName(username);
				break;
			}
			else
			{
				System.out.println("That customer does not exist.");
			}
		}
		
		while(true)
		{
			System.out.println("Are you sure you want to delete this account? (Y/N)");
			
			command = scanner.nextLine();
			
			if(!command.equals("Y") && !command.equals("N"))
			{
				System.out.println("That input is not valid.");
			}
			else
			{
				break;
			}
		}
		
		switch (command)
		{
		case "Y" :
			customerManager.getAccountOwnershipMap().remove(customer);
			for(Account account : accountManager.getAccountOwnershipMap().keySet())
			{
				if(accountManager.getAccountOwnershipMap().get(account) == customer)
				{
					accountManager.getAccountOwnershipMap().remove(account);
					break;
				}
			}
			try 
			{
				customerDAO.deleteCustomer(customer);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			System.out.println("Account deleted.");
			controller.getLastView();
			break;
			
		case "N" :
			controller.getLastView();
			break;
		}
	}	
}
