package com.revature.bank.app.views;

import java.util.Scanner;

import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.CustomerManager;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class CustomerEditView implements View
{
	@Override
	public void show(ViewController controller) 
	{
		CustomerManager customerManager = new CustomerManager();
		
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
			System.out.println("Type the name of the customer you want to edit.");
			
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
		System.out.println("-------------------------------");
		System.out.println("Username: " + customer.getUsername());
		System.out.println(customer.getFirstName() + customer.getLastName());
		System.out.println("Account #: " 
				+ customerManager.getAccountOwnershipMap().get(customer).getAccountNumber());
		System.out.println("Balance: $" 
				+ customerManager.getAccountOwnershipMap().get(customer).getBalance());
		System.out.println("Address: " + customer.getAddress());
		System.out.println("-------------------------------");
		
		while(true)
		{
			System.out.println("What info would you like to edit? (name, address, username, password)");
			System.out.println("Type exit to exit this menu.");
			
			command = scanner.nextLine();
			
			if(!command.equals("name") && !command.equals("address") 
					&& !command.equals("username") && !command.equals("password")
					&& !command.equals("exit"))
			{
				System.out.println("That command does not exist.");
			}
			else
			{
				break;
			}
		}
		
		switch (command)
		{
		case "name" :
			System.out.println("Enter the new first name: ");
			customer.setFirstName(scanner.nextLine());
			System.out.println("Enter the new last name: ");
			customer.setLastName(scanner.nextLine());
			break;
			
		case "address" :
			System.out.println("Enter the new address: ");
			customer.setAddress(scanner.nextLine());
			break;
			
		case "username" :
			System.out.println("Enter the new username: ");
			customer.setUsername(scanner.nextLine());
			break;
			
		case "password" :
			System.out.println("Enter the new password: ");
			customer.setPassword(scanner.nextLine());
			System.out.println("Password changed.");
			break;
			
		case "exit" :
			controller.getLastView();
			break;
		}
	}
}
