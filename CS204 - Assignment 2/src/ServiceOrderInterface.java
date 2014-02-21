/**
 * Interface for Class which contains all the information for a Service Order
 * 
 * Used for Assignment 2 - Fall 2012
 * 
 * @author Professor Myers
 * 
 */
public interface ServiceOrderInterface {

	/**
	 * Sets the orderNumber for the Service Order
	 * @param orderNumber Service Order Number
	 */
	public void setOrderNum(int orderNumber);
    
	/**
	 * Sets the year of the Car to be serviced
	 * @param year year of Car to be serviced
	 */
    public void setYear(int year);
    
    /**
     * Sets the name of the owner of the Car to be serviced
     * @param ownerName name of owner of Car to be serviced (last, first)
     */
    public void setOwner(String ownerName);

    /**
     * Sets the make of the Car to be serviced
     * @param make make of Car to be serviced
     */
    public void setMake(String make);
    
    /**
     * Sets the model of the Car to be serviced
     * @param model model of Car to be serviced
     */
    public void setModel(String model);
    
    /**
     * Retrieves the name of the owner of the Car to be serviced (last, first)
     * @return name of owner of Car to be serviced
     */
      public String getOwner();
      
      /**
       * Retrieves the make of the Car to be serviced
       * @return make of Car to be serviced
       */
      public String getMake();
      
      /**
       * Retrieves the model of the Car to be serviced
       * @return model of Car to be serviced
       */
      public String getModel();
      

      /**
       * Retrieves the service order number of the Car to be serviced
       * @return service order number of Car to be serviced
       */
      public int getOrderNum();
      
      /**
       * Retrieves the year of the Car to be serviced
       * @return year of Car to be serviced
       */
      public int getYear();
      
      /**
       * Retrieves the make, model and year of the Car to be serviced
       * @return make, model and year of Car to be serviced
       */
      public String getMakeModelYear();
      
	/**
	 * Determines if one Service Order is equal to another by comparing order number
	 * @param otherServiceOrder Service Order to check if equal to
	 * @return true if the two Service Orders are equal, false if they are not equal
	 */
    public boolean equals(ServiceOrderInterface otherServiceOrder);
    
    /**
     * Determines if one Service Order is greater than, less than or equal to another Service Order based
     * on the key
     * @param otherServiceOrder service order to compare against
     * @param key key to compare on.  1 = service order number, 2 = owner name (last, first), 3 = make,model,year of car
     * @return <0 if less than other Service Order, 0 if equal to other Service Order, >0 if greater than
     * other Service Order
     */
    public int compareTo(ServiceOrderInterface otherServiceOrder, int key);
    

}
