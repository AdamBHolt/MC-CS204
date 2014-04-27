import java.util.*;

/**
 * This class represents a Friend in a program that is a social network of Friends
 * The Friend objects act as vertices in a graph of Friend connections
 * @author Adam Holt
 * @date 4/27/14
 * @class CS204
 * @time 12:00 MW
 */
public class Friend implements Comparable<Friend>
{
    //First name of the Friend
    private String firstName;
    //Last name of the Friend
    private String lastName;
    //Home town of the Friend
    private String homeTown;
    
    /**
     * Default constructor
     */
    public Friend()
    {
	//Set the member variables to the default String
	setFirstName("");
	setLastName("");
	setHomeTown("");
    }
    
    /**
     * Constructor takes parameters to set the member variables
     * @param f First name
     * @param l Last name
     * @param h Home town
     */
    public Friend(String f, String l, String h)
    {
	//Set the member variables based on the passed arguments
	setFirstName(f);
	setLastName(l);
	setHomeTown(h);
    }
    
    /**
     * Constructor takes a single String as a parameter
     * @param n Full name and home town of Friend in the form <first name> <last name> of <home town>
     */
    public Friend(String n)
    {
	Scanner scan = new Scanner(n);
	
	setFirstName(scan.next());
	setLastName(scan.next());
	scan.next();
	setHomeTown(scan.nextLine().trim());
	scan.close();
    }
    
    /**
     * Copy constructor
     * @param f Friend object to be copied
     */
    public Friend(Friend f)
    {
	setFirstName(f.getFirstName());
	setLastName(f.getLastName());
	setHomeTown(f.getHomeTown());
    }
    
    /**
     * Set the first name
     * @param f First name
     */
    public void setFirstName(String f)
    {
	firstName = f;
    }
    
    /**
     * Get the first name
     * @return First name of the Friend
     */
    public String getFirstName()
    {
	return firstName;
    }
    
    /**
     * Set the last name
     * @param l Last name
     */
    public void setLastName(String l)
    {
	lastName = l;
    }
    
    /**
     * Get the last name
     * @return Last name of the Friend
     */
    public String getLastName()
    {
	return lastName;
    }
    
    /**
     * Set the home town
     * @param h Home town
     */
    public void setHomeTown(String h)
    {
	homeTown = h;
    }
    
    /**
     * Get the home town
     * @return Home town of the friend
     */
    public String getHomeTown()
    {
	return homeTown;
    }
    
    /**
     * Get the full name
     * @return First name + last name of the Friend
     */
    public String getFullName()
    {
	return getFirstName() + " " + getLastName();
    }
    
    /**
     * Compare this Friend object to one passed in
     * @param otherFriend Friend object to compare
     * @return String comparison of the full names of the Friend objects
     */
    public int compareTo(Friend otherFriend)
    {
	//Return the String comparison of the full names of the Friend objects
	return getLastName().compareTo(otherFriend.getLastName());
    }
    
    /**
     * Determine whether or not two Friend objects are equal based on their full names 
     * @param otherFriend Friend object to compare
     * @return String equals result of the full names of the Friend objects
     */
    public boolean equals(Friend otherFriend)
    {
	//Return the String equals result of the full names of the Friend objects
	return getFullName().equals(otherFriend.getFullName());
    }
    
    /**
     * Get a String representation of the Friend object
     * @return String representation of the Friend object
     */
    public String toString()
    {
	return getFullName() + " of " + getHomeTown();
    }
}