package com.revature.bank.app.views;

import java.util.Scanner;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class AdminView implements View
{

	@Override
	public void show(ViewController controller) 
	{	
		Scanner scanner = ViewController.scanner;
		
		String command = "";
		
		while(true)
		{
			System.out.println("Administrator: " 
					+ controller.getCurrentLoggedInAdmin().getUsername());
			System.out.println("-------------------------------");
			System.out.println("Commands: ");
			System.out.println("view info: Displays a specific customer's information.");
			System.out.println("edit info: Opens a menu for editing a specific customer's" 
					+ " information.");
			System.out.println("view apps: Displays all open applications and allows you to " +
					"approve or deny them.");
			System.out.println("cancel: Opens a menu for canceling a customers existing account.");
			System.out.println("transactions: Opens a menu for manuall forcing transactions " +
					"on user's accounts");
			System.out.println("logout: Logs you out of the current session.");
			
			command = scanner.nextLine();
			
			if(!command.equals("view info") && !command.equals("edit info") 
					&& !command.equals("view apps") && !command.equals("cancel")
					&& !command.equals("transactions") && !command.equals("logout"))
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
			
		case "edit info" :
			controller.getNextView("customer edit");
			break;
			
		case "view apps" : 
			controller.getNextView("applications");
			break;
			
		case "cancel" :
			controller.getNextView("customer delete");
			break;
			
		case "transactions" :
			controller.getNextView("admin actions");
			break;
			
		case "logout" :
			controller.getLastView();
			break;
		}
	}

}
