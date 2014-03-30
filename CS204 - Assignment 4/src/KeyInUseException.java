
public class KeyInUseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public KeyInUseException()
    {
	//Call the superclass constructor
	super("Phone number is already in address book");
    }
    
    public KeyInUseException(String message)
    {
	//Call the superclass constructor
	super(message);
    }
}
