import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * This class provides a GUI for an address book to store name, phone number, and addresses
 * Provides method to look people up by their phone number
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class AddressBookPanel extends JPanel
{

    //GUI components to be added to the panel
    private JButton addAddress, reverseLookup, read, write, exit;
    private JTextField fName, lName, pNumber;
    private JTextArea address, numbers, names;
    //AddressBookUtility object to store information
    private AddressBookUtility addressBook;
    //Default serial ID
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor
     */
    public AddressBookPanel()
    {
	//Initialize the AddressBookUtility
	addressBook = new AddressBookUtility();
	//Build and add the top panel
	buildTopPanel();
	//Build and add the bottom panel
	buildBottomPanel();
	//Refresh the panel
	refresh();
    }

    /**
     * Build and add the top panel
     */
    private void buildTopPanel()
    {
	//Top panel and sub panels
	JPanel topPanel = new JPanel();
	JPanel fNamePanel = new JPanel(new GridLayout(1,2));
	JPanel lNamePanel = new JPanel(new GridLayout(1,2));
	JPanel pNumberPanel = new JPanel(new GridLayout(1,2));
	JPanel addressPanel = new JPanel(new GridLayout(1,2));
	//Text fields to get user input
	fName = new JTextField(20);
	lName = new JTextField(20);
	pNumber = new JTextField(20);
	address = new JTextArea();
	//Button to add new entries
	addAddress = new JButton("Add");

	//Set parameters of the add button
	addAddress.setActionCommand("a");
	addAddress.addActionListener(new ButtonListener());
	addAddress.setMnemonic(KeyEvent.VK_A);
	addAddress.setToolTipText("Add address to the address book");

	//Set the parameters of the sub panels and add the text fields
	fNamePanel.setPreferredSize(new Dimension(450,40));
	fNamePanel.add(new JLabel("First Name"));
	fNamePanel.add(fName);
	lNamePanel.setPreferredSize(new Dimension(450,40));
	lNamePanel.add(new JLabel("Last Name"));
	lNamePanel.add(lName);
	pNumberPanel.setPreferredSize(new Dimension(450,40));
	pNumberPanel.add(new JLabel("Phone Number"));
	pNumberPanel.add(pNumber);
	addressPanel.setPreferredSize(new Dimension(450,80));
	addressPanel.add(new JLabel("Address"));
	addressPanel.add(address);

	//Add FocusListener to pNumber
	pNumber.addFocusListener(new FieldListener());

	//Add the sub panels and button to the top panel
	topPanel.add(fNamePanel);
	topPanel.add(lNamePanel);
	topPanel.add(pNumberPanel);
	topPanel.add(addressPanel);
	topPanel.add(addAddress);

	//Set the top panel parameters and add it to the main panel
	topPanel.setPreferredSize(new Dimension(520,275));
	topPanel.setBorder(BorderFactory.createTitledBorder("Add"));
	add(topPanel);
    }

    /**
     * Build and add the bottom panel
     */
    private void buildBottomPanel()
    {
	//Bottom panel and inner panels
	JPanel bottomPanel = new JPanel();
	JPanel innerPanel = new JPanel();
	JPanel textPanel = new JPanel();
	//text areas for reverse lookup
	numbers = new JTextArea(10,19);
	names = new JTextArea(10,34);
	//Action buttons
	reverseLookup = new JButton("Reverse Lookup");
	read = new JButton("Read Text File");
	write = new JButton("Write Text File");
	exit = new JButton("Exit");

	//Set the parameters for the buttons
	reverseLookup.setActionCommand("l");
	read.setActionCommand("r");
	write.setActionCommand("w");
	exit.setActionCommand("e");
	reverseLookup.addActionListener(new ButtonListener());
	read.addActionListener(new ButtonListener());
	write.addActionListener(new ButtonListener());
	exit.addActionListener(new ButtonListener());
	reverseLookup.setMnemonic(KeyEvent.VK_L);
	read.setMnemonic(KeyEvent.VK_R);
	write.setMnemonic(KeyEvent.VK_W);
	exit.setMnemonic(KeyEvent.VK_E);
	reverseLookup.setToolTipText("Perform a reverse lookup on the phone numbers");
	read.setToolTipText("Read from a text file");
	write.setToolTipText("Write to a text file");
	exit.setToolTipText("Exit the program");

	//Set the parameters for the text areas
	numbers.setBorder(BorderFactory.createTitledBorder("Phone Number(s) (XXX)XXX-XXX"));
	names.setBorder(BorderFactory.createTitledBorder("Name (Last, First)"));
	names.setEditable(false);

	//Set the parameters and add the components to the inner panels
	textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	textPanel.add(numbers);
	textPanel.add(names);
	innerPanel.setPreferredSize(new Dimension(650,270));
	innerPanel.setBorder(BorderFactory.createTitledBorder("Reverse Phone Lookup"));
	innerPanel.add(textPanel);
	innerPanel.add(reverseLookup);

	//Set the parameters for the bottom panel and add the inner panels
	bottomPanel.setPreferredSize(new Dimension(700,325));
	bottomPanel.add(innerPanel);
	bottomPanel.add(read);
	bottomPanel.add(write);
	bottomPanel.add(exit);
	//Add the bottom panel to the main panel
	add(bottomPanel);
    }

    /**
     * Add an address to the AddressBookUtility
     */
    private void addAddress()
    {
	//Only proceed if all of the text fields are not empty
	if(!fName.getText().equals("") && !lName.getText().equals("") && !pNumber.getText().equals("") && !address.getText().equals(""))
	{
	    //Attempt to add an entry to the AddressBookUtility
	    try
	    {
		addressBook.add(fName.getText(), lName.getText(), pNumber.getText(), address.getText());
		refresh();
	    }
	    //If an InvalidKeyException is thrown display an error message and return the focus to the phone number field
	    catch(InvalidKeyException ex)
	    {
		pNumber.requestFocus();
		pNumber.selectAll();
		JOptionPane.showMessageDialog(null, ex.getMessage());
	    }
	    //If an KeyInUseException is thrown display an error message and return the focus to the phone number field
	    catch(KeyInUseException ex)
	    {
		pNumber.requestFocus();
		pNumber.selectAll();
		JOptionPane.showMessageDialog(null, ex.getMessage());
	    }
	}
    }
    
    /**
     * Look up a person based on their phone number
     */
    private void reverseLookup()
    {
	//Only proceed if the numbers area is not empty
	if(!numbers.getText().equals(""))
	{
	    //Split the text into tokens delimited by '\n' to get individual phone numbers
	    StringTokenizer tokens = new StringTokenizer(numbers.getText(), "\n");
	    //Set the initial output string to null
	    String output=null;
	    //Clear the names text area
	    names.setText("");

	    //Try to lookup the entry for each phone number in the text area
	    try
	    {
		//Continue as long as there are more tokens
		while(tokens.hasMoreTokens())
		{
		    //Set the output to the lookup of the phone number
		    output = addressBook.reverseLookup(tokens.nextToken());
		    //If the output from the lookup is null set the output text to indicate that the recod wasn't found
		    if(output==null)
			output="Record not located";
		    //Add the output to the text area
		    names.append(output + "\n");
		}
	    }
	    //If an InvalidKeyException is thrown display an error message and return the focus to the phone number field   
	    catch(InvalidKeyException ex)
	    {
		JOptionPane.showMessageDialog(null, ex.getMessage());
	    }
	}
    }

    /**
     * Reads a file from a file chooser and sends the file to the AddressBookUtility to read
     */
    private void readFile()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select a List of Addresses");

	//If the "open" option is chosen in the FileChooser
	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    //File object with the selected file
	    File selectedFile = chooser.getSelectedFile();
	    
	    //Send the File to the AddressBookUtility
	    addressBook.readFile(selectedFile);
	}
    }

    /**
     * Prompt the user to create a new File and send it to the AddressBookUtility to write to the file
     */
    private void writeFile()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Save Current Address Book");

	//If the "save" option is chosen in the FileChooser send the File to the AddressBookUtility
	if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	    addressBook.writeToFile(new File(chooser.getSelectedFile().toString()));
    }

    /**
     * Resets the entry fields in the GUI
     */
    private void refresh()
    {
	//Set the fields to their default state
	fName.setText("");
	lName.setText("");
	pNumber.setForeground(Color.LIGHT_GRAY);
	pNumber.setText("(XXX)XXX-XXXX");
	address.setText("");
	fName.requestFocus();
    }

    /**
     * ActionLisnter for the buttons
     */
    private class ButtonListener implements ActionListener
    {
	/**
	 * Action to be performed on button click
	 */
	public void actionPerformed(ActionEvent e)
	{
	    //Switch based on the action command of the clicked button
	    switch(e.getActionCommand().charAt(0))
	    {
	    case 'a':
		//Add a new entry
		addAddress();
		break;
	    case 'l':
		//Perform a reverse lookup
		reverseLookup();
		break;
	    case 'r':
		//Read from a file
		readFile();
		break;
	    case 'w':
		//Write to a file
		writeFile();
		break;
	    case 'e':
		//Exit the program
		System.exit(0);
	    }
	}
    }

    /**
     * FocusListener for the phone number field
     */
    private class FieldListener implements FocusListener
    {
	/**
	 * Action to be performed when the field gains focus
	 */
	public void focusGained(FocusEvent e)
	{
	    //If the field contains the default text set the format to allow entry
	    if(pNumber.getText().equals("(XXX)XXX-XXXX"))
	    {
		pNumber.setForeground(Color.BLACK);
		pNumber.setText("");
	    }
	}
	
	/**
	 * Action to be performed when the field loses focus
	 */
	public void focusLost(FocusEvent arg0){}
    }
}