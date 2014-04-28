import javax.swing.*;

/**
 * This class is the driver for a program that manages a social network of Friends
 * @author Adam Holt
 * @date 4/27/14
 * @class CS204
 * @time 12:00 MW
 */
public class Driver extends JFrame
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Driver()
    {
	//Set the title
	setTitle("Connections");
	//Set the frame size and prevent resizing
	setSize(800,610);
	setResizable(false);
	//Set the default close operation
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Add a new anonymous FriendPanel
	add(new FriendPanel());
	//Show the frame
	setVisible(true);
    }
    
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)
    {
	//Create a new anonymous Driver
	new Driver();
    }
}