/**
 * A class representing a reader of documents for a program that manages the order of documents based on their priority
 * @author Adam Holt
 * @date 3/09/14
 * @class CS204
 * @time 12:00 MW
 */
public class Reader
{
	//The current status of the reader, either Present or Absent represented by an enum
	private ReaderStatus status;
	//The reader's current activity, Idle, Reading, or None represented by an enum
	private ReaderActivityStatus activity;
	
	/**
	 * Default constructor
	 */
	public Reader()
	{
		//Set the initial status to Absent
		setStatus(ReaderStatus.Absent);
		//Set the initial activity to None
		setActivity(ReaderActivityStatus.None);
	}
	
	/**
	 * Constructor that takes activity and status as parameters
	 * @param s Reader initial status
	 * @param a Reader initial activity
	 */
	public Reader(ReaderStatus s, ReaderActivityStatus a)
	{
		//Set the initial status
		setStatus(s);
		//Set the initial activity
		setActivity(a);
	}
	
	/**
	 * Set the reader status
	 * @param s Reader initial status
	 */
	public void setStatus(ReaderStatus s)
	{
		//Set the initial status
		status = s;
	}
	
	/**
	 * Set the reader activity
	 * @param a Reader initial activity
	 */
	public void setActivity(ReaderActivityStatus a)
	{
		//Set the initial activity
		activity = a;
	}
	
	/**
	 * Get the current status
	 * @return Reader current status
	 */
	public ReaderStatus getStatus()
	{
		//Return the current status
		return status;
	}
	
	/**
	 * Get the current activity
	 * @return Reader current activity
	 */
	public ReaderActivityStatus getActivity()
	{
		//Return the current activity
		return activity;
	}
	
	/**
	 * Get a String representation of the Reader
	 * @return String representation of the Reader
	 */
	public String toString()
	{
		//Return the status and activity of the reader as a String
		return status + " " + activity;
	}
}
