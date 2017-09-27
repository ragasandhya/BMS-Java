package com.capgemini.bmsapplication.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import javax.security.auth.login.AccountException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.bmsapplication.bean.AccountBean;
import com.capgemini.bmsapplication.util.DBConnection;


public class AccountDaoImpl implements IAccountDAO 
{
	Logger logger=Logger.getRootLogger();
	public AccountDaoImpl()
	{
	PropertyConfigurator.configure("jdbc//log4j.properties");
	
	}
	public String addAccountDetails(AccountBean account) throws AccountException
	{
		Connection connection = DBConnection.getInstance().getConnection();	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		
		String account1=null;
		int flag=0;
		int queryResult=0;
		try
		{	
			preparedStatement=connection.prepareStatement(QueryMapper.SELECT_CUSTOMER_ID);
			preparedStatement.setInt(1,account.getCustomerId());
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next())
			{
				flag=1;
			}
			if(flag==0)
			{
				throw new AccountException("customerId does not exist in the account table");
			}
			
			
			
			preparedStatement=connection.prepareStatement(QueryMapper.INSERT_QUERY);

			preparedStatement.setInt(1,account.getCustomerId());	
			preparedStatement.setInt(2,account.getAcnumber());
			preparedStatement.setInt(3,account.getOpeningbalance());
			//java.sql.Date d=AccountServiceImpl.getDate(account.getAcopendate());
			//preparedStatement.setDate(4,account.getAcopendate());
			preparedStatement.setString(4,account.getActype());
			preparedStatement.setString(5,account.getAcstatus());			
			
		
			
			
			queryResult=preparedStatement.executeUpdate();
			
		
			System.out.println(queryResult);
			if(queryResult==0)
			{
				logger.error("Insertion failed ");
				throw new AccountException("Inserting  details failed ");

			}
			else
			{
				logger.info("Account details added successfully:");
				return account1;
			}

		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			logger.error(sqlException.getMessage());
			throw new AccountException("Tehnical problem occured refer log");
		}

		finally
		{
			try 
			{
				//resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{
				logger.error(sqlException.getMessage());
				throw new AccountException("Error in closing db connection");

			}
		}
		
	}
	
	
	
	public ArrayList<AccountBean> viewAccountDetails(String acstatus) throws AccountException
	{
		
		Connection connection=DBConnection.getInstance().getConnection();
		
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		AccountBean bean=null;
		ArrayList<AccountBean> accountList=new ArrayList<AccountBean>();
		
		try
		{
			preparedStatement=connection.prepareStatement(QueryMapper.VIEW_ACCOUNT_DETAILS_QUERY);
			preparedStatement.setString(1,acstatus);
			resultset=preparedStatement.executeQuery();
			
			while(resultset.next())
			{
			
				bean = new AccountBean();
				bean.setCustomerId(resultset.getInt(1));
				bean.setAcnumber(resultset.getInt(2));
				bean.setOpeningbalance(resultset.getInt(3));
				bean.setActype(resultset.getString(4));
				bean.setAcopendate(resultset.getDate(5));
				bean.setAcstatus(resultset.getString(6));
				accountList.add(bean);
				
			}
			
			
			if( bean != null)
			{
				logger.info("Record Found Successfully");
				return accountList;
			}
			else
			{
				logger.info("Record Not Found Successfully");
				return null;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new AccountException(e.getMessage());
		}
		finally
		{
			try 
			{
				resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new AccountException("Error in closing db connection");

			}
		}
		
	}
	public boolean SuspendAccountDetails(int acnumber) throws AccountException
	{
		Connection connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement=null;		
		int resultSet = 0;
		
		try
		{
			preparedStatement=connection.prepareStatement(QueryMapper.SUSPEND_ACCOUNT_DETAILS_QUERY);	
			preparedStatement.setInt(1,acnumber);	
			resultSet=preparedStatement.executeUpdate();
			if(resultSet !=0)
			{
				System.out.println("Updated Successfully");
				return true;
			}
			else
			{
				System.out.println("Ac number doesnot exist");
				return false;
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			logger.error(e.getMessage());
			throw new AccountException(e.getMessage());
		}
		finally
		{
			try 
			{
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new AccountException("Error in closing db connection");

			}
		}
	}
}
			

