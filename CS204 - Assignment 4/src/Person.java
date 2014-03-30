import java.util.*;
import java.util.regex.*;

/**
 * This class represents a person to be stored in an address book utility
 * It contains the persons first and last names, phone number and address
 * Provides methods to sort the person in a hash table
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class Person implements PersonInterface
{
    //Member declarations
    private String fName;			//First name
    private String lName;			//Last name
    private String phone;			//Phone number
    private String address;			//Address
    private static final int p1 = 23;	//Prime number to generate hash code
    private static final int p2 = 31;	//Prime number to generate hash code

    /**
     * Default constructor
     */
    public Person()
    {
	//Set member variables to default values
	setFname("");
	setLname("");
	setPhone("");
	setAddress("");
    }

    /**
     * Constructor that takes four Strings to represent the member variables
     * @param f First name
     * @param l Last name
     * @param p Phone number
     * @param a Address
     */
    public Person(String f, String l, String p, String a)
    {
	//Set member variables to the passed values
	setFname(f);
	setLname(l);
	setPhone(p);
	setAddress(a);
    }

    /**
     * Set the first name
     * @param f First name
     */
    public void setFname(String f)
    {
	//Set first name
	fName = f;
    }

    /**
     * Set the last name
     * @param l Last name
     */
    public void setLname(String l)
    {
	//Set the last name
	lName = l;
    }

    /**
     * Set the phone number
     * @param p Phone number
     */
    public void setPhone(String p)
    {
	//Set the phone number
	phone = p;
    }

    /**
     * Set the address
     * @param a Address
     */
    public void setAddress(String a)
    {
	//Set the address
	address = a;
    }

    /**
     * Get the first name
     * @return First name
     */
    public String getFname() 
    {
	//Return the first name
	return fName;
    }

    /**
     * Get the last name
     * @return Last name
     */
    public String getLname() 
    {
	//Return the last name
	return lName;
    }

    /**
     * Get the phone number
     * @return Phone number
     */
    public String getPhone() 
    {
	//Return the phone number
	return phone;
    }

    /**
     * Get the address
     * @return Address
     */
    public String getAddress() 
    {
	//Return the address
	return address;
    }

    /**
     * Get a Person hash key based on a passed String
     * @param key Key to hash
     * @return Hash key based on the passed String or -1 if the String is an invalid key
     */
    public static int hashKey(String key)
    {
	//String representations of the sections of the phone number
	int areaCode, exchangeCode, extensionCode;
	//If the passed key is valid process the hash key
	if(isValidKey(key))
	{
	    //Split the key into tokens based on the phone number delimiters
	    StringTokenizer t = new StringTokenizer(key, "()-");
	    areaCode = Integer.parseInt(t.nextToken());
	    exchangeCode = Integer.parseInt(t.nextToken());
	    extensionCode = Integer.parseInt(t.nextToken());

	    //Return the calculated hash key
	    return Math.abs((p1 * (areaCode + p2 * exchangeCode) + extensionCode));
	}
	//Return -1 if the key is invalid
	return -1;
    }

    /**
     * Determines whether the passed String is a valid key
     * @param s String representing a phone number key
     * @return True if the key is valid, otherwise false
     */
    public static boolean isValidKey(String s)
    {
	//Determine the key's validity by comparing it to a regex pattern representing the correct key format
	return(Pattern.compile("\\(\\d{3}\\)\\d{3}-\\d{4}").matcher(s).matches());
    }

    /**
     * Get the hash key for a specific Person instance
     * @return The has key for this Person
     */
    public int hashCode()
    {
	//Call the hashKey method using the key for this Person
	return hashKey(getKey());
    }

    /**
     * Determines whether this Person object is equal to a passed PersonInterface object based on the keys
     * @param p Another PersonInterface object
     * @return True if the objects are equal, otherwise false
     */
    public boolean equals(PersonInterface p) 
    {
	//Return the equality value of the keys
	return getKey().equals(p.getKey());
    }

    /**
     * Get the key for this Person object
     * @return The key for this Person object
     */
    public String getKey() {
	//Return the phone number as the key
	return getPhone();
    }

    /**
     * Returns a String representation of this Person
     * @return String representation of this Person in the form Lastnam, Firstname
     */
    public String toString()
    {
	//Return Lastname, Firstname
	return getLname() + ", " + getFname();
    }
}