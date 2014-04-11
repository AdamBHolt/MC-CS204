import java.io.IOException;

public class InvalidSpellingException extends IOException
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidSpellingException()
    {
	//Call the superclass constructor
	super("Only letters (a-z or A-Z) are allowed.");
    }

    /**
     * Constructor that takes a String as an argument
     * @param message String to indicate the type of exception
     */
    public InvalidSpellingException(String message)
    {
	//Call the superclass constructor
	super(message);
    }
}