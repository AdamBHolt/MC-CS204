import javax.swing.*;

/**
 * The driver class for a program that sorts an inbox of documents into queues for a reader
 * @author Adam Holt
 * @date 3/09/14
 * @class CS204
 * @time 12:00 MW
 */
public class Driver extends JFrame
{
	//Default Serial ID
	private static final long serialVersionUID = 1L;

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Create a new anonymous instance of Driver
		new Driver();
	}
	
	/**
	 * Default Constructor
	 */
	public Driver()
	{
		//Set the size of the frame and prevent resizing
		setSize(800,775);
		setResizable(false);

		//Add a new anonymous instance of DocumentPanel to the frame
		add(new DocumentPanel());
		
		//Exit on close of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Display the frame
		setVisible(true);
	}
}