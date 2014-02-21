import java.util.Vector;


public class ServiceOrderManager implements ServiceOrderManagerInterface
{

	@Override
	public boolean startService(int orderNum, String owner, String make,
			String model, int year) throws ServiceOrderInUseException
	{
		// TODO Auto-generated method stub
		return false;
	}  
	@Override
	public boolean startService(ServiceOrderInterface order)
			throws ServiceOrderInUseException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishService(int orderNum)
			throws ServiceOrderNotFoundException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[][] listByKeyTable(int key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<String> listByKeyVector(int key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String outputFileFormat()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
