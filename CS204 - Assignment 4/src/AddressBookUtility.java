import java.io.*;
import java.util.*;

/**
 * This class manages the data for an address book utility through a HashTable of PersonInterface objects
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class AddressBookUtility implements AddressBookInterface<PersonInterface>
{
    //HashTable to contain PersonInterface objects
    private HashTable<PersonInterface> table;

    /**
     * Default constructor
     */
    public AddressBookUtility()
    {
	//Initialize the HashTable
	table = new HashTable<>();
    }

    /**
     * Determine whether the HashTable contains a PersonInterface object
     * @param p PersonInterface object to locate
     * @return True if the HashTable contains the PersonInterfaceobject, otherwise false
     */
    public boolean contains(PersonInterface p)
    {
	//Call the contains method of the HashTable
	return table.contains(p);
    }

    /**
     * Determine whether the HashTable contains a passed key
     * @param key Key to locate
     * @return True if the HashTable contains the key, otherwise false
     * @throws InvalidKeyException
     */
    public boolean contains(String key) throws InvalidKeyException
    {
	//If the key is not in a valid form, throw an InvalidKeyException
	if(!Person.isValidKey(key))
	    throw new InvalidKeyException();
	//Return true if the key is found, otherwise false
	return table.contains(key);
    }

    /**
     * Determine whether a key is valid
     * @param s String representation of a key to be tested
     * @return True if the key is valid
     * @throws InvalidKeyException
     */
    public boolean isValidKey(String s) throws InvalidKeyException
    {
	//If the key is not in a valid form, throw an InvalidKeyException
	if(!Person.isValidKey(s))
	    throw new InvalidKeyException();
	//Otherwise return true
	return true;
    }

    /**
     * Look up a PersonInterface object in the HashTable based on the key/phone number
     * @param key Key to be looked up
     * @return String representation of the PersonInterface object if it found, otherwise null
     */
    public String reverseLookup(String key) throws InvalidKeyException
    {
	//If the key is not in a valid form, throw an InvalidKeyException
	if(!Person.isValidKey(key))
	    throw new InvalidKeyException();
	//If the key is contained in the HashTable return a String representation
	if(table.contains(key))
	    return table.getValue(key).toString();
	//Otherwise return null
	else
	    return null;
    }

    /**
     * Read from a text file to create and add a series of Person objects to the HashTable
     * @param f File object to be read from
     * @return True if the read process succeeds, otherwise false
     */
    public boolean readFile(File f)
    {
	//Scanner to read from the selected file
	Scanner inputFile=null;
	//Temporary Person object
	Person p = null;

	//Try to create a new Scanner
	try
	{
	    inputFile = new Scanner(f);
	}
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	    //Return false if an exception is thrown
	    return false;
	}

	//Read each line in the text file
	while(inputFile.hasNext())
	{
	    //Create a Person object based on the file
	    p=new Person(inputFile.next(), inputFile.next(), inputFile.next(), inputFile.nextLine());
	    
	    //If the HashTable does not already contain the Person, then add it
	    if(!table.contains(p))
		table.add(p);
	}

	//Close the file
	inputFile.close();
	//Return true to indicate that the read process was successful
	return true;
    }

    /**
     * Add a new Person to the HashTable
     * @param fName The first name
     * @param lName The last name
     * @param pNumber The phone number
     * @param add The address
     * @throws InvalidKeyException
     * @throws KeyInUseException
     */
    public void add(String fName, String lName, String pNumber, String add)
	    throws InvalidKeyException, KeyInUseException
	    {
	//If the key is not valid throw an InvalidKeyException
	if(!Person.isValidKey(pNumber))
	    throw new InvalidKeyException();
	//If the object already exists in the HashTable throw a KeyInUseException
	if(table.contains(pNumber))
	    throw new KeyInUseException();
	//If no exceptions are thrown create a new Person object and add it to the HashTable
	table.add(new Person(fName, lName, pNumber, add));
	    }

    /**
     * Write the current contents of the HashTable to a text file
     * @param f File object to be written to
     * @return True if the write process was successful, otherwise false
     */
    public boolean writeToFile(File f)
    {
	//PrintWriter to write to file
	PrintWriter writer = null;
	//ArrayList to hold the PersonInterface objects
	ArrayList<PersonInterface> personList = null;

	//Try to create a PrintWriter based on the passed File object
	try
	{
	    writer = new PrintWriter(f.toString());
	} 
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	    //If the PrintWriter cannot be create return false
	    return false;
	}

	//Get a sorted ArrayList representation of the HashTable
	personList = table.sort();

	//Write each Person object to the text file
	for(PersonInterface person : personList)
	    writer.println((person.getFname() + " " + person.getLname() + " " + person.getPhone() + " " + person.getAddress()));
	
	//Close the print writer
	writer.close();
	
	//Return true to indicate that the write process was successful
	return true;
    }
}