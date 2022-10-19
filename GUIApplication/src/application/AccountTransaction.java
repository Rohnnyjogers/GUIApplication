package application;


import java.util.Date;

public class AccountTransaction 
{
	//AccountTransaction class reference variables
	private Date date;
	private String transactionType;
	private double transactionAmount;
	
	//Constructors
	public AccountTransaction() 
	{
		
		this.date = null;
		this.transactionType = "";
		this.transactionAmount = 0.0;
		
	}
	
	public AccountTransaction(Date aDate, String type, double amount) 
	{
		
		this.date = aDate;
		this.transactionType = type;
		this.transactionAmount = amount;
	
	}
	//Constructors end
	
	//AccountTransaction class toString
	public String toString() 
	{
		return " Date: " +this.date+ ", Transaction type: " +this.transactionType+ ", Amount: " +this.transactionAmount; 
	}
}
