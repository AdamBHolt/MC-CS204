import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AddressBookPanel extends JPanel
{

    private JButton addAddress, reverseLookup, read, write, exit;
    private JTextField fName, lName, pNumber;
    private JTextArea address, numbers, names;
    
    public AddressBookPanel()
    {
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
	
	topPanel.setPreferredSize(new Dimension(500,300));
	topPanel.setBorder(BorderFactory.createTitledBorder("Add"));
	add(topPanel);
    }
    
    private void buildBottomPanel()
    {
	JPanel bottomPanel = new JPanel();
	
	
	add(bottomPanel);
    }
    
    private class ButtonListener implements ActionListener
    {

	public void actionPerformed(ActionEvent e)
	{

	    
	}
    }
    
}
