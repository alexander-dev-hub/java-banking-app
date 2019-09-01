package com.revature.bank.app.views;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.CustomerManager;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class AdminActionsView implements View
{
	@Override
	public void show(ViewController controller) 
	{
		CustomerManager customerManager = new CustomerManager();
		
		Scanner scanner = ViewController.scanner;
		
		String username = "";
		
		Customer customer = null;
		
		Customer customer2 = null;
		
		String command = "";
		
		double amount = 0;
		
		for(Customer c : customerManager.getAccountOwnershipMap().keySet())
		{
			System.out.println(c.getUsername() 
					+ " " + customerManager.getAccountOwnershipMap().get(c).getAccountNumber());
		}
		
		System.out.println("-------------------------------");
		
		while(true)
		{
			System.out.println("Enter the username associated to the account you want to " 
					+ "perform actions on.");
			
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
		
		while(true)
		{
			System.out.println("What action would you like to perform?");
			System.out.println("withdraw: Allows you to withdraw from the customer.");
			System.out.println("deposit: Allows you to deposit to the customer.");
			System.out.println("transfer: Allows you to transfer between two accounts.");
			System.out.println("exit: exits this menu.");
			
			command = scanner.nextLine();
			
			if(!command.equals("withdraw") && !command.equals("deposit") 
					&& !command.equals("transfer") && !command.equals("exit"))
			{
				System.out.println("That is not a valid command.");
			}
			else
			{
				break;
			}
		}
		
		switch (command)
		{
		case "withdraw" :
			while(true)
			{
				try
				{
					System.out.println("How much would you like to withdraw?");
					amount = scanner.nextDouble();
					scanner.nextLine();
					
					if(amount < 0)
					{
						System.out.println("Please input a positive number.");
					}
					else
					{
						break;
					}
				}
				catch(InputMismatchException e)
				{
					System.out.println("Please enter a number.");
				}
			}
			customerManager.withdraw(customer, amount);
			break;
			
		case "deposit" : 
			while(true)
			{
				try
				{
					System.out.println("How much would you like to deposit?");
					amount = scanner.nextDouble();
					scanner.nextLine();
					
					if(amount < 0)
					{
						System.out.println("Please input a positive number.");
					}
					else
					{
						break;
					}
				}
				catch(InputMismatchException e)
				{
					System.out.println("Please enter a number.");
				}
			}
			customerManager.deposit(customer, amount);
			break;
			
		case "transfer" :
			while(true)
			{
				if(customerManager.getAccountOwnershipMap().keySet().size() >= 2)
				{
					System.out.println("Enter the username associated to the account you want to " 
							+ "transfer to.");
					
					username = scanner.nextLine();
					
					if(customerManager.findCustomerWithUserName(username) != null)
					{
						customer2 = customerManager.findCustomerWithUserName(username);
						break;
					}
					else
					{
						System.out.println("That customer does not exist.");
					}
				}
				else 
				{
					System.out.println("There are not enough accounts to do a transfer.");
					break;
				}
			}
			while(true)
			{
				try
				{
					System.out.println("Enter the amount to transfer to " + username);
					amount = scanner.nextDouble();
					scanner.nextLine();
					
					if(amount < 0)
					{
						System.out.println("Please input a positive number.");
					}
					else
					{
						break;
					}
				}
				catch(InputMismatchException e)
				{
					System.out.println("Please enter a number.");
				}
			}
			customerManager.transfer(customer, customer2, amount);
			break;
			
		case "exit" :
			break;
		}
		controller.getLastView();
	}
}
