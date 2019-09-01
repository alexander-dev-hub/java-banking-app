package com.revature.bank.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public interface UserDAO 
{
	void addNewUser(String username, String password, String type) throws SQLException;
	
	void deleteUser(String username) throws SQLException;
	
	ResultSet getAllEmployees() throws SQLException;
	
	ResultSet getAllAdmins() throws SQLException;
	
	void updateUsername(String newUsername, String oldUsername) throws SQLException;
	
	void updatePassword(String newPassword, String username) throws SQLException;
}
