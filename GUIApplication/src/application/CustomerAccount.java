package application;


import java.util.ArrayList;

public class CustomerAccount 
{
	
	//CustomerAccount class reference variables
	private int accountNumber;
	private double accountBalance;
	private ArrayList<AccountTransaction> account;
	
	//Constructors
	public CustomerAccount() 
	{
		
		this.accountNumber = 0;
		this.accountBalance = 0.0;
		
	}
	
	public CustomerAccount(int accNumber, double accBalance) 
	{
	
		this.accountNumber = accNumber;
		this.accountBalance = accBalance;
		
	} 
	//Constructors end
	
	//Getter functions
	public int getAccNumber() 
	{
	
		return this.accountNumber;
	
	}
	
	public double getAccBalance() 
	{
		
		return this.accountBalance;
	
	}
	//Getter functions end
	
	//Setter functions
	public void setAccNummber(int accNumber) 
	{
		
		this.accountNumber = accNumber;
		
	}
	
	public void setAccBalance(double accBalance) 
	{
		
		this.accountBalance = accBalance;
		
	}
	//Setter functions end
	
	//Deposit into account method
	public void depositAccount(double deposit) 
	{
	
		this.accountBalance = this.accountBalance + deposit;
	
	}
	
	//Withdraw from account
	public void withdrawAccount(double withdraw) 
	{
		
		this.accountBalance = this.accountBalance - withdraw;
		
	}
	
	//Add transaction to ArrayList account method
	public void addTransaction(AccountTransaction aTransaction) 
	{
		
		AccountTransaction transaction = aTransaction;
		
		account.add(transaction);
		
	}
}
