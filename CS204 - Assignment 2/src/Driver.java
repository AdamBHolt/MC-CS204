import javax.swing.*;

/**
 * The driver class for a program that stores and displays service orders for an auto shop 
 * @author Adam Holt
 * @date 2/23/14
 * @class CS204
 * @time 12:00 MW
 */
public class Driver extends JFrame
{
	//Default serialized ID
	private static final long serialVersionUID = 1L;

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Create a new anonymous instance of Driver
		new Driver();
	}
	
	/**
	 * Default constructor
	 */
	public Driver()
	{
		//Add a new anonymous instance of CarPanel
		add(new CarPanel(getRootPane()));
		//Set the frame size
		setSize(950,600);
		//Prevent resizing of the frame
		setResizable(false);
		//Set the frame title
		setTitle("Adam's Autobody Shop");
		//Exit the program when the frame is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Display the frame
		setVisible(true);
	}
}