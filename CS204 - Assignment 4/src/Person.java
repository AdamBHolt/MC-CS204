import java.util.*;

public class Person implements PersonInterface
{

	private String fName;
	private String lName;
	private String phone;
	private String address;
	private static final int p1 = 23;
	private static final int p2 = 31;

	public Person()
	{
	    setFname("");
	    setLname("");
	    setPhone("");
	    setAddress("");
	}
	
	public Person(String f, String l, String p, String a)
	{
	    setFname(f);
	    setLname(l);
	    setPhone(p);
	    setAddress(a);
	}
	
	public void setFname(String f)
	{
	    fName = f;
	}
	
	public void setLname(String l)
	{
	    lName = l;
	}
	
	public void setPhone(String p)
	{
	    phone = p;
	}
	
	public void setAddress(String a)
	{
	    address = a;
	}
	
	public String getFname() 
	{
		return fName;
	}

	public String getLname() 
	{
		return lName;
	}

	public String getPhone() 
	{
		return phone;
	}

	public String getAddress() 
	{
		return address;
	}

	public static int hashKey(String key)
	{
	    int areaCode, exchangeCode, extensionCode;
	    StringTokenizer t = new StringTokenizer(key, "()-");
	    areaCode = Integer.parseInt(t.nextToken());
	    exchangeCode = Integer.parseInt(t.nextToken());
	    extensionCode = Integer.parseInt(t.nextToken());
	    
	    return Math.abs((p1 * (areaCode + p2 * exchangeCode) + extensionCode));
	}
	
	public static boolean isValidKey(String s)
	{
	    for(int i=0; i<s.length()-1; i++)
	    {
		if(i%4==0)
		{
		    if(s.charAt(i) != '(' && s.charAt(i) != ')' && s.charAt(i) != '-')
			return false;
		}
		else
		    if(s.charAt(i)<0 && s.charAt(i)>9)
			return false;
	    }
	    return true;
	}
	
	public int hashCode()
	{
	    return hashKey(getPhone());
	}
	
	public boolean equals(PersonInterface p) 
	{
	    return getPhone().equals(p.getPhone());
	}

	public String getKey() {
		return getPhone();
	}
	
	public String toString()
	{
	    return getLname() + ", " + getFname();
	}
}