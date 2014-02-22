
public class ServiceOrder implements ServiceOrderInterface
{
	
	private int order, year;
	private String owner, make, model;
	
	public ServiceOrder()
	{
		setOrderNum(0);
		setOwner("");
		setMake("");
		setModel("");
		setYear(0);
	}
	
	public ServiceOrder(int or, String ow, String ma, String mo, int ye)
	{
		setOrderNum(or);
		setOwner(ow);
		setMake(ma);
		setModel(mo);
		setYear(ye);
	}
	
	public void setOrderNum(int o)
	{
		order = o;
	}

	public void setYear(int y)
	{
		year = y;
	}

	public void setOwner(String o)
	{
		owner = o;
	}

	public void setMake(String m)
	{
		make = m;
	}

	public void setModel(String m)
	{
		model = m;
	}

	public String getOwner()
	{
		return owner;
	}

	public String getMake()
	{
		return make;
	}

	public String getModel()
	{
		return model;
	}

	public int getOrderNum()
	{
		return order;
	}

	public int getYear()
	{
		return year;
	}

	public String getMakeModelYear()
	{
		return make + " " + model + " " + year;
	}

	public boolean equals(ServiceOrderInterface other)
	{
		if(getOrderNum() == other.getOrderNum())
			return true;
		return false;
	}

	public int compareTo(ServiceOrderInterface other, int key)
	{
		switch(key)
		{
			case 1:
				if(getOrderNum() < other.getOrderNum())
					return -1;
				else if(getOrderNum() > other.getOrderNum())
					return 1;
				else return 0;
			case 2:
				return getOwner().compareTo(other.getOwner());
			case 3:
				return getMakeModelYear().compareTo(other.getMakeModelYear());
			default:
				return 0;
		}
	}
	
	public String toString()
	{
		return "" + getOrderNum() + " " + getOwner() + " " + getMakeModelYear();
	}
}