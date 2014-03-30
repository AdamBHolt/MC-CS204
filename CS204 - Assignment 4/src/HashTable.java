import java.util.*;

/**
 * This class provides a hash table used to store Person objects in an address book utility
 * The hash table is implemented as an array of LinkedLists that contain PersonInterface objects
 * Uses buckets to store objects with the same hash code
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class HashTable<T extends PersonInterface> implements HashTableInterface<T>
{

    //Array of LinkedLists containing PersonInterface objects
    private LinkedList<T>[] theArray;
    //Size of the array - number of LinkedLists
    private int size;

    /**
     * Default constructor sets the default size of the HashTable to 23
     */
    public HashTable()
    {
	//Find the next 4k+3 prime greater than 10 and set the size
	size = 23;

	//Initialize the array based on the size
	theArray = (LinkedList<T>[]) new LinkedList[size];

	//Initialize each LinkedList in the array
	for(int i=0; i<size; i++)
	    theArray[i] = new LinkedList<T>();
    }

    /**
     * Constructor that takes an int representing the number of Person objects intended to be stored in the HashTable
     * @param e Number of elements to be stored in the HashTable
     */
    public HashTable(int e)
    {
	//Find the next 4k+3 prime greater than the number of elements and set the size
	size = fourKPlus3(e/3);

	//Initialize the array based on the size
	theArray = (LinkedList<T>[]) new LinkedList[size];

	//Initialize each LinkedList in the array
	for(int i=0; i<size; i++)
	    theArray[i] = new LinkedList<T>();
    }

    /**
     * Add a PersonInterface object to the HashTable
     * @param PersonInterface object
     * @return Number of PersonInterface objects currently in the HashTable
     */
    public int add(T p)
    {
	//Number of PersonInterface objects in the HashTable
	int personCount=0;

	//Add the person to the LinkedList at the location in the array that is the hash code%size of the array
	theArray[p.hashCode()%size].add(p);
	//Check each LinkedList in the array and add its size to the personCount
	for(int i=0; i<size; i++)
	    personCount+=theArray[i].size();
	//Return the number of PersonInterface objects currently in the HashTable
	return personCount;
    }

    /**
     * Determine whether a PersonInterface object is in the HashTable
     * @param p A PersonInterface object
     * @return True if the PeronInterface object is in the HashTable, otherwise false
     */
    public boolean contains(T p)
    {
	//Call the contains method using the key of the passed PersonInterface object
	return contains(p.getKey());
    }

    /**
     * Get the value of a PersonInterface object with the passed key
     * @param key The key of the PersonInterface object to locate
     * @return The PersonInterface object if it is found, otherwise, null
     */
    public T getValue(String key)
    {
	//Determine which LinkedList to check based on the hash key of the passed key
	int list = Person.hashKey(key)%size;

	//Check each element of the correct LinkedList
	for(T p : theArray[list])
	    //If the object is found, return it
	    if(p.getKey().equals(key))
		return p;
	//Otherwise, return null
	return null;
    }

    /**
     * Determine whether a PersonInterface object is in the HashTable based on its key
     * @param p String representing a phone number key
     * @return True if the key is in the HashTable, otherwise false
     */
    public boolean contains(String key)
    {
	//Determine which LinkedList to check based on the hash key of the passed key
	int list = Person.hashKey(key)%size;

	//Check each element of the correct LinkedList
	for(T p : theArray[list])
	    //If the key is found, return it
	    if(p.getKey().equals(key))
		return true;
	//Otherwise, return null
	return false;
    }

    /**
     * Put the elements of the HashTable into an ArrayList and return it sorted by phone number
     * @return Sorted ArrayList containing all elements of the HashTable
     */
    public ArrayList<T> sort()
    {
	//ArrayList to be returned
	ArrayList<T> theList = new ArrayList<>();
	//Comparator to allow the ArrayList to be sorted
	PersonComparator<T> compare = new PersonComparator<>();

	//Add each element of the HashTable to the ArrayList
	for(LinkedList<T> list : theArray)
	    for(T p : list)
		theList.add(p);

	//Sort the ArrayList
	Collections.sort(theList, compare);

	//Return the ArrayList
	return (ArrayList<T>)theList;
    }

    /**
     * Generate the next 4k+3 prime number larger than the passed int
     * @param n Number to find the next larger 4k+3 prime
     * @return The next 4k+3 prime number larger than the passed int
     */
    public static int fourKPlus3(int n) 
    {
	//Flag to represent whether the number is a 4k+3 prime
	boolean fkp3 = false;
	//Flag to represent whether the number is prime
	boolean aPrime = false;
	//Potential prime number
	int prime;
	//Largest divisor
	int highDivisor;
	//Current divisor
	int d;

	//Generate a number
	prime = (int)(n * (1.0 + (1.0 / 100.0)));  
	//If the prime number is even make it odd
	if(prime % 2 == 0)
	    prime = prime +1;
	//Repeat until the number is a 4k+3 prime
	while(fkp3 == false)
	{  
	    //Repeat until the number is prime
	    while(aPrime == false)
	    {  
		//Determine the highest divisor for the number
		highDivisor = (int)(Math.sqrt(prime) + 0.5);
		//Starting with the highest divisor try to divide prime by the divisor
		for(d = highDivisor; d > 1; d--)
		{  
		    //If prime is evenly divisible by the divisor it is not prime
		    if(prime % d == 0)
			break;
		}
		//If a prime is not found, increment prime by 2
		if(d != 1)
		    prime = prime + 2;
		//Otherwise set the prime flag to true
		else
		    aPrime = true;
	    }
	    //If the number is prime, check if it is a 4k+3 prime and set the flag to true if it is
	    if((prime - 3) % 4 == 0)
		fkp3 = true;
	    //Otherwise increment prime by 2 and set the prime flag to false
	    else
	    {
		prime = prime + 2;
		aPrime = false;
	    }
	}
	//Return the prime number one the prime and 4k+3 flags are true
	return prime;
    }
}