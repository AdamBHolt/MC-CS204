import javax.swing.*;

/**
 * This class provides the GUI for an address book utility that stores phone numbers, names, and addresses
 * And allows for lookup based on the phone number for each entry
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class Driver extends JFrame
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)
    {
	//Create an anonymous instance of the Driver class
	new Driver();
    }
    
    /**
     * Default constructor
     */
    public Driver()
    {
	//Add anonymous instance of the AddressBookPanel class to the frame
	add(new AddressBookPanel());
	//Set the frame title
	setTitle("Address Book");
	//Set the frame size
	setSize(800,635);
	//Prevent the frame from being resized
	setResizable(false);
	//Set the default close operation
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Show the frame
	setVisible(true);
    }
}