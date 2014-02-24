/**
 * This class contains the information needed to store service orders for an auto shop
 * @author Adam Holt
 * @date 2/23/14
 * @class CS204
 * @time 12:00 MW
 */
public class ServiceOrder implements ServiceOrderInterface
{
	//Class member declarations
	private int order;			//Order number
	private String owner;		//Name of the car's owner
	private String make;		//Make of the car
	private String model;		//Model of the car
	private int year; 			//The car's year of manufacture
	
	/**
	 * Default constructor - set all values to defaults
	 */
	public ServiceOrder()
	{
		setOrderNum(0);
		setOwner("");
		setMake("");
		setModel("");
		setYear(0);
	}
	
	/**
	 * Constructor - takes parameters to set each of the fields
	 * @param or Order number
	 * @param ow Owner name
	 * @param ma Make of car
	 * @param mo Model of car
	 * @param ye Year of car
	 */
	public ServiceOrder(int or, String ow, String ma, String mo, int ye)
	{
		setOrderNum(or);
		setOwner(ow);
		setMake(ma);
		setModel(mo);
		setYear(ye);
	}
	
	/**
	 * Set the order number
	 * @param o Order number
	 */
	public void setOrderNum(int o)
	{
		order = o;
	}

	/**
	 * Set the year of the car
	 * @param y Year of the car
	 */
	public void setYear(int y)
	{
		year = y;
	}

	/**
	 * Set the owner's name
	 * @param o Owner's name
	 */
	public void setOwner(String o)
	{
		owner = o;
	}

	/**
	 * Set the make of the car
	 * @param Make of the car
	 */
	public void setMake(String m)
	{
		make = m;
	}

	/**
	 * Set the model of the car
	 * @param Model of the car
	 */
	public void setModel(String m)
	{
		model = m;
	}

	/**
	 * Get the owner's name
	 * @return Owner's name
	 */
	public String getOwner()
	{
		return owner;
	}

	/**
	 * Get the make of the car
	 * @return Make of the car
	 */
	public String getMake()
	{
		return make;
	}

	/**
	 * Get the model of the car
	 * @return Model of the car
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * Get the order number
	 * @return Order number
	 */
	public int getOrderNum()
	{
		return order;
	}
	
	/**
	 * Get the year of the car
	 * @return Year of the car
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Get the make, model, and year of the car
	 * @return Make, model, and year of the car
	 */
	public String getMakeModelYear()
	{
		//Concatenate and return the make, model, and year of the car
		return getMake() + " " + getModel() + " " + getYear();
	}

	/**
	 * Determines whether a ServiceOrderInterface object is equal to this ServiceOrderInterface object by comparing the order number
	 * @param other A ServiceOrderInterface object
	 * @return true if the passed ServiceOrderInterface has the same order number as the current ServiceOrderInterface
	 */
	public boolean equals(ServiceOrderInterface other)
	{
		//If the order numbe rof the passed object is equal to the current order number, return true
		if(getOrderNum() == other.getOrderNum())
			return true;
		//Otherwise, return false
		return false;
	}

	/**
	 * Compares a ServiceOrderInterface object with this ServiceOrderInterface object
	 * @param other A ServiceOrderInterface object
	 * @param key An int representing the field to compare in the objects
	 * @return <0 if the passed object is less than this object, >0 if the passed object is greater than this object, 0 if the objects are equal
	 */
	public int compareTo(ServiceOrderInterface other, int key)
	{
		//Switch based on the key
		switch(key)
		{
			//If the key is 1, compare based on the relative values of the order number
			case 1:
				if(getOrderNum() < other.getOrderNum())
					return -1;
				else if(getOrderNum() > other.getOrderNum())
					return 1;
				else return 0;
			//If the key is 2, compare based on the String compareTo() method of the owner name
			case 2:
				return getOwner().compareTo(other.getOwner());
			//If the key is 3, compare based on the String compareTo() method of the make, model, and year
			case 3:
				return getMakeModelYear().compareTo(other.getMakeModelYear());
			default:
				return 0;
		}
	}
	
	/**
	 * Get a String representation of the service order
	 * @return a String representation of the service order
	 */
	public String toString()
	{
		//Concatenate and return the fields of the order
		return "" + getOrderNum() + " " + getOwner() + " " + getMakeModelYear();
	}
}