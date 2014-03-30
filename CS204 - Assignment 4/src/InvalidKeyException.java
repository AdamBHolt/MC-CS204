/**
 * Exception to indicate that a phone number is in an invalid format
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class InvalidKeyException extends RuntimeException
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidKeyException()
    {
	//Call the superclass constructor
	super("This is an invalid phone number.\nPhone number must be in the form: (XXX)XXX-XXXX\nwhere X is a digit");
    }

    /**
     * Constructor that takes a String as an argument
     * @param message String to indicate the type of exception
     */
    public InvalidKeyException(String message)
    {
	//Call the superclass constructor
	super(message);
    }
}
