import java.io.IOException;

/**
 * This class is an exception for a program that checks the spelling of words against a dictionary of words
 * It indicates that a word being added already exists in the dictionary
 * @author Adam Holt
 * @date 4/13/14
 * @class CS204
 * @time 12:00 MW
 */
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
	super("The word already exists in the dictionary.");
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