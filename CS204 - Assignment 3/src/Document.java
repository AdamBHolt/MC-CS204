/**
 * A class representing a document for a program that manages the order of documents based on their priority
 * @author Adam Holt
 * @date 3/09/14
 * @class CS204
 * @time 12:00 MW
 */
public class Document
{
	//Name of the Document
	private String name;
	//Priority of the Document
	private String priority;
	
	/**
	 * Default constructor
	 */
	public Document()
	{
		//Set the name to the default String
		setName("");
		//Set the priority to normal
		setPriority("normal");
	}
	
	/**
	 * Constructor that takes a name and priority as parameters
	 * @param n Name of the Document
	 * @param p Priority of the Document
	 */
	public Document(String n, String p)
	{
		//Set the name of the Document
		setName(n);
		//Set the priority of the Document
		setPriority(p);
	}
	
	/**
	 * Set the name of the Document
	 * @param n Name of the Document
	 */
	public void setName(String n)
	{
		//Set the name of the Document
		name = n;
	}
	
	/**
	 * Set the priority of the Document
	 * @param p Priority of the Document
	 */
	public void setPriority(String p)
	{
		//Set the priority of the Document
		priority = p;
	}
	
	/**
	 * Get the name of the Document
	 * @return Name of the Document
	 */
	public String getName()
	{
		//Return the name of the Document
		return name;
	}
	
	/**
	 * Get the priority of the Document
	 * @return Priority of the Document
	 */
	public String getPriority()
	{
		//Return the priority of the Document
		return priority;
	}
	
	/**
	 * Get a String representation of the Document
	 * @return String representation of the Document
	 */
	public String toString()
	{
		//Return the name of the Document and the priority with the first letter capitalized
		return getName() + " - " + getPriority().substring(0, 1).toUpperCase() + getPriority().substring(1);
	}
	
	/**
	 * Determine if two Documents are equal to each other
	 * @param otherDoc Document to be compared
	 * @return True if the Documents are the same, otherwise false
	 */
	public boolean equals(Document otherDoc)
	{
		//Compare the String representation of each object using the String equals() method
		return this.toString().equals(otherDoc.toString());
	}
}