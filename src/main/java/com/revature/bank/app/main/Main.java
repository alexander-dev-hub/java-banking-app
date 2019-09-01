package com.revature.bank.app.main;

import com.revature.bank.app.models.JdbcConnector;
import com.revature.bank.app.views.ViewController;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class Main 
{

	public static void main(String[] args) 
	{	
		JdbcConnector jdbcConnector = new JdbcConnector();
		
		ViewController viewController = new ViewController();
		
		jdbcConnector.importData();
		
		viewController.start();
	}
}
