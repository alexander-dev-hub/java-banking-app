package com.revature.bank.app.views;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class ViewFactory 
{
	public View determineNextView(String input)
	{
		View nextView = null;
		
		switch (input)
		{
		case "login" : 
			nextView = new LoginView();
			break;
			
		case "customer" : 
			nextView = new CustomerView();
			break;
			
		case "new account" :
			nextView = new NewAccountView();
			break;
			
		case "new employee" :
			nextView = new NewEmployeeView();
			break;
			
		case "employee" : 
			nextView = new EmployeeView();
			break;
			
		case "new admin" :
			nextView = new NewAdminView();
			break;
			
		case "admin" :
			nextView = new AdminView();
			break;
			
		case "customer info" :
			nextView = new CustomerInfo();
			break;
			
		case "applications" :
			nextView = new ApplicationListView();
			break;
			
		case "customer edit" :
			nextView = new CustomerEditView();
			break;
			
		case "customer delete" :
			nextView = new CustomerDeleteView();
			break;
			
		case "admin actions" :
			nextView = new AdminActionsView();
			break;
			
		default :
			System.out.println("View not found");
		}
		return nextView;
	}
}
