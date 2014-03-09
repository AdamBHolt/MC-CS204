public class Document
{
	private String name;
	private String priority;
	
	public Document()
	{
		setName("");
		setPriority("normal");
	}
	
	public Document(String n, String p)
	{
		setName(n);
		setPriority(p);
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setPriority(String p)
	{
		priority = p;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPriority()
	{
		return priority;
	}
	
	public String toString()
	{
		return getName() + " - " + getPriority().substring(0, 1).toUpperCase() + getPriority().substring(1);
	}
}