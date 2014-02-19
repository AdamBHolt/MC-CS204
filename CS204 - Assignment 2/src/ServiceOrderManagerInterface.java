import java.util.Vector;


/**
 * This class that holds all the service order objects in the ordered linked-lists that 
 * keep the objects ordered based on a particular key. This class adds to the data structures 
 * (Start Service) and deletes from the data structures (Finish Service).  
 * It also returns the contents of the ordered linked lists.  
 * 
 * Used for Assignment 2 Fall 2012
 * 
 * @author Professor Myers
 *
 */
public interface ServiceOrderManagerInterface {
	

	/**
	 * Creates a Service Order object and adds it to the ordered linked lists
	 * @param orderNum Service Order Number
	 * @param owner name of owner of car to be serviced
	 * @param make make of car to be serviced
	 * @param model model of car to be serviced
	 * @param year year of car to be serviced
	 * @return true if the startService was successful
	 * @throws ServiceOrderInUseException if Service Order Number is already in use
	 */
	public boolean startService(int orderNum, String owner, String make, String model, int year) throws ServiceOrderInUseException;
	
	/**
	 * Adds a Service Order to the ordered linked lists.  This is for testing purposes.  
	 * Not intended to be used by the user
	 * @param order a Service Order to be added to the ordered linked lists.
	 * @return true if the startService was successful
	 * @throws ServiceOrderInUseException if Service Order Number is already in use
	 */
	public boolean startService(ServiceOrderInterface order) throws ServiceOrderInUseException;
	
	/**
	 * Removes the Service Order from the ordered linked lists using the service order number
	 * @param orderNum Service Order Number
	 * @return true if the finishService is successful
	 * @throws ServiceOrderNotFoundException if the Service Order Number is not found
	 */
	public boolean finishService(int orderNum) throws ServiceOrderNotFoundException;
	

	/**
	 * Returns a two dimensional array of Strings.  This can be used to populate a JTable
	 * @param key, how service orders are to be ordered 1 = service order number, 2 = owner name (last, first), 
	 * 3 = make,model,year of car
	 * @return a two dimensional array of Strings to populate a JTable
	 */
	public String[][] listByKeyTable(int key);
	
	/**
	 * Returns a Vector of Strings.  This can be used to populate a JList
	 * @param key how service orders are to be ordered 1 = service order number, 2 = owner name (last, first), 
	 * 3 = make,model,year of car
	 * @return a Vector of Strings to populate a JList
	 */
	public Vector<String> listByKeyVector(int key);
	
	/**
	 * Returns a string with all the current Service Orders in the following format:
	 * 
	 *  Line	Information
	 *  1		Order #  (integer)
	 *  2		Name (last name, first name) (String)
	 *  3		Make (String)
	 *  4		Model (String)
	 *  5		Year (String)
	 *  ** No blank lines between Service Orders
	 *  The orders are placed in the string in order by Service Order Number, i.e. 
	 *  order# 1001 is before order# 3208
	 * @return a String that can be used with a PrintWriter object to write to a file.
	 */
	public String outputFileFormat();


}
