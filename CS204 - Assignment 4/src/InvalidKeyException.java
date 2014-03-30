
public class InvalidKeyException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public InvalidKeyException()
    {
	//Call the superclass constructor
	super("This is an invalid phone number.\nPhone number must be in the form: (XXX)XXX-XXXX\nwhere X is a digit");
    }

    public InvalidKeyException(String message)
    {
	//Call the superclass constructor
	super(message);
    }
}
