package com.capgemini.bmsapplication.dao;
import java.util.ArrayList;

import javax.security.auth.login.AccountException;

import com.capgemini.bmsapplication.bean.AccountBean;

public interface IAccountDAO {
	public String addAccountDetails(AccountBean account) throws AccountException;
	public ArrayList<AccountBean> viewAccountDetails(String acstatus) throws AccountException;
	public boolean SuspendAccountDetails(int acnumber)throws AccountException;
}
