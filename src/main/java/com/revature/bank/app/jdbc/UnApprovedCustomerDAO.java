package com.revature.bank.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bank.app.models.AccountApplication;
import com.revature.bank.app.models.Customer;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public interface UnApprovedCustomerDAO 
{
	ResultSet getAllUnApprovedCustomers() throws SQLException;
	
	ResultSet getUnApprovedCustomers(int customerID) throws SQLException;
	
	void addNewUnApprovedCustomer(Customer customer,AccountApplication application) throws SQLException;
	
	void deleteUnApprovedCustomer(Customer customer) throws SQLException;
}
