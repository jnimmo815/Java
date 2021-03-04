package com.revature.model;



public class AccountApp {

	private int id;	
	private int AccountTypeID;
	private double startingBalance;
	private double salary;
	private int statusID;
	
	public AccountApp() {
		super();		
	}

	public AccountApp(int id, int accountTypeID, double startingBalance, double salary, int statusID) {
		super();
		this.id = id;
		AccountTypeID = accountTypeID;
		this.startingBalance = startingBalance;
		this.salary = salary;
		this.statusID = statusID;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountTypeID() {
		return AccountTypeID;
	}

	public void setAccountTypeID(int accountTypeID) {
		AccountTypeID = accountTypeID;
	}

	public double getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(double startingBalance) {
		this.startingBalance = startingBalance;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	// Hash Code and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + AccountTypeID;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(startingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + statusID;
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
		AccountApp other = (AccountApp) obj;
		if (AccountTypeID != other.AccountTypeID)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (Double.doubleToLongBits(startingBalance) != Double.doubleToLongBits(other.startingBalance))
			return false;
		if (statusID != other.statusID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountApp [id=" + id + ", AccountTypeID=" + AccountTypeID + ", startingBalance=" + startingBalance
				+ ", salary=" + salary + ", statusID=" + statusID + "]";
	}
		
	
}
