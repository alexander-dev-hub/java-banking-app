package com.revature.bank.app.views;

import java.util.Scanner;

import com.revature.bank.app.models.AdminManager;
import com.revature.bank.app.models.Administrator;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class NewAdminView implements View
{

	@Override
	public void show(ViewController controller) 
	{
		String username = "";
		
		String password = "";
		
		Scanner scanner = ViewController.scanner;
		
		AdminManager manager = new AdminManager();
		
		System.out.println("Welcome to the new admin creation. \n" 
				+ "Please enter your username: ");
		
		username = scanner.nextLine();
		
		System.out.println("Please enter your password: ");
		
		password = scanner.nextLine();
		
		Administrator admin = new Administrator(username, password);
		
		manager.addNewAdministrator(admin);
		
		System.out.println("You have successfully created a new admin account");
		
		controller.getLastView();	
	}

}
