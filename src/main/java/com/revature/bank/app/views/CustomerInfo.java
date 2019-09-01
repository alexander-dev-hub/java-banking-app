package com.revature.bank.app.views;

import java.util.Scanner;

import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.CustomerManager;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class CustomerInfo implements View
{
	@Override
	public void show(ViewController controller) 
	{
		CustomerManager customerManager = new CustomerManager();
		
		Scanner scanner = ViewController.scanner;
		
		String username = "";
		
		Customer customer = null;
		
		for(Customer c : customerManager.getAccountOwnershipMap().keySet())
		{
			System.out.println(c.getUsername() 
					+ " " + customerManager.getAccountOwnershipMap().get(c).getAccountNumber());
		}
		
		System.out.println("-------------------------------");
		
		while(true)
		{
			System.out.println("Type the name of the customer you want to view.");
			System.out.println("Or type exit to exit this menu.");
			
			username = scanner.nextLine();
			
			if(customerManager.findCustomerWithUserName(username) != null)
			{
				customer = customerManager.findCustomerWithUserName(username);
				break;
			}
			else if(username.equals("exit"))
			{
				break;
			}
			else
			{
				System.out.println("That customer does not exist.");
			}
		}
		if(customer != null)
		{
			System.out.println("-------------------------------");
			System.out.println(customer.getFirstName() + customer.getLastName());
			System.out.println("Account #: " 
					+ customerManager.getAccountOwnershipMap().get(customer).getAccountNumber());
			System.out.println("Balance: $" 
					+ customerManager.getAccountOwnershipMap().get(customer).getBalance());
			System.out.println("Address: " + customer.getAddress());
			System.out.println("-------------------------------");
		}
	
		controller.getLastView();
	}

}
