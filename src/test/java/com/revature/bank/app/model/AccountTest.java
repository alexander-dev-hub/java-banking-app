package com.revature.bank.app.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.revature.bank.app.models.Account;

/**
 * 
 * @author Berenice Garcia
 *
 */
public class AccountTest {

	private static Account account;
	
	@BeforeAll
	static void setUp() throws Exception 
	{
		account = new Account(1000, 12345);
	}
	
	@BeforeEach
	 void reset()
	 {
		account.setBalance(1000);
	 }

}