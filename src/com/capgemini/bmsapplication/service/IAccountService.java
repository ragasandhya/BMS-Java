package com.capgemini.bmsapplication.service;



import java.util.ArrayList;

import com.capgemini.bmsapplication.bean.AccountBean;
import com.capgemini.bmsapplication.exception.AccountException;

public interface IAccountService {

	public String addAccountDetails(AccountBean account) throws AccountException;
	public ArrayList<AccountBean> viewAccountDetails(String acstatus) throws AccountException;
	public boolean SuspendAccountDetails(int acnumber) throws AccountException;
}