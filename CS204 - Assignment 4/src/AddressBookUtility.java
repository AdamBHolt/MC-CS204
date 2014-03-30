import java.io.File;


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
	    throw new InvalidKeyException();
	if(table.contains(key))
	    return table.getValue(key).toString();
	else
	    return null;
    }

    @Override
    public boolean readFile(File f)
    {
	// TODO Auto-generated method stub
	return false;
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

    @Override
    public boolean writeToFile(File f)
    {
	// TODO Auto-generated method stub
	return false;
    }


}
