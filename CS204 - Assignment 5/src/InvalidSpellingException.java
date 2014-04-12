import java.io.IOException;

/**
 * This class is an exception for a program that checks the spelling of words against a dictionary of words
 * It indicates that a word contains invalid characters
 * @author Adam Holt
 * @date 4/13/14
 * @class CS204
 * @time 12:00 MW
 */
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