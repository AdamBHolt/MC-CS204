import java.util.*;

/**
 * This class creates 3 LinkedLists that contain ServiceOrderInterface objects
 * In order to send and receive information from a GUI to display, add, and delete orders
 * For an auto shop
 * Each LinkedLsit sorts the objects based on different criteria
 * @author Adam Holt
 * @date 2/23/14
 * @class CS204
 * @time 12:00 MW
 */
public class ServiceOrderManager implements ServiceOrderManagerInterface
{

	//The LinkedLists
	private OrderList orderList;	//Sorted based on order number
	private OwnerList ownerList;	//Sorted based on owner name
	private MakeList makeList;		//Sorted based on make, model, and year
	
	/**
	 * Default constructor
	 */
	public ServiceOrderManager()
	{
		//Instantiate the LinkedLists
		orderList = new OrderList();
		ownerList = new OwnerList();
		makeList = new MakeList();
	}
	
	/**
	 * Adds a new ServiceOrder object to the LinkedLists based on passed parameters
	 * @param orderNum The order number
	 * @param owner The name of the owner
	 * @param make The make of the car
	 * @param model The model of the car
	 * @param year The year of the car
	 * @return true if the add was successful, otherwise false
	 * @throws ServiceOrderInUseException
	 */
	public boolean startService(int orderNum, String owner, String make,
			String model, int year) throws ServiceOrderInUseException
	{
		//Create a new service order based on the parameters
		ServiceOrder order = new ServiceOrder(orderNum, owner, make, model, year);
		
		//Check each element already in the orderList
		for(ServiceOrderInterface o : orderList)
			//If the order is already in use throw a ServiceOrderInUseException
			if(o.equals(order))
				throw new ServiceOrderInUseException();
		
		//Return the results of the attempt to add the object to each list
		//If all three attempts are successful, the return value is true
		//If any fail, the result is false
		return orderList.add(order) && ownerList.add(order) && makeList.add(order);
	}
	
	/**
	 * Adds a new ServiceOrder object to the LinkedLists based on passed parameters
	 * @param order a ServiceOrderInterface object
	 * @return true if the add was successful, otherwise false
	 * @throws ServiceOrderInUseException
	 */
	public boolean startService(ServiceOrderInterface order)
			throws ServiceOrderInUseException
	{
		//Check each element already in the orderList
		for(ServiceOrderInterface o : orderList)
			//If the order is already in use throw a ServiceOrderInUseException
			if(o.equals(order))
				throw new ServiceOrderInUseException();
		
		//Return the results of the attempt to add the object to each list
		//If all three attempts are successful, the return value is true
		//If any fail, the result is false
		return orderList.add(order) && ownerList.add(order) && makeList.add(order);
	}

	/**
	 * Removes an order from the LinkedLists based on a passed order number
	 * @param orderNum The order number to be removed
	 * @return true if the removal was successful, otherwise false
	 * @throws ServiceOrderNotFoundException
	 */
	public boolean finishService(int orderNum)
			throws ServiceOrderNotFoundException
	{
		//Create a new default ServiceOrder object
		ServiceOrder order = new ServiceOrder();
		//Set the order number to the passed order number
		order.setOrderNum(orderNum);
		
		//Check each element already in the orderList
		for(ServiceOrderInterface o : orderList)
			//If the order number is found
			if(o.equals(order))
			{
				//Return the results of the attempt to remove the object from each list
				//If all three attempts are successful, the return value is true
				//If any fail, the result is false
				return orderList.remove(o) && ownerList.remove(o) && makeList.remove(o);
			}
		//If the order number is not found, throw a ServiceOrderNotFoundException
		throw new ServiceOrderNotFoundException();
	}

	/**
	 * Get a 2D array representation of the orders in the LinkedLists
	 * @param key An integer representation of the sort criteria to be returned
	 * @return A 2D array containing the individual String elements of the LinkedLsits
	 */
	public String[][] listByKeyTable(int key)
	{
		//2D array to be returned - the rows number is determined by the number of elements in the LinkedLists
		String[][] returnString = new String[orderList.size()][3];
		//Row index to search
		int i = 0;
		
		//Switch based on the sort key
		switch(key)
		{
			//Return the list sorted by order number
			case 1:
				//Scan each elements in the LinkedList
				for(ServiceOrderInterface order : orderList)
				{
					//Set the columns for the current row index and increment it to the next row
					returnString[i][0] = String.valueOf(order.getOrderNum());
					returnString[i][1] = order.getOwner();
					returnString[i++][2] = order.getMakeModelYear();
				}
				break;
			//Return the list sorted by owner name
			case 2:
				//Scan each elements in the LinkedList
				for(ServiceOrderInterface order : ownerList)
				{
					//Set the columns for the current row index and increment it to the next row
					returnString[i][0] = order.getOwner();
					returnString[i][1] = String.valueOf(order.getOrderNum());
					returnString[i++][2] = order.getMakeModelYear();	
				}
				break;
			//Return the list sorted by make, model, and year
			case 3:
				//Scan each elements in the LinkedList
				for(ServiceOrderInterface order : makeList)
				{
					//Set the columns for the current row index and increment it to the next row
					returnString[i][0] = order.getMakeModelYear();
					returnString[i][1] = String.valueOf(order.getOrderNum());
					returnString[i++][2] = order.getOwner();
					
				}
				break;
			default:
		}
		
		//Return the final 2D array
		return returnString;
	}

	/**
	 * Get aVector representation of the orders in the LinkedLists
	 * @param key An integer representation of the sort criteria to be returned
	 * @return An array containing String representing each object in the LinkedLists
	 */
	public Vector<String> listByKeyVector(int key)
	{
		//Vector to be returned
		Vector<String> returnVector = new Vector<>();

		//Switch based on the sort key
		switch(key)
		{
			case 1:
				//Scan each elements in the LinkedList
				for(ServiceOrderInterface order : orderList)
					//Add a String based on the sort order by order number
					returnVector.add(String.valueOf(order.getOrderNum()) + " " + order.getOwner() + " " + order.getMakeModelYear());
				break;
			case 2:
				//Scan each elements in the LinkedList
				for(ServiceOrderInterface order : ownerList)
					//Add a String based on the sort order by owner name
					returnVector.add(order.getOwner() + " " + String.valueOf(order.getOrderNum()) + " " + order.getMakeModelYear());
				break;
			case 3:
				//Scan each elements in the LinkedList
				for(ServiceOrderInterface order : makeList)
					//Add a String based on the sort order by make, model, and year
					returnVector.add(order.getMakeModelYear() + " " + String.valueOf(order.getOrderNum())+ " " + order.getOwner());
				break;
			default:
		}
		
		//Return the final vector
		return returnVector;
	}

	/**
	 * Creates a string representation of each order in the orderList that can be output to a file
	 * Which can be read back into the program
	 * @return String representation of the objects in the orderList
	 */
	public String outputFileFormat()
	{
		//String to be returned
		String returnString = "";
		
		//Scan each element in orderList
		for(ServiceOrderInterface order : orderList)
			//Add each field from the current object followed by a new line
			returnString += order.getOrderNum() + "\n" +
							order.getOwner() + "\n" +
							order.getMake() + "\n" +
							order.getModel() + "\n" +
							order.getYear() + "\n";
		//Return the final String
		return returnString;
	}
	
	/**
	 * Returns a String representation of the objects in the orderList
	 * @return String representation of the objects in the orderList
	 */
	public String toString()
	{
		//String to be returned
		String returnString = "";
		
		//Scan each element in orderList
		for(ServiceOrderInterface order : orderList)
			//Add the String representation of each element followed by a new line to the return string
			returnString += order + "\n";
		
		//Return the final String
		return returnString;
	}
}