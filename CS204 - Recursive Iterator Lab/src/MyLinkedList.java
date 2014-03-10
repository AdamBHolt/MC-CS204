import java.util.*;

/**
 * This class extends LinkedList<String> and contains a method to find the largest String in the list recursively
 * @author Adam Holt
 * @date 03/09/14
 * @class CS204
 * @time 12:00 MW
 */
public class MyLinkedList extends LinkedList<String>
{
	//default serialized ID
	private static final long serialVersionUID = 1L;
	//Largest String in the list
	private String largestString;
	//Current String being compared
	private String currentString;
	//Iterator to traverse the list
	ListIterator<String> i;
	//Counter to set the beginning location of the ListIterator
	int j;
	
	/*
	 * Default constructor
	 */
	public MyLinkedList()
	{
		//Call super constructor
		super();
		//Set the largest String to empty
		largestString = "";
		//Set the current String to empty
		currentString = "";
		//The first ListIterator is set to search at index 0
		j=0;
	}

	/**
	 * Find the value of the largest String in the list
	 * @return The largest String in the list
	 */
	public String findLargest()
	{
		//Instantiate the ListIsterator at the next index in the list
		i = listIterator(j++);
		
		//Base case
		//If there are no more elements in the list return largestString
		if(!i.hasNext())
			return largestString;
		
		//Recursive case
		//If there are more elements in the list
		else
		{
			//Set currentString to the value of the next String in the list
			currentString = i.next();
			
			//Compare the String values of currentString and largestString
			//If largestString is smaller than currentString set largestString to equal currentString
			if(largestString.compareTo(currentString)<0)
				largestString = currentString;
			//Call findLargest recursively to compare the next element in the list
			return findLargest();
		}
	}
}