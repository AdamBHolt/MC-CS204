import javax.swing.*;

/**
 * Exception to indicate a specified order was not found
 * in an auto shop service order list
 * Used when finishing an existing order
 */
public class ServiceOrderNotFoundException extends RuntimeException
{
	//Default serialized ID
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public ServiceOrderNotFoundException()
	{
		JOptionPane.showMessageDialog(new JPanel(), "The Service Order Number is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
	/**
	 * Constructor with a passed message
	 * @param message Message to display
	 */
	public ServiceOrderNotFoundException(String message)
	{
		//Call the superclass constructor
		super(message);
	}
}