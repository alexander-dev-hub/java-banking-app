package com.revature.bank.app.models;

import java.io.Serializable;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class AccountApplication implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6471022586696891991L;
	
	private int applicationID;

	private boolean applicationStatus;
	
	private final Customer customer;
	
	private final double initialBalance;
	
	public AccountApplication(Customer customer)
	{
		this.customer = customer;
		
		this.initialBalance = 0;
		
		this.applicationStatus = false;
		
		this.applicationID = 0;
	}

	public AccountApplication(Customer customer, double initialBalance)
	{
		this.customer = customer;
		
		this.initialBalance = initialBalance;
		
		this.applicationStatus = false;
	}
	
	public int getApplicationID() 
	{
		return applicationID;
	}

	public void setApplicationID(int accountID) 
	{
		this.applicationID = accountID;
	}

	public boolean isApplicationStatus() 
	{
		return applicationStatus;
	}

	public void setApplicationStatus(boolean applicationStatus) 
	{
		this.applicationStatus = applicationStatus;
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public double getInitialBalance() 
	{
		return initialBalance;
	}
}
