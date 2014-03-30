import java.util.*;

public class HashTable<T extends PersonInterface> implements HashTableInterface<T>
{

    private LinkedList<T>[] theArray;
    private int size;

    public HashTable(int e)
    {
	size = fourKPlus3(e/3);
	theArray = (LinkedList<T>[]) new LinkedList[size];

	for(int i=0; i<size; i++)
	    theArray[i] = new LinkedList<T>();
    }

    public int add(T p)
    {
	int personCount=0;

	theArray[p.hashCode()%size].add(p);
	for(int i=0; i<size; i++)
	    personCount+=theArray[i].size();
	return personCount;
    }

    public boolean contains(T p)
    {
	return contains(p.getKey());
    }

    public T getValue(String key)
    {
	int list = Person.hashKey(key)%size;
	
	for(T p : theArray[list])
	    if(p.getKey().equals(key))
		return p;
	return null;
    }

    public boolean contains(String key)
    {
	int list = Person.hashKey(key)%size;

	for(T p : theArray[list])
	    if(p.getKey().equals(key))
		return true;
	return false;
    }

    public ArrayList<T> sort()
    {
	ArrayList<T> theList = new ArrayList<>();
	PersonComparator<T> compare = new PersonComparator<>();
	
	for(LinkedList<T> list : theArray)
	    for(T p : list)
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