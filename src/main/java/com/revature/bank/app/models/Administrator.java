package com.revature.bank.app.models;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class Administrator extends Employee 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7439050491767585139L;
	
	private static CustomerManager customerManager = new CustomerManager();
	
	public Administrator(String username, String password) 
	{
		super(username, password);
	}
	
	public void adminTransfer(Customer user1, Customer user2, double amount)
	{
		customerManager.transfer(user1, user2, amount);
	}
	
	public void adminDeposit(Customer user, double amount)
	{
		customerManager.deposit(user, amount);
	}
	
	public void adminWithdraw(Customer user, double amount)
	{
		customerManager.deposit(user, amount);
	}
}
