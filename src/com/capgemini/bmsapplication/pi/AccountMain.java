package com.capgemini.bmsapplication.pi;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
//import java.util.logging.Logger;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.bmsapplication.service.IAccountService;
import com.capgemini.bmsapplication.bean.AccountBean;
import com.capgemini.bmsapplication.exception.AccountException;
import com.capgemini.bmsapplication.service.AccountServiceImpl;



public class AccountMain {
	static Scanner sc = new Scanner(System.in);
	static IAccountService accountService=null;
	static AccountServiceImpl accountServiceImpl=null;
	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) throws AccountException {
		PropertyConfigurator.configure("jdbc//log4j.properties");
		AccountBean accountBean = null;
		ArrayList<AccountBean> accountList=null;
		String account1=null;
		int option = 0;

		while (true)
		{
			System.out.println(" Account Details");
			System.out.println("1.Add Account ");
			System.out.println("2.Retrive All By Status");
			System.out.println("3.Update");
			System.out.println("4.Exit");
			System.out.println("Select an option:");
		try{
				option = sc.nextInt();

				switch (option) 
				{

				case 1:

					while (accountBean == null) 
					{
						accountBean = populateAccountBean();					
						
					}

					try 
					{
						accountService = new AccountServiceImpl();
						account1 = accountService.addAccountDetails(accountBean);

						System.out.println("Account details  has been successfully registered ");

					} catch (Exception accountException)
					{
						accountException.printStackTrace();
						System.out.println("Error " +accountException.getMessage());
					} 
					finally
					{
						account1=null;
						accountService = null;
						accountBean = null;
					}

					break;
				
		    case 2:

		    	String acstatus=null;
			try 
			{
				accountService=new AccountServiceImpl();
				System.out.println("Enter the acstatus");
				acstatus=sc.next();
				accountList=new ArrayList<AccountBean>();
				accountList=accountService.viewAccountDetails(acstatus);
				for(AccountBean a:accountList)
				{
				System.out.println(a.getCustomerId()+" "+a.getAcnumber()+" "+a.getOpeningbalance()+" "+a.getAcopendate()+" "+a.getActype());
				}
				
			}
			catch(AccountException accountException)
			{
				System.out.println("Error" +accountException.getMessage());
			}
			finally{
				acstatus=null;
				accountService=null;
				accountBean=null;
				
			}

			break;
		    case 3:
		    	int acnumber=0;
		    	try
		    	{
		    		accountService=new AccountServiceImpl();
					System.out.println("Enter the acnumber");
					acnumber=sc.nextInt();
					boolean accountBean1=accountService.SuspendAccountDetails(acnumber);
		    	}
		    	catch(AccountException accountException)
		    	{
		    		System.out.println("Error" +accountException.getMessage());
		    		
		    	}
		    	finally
		    	{
		    		acnumber=0;
		    		accountService=null;
					accountBean=null;
		    		
		    	}
		    	break;
		    case 4:
		    	System.out.print("Program Terminated");
				System.exit(0);
				break;
			default:
				System.out.println("Enter a valid option");
			}
		}
	
		catch(InputMismatchException e)
		{
			sc.nextLine();
			System.err.println("please enter the valid input");
		}
		
	}
}
	
	private static AccountBean populateAccountBean() 
	{

			AccountBean accountBean = new AccountBean();
			
			System.out.println("\n Enter Details");

			System.out.println("Customer Id: ");
			accountBean.setCustomerId(sc.nextInt());
			
			System.out.println("Enter Account number: ");
			accountBean.setAcnumber(sc.nextInt());
			
			System.out.println("Enter opening balance: ");
			accountBean.setOpeningbalance(sc.nextInt());
			
			System.out.println("Enter ac type ");
			accountBean.setActype(sc.next());
			
			System.out.println("Enter ac status ");
			accountBean.setAcstatus(sc.next());
			
			try 
			{
				accountServiceImpl=new AccountServiceImpl();
				accountServiceImpl.validateAccount(accountBean);
				
				return accountBean;
			} 
			catch (AccountException e)
			{
				//logger.error("exception occured", donorException);
				System.err.println("Invalid data:");
				System.err.println(e.getMessage() + " \n Try again..");
				System.exit(0);
			}
			return null;    
	}
}

