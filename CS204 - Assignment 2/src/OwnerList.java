import java.util.*;

/**
 * A LinkedList that holds objects that implement ServiceOrderInterface
 * As ServiceOrderInterface objects are added to the LinkedList they are
 * sorted in order based on the owner name
 * @author Adam Holt
 * @date 2/23/14
 * @class CS204
 * @time 12:00 MW
 */
public class OwnerList extends LinkedList<ServiceOrderInterface>
{

	//Default serialized ID
	private static final long serialVersionUID = 1L;

	/**
	 * Adds new objects to the LinkedList - overrides super class method
	 * @param order An instance of a ServiceOrderInterface
	 * @return true if successfully added, otherwise false
	 */
	public boolean add(ServiceOrderInterface order)
	{
		//ListIterator to scan the list
		ListIterator<ServiceOrderInterface> i = listIterator();
		
		//Repeat as long as the list has more nodes
		while(i.hasNext())
		{
			//Compare the passed object to the next object in the list based on the owner name
			//If the order number of the passed object is less than the next object it should be added at that location
			if(order.compareTo(i.next(), 2) < 0)
			{
				//Return to the previous node since the next() call advanced past the correct location
				i.previous();
				//Add the object at the current location
				i.add(order);
				//Return true to indicate success
				return true;
			}
		}
		
		//If the loop completes and the object has not been added, it is large than the others in the list
		//Add the object at the end of the list
		i.add(order);
		//Return true to indicate success
		return true;
	}
}