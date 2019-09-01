package com.revature.bank.app.models;

import java.util.ArrayList;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class AdminManager 
{
	private static ArrayList<Administrator> adminList = new ArrayList<Administrator>();

	public static ArrayList<Administrator> getAdminList() {
		return adminList;
	}

	public static void setAdminList(ArrayList<Administrator> adminList) 
	{
		AdminManager.adminList = adminList;
	}
	
	public void addNewAdministrator(Administrator admin)
	{
		adminList.add(admin);
	}
	
	public Administrator findAdmin(String username)
	{
		Administrator foundAdmin = null;
		
		for(Administrator admin : adminList)
		{
			if(admin.getUsername().equals(username))
			{
				foundAdmin = admin;
			}
		}
		
		return foundAdmin;
	}
	
}
