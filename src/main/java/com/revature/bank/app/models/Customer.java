package com.revature.bank.app.models;

import java.sql.SQLException;

import com.revature.bank.app.jdbc.ApprovedCustomerDAOImp;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class Customer extends User 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 346129078310983645L;
	
	private static ApprovedCustomerDAOImp customerDAO = new ApprovedCustomerDAOImp();
	
	private int customerID;

	private String address;
	
	private String firstName;
	
	private String lastName;
	
	public Customer(String username, String password) 
	{
		super(username, password);
		
		address = "";
		
		firstName = "";
		
		lastName = "";
		
		customerID = 0;
	}

	public int getCustomerID() 
	{
		return customerID;
	}

	public void setCustomerID(int customerID) 
	{
		this.customerID = customerID;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
		
		try 
		{
			customerDAO.updateCustomerAddress(address, customerID);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
		
		try 
		{
			customerDAO.updateCustomerFirstname(firstName, customerID);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
		
		try 
		{
			customerDAO.updateCustomerLastname(lastName, customerID);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	

}
