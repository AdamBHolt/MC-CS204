import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java .util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This class provides the GUI for a program that stores and displays service orders for an auto shop
 * @author Adam Holt
 * @date 2/23/14
 * @class CS204
 * @time 12:00 MW
 */
public class CarPanel extends JPanel
{
	
	//Class member declarations
	private static final long serialVersionUID = 1L;				//Default serialized ID
	private ServiceOrderManager manager;							//Instance of the ServiceOrderManager class to store the orders
	private JRadioButton owner, order, makeModelYear;				//Radio buttons to change the sort order
	private JTable table;											//Table to display the current orders
	private JTextArea list;											//Text area to display the current orders
	private JTextField orderNumber, ownerName, make, model, year;	//Text fields to enter new orders or choose order to delete
	private JButton start, finish, exit;							//Control buttons to start or finish and order or to exit the program
	private JRootPane rootPane;										//JFrame root pane from Driver class
	private int key;												//Order in which the orders are sorted (1=By order, 2=By owner, 3=By make, model, and year)
	
	/**
	 * Default constructor to construct the JPanel
	 * @param j Root pane passed from JFrame object
	 */
	public CarPanel(JRootPane j)
	{
		//Set rootPane to the pass JRootPane
		rootPane = j;
		//Create a new ServiceOrderManger
		manager = new ServiceOrderManager();
		//Set the layout of the panel
		setLayout(new BorderLayout());
		//Set a transparent border for the panel to add padding
		setBorder(new EmptyBorder(50, 50, 50, 50) );
		//Build and add the left panel
		buildLeftPanel();
		//Build and add the right panel
		buildRightPanel();
		//Call the getOrders method to prompt for the user to select a file of existing orders
		try{getOrders();}
		catch(IOException ex){};
		//Click the order radio button to sort by order number initially
		order.doClick();
	}
	
	/**
	 * Build the left side panel and add it to the main panel
	 */
	private void buildLeftPanel()
	{
		//Outer panel and sub panels
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		JPanel addressPanel = new JPanel();
		JPanel sortPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		//Radio buttons to change sort order
		order = new JRadioButton("Order #");
		owner = new JRadioButton("Owner");
		makeModelYear = new JRadioButton("Make, Model, Year");
		//Button group for the radio buttons
		ButtonGroup group = new ButtonGroup();
		//Table to display current orders
		table = new JTable(20,3)
		{
			//Default serialized ID
			private static final long serialVersionUID = 1L;
			//Prevent the table from being directly edited
			public boolean isCellEditable(int row, int column)
			{                
            	return false;
            };
		};
		//Scroll pane to scroll the table - add the table to the scroll pane
		JScrollPane tableScroll = new JScrollPane(table);

		//Set a titled border and size for the address panel
		addressPanel.setBorder(BorderFactory.createTitledBorder("Adam's Autobody Shop"));
		addressPanel.setPreferredSize(new Dimension(390,75));
		//Add a label with the address of the auto shop to the address panel
		addressPanel.add(new JLabel("123 Fake Street College Park, MD 20740"));
		
		//Set a titled border and size for the sort panel
		sortPanel.setBorder(BorderFactory.createTitledBorder("Sort By"));
		sortPanel.setPreferredSize(new Dimension(300,75));
		
		//Add the radio buttons to the button group
		group.add(order);
		group.add(owner);
		group.add(makeModelYear);
		
		//Set the action commands of the radio buttons
		//To correspond with the appropriate sorting key
		order.setActionCommand("1");
		owner.setActionCommand("2");
		makeModelYear.setActionCommand("3");
		
		//Add action listeners to the radio buttons to change the sort order
		order.addActionListener(new RadioListener());
		owner.addActionListener(new RadioListener());
		makeModelYear.addActionListener(new RadioListener());
		
		//Set mnemonics for the radio buttons
		order.setMnemonic(KeyEvent.VK_R);
		owner.setMnemonic(KeyEvent.VK_W);
		makeModelYear.setMnemonic(KeyEvent.VK_M);
		
		//Set tool tips for the radio buttons
		order.setToolTipText("Sort the orders by order number");
		owner.setToolTipText("Sort the orders by owner name");
		makeModelYear.setToolTipText("Sort the orders by make, model, and year");
		
		//Prevent the radio buttons from getting the focus
		order.setFocusable(false);
		owner.setFocusable(false);
		makeModelYear.setFocusable(false);
		
		//add the radio buttons to the sort panel
		sortPanel.add(owner);
		sortPanel.add(order);
		sortPanel.add(makeModelYear);
		
		//Set a titled border and size for the table panel
		//Display the vertical scroll bar, but not the horizontal scroll bar
		tablePanel.setBorder(BorderFactory.createTitledBorder("Car Table"));
		tableScroll.setPreferredSize(new Dimension(370,200));
		tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Do not display the table headers
		table.setTableHeader(null);
		//Add a list selection listener to the table to detect when an order is selected
		table.getSelectionModel().addListSelectionListener(new TableListener());
		
		//Add the scroll pane to the table panel
		tablePanel.add(tableScroll);
		
		//Set the size of the left panel
		leftPanel.setPreferredSize(new Dimension(400,300));
		
		//Add each sub panel to the left panel
		leftPanel.add(addressPanel);
		leftPanel.add(sortPanel);
		leftPanel.add(tablePanel);
		
		//Add the left panel to the outer panel
		add(leftPanel, BorderLayout.WEST);
	}
	
	/**
	 * Build the right side panel and add it to the main panel
	 */
	private void buildRightPanel()
	{
		//Outer panel and sub panels
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel listPanel = new JPanel();
		JPanel detailPanel = new JPanel(new GridLayout(5,2));
		JPanel buttonPanel = new JPanel();
		//Text area to display the current orders
		list = new JTextArea(10,30);
		//Text fields to input new orders or delete existing orders
		orderNumber = new JTextField(15);
		ownerName = new JTextField(15);
		make = new JTextField(15);
		model = new JTextField(15);
		year = new JTextField(15);
		//Control buttons for the GUI
		start = new JButton("Start an Order");
		finish = new JButton("Finish an Order");
		exit = new JButton("Exit");
		
		//Set a titled border for the list panel
		listPanel.setBorder(BorderFactory.createTitledBorder("Car List"));
		//Add the text area to the list panel
		listPanel.add(list);
		//Prevent the text area from being edited
		list.setEditable(false);
		
		//Set a titled border and size for the detail panel
		detailPanel.setBorder(BorderFactory.createTitledBorder("Service Order Details"));
		detailPanel.setPreferredSize(new Dimension(420,200));
		
		//Add labels and entry fields to the detail panel
		detailPanel.add(new JLabel("Service Order Number"));
		detailPanel.add(orderNumber);
		detailPanel.add(new JLabel("Owner"));
		detailPanel.add(ownerName);
		detailPanel.add(new JLabel("Make"));
		detailPanel.add(make);
		detailPanel.add(new JLabel("Model"));
		detailPanel.add(model);
		detailPanel.add(new JLabel("Year"));
		detailPanel.add(year);
		
		//Set the action commands for the buttons
		start.setActionCommand("s");
		finish.setActionCommand("f");
		exit.setActionCommand("e");
		
		//Add action listeners to the buttons
		start.addActionListener(new ButtonListener());
		finish.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
		
		//Set mnemonics for the buttons
		start.setMnemonic(KeyEvent.VK_S);
		finish.setMnemonic(KeyEvent.VK_F);
		exit.setMnemonic(KeyEvent.VK_E);
		
		//Set tool tips for the buttons
		start.setToolTipText("Start a new service order");
		finish.setToolTipText("Finish and delete an existing service order");
		exit.setToolTipText("Exit the program");
		
		//Make "Start and Order" the default button when hitting the <Enter> key
		rootPane.setDefaultButton(start);
		
		//Add the buttons to the button panel
		buttonPanel.add(start);
		buttonPanel.add(finish);
		buttonPanel.add(exit);
		
		//Set the size of the right panel
		rightPanel.setPreferredSize(new Dimension(440,400));
		
		//Add the sub panels to the right panel
		rightPanel.add(listPanel);
		rightPanel.add(detailPanel);
		rightPanel.add(buttonPanel);
		
		//Add the right panel to the outer panel
		add(rightPanel, BorderLayout.EAST);
	}
	
	/**
	 * Clears the table and text area
	 */
	private void reset()
	{
		//Iterate through each cell of the table and clear the contents
		for(int i=0; i<20; i++)
			for(int j=0; j<3; j++)
				table.setValueAt("", i, j);
		//Clear the text area
		list.setText("");
	}
	
	/**
	 * Re-enter the data in the table and text area after sorting
	 */
	private void sort()
	{
		//Get a 2D array of the current orders to enter in the table
		String[][] aValues = manager.listByKeyTable(key);
		//Get a Vector containing String representations of the current orders to display in the text area
		Vector<String> vValues = manager.listByKeyVector(key);
		
		//Row index of the table
		int r = 0;
		
		reset();
		
		for(String[] row : aValues)
		{
			for(int c=0; c<3; c++)
				table.setValueAt(row[c], r, c);
			r++;
		}
		
		for(String value : vValues)
			list.append(value + "\n");

	}
	
	/**
	 * Clear the text fields
	 */
	private void clearFields()
	{
		//Set the text of each field to ""
		orderNumber.setText("");
		ownerName.setText("");
		make.setText("");
		model.setText("");
		year.setText("");
	}
	
	/**
	 * Get a file containing service order information
	 * @throws IOException
	 */
	private void getOrders() throws IOException
	{
		//File chooser to select the file
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select a Service Order File");

		//If the "open" option is chosen in the FileChooser
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			//File object with the selected file
			File selectedFile = chooser.getSelectedFile();

			//Scanner to read from the selected file
			Scanner inputFile = new Scanner(selectedFile);

			//Read each line in the text file
			while(inputFile.hasNext())
				//Start a new service order based on the next 5 lines in the text file
				manager.startService(Integer.parseInt(inputFile.nextLine()), inputFile.nextLine(), inputFile.nextLine(), inputFile.nextLine(), Integer.parseInt(inputFile.nextLine()));

			//Close the file
			inputFile.close();
		}
	}
	
	/**
	 * Add a new service order to the manager
	 */
	public void startOrder()
	{
		//Only perform the action if none of the text fields is blank
		if(!orderNumber.getText().equals("") &&
				!ownerName.getText().equals("") &&
				!make.getText().equals("") &&
				!model.getText().equals("") &&
				!year.getText().equals(""))
		{
			//Attempt to add a new service order based on the data in the fields
			try
			{
				manager.startService(Integer.parseInt(orderNumber.getText()), ownerName.getText(), make.getText(), model.getText(), Integer.parseInt(year.getText()));
				clearFields();
			}
			//Catch an exception if the order number is already in use
			catch(ServiceOrderInUseException ex)
			{
				//Clear the order number field if an exception is thrown
				orderNumber.setText("");
			}
			finally
			{
				//Select the order number field
				orderNumber.requestFocus();
			}
			//Resort the current list
			sort();
		}
	}
	
	//Delete an existing service order from the manager
	private void finishOrder()
	{
		//Only perform the action if the order number field is not blank
		if(!orderNumber.getText().equals(""))
		{
			//Attempt to remove the specified order number
			try
			{
				manager.finishService(Integer.parseInt(orderNumber.getText()));
			}
			//Catch an exception if the order number is not found
			catch (ServiceOrderNotFoundException ex)
			{
				//Clear the order number field if an exception is thrown
				orderNumber.setText("");
			}
			finally
			{
				//Clear the fields
				clearFields();
				//Select the order number field
				orderNumber.requestFocus();
			}
		}
		//Resort the current list
		sort();
	}
	
	/**
	 * Action listener class for the radio buttons
	 */
	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Set the sort key to the integer value of the  action command of the selected radio button
			key = Integer.parseInt(e.getActionCommand());
			//Resort the current list based on the sort key
			sort();
		}
	}
	
	/**
	 * Action listener class for the buttons
	 */
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Switch based on the action command of the button clicked
			switch(e.getActionCommand().charAt(0))
			{
				case 's':
					//Start a new order
					startOrder();
					break;
				case 'f':
					//Finish an existing order
					finishOrder();
					break;
				case 'e':
					//Exit the program
					System.exit(0);
				default:
			}
		}
	}
	
	/**
	 * List selection listener for the table to detect when a row is selcted
	 */
	private class TableListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			//Get the location of the selected row and set the order number field to
			//The order number of the current order - the location in the table varies depending on the current sort order
			if(key==1)
				orderNumber.setText(String.valueOf(table.getValueAt(e.getFirstIndex(), 0)));
			else
				orderNumber.setText(String.valueOf(table.getValueAt(e.getFirstIndex(), 1)));
		}
	}
}