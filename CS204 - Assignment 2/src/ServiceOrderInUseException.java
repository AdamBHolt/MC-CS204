import javax.swing.*;

/**
 * Exception to indicate an order number has already been used
 * in an auto shop service order list
 * Used when starting a new order
 */
public class ServiceOrderInUseException extends RuntimeException
{
	//Default serialized ID
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public ServiceOrderInUseException()
	{
		JOptionPane.showMessageDialog(new JPanel(), "The Service Order Number is already being used.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
	/**
	 * Constructor with a passed message
	 * @param message Message to display
	 */
	public ServiceOrderInUseException(String message)
	{
		//Call the superclass constructor
		super(message);
	}
}