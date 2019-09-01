package com.revature.bank.app.views;

import java.util.Scanner;

import com.revature.bank.app.models.Employee;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class EmployeeView implements View
{
	public void show(ViewController controller) 
	{
		Employee employee = controller.getCurrentLoggedInEmployee();
		
		Scanner scanner = ViewController.scanner;
		
		String command = "";
		
		while(true)
		{
			System.out.println("Employee: " + employee.getUsername());
			
			System.out.println("Commands: ");
			System.out.println("view info: Displays a specific customer's info.");
			System.out.println("view apps: Displays all open applications.");
			System.out.println("logout: Logs you out of your current session.");
			
			command = scanner.nextLine();
			
			if(!command.equals("view info") && !command.contentEquals("view apps") 
					&& !command.equals("logout"))
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
		case "view info" :
			controller.getNextView("customer info");
			break;
			
		case "view apps" :
			controller.getNextView("applications");
			break;
			
		case "logout" :
			controller.getLastView();
			break;
		}
	}
	
}
