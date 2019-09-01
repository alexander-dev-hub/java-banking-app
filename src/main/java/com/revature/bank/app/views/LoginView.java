package com.revature.bank.app.views;

import java.util.Map;
import java.util.Scanner;

import com.revature.bank.app.models.AccountApplication;
import com.revature.bank.app.models.AdminManager;
import com.revature.bank.app.models.ApplicationManager;
import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.CustomerManager;
import com.revature.bank.app.models.EmployeeManager;
import com.revature.bank.app.models.Login;
import com.revature.bank.app.models.User;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class LoginView implements View
{
	private static Login login = new Login();
	
	@Override
	public void show(ViewController controller) 
	{
		Scanner scanner = ViewController.scanner;
		
		String username = "";
		
		String password = "";
		
		String loginType = "";
		
		while(true)
		{
			System.out.println("Enter your login type (customer, employee or admin): ");
			System.out.println("Or type exit to exit the app.");
			
			loginType = scanner.nextLine();
			
			if(!loginType.equals("customer") && !loginType.equals("employee") 
					&& !loginType.equals("admin") && !loginType.equals("exit"))
			{
				System.out.println("That is not a valid login type. \n" 
						+ "Please enter either customer, employee or admin.");
			}
			else
			{
				break;
			}
		}
		
		switch (loginType)
		{
		case "customer" :
			this.performCustomerLogin(username, password, controller, scanner);
			break;
			
		case "employee" :
			this.performEmployeeLogin(username, password, controller, scanner);
			break;
			
		case "admin" :
			this.performAdminLogin(username, password, controller, scanner);
			break;
			
		case "exit" :
			controller.getLastView();
			break;
		}
	}
	
	private boolean findExistingUsername(String username)
	{
		boolean found = false;
		for(User user : CustomerManager.getAccountOwnershipMap().keySet())
		{
			if(user.getUsername().equals(username))
			{
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	private boolean foundWaitingUser(String username, Map<AccountApplication, Customer> map)
	{
		boolean found = false;
		for(AccountApplication application : map.keySet())
		{
			if(username.equals(map.get(application).getUsername()))
			{
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	private void performCustomerLogin(String username, String password, 
			ViewController controller, Scanner scanner)
	{
		ApplicationManager applicationManager = new ApplicationManager();
		
		CustomerManager customerManager = new CustomerManager();
		
		System.out.println("Please enter your username: ");
		
		username = scanner.nextLine();
		
		if(!this.foundWaitingUser(username, applicationManager.getApplicationOwnershipMap()))
		{
			if(this.findExistingUsername(username))
			{
				System.out.println("Please enter your password: ");
				
				password = scanner.nextLine();
				
				if(login.customerLogin(username, password))
				{
					controller.setCurrentLoggedInCustomer(
							customerManager.findCustomerWithUserName(username));
					controller.getNextView("customer");
				}
				else 
				{
					System.out.println("That password is incorrect");
				}
			}
			else
			{
				System.out.println("No account found, creating new Account.");
				
				controller.getNextView("new account");
			}
		}
		else
		{
			System.out.println("That account is still pending approval.");
		}
	}
	
	private void performEmployeeLogin(String username, String password, 
			ViewController controller, Scanner scanner)
	{
		EmployeeManager manager = new EmployeeManager();
		
		System.out.println("Please enter your username: ");
		
		username = scanner.nextLine();
		
		if(manager.findEmployee(username) != null)
		{
			System.out.println("Please enter your password: ");
			
			password = scanner.nextLine();
			
			if(login.employeeLogin(username, password))
			{
				controller.setCurrentLoggedInEmployee(manager.findEmployee(username));
				controller.getNextView("employee");
			}
			else
			{
				System.out.println("That password is incorrect.");
			}
		}
		else
		{
			controller.getNextView("new employee");
		}
	}
	
	private void performAdminLogin(String username, String password, 
			ViewController controller, Scanner scanner)
	{
		AdminManager manager = new AdminManager();
		
		System.out.println("Please enter your username: ");
		
		username = scanner.nextLine();
		
		if(manager.findAdmin(username) != null)
		{
			System.out.println("Please enter your password: ");
			
			password = scanner.nextLine();
			
			if(login.adminLogin(username, password))
			{
				controller.setCurrentLoggedInAdmin(manager.findAdmin(username));
				controller.setCurrentLoggedInEmployee(manager.findAdmin(username));
				controller.getNextView("admin");
			}
			else
			{
				System.out.println("That password is incorrect.");
			}
		}
		else
		{
			controller.getNextView("new admin");
		}
	}

}
