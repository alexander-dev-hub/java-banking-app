package com.revature.bank.app.views;

import java.util.Scanner;
import java.util.Stack;

import com.revature.bank.app.models.Administrator;
import com.revature.bank.app.models.Customer;
import com.revature.bank.app.models.Employee;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class ViewController 
{
	public static Scanner scanner = new Scanner(System.in);
	
	private Stack<View> viewStack;
	
	private ViewFactory viewFactory;
	
	private Customer currentLoggedInCustomer;
	
	private Employee currentLoggedInEmployee;
	
	private Administrator currentLoggedInAdmin;
	
	public ViewController()
	{
		viewStack = new Stack<View>();
		
		viewFactory = new ViewFactory();
		
		this.currentLoggedInCustomer = null;
		
		LoginView loginView = new LoginView();
		
		viewStack.push(loginView);
	}

	public void start()
	{
		while(true)
		{
			if(!viewStack.isEmpty())
			{
				if(viewStack.peek() != null)
				{
					viewStack.peek().show(this);
				}
				else
				{
					break;
				}
			}
			else 
			{
				break;
			}
		}
	}
	
	public Employee getCurrentLoggedInEmployee() 
	{
		return currentLoggedInEmployee;
	}

	public void setCurrentLoggedInEmployee(Employee currentLoggedInEmployee) 
	{
		this.currentLoggedInEmployee = currentLoggedInEmployee;
	}

	public Administrator getCurrentLoggedInAdmin() 
	{
		return currentLoggedInAdmin;
	}

	public void setCurrentLoggedInAdmin(Administrator currentLoggedInAdmin) 
	{
		this.currentLoggedInAdmin = currentLoggedInAdmin;
	}
	
	public Customer getCurrentLoggedInCustomer() 
	{
		return currentLoggedInCustomer;
	}

	public void setCurrentLoggedInCustomer(Customer currentLoggedInCustomer) 
	{
		this.currentLoggedInCustomer = currentLoggedInCustomer;
	}
	
	public void getNextView(String string)
	{
		viewStack.push(viewFactory.determineNextView(string));
	}
	
	public void getLastView()
	{
		viewStack.pop();
	}
}
