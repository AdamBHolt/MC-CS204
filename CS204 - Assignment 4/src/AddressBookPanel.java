import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AddressBookPanel extends JPanel
{

    private JButton addAddress, reverseLookup, read, write, exit;
    private JTextField fName, lName, pNumber;
    private JTextArea address, numbers, names;
    private AddressBookUtility addressBook;

    public AddressBookPanel()
    {
	addressBook = new AddressBookUtility();
	buildTopPanel();
	buildBottomPanel();
    }

    private void buildTopPanel()
    {
	JPanel topPanel = new JPanel();
	JPanel fNamePanel = new JPanel(new GridLayout(1,2));
	JPanel lNamePanel = new JPanel(new GridLayout(1,2));
	JPanel pNumberPanel = new JPanel(new GridLayout(1,2));
	JPanel addressPanel = new JPanel(new GridLayout(1,2));
	fName = new JTextField(20);
	lName = new JTextField(20);
	pNumber = new JTextField(20);
	address = new JTextArea();
	addAddress = new JButton("Add");


	addAddress.setActionCommand("a");
	addAddress.addActionListener(new ButtonListener());
	addAddress.setMnemonic(KeyEvent.VK_A);
	addAddress.setToolTipText("Add address to the address book");

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

	topPanel.add(fNamePanel);
	topPanel.add(lNamePanel);
	topPanel.add(pNumberPanel);
	topPanel.add(addressPanel);
	topPanel.add(addAddress);

	topPanel.setPreferredSize(new Dimension(520,275));
	topPanel.setBorder(BorderFactory.createTitledBorder("Add"));
	add(topPanel);
    }

    private void buildBottomPanel()
    {
	JPanel bottomPanel = new JPanel();
	JPanel innerPanel = new JPanel();
	JPanel textPanel = new JPanel();
	numbers = new JTextArea(10,19);
	names = new JTextArea(10,34);
	reverseLookup = new JButton("Reverse Lookup");
	read = new JButton("Read Text File");
	write = new JButton("Write Text File");
	exit = new JButton("Exit");

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

	numbers.setBorder(BorderFactory.createTitledBorder("Phone Number(s) (XXX)XXX-XXX"));
	names.setBorder(BorderFactory.createTitledBorder("Name (Last, First)"));

	textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	textPanel.add(numbers);
	textPanel.add(names);

	innerPanel.setPreferredSize(new Dimension(650,270));
	innerPanel.setBorder(BorderFactory.createTitledBorder("Reverse Phone Lookup"));
	innerPanel.add(textPanel);
	innerPanel.add(reverseLookup);

	bottomPanel.setPreferredSize(new Dimension(700,325));
	bottomPanel.add(innerPanel);
	bottomPanel.add(read);
	bottomPanel.add(write);
	bottomPanel.add(exit);
	add(bottomPanel);
    }

    private void addAddress()
    {

    }

    private void reverseLookup()
    {
	try{addressBook.reverseLookup("(310)422-9648");}
	catch(InvalidKeyException e)
	{JOptionPane.showMessageDialog(new JPanel(), "This is and invalid phone number." +
							"\nPhone number must be in the form: (XXX)XXX-XXXX" +
							"\nwhere X is a digit", "Error", JOptionPane.ERROR_MESSAGE);}
    }

    private void readFile()
    {

    }

    private void writeFile()
    {

    }

    private class ButtonListener implements ActionListener
    {

	public void actionPerformed(ActionEvent e)
	{
	    switch(e.getActionCommand().charAt(0))
	    {
	    case 'a':
		addAddress();
		break;
	    case 'l':
		reverseLookup();
		break;
	    case 'r':
		readFile();
		break;
	    case 'w':
		writeFile();
		break;
	    case 'e':
		System.exit(0);
	    }

	}
    }

}
