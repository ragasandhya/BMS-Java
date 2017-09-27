package com.capgemini.bmsapplication.dao;



public class QueryMapper {
	public static final String INSERT_QUERY="INSERT INTO account VALUES(?,?,?,SYSDATE,?,?)";
	public static final String VIEW_ACCOUNT_DETAILS_QUERY="SELECT customerId,acnumber,openingbalance,actype,acopendate,acstatus FROM account WHERE  acstatus=?";
	public static final String SUSPEND_ACCOUNT_DETAILS_QUERY="UPDATE account set acstatus='suspend' WHERE acnumber=?";
	public static final String SELECT_CUSTOMER_ID="select customerId from account where customerId=?";
}
