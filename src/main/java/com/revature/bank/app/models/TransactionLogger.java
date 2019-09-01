package com.revature.bank.app.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class TransactionLogger 
{
	static Logger transactionLogger = LogManager.getLogger(TransactionLogger.class);
	
	public void logWithdrawl(String username, double amount)
	{
		transactionLogger.info(username 
				+ " withdrew $" + String.valueOf(amount));
	}
	
	public void logDeposit(String username, double amount)
	{
		transactionLogger.info(username 
				+ " deposited $" + String.valueOf(amount));
	}
	
	public void logTransfer(String username1, String username2, double amount)
	{
		transactionLogger.info(username1 
				+ " transferred $" + String.valueOf(amount) + " to " + username2);
	}
}
