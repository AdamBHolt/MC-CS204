public class Document
{
	private String name;
	private DocumentPriority priority;
	
	public Document()
	{
		setName("");
		setPriority(DocumentPriority.Normal);
	}
	
	public Document(String n, DocumentPriority p)
	{
		setName(n);
		setPriority(p);
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setPriority(DocumentPriority p)
	{
		priority = p;
	}
	
	public String getName()
	{
		return name;
	}
	
	public DocumentPriority getPriority()
	{
		return priority;
	}
	
	public String toString()
	{
		return name + " " + priority;
	}
}