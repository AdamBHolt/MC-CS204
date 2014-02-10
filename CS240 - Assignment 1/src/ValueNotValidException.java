import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Exception for invalid entries in a sudoku game
 */
public class ValueNotValidException extends RuntimeException
{
	//Default serialized ID
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public ValueNotValidException()
	{
		JOptionPane.showMessageDialog(new JPanel(), "This is not a valid entry for this row and column", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Constructor with a passed message
	 * @param message Message to display
	 */
	public ValueNotValidException(String message)
	{
		//Call the superclass constructor
		super(message);
	}
}