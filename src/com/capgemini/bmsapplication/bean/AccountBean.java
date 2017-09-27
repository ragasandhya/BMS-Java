package com.capgemini.bmsapplication.bean;

import java.util.Date;

public class AccountBean {
	private int customerId;
	private int acnumber;          
	private int openingbalance;
	private Date acopendate;
	private String actype;
	private String acstatus;
      
      
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(int acnumber) {
		this.acnumber = acnumber;
	}
	public int getOpeningbalance() {
		return openingbalance;
	}
	public void setOpeningbalance(int openingbalance) {
		this.openingbalance = openingbalance;
	}
	public Date getAcopendate() {
		return acopendate;
	}
	public void setAcopendate(Date acopendate) {
		this.acopendate = acopendate;
	}
	public String getActype() {
		return actype;
	}
	public void setActype(String actype) {
		this.actype = actype;
	}
	public String getAcstatus() {
		return acstatus;
	}
	public void setAcstatus(String acstatus) {
		this.acstatus = acstatus;
	}
	
	public AccountBean(int customerId, int acnumber, int openingbalance,
			Date acopendate, String actype, String acstatus) {
		
		this.customerId = customerId;
		this.acnumber = acnumber;
		this.openingbalance = openingbalance;
		this.acopendate = acopendate;
		this.actype = actype;
		this.acstatus = acstatus;
	}
	
	public AccountBean() {
	
	}
		
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Printing Account Details \n");
		sb.append("Customer Id: " +customerId +"\n");
		sb.append("Ac Number: "+ acnumber+"\n");
		sb.append("Opening Balance: "+ openingbalance +"\n");
		sb.append("Ac Open Date: "+ acopendate +"\n");
		sb.append("Ac Type: "+ actype +"\n");
		sb.append("Ac Status: "+ acstatus);
		return sb.toString();
	}
	
}  
