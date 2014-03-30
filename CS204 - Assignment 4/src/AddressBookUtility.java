import java.io.*;
import java.util.*;


public class AddressBookUtility implements AddressBookInterface<PersonInterface>
{

    private HashTable<PersonInterface> table;

    public AddressBookUtility()
    {
	table = new HashTable<>(10);
    }


    public boolean contains(PersonInterface p)
    {
	return table.contains(p.getKey());
    }

    public boolean contains(String key) throws InvalidKeyException
    {
	if(!Person.isValidKey(key))
	    throw new InvalidKeyException();
	return table.contains(key);
    }

    public boolean isValidKey(String s) throws InvalidKeyException
    {
	if(!Person.isValidKey(s))
	    throw new InvalidKeyException();
	return Person.isValidKey(s);
    }

    public String reverseLookup(String key) throws InvalidKeyException
    {

	if(!Person.isValidKey(key))
	{
	    throw new InvalidKeyException();
	}
	if(table.contains(key))
	    return table.getValue(key).toString();
	else
	    return null;
    }

    public boolean readFile(File f)
    {
	//Scanner to read from the selected file
	Scanner inputFile=null;
	Person p = null;

	try
	{
	    inputFile = new Scanner(f);
	}
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	    return false;
	}

	//Read each line in the text file
	while(inputFile.hasNext())
	{
	    p=new Person(inputFile.next(), inputFile.next(), inputFile.next(), inputFile.nextLine());
	    
	    if(!table.contains(p))
		table.add(p);
	}

	//Close the file
	inputFile.close();
	return true;
    }

    public void add(String fName, String lName, String pNumber, String add)
	    throws InvalidKeyException, KeyInUseException
	    {
	if(!Person.isValidKey(pNumber))
	    throw new InvalidKeyException();
	if(table.contains(pNumber))
	    throw new KeyInUseException();
	table.add(new Person(fName, lName, pNumber, add));
	    }

    public boolean writeToFile(File f)
    {
	//PrintWriter to write to file
	PrintWriter writer = null;
	ArrayList<PersonInterface> personList = null;

	//File object with the selected file
	try
	{
	    writer = new PrintWriter(f.toString());
	} 
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	    return false;
	}

	personList = table.sort();

	//Write the current orders to the file
	for(PersonInterface person : personList)
	    writer.println((person.getFname() + " " + person.getLname() + " " + person.getPhone() + " " + person.getAddress()));
	//Close the print writer
	writer.close();
	return true;
    }
}