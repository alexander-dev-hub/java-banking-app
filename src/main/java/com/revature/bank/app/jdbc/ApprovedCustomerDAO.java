package com.revature.bank.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bank.app.models.Account;
import com.revature.bank.app.models.Customer;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public interface ApprovedCustomerDAO 
{
	ResultSet getAllApprovedCustomers() throws SQLException;
	
	ResultSet getApprovedCustomers(int customerID) throws SQLException;
	
	void updateCustomerBalance(Customer customer) throws SQLException;
	
	void updateCustomerFirstname(String firstname, int customerID) throws SQLException;
	
	void updateCustomerLastname(String lastname, int customerID) throws SQLException;
	
	void updateCustomerAddress(String address, int customerID) throws SQLException;
	
	void insertNewCustomer(Customer customer,Account account) throws SQLException;
	
	void deleteCustomer(Customer customer) throws SQLException;
}
