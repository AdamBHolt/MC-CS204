import java.util.*;

public class HashTable<T extends PersonInterface> implements HashTableInterface<T>
{

    private LinkedList<Person>[] theArray;
    private int size;

    public HashTable(int e)
    {
	size = fourKPlus3(e/3);
	theArray = (LinkedList<Person>[]) new LinkedList[size];

	for(int i=0; i<size; i++)
	    theArray[i] = new LinkedList<Person>();
    }

    public int add(T p)
    {
	int personCount=0;

	theArray[p.hashCode()%size].add((Person)p);
	for(int i=0; i<size; i++)
	    personCount+=theArray[i].size();
	return personCount;
    }

    public boolean contains(T p)
    {
	for(int i=0; i<size; i++)
	    if(theArray[i].contains(p))
		return true;
	return false;
    }

    public T getValue(String key)
    {
	int list = Person.hashKey(key)%size;
	
	for(Person p : theArray[list])
	    if(p.getKey().equals(key))
		return (T)p;
	return null;
    }

    public boolean contains(String key)
    {
	int list = Person.hashKey(key)%size;

	for(Person p : theArray[list])
	    if(p.getKey().equals(key))
		return true;
	return false;
    }

    public ArrayList<T> sort()
    {
	ArrayList<Person> theList = new ArrayList<>();
	PersonComparator compare = new PersonComparator();
	
	for(LinkedList<Person> list : theArray)
	    for(Person p : list)
		theList.add(p);

	Collections.sort(theList, compare);
	
	return (ArrayList<T>)theList;
    }

    public static int fourKPlus3(int n) 
    {
	boolean fkp3 = false;
	boolean aPrime = false;
	int prime, highDivisor, d;

	prime = (int)(n * (1.0 + (1.0 / 100.0)));  
	if(prime % 2 == 0) // if even make odd
	    prime = prime +1;
	while(fkp3 == false) // not a 4k+3 prime
	{  
	    while(aPrime == false) // not a prime
	    {  
		highDivisor = (int)(Math.sqrt(prime) + 0.5);
		for(d = highDivisor; d > 1; d--)
		{  
		    if(prime % d == 0)
			break; // not a prime
		}
		if(d != 1) // prime not found
		    prime = prime + 2;
		else
		    aPrime = true;
	    } // end of the prime search loop
	    if((prime - 3) % 4 == 0)
		fkp3 = true;
	    else
	    {  prime = prime + 2;
	    aPrime = false;
	    }
	} // end of 4k+3 prime search loop
	return prime;
    }
}