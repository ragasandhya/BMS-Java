package com.capgemini.bmsapplication.service;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.bmsapplication.bean.AccountBean;
import com.capgemini.bmsapplication.dao.AccountDaoImpl;
import com.capgemini.bmsapplication.dao.IAccountDAO;
import com.capgemini.bmsapplication.exception.AccountException;



public class AccountServiceImpl implements IAccountService {
	IAccountDAO accountDao;



	public String addAccountDetails(AccountBean account) throws AccountException{
		
		accountDao=new AccountDaoImpl();	
		String accountSeq = null;
		try {
			accountSeq= accountDao.addAccountDetails(account);
		} 
		catch (javax.security.auth.login.AccountException e) 
		{

			
			e.printStackTrace();
		}
		return accountSeq; 
	}
	public ArrayList<AccountBean> viewAccountDetails(String acstatus) throws AccountException
	{
		accountDao=new AccountDaoImpl();
		ArrayList<AccountBean> bean = null;
		try {
			bean = accountDao.viewAccountDetails(acstatus);
		} catch (javax.security.auth.login.AccountException e) {
			e.printStackTrace();
		}

		return bean;
	}
	public boolean SuspendAccountDetails(int acnumber) throws AccountException
	{
		accountDao=new AccountDaoImpl();
		boolean bean = true;
		try
		{
			bean=accountDao.SuspendAccountDetails(acnumber);
		}
		catch (javax.security.auth.login.AccountException e)
		{
		
			e.printStackTrace();
		}
		return bean;
	}
	public void validateAccount(AccountBean bean) throws AccountException
	{
	
		List<String> validationErrors = new ArrayList<String>();


		if(!(validateCustomerId(bean.getCustomerId()))) {
			validationErrors.add("\n please give valid customerId \n");
		}
		//Validating acnumber name
		if(!(isValidAcnumber(bean.getAcnumber()))) {
			validationErrors.add("\n please add valid acnumber \n");
		}
		//Validating opening balance 
		if(!(isValidAmount(bean.getOpeningbalance()))){
			validationErrors.add("\n opening balance >0 \n" );
		}
		//Validating actype
		if(!(isValidActype(bean.getActype()))){
			validationErrors.add("\n no such actype \n");
		}
		//Validating acstatus
		if(!(isValidAcstatus(bean.getAcstatus()))){
			validationErrors.add("\n no such acstatus \n");
		}
		if(!validationErrors.isEmpty())
		{
			throw new AccountException(""+validationErrors);
		}
	}
	public boolean validateCustomerId(int customerId) {

		Pattern idPattern = Pattern.compile("[0-9]{1,4}");
		Matcher idMatcher = idPattern.matcher(customerId+"");

		if(idMatcher.matches())
			return true;
		else
			return false;		
	}

	public boolean isValidAcnumber(int acnumber){
		Pattern namePattern=Pattern.compile("^[0-9]{6,}$");
		Matcher nameMatcher=namePattern.matcher(acnumber+"");
		return nameMatcher.matches();
	}
	public boolean isValidAmount(int amount){
		return (amount>0);
	}
	public boolean isValidActype(String actype){
		
		Pattern phonePattern=Pattern.compile("^[A-Za-z]{3,}");
		Matcher phoneMatcher=phonePattern.matcher(actype);
		return phoneMatcher.matches();
	}

	public boolean isValidAcstatus(String acstatus)
	{
		Pattern phonePattern=Pattern.compile("^[A-Za-z]{3,}");
		Matcher phoneMatcher=phonePattern.matcher(acstatus);
		return phoneMatcher.matches();

	}

}





