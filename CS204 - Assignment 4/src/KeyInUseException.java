/**
 * Exception to indicate that a phone number already exists in an address book
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class KeyInUseException extends RuntimeException
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public KeyInUseException()
    {
	//Call the superclass constructor
	super("Phone number is already in address book");
    }
    
    /**
     * Constructor that takes a String as an argument
     * @param message String to indicate the type of exception
     */
    public KeyInUseException(String message)
    {
	//Call the superclass constructor
	super(message);
    }
}
