package com.revature.bank.app.models;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class Login 
{
	private final CustomerManager customerManager = new CustomerManager();
	
	private final EmployeeManager employeeManager = new EmployeeManager();
	
	private final AdminManager adminManager = new AdminManager();
	
	public boolean customerLogin(String username, String password)
	{
		boolean loginSuccess = false;
		
		for(User user : customerManager.getAccountOwnershipMap().keySet())
		{
			if(user.getUsername().equals(username))
			{
				if(user.getPassword().equals(password))
				{
					loginSuccess = true;
				}
				
				break;
			}
		}
		
		return loginSuccess;
	}
	
	public boolean employeeLogin(String username, String password)
	{
		boolean loginSuccess = false;
		
		if(employeeManager.findEmployee(username) != null)
		{
			if(employeeManager.findEmployee(username).getUsername().equals(username))
			{
				if(employeeManager.findEmployee(username).getPassword().equals(password))
				{
					loginSuccess = true;
				}
			}
		}
		
		return loginSuccess;
	}
	
	public boolean adminLogin(String username, String password)
	{
		boolean loginSuccess = false;
		
		if(adminManager.findAdmin(username) != null)
		{
			if(adminManager.findAdmin(username).getUsername().equals(username))
			{
				if(adminManager.findAdmin(username).getPassword().equals(password))
				{
					loginSuccess = true;
				}
			}
		}
		
		return loginSuccess;
	}
}
