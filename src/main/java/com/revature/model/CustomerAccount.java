package com.revature.model;

public class CustomerAccount {
	
	private int accountNumber;	
	private int accountTypeID;
	private String accountTypeName;
	private double accountBalance;	
	private int status;
	private String statusName;
	
	public CustomerAccount() {
		super();
	}
	

	public CustomerAccount(int accountNumber, int accountTypeID, String accountTypeName, double accountBalance,
			int status, String statusName) {
		super();
		this.accountNumber = accountNumber;
		this.accountTypeID = accountTypeID;
		this.accountTypeName = accountTypeName;
		this.accountBalance = accountBalance;
		this.status = status;
		this.statusName = statusName;
	}
public CustomerAccount(int accountNumber, int accountTypeID, double accountBalance, int status) {
	super();
	this.accountNumber = accountNumber;
	this.accountTypeID = accountTypeID;
	this.accountBalance = accountBalance;
	this.status = status;
}



	public CustomerAccount(int accountNumber, String accountTypeName, double accountBalance, String statusName) {
		super();
		this.accountNumber = accountNumber;
		this.accountTypeName = accountTypeName;
		this.accountBalance = accountBalance;
		this.statusName = statusName;
	}

	// Getters and Setters
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountTypeID() {
		return accountTypeID;
	}

	public void setAccountTypeID(int accountTypeID) {
		this.accountTypeID = accountTypeID;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	// Hash Code and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + accountNumber;
		result = prime * result + accountTypeID;
		result = prime * result + ((accountTypeName == null) ? 0 : accountTypeName.hashCode());
		result = prime * result + status;
		result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerAccount other = (CustomerAccount) obj;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountTypeID != other.accountTypeID)
			return false;
		if (accountTypeName == null) {
			if (other.accountTypeName != null)
				return false;
		} else if (!accountTypeName.equals(other.accountTypeName))
			return false;
		if (status != other.status)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		return true;
	}

	// toString
	@Override
	public String toString() {
		return "CustomerAccount [accountNumber=" + accountNumber + ", accountTypeID=" + accountTypeID
				+ ", accountTypeName=" + accountTypeName + ", accountBalance=" + accountBalance + ", status=" + status
				+ ", statusName=" + statusName + "]";
	}					
			
}
