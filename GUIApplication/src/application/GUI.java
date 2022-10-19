package application;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import application.Customer;
import application.CustomerAccount;
import application.GUI;
import application.GUI.Button;

public class GUI extends JFrame{

	private ArrayList<Customer> custList;
	private JButton create, clear, closeTab, next, update, apply;
	private JTextField PPS, surname, name, DOB, custID, custPass;
	//private JFormattedTextField DOB;
	private Customer customer, oldCustomer;
	private JTextArea viewArea;
	private boolean admin;

	public GUI() 
	{
		
		//Setting up JFrame
		super("Johnny's Bank");
		setLayout(new BorderLayout());
		setSize(700, 425);
		setLocation(400, 100);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initiating ArrayList custList
		custList = new ArrayList<Customer>();
		
		//Adding JPanel to the JFrame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		
		//Adding JTabbedPane to main JPanel	
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setBounds(75,75,200,200);
		add(tabPane, BorderLayout.CENTER);
		
		//creating JMenuBar and adding it to JFrame
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		
		//Creating JMenu File
		JMenu file = new JMenu("File");
				
		//JMenuItem New Customer for File menu
		JMenuItem newCustomer = new JMenuItem("New Customer");
		//Setting accelerator Ctrl+N to access New Customer menu item
		newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));		
		
		//Action Listener for new customer
		newCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Initiating a date for DOB
				Date date = null;
				
				//Creating newCust panel which will be added to the tabbed pane when new customer is clicked
				JPanel newCust = new JPanel();
				newCust.setLayout(new BorderLayout());
				
				//Creating a GridBag Panel to contain Labels and TextFields associated with New Customer
				JPanel gridBagPnl = new JPanel();
				gridBagPnl.setLayout(new GridBagLayout());
				gridBagPnl.setBorder(new TitledBorder("Customer Details"));
				GridBagConstraints gc1 = new GridBagConstraints();
				gc1.insets = new Insets(15, 15, 15, 15);
				
				//First row
				JLabel newInfo = new JLabel("Input the following details necessary to create a new customer.");
				gc1.gridx = 0;
				gc1.gridy = 1;
				gridBagPnl.add(newInfo, gc1);				
				
				// Second row
				JLabel pps = new JLabel("PPS number: ");
				gc1.gridx = 0;
				gc1.gridy = 3;
				gc1.fill = GridBagConstraints.BOTH;
				gc1.gridwidth = 1;
				gc1.gridheight = 1;
				gridBagPnl.add(pps, gc1);
				
				PPS = new JTextField(25);
				gc1.gridx = 1;
				gc1.gridy = 3;
				gc1.gridwidth = 3;
				gc1.fill = GridBagConstraints.BOTH;
				gridBagPnl.add(PPS, gc1);
				
				//Third row
				JLabel surName = new JLabel("Surname: ");
				gc1.gridx = 0;
				gc1.gridy = 4;
				gc1.fill = GridBagConstraints.BOTH;
				gc1.gridwidth = 1;
				gc1.gridheight = 1;
				gridBagPnl.add(surName, gc1);
				
				surname = new JTextField();
				gc1.gridx = 1;
				gc1.gridy = 4;
				gc1.gridwidth = 3;
				gc1.fill = GridBagConstraints.BOTH;
				gridBagPnl.add(surname, gc1);
				
				//Fourth row
				JLabel nameLbl = new JLabel("First name:");
				gc1.gridx = 0;
				gc1.gridy = 5;
				gc1.fill = GridBagConstraints.BOTH;
				gc1.gridwidth = 1;
				gc1.gridheight = 1;
				gridBagPnl.add(nameLbl, gc1);
				
				name = new JTextField();
				gc1.gridx = 1;
				gc1.gridy = 5;
				gc1.gridwidth = 3;
				gridBagPnl.add(name, gc1);
				
				//Fifth row
				JLabel dob = new JLabel("Date of birth: ");
				gc1.gridx = 0;
				gc1.gridy = 6;
				gc1.fill = GridBagConstraints.BOTH;
				gc1.gridwidth = 1;
				gc1.gridheight = 1;
				gridBagPnl.add(dob, gc1);
				
				//DOB = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
				DOB = new JTextField();
				gc1.gridx = 1;
				gc1.gridy = 6;
				gc1.gridwidth = 3;
				gridBagPnl.add(DOB, gc1);
				//GridBag end
				
				//Adding GridBag panel to centre of newCust panel
				newCust.add(gridBagPnl, BorderLayout.CENTER);
				
				//Creating button panel for newCust
				JPanel newBtnPanel = new JPanel();
				
				//Adding 'Create' button and adding an action listener to it
				create = new JButton("Create");
				newBtnPanel.add(create);
				create.addActionListener(new Button());
				
				//Creating 'Clear' button and adding an action listener to it
				clear = new JButton("Clear");
				clear.addActionListener(new Button());
				newBtnPanel.add(clear);
				
				//Adding button panel in the south of newCust panel
				newCust.add(newBtnPanel, BorderLayout.SOUTH);
				
				//Adding newCust to tabbed pane
				tabPane.add("New Customer", newCust);	
				
			}
									
		});
		
		
		//JMenuItem Modify Customer for File menu
		JMenuItem modCustomer = new JMenuItem("Modify Customer");
		
		//Action listener for modify customer
		modCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//LOGIN AND SEARCH SECTION: This section asks the user to login and input Account number to perform Modify Customer
				
				//Login window components
				JLabel modInfo = new JLabel("Sign in as a Customer or Administrator to continue");
				JLabel modIDLbl = new JLabel("Username: ");
				JTextField modIDTF = new JTextField();
				JLabel modPassLbl = new JLabel("Password: ");
				JTextField modPassTF = new JTextField();
				int optionPaneHolder = 0;
				
				//Error dialog if custList is empty
				if(custList.isEmpty())
				{
					
					JLabel error = new JLabel("Error: The customer list must contain customers.");
					
					JOptionPane.showMessageDialog(null, new Object[] {error}, "Error Message", JOptionPane.ERROR_MESSAGE);
				
				}
				else
				{
					//If Administrator is not logged in then display login dialog
					if(!admin)
					{
						int login =  JOptionPane.showOptionDialog(null, new Object[] {modInfo, modIDLbl, modIDTF, modPassLbl, modPassTF}, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
						optionPaneHolder = login;
					}
					
					
					//Ok option for login dialog
					if(optionPaneHolder == JOptionPane.OK_OPTION || admin)
					{
						//custList iterator
						for(Customer c: custList)
						{
							//If ID and password TextFields match Customer's ID and password or if ID and password TextFields match 'Admin' and 'password' 
							if(c.getCustID().equals(modIDTF.getText()) && c.getCustPass().equals(modPassTF.getText()) || modIDTF.getText().equals("Admin") && modPassTF.getText().equals("password") || admin)
							{
								//Updates admin boolean if Administrator logs in
								if(modIDTF.getText().equals("Admin") && modPassTF.getText().equals("password"))
								{
									admin = true;
								}
								
								//Dialog asking for account number
								JLabel searchLbl = new JLabel("Input the Account number of the customer details to be modified.");
								JTextField searchTF = new JTextField();
								
								int searchD = JOptionPane.showOptionDialog(null, new Object[] {searchLbl, searchTF}, "Customer Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
								
								//Ok option for search dialog
								if(searchD == JOptionPane.OK_OPTION)
								{
									//Converting search TextField value to integer to compare with List's index
									int accNum = Integer.parseInt(searchTF.getText());
									
									//custList iterator
									//LOGIN AND SEARCH SECTION: End
									for(Customer c1 : custList)
									{
										//If search TextField's integer value is the same as the Customer's list index number
										if(c1.getAccount().getAccNumber() == accNum ) 
										{	
											//Assigning c to a global Customer so the Button action listener can see it
											oldCustomer = c1;
											
											//Found customer message dialog
											JLabel found = new JLabel("Customer found!");
											JOptionPane.showMessageDialog(null, new Object[] {found}, "Customer Search", JOptionPane.INFORMATION_MESSAGE);
											
											//Creating modCust panel with border layout that will be added to the tabbed pane once Modify Customer is clicked
											JPanel modCust = new JPanel();
											modCust.setLayout(new BorderLayout());
											
											//Adding a GribBag panel to hold Label and TextFields associated with Modify Customer
											JPanel gridBagPnl = new JPanel();
											gridBagPnl.setLayout(new GridBagLayout());
											gridBagPnl.setBorder(new TitledBorder("Modify Customer Details"));
											GridBagConstraints gc1 = new GridBagConstraints();
											gc1.insets = new Insets(10, 10, 10, 10);
											
											//First row
											JLabel modInfo1 = new JLabel("Modify the customer's details below.");
											gc1.gridx = 0;
											gc1.gridy = 1;
											gridBagPnl.add(modInfo1, gc1);				
											
											//Second row
											JLabel pps = new JLabel("Customer PPS: ");
											gc1.gridx = 0;
											gc1.gridy = 3;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(pps, gc1);
											
											PPS = new JTextField(25);
											PPS.setText(c.getPPS());
											gc1.gridx= 1;
											gc1.gridy = 3;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(PPS, gc1);
											
											//Third row
											JLabel surnameLbl = new JLabel("Surname: ");
											gc1.gridx = 0;
											gc1.gridy = 4;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(surnameLbl, gc1);
											
											surname = new JTextField();
											surname.setText(c.getSurname());
											gc1.gridx= 1;
											gc1.gridy = 4;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(surname, gc1);
											
											//Fourth row
											JLabel nameLbl = new JLabel("Name: ");
											gc1.gridx = 0;
											gc1.gridy = 5;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(nameLbl, gc1);
											
											surname = new JTextField();
											surname.setText(c.getName());
											gc1.gridx= 1;
											gc1.gridy = 5;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(surname, gc1);
											
											//Fifth row
											JLabel dobLbl = new JLabel("Date of Birth: ");
											gc1.gridx = 0;
											gc1.gridy = 6;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(dobLbl, gc1);
											
											
											//DOB = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
											DOB = new JTextField();
											DOB.setText(c.getDOB());
											gc1.gridx= 1;
											gc1.gridy = 6;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(DOB, gc1);
											
											//Sixth row
											JLabel custIDLbl = new JLabel("Customer ID ");
											gc1.gridx = 0;
											gc1.gridy = 7;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(custIDLbl, gc1);
																		
											custID = new JTextField();
											custID.setText(c.getCustID());
											gc1.gridx= 1;
											gc1.gridy = 7;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(custID, gc1);
											
											//Seventh row
											JLabel custPassLbl = new JLabel("Customer password: ");
											gc1.gridx = 0;
											gc1.gridy = 8;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(custPassLbl, gc1);
											
											custPass = new JTextField();
											custPass.setText(c.getCustPass());
											gc1.gridx= 1;
											gc1.gridy = 8;
											gc1.fill = GridBagConstraints.BOTH;
											gridBagPnl.add(custPass, gc1);
											//GridBag end
											
											//Adding GridBag panel to the centre of the modCust panel
											modCust.add(gridBagPnl, BorderLayout.CENTER);
											
											//Creating a button panel and adding it to the south of the modCust panel
											JPanel newBtnPanel = new JPanel();
											
											//Update button with action listener
											update = new JButton("Update");
											update.addActionListener(new Button());
											newBtnPanel.add(update);
											
											//Adding clear button and adding action listener
											clear = new JButton("Clear");
											clear.addActionListener(new Button());
											newBtnPanel.add(clear);
											
											//Adding button panel in the south of the modCust panel 
											modCust.add(newBtnPanel, BorderLayout.SOUTH);
											
											//Adding modCust pane to the tabbed pane
											tabPane.add("Modify Customer", modCust);
										}			
										//Message dialog which displays if account number cannot be found
										else
										{
											
											JLabel searchFail = new JLabel("Error: Account number "+searchTF.getText()+" does not exist in the list. Please revise your search and try again.");
											
											JOptionPane.showMessageDialog(null, new Object[] {searchFail}, "Error Message", JOptionPane.ERROR_MESSAGE);
																					
										}																
									}
								}
								else if(searchD == JOptionPane.CANCEL_OPTION)
								{
															
								}
							}
							//Message dialog which displays if login is unsuccessful
							else
							{
								JLabel loginFail = new JLabel("Your login attempt was unsuccessful. Please revise your username and password and try again.");
								
								JOptionPane.showMessageDialog(null, new Object[] {loginFail}, "Login Failure", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(optionPaneHolder == JOptionPane.CANCEL_OPTION)
					{
						
					}
				}	
			}	
});
		
		
		//JMenuItem Delete Customer for File menu
		JMenuItem delCustomer = new JMenuItem("Delete Customer");
		
		//action listener for Delete Customer
		delCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//LOGIN AND SEARCH SECTION: This section asks the user to login and input Account number to perform Modify Customer
				
				//Login window components
				JLabel delInfo = new JLabel("You must be signed in as Administrator to delete a customer. Please sign in as Administrator to continue.");
				JLabel delIDLbl = new JLabel("Admin Username: ");
				JTextField delIDTF = new JTextField();
				JLabel delPassLbl = new JLabel("Admin Password: ");
				JTextField delPassTF = new JTextField();
				int optionPaneHolder = 0;
				
				//Error dialog if custList is empty
				if(custList.isEmpty())
				{
					
					JLabel error = new JLabel("Error: The customer list must contain customers.");
					
					JOptionPane.showMessageDialog(null, new Object[] {error}, "Error Message", JOptionPane.ERROR_MESSAGE);
				
				}
				else
				{
					//If Administrator is not logged in then display login dialog
					if(!admin)
					{
						int login =  JOptionPane.showOptionDialog(null, new Object[] {delInfo, delIDLbl, delIDTF, delPassLbl, delPassTF}, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						optionPaneHolder = login;
					}
					
					//Ok option for login dialog or if Administrator is logged in
					if(optionPaneHolder == JOptionPane.OK_OPTION || admin)
					{
						//custList iterator 
						for(Customer c: custList)
						{
							
							//If ID and password TextFields match Customer's ID and password or if ID and password TextFields match 'Admin' and 'password' 
							if(c.getCustID().equals(delIDTF.getText()) && c.getCustPass().equals(delPassTF.getText()) || delIDTF.getText().equals("Admin") && delPassTF.getText().equals("password") || admin)
							{
								
								//Updates admin boolean if Administrator logs in
								if(delIDTF.getText().equals("Admin") && delPassTF.getText().equals("password"))
								{
									admin = true;
								}
								
								//Dialog asking for account number
								JLabel searchLbl = new JLabel("Input the Account number of the customer to be deleted.");
								JTextField searchTF = new JTextField();
								
								int searchD = JOptionPane.showOptionDialog(null, new Object[] {searchLbl, searchTF}, "Customer Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
								
								//Ok option for search dialog
								if(searchD == JOptionPane.OK_OPTION)
								{
									//Converting search TextField value to integer to compare with List's index
									int accNum = Integer.parseInt(searchTF.getText());
									
									//custList iterator
									for(Customer c1: custList)
									{
										
										//If search TextField's integer value is the same as the Customer's list index number
										//LOGIN AND SEARCH SECTION: End
										if(c1.getAccount().getAccNumber() == accNum) 
										{
											//Creating delete panel which will be added to tabbe pane once Delete Customer is clicked
											JPanel delete = new JPanel();
											
											//Attempt at Delete Customer code missing for reason given in email
											
											//Adding delete panel to tabbed pane
											tabPane.add("Delete Customer", delete);
										}
										//Message dialog which displays if account number search fails
										else
										{
											
											JLabel searchFail = new JLabel("Error: Account number "+searchTF.getText()+" does not exist in the list. Please revise your search and try again.");
											
											JOptionPane.showMessageDialog(null, new Object[] {searchFail}, "Error Message", JOptionPane.ERROR_MESSAGE);
																			
										}	
											
									}
								}
								else if(searchD == JOptionPane.CANCEL_OPTION)
								{
									
								}
								
							}
							//Message dialog displays if login fails
							else
							{
								
								JLabel loginFail = new JLabel("Your login attempt was unsuccessful. Please revise your username and password and try again.");
							
								JOptionPane.showMessageDialog(null, new Object[] {loginFail}, "Login Failure", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(optionPaneHolder == JOptionPane.CANCEL_OPTION)
					{	
						
					}					
				}				
			}			
		});
		
		
		//JMenuItem Apply Deposit for File menu
		JMenuItem transaction = new JMenuItem("Apply Transaction");
		
		//Action listener for transaction menu item 
		transaction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				//LOGIN AND SEARCH SECTION: This section asks the user to login and input Account number to perform Modify Customer
				
				//Login window components
				JLabel transInfo = new JLabel("Sign in as Customer or Administrator to continue.");
				JLabel transIDLbl = new JLabel("Username: ");
				JTextField transIDTF = new JTextField();
				JLabel transPassLbl = new JLabel("Password: ");
				JTextField transPassTF = new JTextField();
				int optionPaneHolder = 0;
				
				//Error dialog if custList is empty
				if(custList.isEmpty())
				{
					
					JLabel error = new JLabel("Error: The customer list must contain customers.");
					
					JOptionPane.showMessageDialog(null, new Object[] {error}, "Error Message", JOptionPane.ERROR_MESSAGE);
				
				}
				else
				{
					
					//If Administrator is not logged in then display login dialog
					if(!admin)
					{
						int login =  JOptionPane.showOptionDialog(null, new Object[] {transInfo, transIDLbl, transIDTF, transPassLbl, transPassTF}, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
						optionPaneHolder = login;
					}
					
					//Ok option for login dialog or if Administrator is logged in
					if(optionPaneHolder == JOptionPane.OK_OPTION || admin)
					{
						//custList Iterator
						for(Customer c: custList)
						{
							
							//If ID and password TextFields match Customer's ID and password or if ID and password TextFields match 'Admin' and 'password'
							if(c.getCustID().equals(transIDTF.getText()) && c.getCustPass().equals(transPassTF.getText()) || transIDTF.getText().equals("Admin") && transPassTF.getText().equals("password") || admin)
							{
								
								//Updates admin boolean if Administrator logs in
								if(transIDTF.getText().equals("Admin") && transPassTF.getText().equals("password"))
								{
									admin = true;
								}
								
								//Dialog asking for account number
								JLabel searchLbl = new JLabel("Input the Account number of the customer to apply a transaction.");
								JTextField searchTF = new JTextField();
								
								int searchD = JOptionPane.showOptionDialog(null, new Object[] {searchLbl, searchTF}, "Customer Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
							
								//Ok option for search dialog
								if(searchD == JOptionPane.OK_OPTION)
								{	
								
									//Converting search TextField value to integer to compare with List's index
									int accNum = Integer.parseInt(searchTF.getText());
									
									//custList iterator
									for(Customer c1: custList)
									{
										
										//If search TextField's integer value is the same as the Customer's list index number
										//LOGIN AND SEARCH SECTION: End
										if(c1.getAccount().getAccNumber() == accNum) 
										{
											//Creating transaction panel with border layout that is added to the tabbed pane once Apply Transaction is clicked
											JPanel transaction = new JPanel();
											transaction.setLayout(new BorderLayout());
											
											//Adding transPanel to centre of transaction panel 
											JPanel transPanel = new JPanel();		
											transaction.add(transPanel, BorderLayout.CENTER);
											
											//Creating button panel for transaction panel
											JPanel btnPanel = new JPanel();
											
											//Creating Apply button and adding action listener
											apply = new JButton("Apply");
											apply.addActionListener(new Button());
											btnPanel.add(apply);
											
											//Adiing btnPanel in the south of transaction panel			
											transaction.add(btnPanel, BorderLayout.SOUTH);
											
											//Adding transaction panel to tabbed pane
											tabPane.add("Transaction", transaction);
										}
										
										//Message dialog displays if account search fails
										else
										{
											
											JLabel searchFail = new JLabel("Error: Account number "+searchTF.getText()+" does not exist in the list. Please revise your search and try again.");
											
											JOptionPane.showMessageDialog(null, new Object[] {searchFail}, "Error Message", JOptionPane.ERROR_MESSAGE);
																			
										}	
											
									}
								}
								else if(searchD ==  JOptionPane.CANCEL_OPTION)
								{
									
								}
								
							}
							//Message dialog displays if login fails
							else
							{
								
								JLabel loginFail = new JLabel("Your login attempt was unsuccessful. Please revise your username and password and try again.");
							
								JOptionPane.showMessageDialog(null, new Object[] {loginFail}, "Login Failure", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(optionPaneHolder == JOptionPane.CANCEL_OPTION)
					{
						
					}      
				}
			}
			
		});
		
				
		//Adding menu item New Customer, Modify Customer, Delete Customer and Transaction to File menu
		file.add(newCustomer);
		file.addSeparator();
		file.add(modCustomer);
		file.add(delCustomer);
		file.addSeparator();
		file.add(transaction);
		
		//Adding File menu to menu bar
		bar.add(file);
		
		
		//Creating JMenu View
		JMenu view = new JMenu("View");
		
		//JMenuItem Customer List for View menu
		JMenuItem customerList = new JMenuItem("Customer List");
		//Setting accelerator Ctrl+L to access Customer List menu item  
		customerList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
	
		//Adding action listener to viewCustomer menu item
		customerList.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//This method use a JSplitPane layout to display customer details and list navigation buttons 
				//Creating JPanels to add to JSplitPane which will display once Customer List menu item is clicked	
				JPanel infoPanel = new JPanel();
				JPanel viewBtnPanel = new JPanel();
				
				//Adding JTextArea which will hold customer.toString in infoPanel
				viewArea = new JTextArea(15, 40);
				viewArea.setEditable(false);
				infoPanel.add(viewArea);
				
				//if custList is not empty conditional
				if(!custList.isEmpty())
				{

					Customer customer;
					
					//Get customer at index 0 and display to string in text area
					customer = custList.get(0);
					viewArea.append(customer.toString());
					
					
				}
					
				else
				{
					//If list is empty then display such in text area
					viewArea.append("List is empty");
					
				}
				
				//Setting GridBag layout to hold label and buttons on button panel 
				viewBtnPanel.setLayout(new GridBagLayout());
				GridBagConstraints gridC = new GridBagConstraints();
				gridC.insets = new Insets(5, 5, 5, 5);
				
				//First row
				JLabel viewInfo = new JLabel("Navigate through the bank's list of customer using these buttons:");
				gridC.gridx = 0;
				gridC.gridy = 0;
				gridC.fill = GridBagConstraints.BOTH;
				gridC.gridwidth = 1;
				gridC.gridheight = 1;
				viewBtnPanel.add(viewInfo, gridC);
				
				//Second row
				JButton next = new JButton("Next");
				gridC.gridx = 0;
				gridC.gridy = 3;
				gridC.fill = GridBagConstraints.BOTH;
				gridC.gridwidth = 1;
				gridC.gridheight = 1;
				next.addActionListener(new Button());
				viewBtnPanel.add(next, gridC);
				
				//Third row
				JButton previous = new JButton("Previous");
				gridC.gridx = 0;
				gridC.gridy = 4;
				gridC.fill = GridBagConstraints.BOTH;
				gridC.gridwidth = 1;
				gridC.gridheight = 1;
				previous.addActionListener(new Button());
				viewBtnPanel.add(previous, gridC);
				
				//Fourth row
				JButton first = new JButton("First");
				gridC.gridx = 0;
				gridC.gridy = 5;
				gridC.fill = GridBagConstraints.BOTH;
				gridC.gridwidth = 1;
				gridC.gridheight = 1;
				first.addActionListener(new Button());
				viewBtnPanel.add(first, gridC);
				
				//Fifth row
				JButton last = new JButton("Last");
				gridC.gridx = 0;
				gridC.gridy = 6;
				gridC.fill = GridBagConstraints.BOTH;
				gridC.gridwidth = 1;
				gridC.gridheight = 1;
				last.addActionListener(new Button());
				viewBtnPanel.add(last, gridC);
						
				
				//Initiating JSplitPane for Customer List menu item
				JSplitPane viewPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel, viewBtnPanel);
				viewPane.setDividerLocation(450);
				
				//Adding viewPane to tabbed pane
				tabPane.add("View Customer" , viewPane);
			}
			
		});
	
		
		//JMenuItem Statements for View menu
		JMenuItem statements = new JMenuItem("Statements");
		
		//Action listener for Statements menu item
		statements.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				//LOGIN AND SEARCH SECTION: This section asks the user to login and input Account number to perform Modify Customer
				
				//Login window components
				JLabel statInfo = new JLabel("Sign in as Administrator to continue.");
				JLabel statIDLbl = new JLabel("Username: ");
				JTextField statIDTF = new JTextField();
				JLabel statPassLbl = new JLabel("Password: ");
				JTextField statPassTF = new JTextField();
				int optionPaneHolder = 0;
				
				//Error dialog if custList is empty
				if(custList.isEmpty())
				{
					
					JLabel error = new JLabel("Error: The customer list must contain customers.");
					
					JOptionPane.showMessageDialog(null, new Object[] {error}, "Error Message", JOptionPane.ERROR_MESSAGE);
				
				}
				else
				{
					//If Administrator is not logged in then display login dialog
					if(!admin)
					{
						int login =  JOptionPane.showOptionDialog(null, new Object[] {statInfo, statIDLbl, statIDTF, statPassLbl, statPassTF}, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
						optionPaneHolder = login;
					}
					
					//Ok option for login dialog or if Administrator is logged in
					if(optionPaneHolder == JOptionPane.OK_OPTION || admin)
					{
						//custList iterator
						for(Customer c: custList)
						{
							//If ID and password TextFields match Customer's ID and password or if ID and password TextFields match 'Admin' and 'password'
							if(c.getCustID().equals(statIDTF.getText()) && c.getCustPass().equals(statPassTF.getText()) || statIDTF.getText().equals("Admin") && statPassTF.getText().equals("password") || admin)
							{
								
								//Updates admin boolean if Administrator logs in
								if(statIDTF.getText().equals("Admin") && statPassTF.getText().equals("password"))
								{
									admin = true;
								}
								
								//Dialog asking for account number
								JLabel searchLbl = new JLabel("Input the Account number of the customer to view Statment.");
								JTextField searchTF = new JTextField();
								
								int searchD = JOptionPane.showOptionDialog(null, new Object[] {searchLbl, searchTF}, "Customer Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
							
								//Ok option for search dialog
								if(searchD == JOptionPane.OK_OPTION)
								{	
								
									//Converting search TextField value to integer to compare with List's index
									int accNum = Integer.parseInt(searchTF.getText());
									
									//custList iterator
									for(Customer c1: custList)
									{
										
										//If search TextField's integer value is the same as the Customer's list index number
										//LOGIN AND SEARCH SECTION: End
										if(c1.getAccount().getAccNumber() == accNum) 
										{
											//Creating Statement panel which will be added to tabbed pane once Statement menu item is clicked
											JPanel statement = new JPanel();
											
											//Adding 
											tabPane.add("Statement", statement);
										}
										//Message dialog which displays if account number search fails
										else
										{
											
											JLabel searchFail = new JLabel("Error: Account number "+searchTF.getText()+" does not exist in the list. Please revise your search and try again.");
											
											JOptionPane.showMessageDialog(null, new Object[] {searchFail}, "Error Message", JOptionPane.ERROR_MESSAGE);
																			
										}	
											
									}
								}
								else if(searchD ==  JOptionPane.CANCEL_OPTION)
								{
									
								}
								
							}
							//Message dialog that displays if login fails
							else
							{
								
								JLabel loginFail = new JLabel("Your login attempt was unsuccessful. Please revise your username and password and try again.");
							
								JOptionPane.showMessageDialog(null, new Object[] {loginFail}, "Login Failure", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(optionPaneHolder == JOptionPane.CANCEL_OPTION)
					{
						
					}
				}				
			}
			
		});
		
		//Adding menu items Customer List and Statements to the View menu 
		view.add(customerList);
		view.add(statements);
		
		//Adding View menu to menu bar
		bar.add(view);
		
		
		//Creating Run As menu
		JMenu runAs = new JMenu("Run As...");
		
		//JMenuItem Administrator for Run As menu
		JMenuItem administrator = new JMenuItem("Administrator");		
		//Setting accelerator Ctrl+A to access Administrator menu item
		administrator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		
		//Action listener for Administrator.
		administrator.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Declaring components for Administrator-related dialogs				
				JLabel adminInfo = new JLabel("Log in as Administrator.");
				JLabel newAdminLogin = new JLabel("You are now logged in as Administrator.");
				JLabel adminLogdAtm = new JLabel("Administrator is already logged in.");
				JLabel adminLbl = new JLabel("Admin username:");
				JTextField adminTF = new JTextField("Admin");
				JLabel adminPassLbl = new JLabel("Admin password: ");
				JPasswordField adminPF = new JPasswordField();
				
				//If Administrator is already login then display message dialog
				if(admin)
				{
					JOptionPane.showMessageDialog(null, new Object[] {adminLogdAtm}, "Administrator Login", JOptionPane.INFORMATION_MESSAGE);
				}
				//If Administrator is not logged in
				else if(!admin)
				{
					//Option dialog asking for admin username and password
					int login =  JOptionPane.showOptionDialog(null, new Object[] {adminInfo, adminLbl, adminTF, adminPassLbl, adminPF}, "Administrator Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
					
					//Ok option for admin login dialog
					if(login == JOptionPane.OK_OPTION)
					{
						//If TextField and PassField match 'admin' and 'password' 
						if(adminTF.getText().equals("Admin") && adminPF.getText().equals("password"))
						{
							//Set admin boolean to true and display message dialog
							admin = true;
							JOptionPane.showMessageDialog(null, new Object[] {newAdminLogin}, "Administrator Login", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(login == JOptionPane.CANCEL_OPTION)
					{
						
					}
				}
				
			}
			
		});
		
		//Adding menu item Administrator to Run As menu
		runAs.add(administrator);
		
		//Adding Run As menu to menu bar
		bar.add(runAs);
		
		//Setting the visibility of the JFrame to true
		setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	//ActionListener Button controlling actions when any button is pressed
	public class Button implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//If Create button pressed (from New Customer)
			if(e.getSource() == create)
			{
				//Initiate a Customer 
				CustomerAccount account;
			
				//Dialog components to display customer details input and ask if they want to be created and saved to list
				JLabel label = new JLabel("New customer details:");
				JTextArea TF = new JTextArea(5,4);
				JLabel confirm = new JLabel("Are you sure you'd like to create this customer?");
				String details = PPS.getText() +"\n"+ surname.getText() +"\n"+ name.getText() +"\n"+ DOB.getText();
				int accountNum;
				
				TF.append(details);
				
				int createD = JOptionPane.showOptionDialog(null, new Object[] {label, TF, confirm}, "Confirm customer creation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				//If ok option for createD dialog
				if(createD == JOptionPane.OK_OPTION)
				{
					//Creating string and getting their values from New Customer TextFields
					String pps = PPS.getText(), lastName = surname.getText(), firstName = name.getText(), dob = DOB.getText();	
					//Generating a customer ID by call ID generator function
					String custID = customerIDGenerator();
					//Generating a password by calling password generator function
					String custPass = passwordGenerator();
					
					//Components for message dialog that displays once ok is pressed in createD dialog
					JLabel success = new JLabel("New Customer successfully created!");
					JLabel IDLbl = new JLabel("Customer ID: ");
					JTextField IDTF = new JTextField(custID);
					JLabel passLbl = new JLabel("Customer password: ");
					JTextField passTF = new JTextField(custPass);
					JLabel infoLbl = new JLabel("Full customer details are available through the View menu."); 
					
					//Creating a customer with details input
					customer = new Customer(pps, lastName, firstName, dob, custID, custPass);
					//Adding customer to custList
					custList.add(customer);
					
					//Assign account number to index+1
					accountNum = custList.indexOf(customer) + 1;
					//Creating 0.0 balance for customer's account
					account = new CustomerAccount(accountNum, 0.0);
					//Create customer account from customer class
					customer.createCustomerAccount(account);
					
					//Success message dialog
					JOptionPane.showMessageDialog(null, new Object[] {success, IDLbl, IDTF, passLbl, passTF, infoLbl});
				}
				else if(createD == JOptionPane.CANCEL_OPTION)
				{
					
				}
			}
			
			//If clear button is pressed
			else if(e.getSource() == clear)
			
			{
				//Set following TextFields to null (not finished)
				PPS.setText(null);
				surname.setText(null);
				name.setText(null);
				DOB.setText(null);
			}
			
			//If next is pressed (from New Customer)
			else if(e.getSource() == next)
			{
				//initiating a null customer object
				Customer customer = null;
				
				//iterating throough custList
				for(int i = 0; i <= custList.size(); i++)
				{	
					//Assigning customer to index value i
					customer = custList.get(i);
					
					//Setting text area to null
					viewArea.setText(null);
					
					//Appending text area with customer to string
					viewArea.append("Customer Details: \n" + customer.toString());
					
				}
							
			}
			//if update button pressed (from Modify Customer)
			else if(e.getSource() == update)
			{
			
				//Initiating String variable to hold customer details
				String pps, surName, fName, dob, customerID, customerPass;
				
				//Getting customer details from appropriate TextFields
				pps = PPS.getText();
				surName = surname.getText();//Error here, incorrect value in dialog
				fName = name.getText();
				dob = DOB.getText();
				customerID = custID.getText();
				customerPass = custPass.getText();
				//Components for update customer details dialog
				String details = pps+"\n"+surName+"\n"+fName+"\n"+dob+"\n"+customerID+"\n"+customerPass;
				JLabel updateLbl = new JLabel("Are you sure you want to update customer details with the following: ");
				JTextArea updateTF = new JTextArea(5,4);
				
				updateTF.append(details);
				
				//Update customer details dialog
				int updateD =  JOptionPane.showOptionDialog(null, new Object[] {updateLbl, updateTF}, "Customer Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				//If ok option for update dialog
				if(updateD == JOptionPane.OK_OPTION)
				{
					//Label for success message dialog
					JLabel updateLbl1 = new JLabel("Cutomer destails successfully updated!");
					
					//Updating customer details with ones input by user in Modify Customer
					oldCustomer.setPPS(pps);
					oldCustomer.setSurname(surName);
					oldCustomer.setName(fName);
					oldCustomer.setDOB(dob);
					oldCustomer.setCustID(customerID);
					oldCustomer.setCustPass(customerPass);
					
					//Success message dialog
					JOptionPane.showMessageDialog(null, new Object[] {updateLbl1});
					
				}
				
						
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	//Function generating a customer ID String for a given set of customer details
	public String customerIDGenerator() 
	{
		
		//Initiating String variables that will hold retrieved data
		String customerID, fName, sName, dob;
		
		//Set strings to values in appropriate TextFields (New Customer TF instances)
		fName = name.getText();
		sName = surname.getText();
		dob = DOB.getText();
		
		//Set ID to customer's first name initial + their full second name + the first 2 digits of their dob
		customerID = fName.charAt(0) + sName + dob.charAt(0) + dob.charAt(1);
		
		//Return customerIB when function is called
		return customerID;
		
	}
	
	//Function generating a random (within a set) password 
	public String passwordGenerator() 
	{
		//Setting password length
		int length = 7;
		//Initiating password string
		String password = "";
		//Setting string to a set of letters
		String letterSet = "QWERTYmnbvcx";
		//Setting string to the number set 
		String numberSet = "1234567890";
		//Setting string to a set of special characters
		String specialSet = "!£$%^*()_+";
		//Setting string fullSet to letters set + number set + special set
		String fullSet = letterSet+numberSet+specialSet;
		
		//Initiating an array of characters of size 7
		char[] pass = new char[7];
		//Initiating Random picker
		Random random = new Random();
		
		//iterating through length
		for(int i = 0; i < length; i++)
		{
		
			//For each i pick a character at random  within the confines of fullSet
			pass[i] = fullSet.charAt(random.nextInt(fullSet.length())); 
			
		}
		
		//iterating through pass array
		for(int i = 0; i < pass.length; i++)
		{
			//For each i in the array update password with that value
			password = password + pass[i];
		}
		
		//Return password when function is called
		return password;
		
	}
	
	//Main method
	public static void main(String[] args)
	{
		
		new GUI();
		
	}
		
}
