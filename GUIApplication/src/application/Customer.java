package application;
public class Customer 
{
	//Customer class reference variables
	private String ppsNumber;
	private String surname;
	private String firstName;
	private String customerDOB;
	private String customerID;
	private String customerPass;
	private CustomerAccount account;

	//Constructors
	public Customer() 
	{
		
		this.ppsNumber = "";
		this.surname = "";
		this.firstName = "";
		this.customerDOB = "";
		this.customerID = "";
		this.customerPass = "";

	}
	
	public Customer(String pps, String surname, String name, String DOB, String custID, String custPass)
	{
		
		this.ppsNumber = pps;
		this.surname = surname;
		this.firstName = name;
		this.customerDOB = DOB;
		this.customerID = custID;
		this.customerPass = custPass;

	}
	//Constructors end
	
	//Getter functions
	public String getPPS() 
	{
		
		return this.ppsNumber;
	
	}	
	
	public String getSurname() 
	{
		
		return this.surname;
		
	}
	
	public String getName() 
	{
		
		return this.firstName;
		
	}
	
	public String getDOB() 
	{
		
		return this.customerDOB;
		
	}
	
	public String getCustID()
	{
		
		return this.customerID;
		
	}
		
	public String getCustPass() 
	{
		
		return this.customerPass;
		
	}
	
	public CustomerAccount getAccount() 
	{
		
		return this.account;
	
	}
	
	//Setter functions start
	public void setPPS(String pps)
	{
		
		this.ppsNumber = pps;
		
	}
	
	public void setSurname(String surname) 
	{
		
		this.surname = surname;
		
	}
	
	public void setName(String name) 
	{
		
		this.firstName = name;
		
	}
	
	public void setDOB(String dob) 
	{
		
		this.customerDOB = dob;
		
	}
	
	public void setCustID(String id) 
	{
		
		this.customerID = id;
		
	}
	
	public void setCustPass(String password) 
	{
		
		this.customerPass = password;
		
	}
	
	//Method for creating a CustomerAccount for this Customer
	public void createCustomerAccount(CustomerAccount anAccount)
	{
		
		this.account = anAccount;
			
	}
	
	//Customer class toString function
	public String toString() 
	{
		
		return " PPS Number: "+this.ppsNumber+"\n Name: "+this.surname+","+this.firstName+"\n Customer DOB:"+this.customerDOB+"\n Customer ID:"+this.customerID+
				"\n Customer Password: "+this.customerPass+"\n Account Number: "+this.account.getAccNumber()+"\n Account Balance: €"+this.account.getAccBalance();
		
	}
		
}
