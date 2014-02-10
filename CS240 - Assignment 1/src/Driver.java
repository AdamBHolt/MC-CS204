import javax.swing.*;

/**
 * This is the driver class for a sudoku board game
 * @author Adam Holt
 * @date 2/9/14
 * @class CS204
 * @time 12:00 MW
 */
public class Driver extends JFrame
{
	private static final long serialVersionUID = 1L;		//Default serialized ID

	/**
	 * Main method for the program
	 * @param args
	 */
	public static void main(String[] args)
    {
		//Create an instance of the driver
    	new Driver();
    }
    
	/**
	 * Default constructor for the driver class
	 */
    public Driver()
    {
    	//Create and add a new SudokuPanel instance
    	add(new SudokuPanel(getRootPane()));
    	//Set the size of the frame
    	setSize(500, 470);
    	//Prevent resizing the frame
    	setResizable(false);
    	//Set the frame title
    	setTitle("Sudoku");
    	//Exit the program on close
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//Display the frame
    	setVisible(true);
    }
}