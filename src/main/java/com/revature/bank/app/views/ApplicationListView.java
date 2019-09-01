package com.revature.bank.app.views;

import java.util.Scanner;

import com.revature.bank.app.models.AccountApplication;
import com.revature.bank.app.models.ApplicationManager;
import com.revature.bank.app.models.CustomerManager;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class ApplicationListView implements View
{
	@Override
	public void show(ViewController controller) 
	{
		ApplicationManager applicationManager = new ApplicationManager();
		
		CustomerManager customerManager = new CustomerManager();
		
		Scanner scanner = ViewController.scanner;
		
		for(AccountApplication application : applicationManager.getApplicationOwnershipMap().keySet())
		{
			System.out.println(application.getCustomer().getUsername() + " Status: " 
					+ application.isApplicationStatus());
		}
		
		System.out.println("---------------------------");
		
		AccountApplication application = null;
		
		String selectedUsername = "";
		
		while(true)
		{
			System.out.println("Please enter the username associated with the application "
					+ "you wish to view.");
			
			selectedUsername = scanner.nextLine();
			
			if(applicationManager.findApplicationByUsername(selectedUsername) != null)
			{
				application = applicationManager.findApplicationByUsername(selectedUsername);
				break;
			}
			else
			{
				System.out.println("That username is not asspciated with an application.");
			}
		}
		
		System.out.println("Application Status: " + application.isApplicationStatus());
		System.out.println(application.getCustomer().getFirstName() + " " 
				+ application.getCustomer().getLastName());
		System.out.println("Address: " + application.getCustomer().getAddress());
		
		System.out.println("---------------------------");
		
		String command = "";
		
		while(true)
		{
			System.out.println("To approve or deny this application, type approve or deny.");
			
			command = scanner.nextLine();
			
			if(!command.equals("approve") && !command.equals("deny"))
			{
				System.out.println("That input is not valid. Type either approve or deny.");
			}
			else
			{
				break;
			}
		}
		
		switch (command)
		{
		case "approve" :
			controller.getCurrentLoggedInEmployee().approveApplication(application);
			System.out.println("Application approved");
			System.out.println("-------------------------------");
			break;
			
		case "deny" :
			controller.getCurrentLoggedInEmployee().denyApplication(application);
			System.out.println("Application denied");
			System.out.println("-------------------------------");
			break;
		}
		
		controller.getLastView();
		
	}
}
