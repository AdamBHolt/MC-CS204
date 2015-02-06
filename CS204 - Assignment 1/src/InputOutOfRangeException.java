import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Exception for invalid entries in a sudoku game
 */
public class InputOutOfRangeException extends RuntimeException
{
	//Default serialized ID
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public InputOutOfRangeException()
	{
		JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
	/**
	 * Constructor with a passed message
	 * @param message Message to display
	 */
	public InputOutOfRangeException(String message)
	{
		//Call the superclass constructor
		super(message);
	}
}