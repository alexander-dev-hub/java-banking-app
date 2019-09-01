package com.revature.bank.app.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.bank.app.jdbc.UnApprovedCustomerDAOImp;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class ApplicationManager implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3190714050416592140L;
	
	private static Map<AccountApplication, Customer> applicationOwnershipMap 
		= new HashMap<AccountApplication, Customer>();
	
	private static UnApprovedCustomerDAOImp customerDAO = new UnApprovedCustomerDAOImp();
	
	public void createNewApplication(Customer user)
	{
		this.createNewApplication(user, 0);
	}
	
	public void createNewApplication(Customer user, double intitialBalance)
	{
		AccountApplication application = new AccountApplication(user, intitialBalance);
		applicationOwnershipMap.put(application, user);
		try 
		{
			customerDAO.addNewUnApprovedCustomer(user, application);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void approveApplication(AccountApplication application)
	{
		application.setApplicationStatus(true);
		
		AccountManager accountManager = new AccountManager();
		
		accountManager.addNewAccount(application.getCustomer(), application.getInitialBalance());
		
		applicationOwnershipMap.remove(application);
		
		try 
		{
			customerDAO.deleteUnApprovedCustomer(application.getCustomer());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void denyApplication(AccountApplication application)
	{
		application.setApplicationStatus(false);
		
		ApplicationManager.applicationOwnershipMap.remove(application);
	}
	
	public AccountApplication findApplicationByUsername(String username)
	{
		AccountApplication foundApplication = null;
		
		for(AccountApplication application : ApplicationManager.applicationOwnershipMap.keySet())
		{
			if(ApplicationManager.applicationOwnershipMap.get(application).getUsername().equals(username))
			{
				foundApplication = application;
				break;
			}
		}
		
		return foundApplication;
	}

	public static Map<AccountApplication, Customer> getApplicationOwnershipMap() 
	{
		return applicationOwnershipMap;
	}

	public static void setApplicationOwnershipMap(Map<AccountApplication, Customer> applicationOwnershipMap) 
	{
		ApplicationManager.applicationOwnershipMap = applicationOwnershipMap;
	}
	
}
