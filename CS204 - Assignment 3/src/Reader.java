public class Reader
{
	private ReaderStatus status;
	private ReaderActivityStatus activity;
	
	public Reader()
	{
		setStatus(ReaderStatus.Absent);
		setActivity(ReaderActivityStatus.None);
	}
	
	public Reader(ReaderStatus s, ReaderActivityStatus a)
	{
		setStatus(s);
		setActivity(a);
	}
	
	public void setStatus(ReaderStatus s)
	{
		status = s;
	}
	
	public void setActivity(ReaderActivityStatus a)
	{
		activity = a;
	}
	
	public ReaderStatus getStatus()
	{
		return status;
	}
	
	public ReaderActivityStatus getActivity()
	{
		return activity;
	}
	
	public String toString()
	{
		return status + " " + activity;
	}
}
