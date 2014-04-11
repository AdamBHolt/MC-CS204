import java.io.IOException;

public class DuplicateWordException extends IOException
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public DuplicateWordException()
    {
	//Call the superclass constructor
	super("This word already exists in the dictionary.");
    }

    /**
     * Constructor that takes a String as an argument
     * @param message String to indicate the type of exception
     */
    public DuplicateWordException(String message)
    {
	//Call the superclass constructor
	super(message);
    }
}