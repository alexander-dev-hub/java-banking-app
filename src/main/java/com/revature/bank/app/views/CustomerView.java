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
public class CustomerView implements View 
{
	@Override
	public void show(ViewController controller) 
	{
		CustomerManager customerManager = new CustomerManager();
		
		Customer customer = controller.getCurrentLoggedInCustomer();
		
		String command = "";
		
		Scanner scanner = ViewController.scanner;
		
		System.out.println("Account info: ");
	
		System.out.println(customer.getUsername() + " " 
				+ customerManager.getAccountOwnershipMap().get(customer).getAccountNumber());
		
		System.out.println(customer.getFirstName() + " " + customer.getLastName());
		
		System.out.println("Balance: $" + CustomerManager.getAccountOwnershipMap().get(customer).getBalance());
		
		System.out.println(customer.getAddress() + "\n");
		
		System.out.println("---------------------------");
		
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
		
		double amount = 0;
		String username = "";
		
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
			Customer customer2 = null;
			while(true)
			{
				if(customerManager.getAccountOwnershipMap().keySet().size() >= 2)
				{
					for(Customer c : customerManager.getAccountOwnershipMap().keySet())
					{
						System.out.println(c.getUsername() 
								+ " " + customerManager.getAccountOwnershipMap().get(c).getAccountNumber());
					}
					while(true)
					{
						System.out.println("---------------------------");
						System.out.println("Enter the username associated to the account you want to " 
								+ "transfer to.");
						
						username = scanner.nextLine();
						
						if(customerManager.findCustomerWithUserName(username).equals(customer))
						{
							System.out.println("That is your username. Enter someone else's.");
						}
						else
						{
							break;
						}
					}
					if(customerManager.findCustomerWithUserName(username) != null)
					{
						customer2 = customerManager.findCustomerWithUserName(username);
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
			}
			break;
			
		case "exit" :
			controller.getLastView();
			break;
		}
	}	
}
