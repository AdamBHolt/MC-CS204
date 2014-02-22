import java.util.*;

public class ServiceOrderManager implements ServiceOrderManagerInterface
{

	private OrderList orderList;
	private OwnerList ownerList;
	private MakeList makeList;
	
	public ServiceOrderManager()
	{
		orderList = new OrderList();
		ownerList = new OwnerList();
		makeList = new MakeList();
	}
	
	public boolean startService(int orderNum, String owner, String make,
			String model, int year) throws ServiceOrderInUseException
	{
		
		for(ServiceOrderInterface o : orderList)
			if(o.equals(new ServiceOrder(orderNum, owner, make, model, year)))
				throw new ServiceOrderInUseException();
		
		ServiceOrder order = new ServiceOrder(orderNum, owner, make, model, year);
		orderList.add(order);
		ownerList.add(order);
		makeList.add(order);
			
		return true;
	}
	
	public boolean startService(ServiceOrderInterface order)
			throws ServiceOrderInUseException
	{
		for(ServiceOrderInterface o : orderList)
			if(o.equals(order))
				throw new ServiceOrderInUseException();
		
		orderList.add(order);
		ownerList.add(order);
		makeList.add(order);
		
		return true;
	}

	public boolean finishService(int orderNum)
			throws ServiceOrderNotFoundException
	{
		for(ServiceOrderInterface o : orderList)
			if(o.equals(orderNum))
			{
				orderList.remove(o);
				ownerList.remove(o);
				makeList.remove(o);
				return true;
			}
		
		throw new ServiceOrderNotFoundException();
	}

	public String[][] listByKeyTable(int key)
	{
		
		String[][] returnString = new String[orderList.size()][3];
		int i = 0;
		
		switch(key)
		{
			case 1:
				for(ServiceOrderInterface order : orderList)
				{
					returnString[i][0] = order.getOwner();
					returnString[i][1] = String.valueOf(order.getOrderNum());
					returnString[i++][2] = order.getMakeModelYear();
				}
				break;
			case 2:
				for(ServiceOrderInterface order : ownerList)
				{
					returnString[i][0] = order.getOwner();
					returnString[i][1] = String.valueOf(order.getOrderNum());
					returnString[i++][2] = order.getMakeModelYear();	
				}
				break;
			case 3:
				for(ServiceOrderInterface order : makeList)
				{
					returnString[i][0] = order.getOwner();
					returnString[i][1] = String.valueOf(order.getOrderNum());
					returnString[i++][2] = order.getMakeModelYear();
				}
				break;
			default:
		}
		
		return returnString;
	}

	public Vector<String> listByKeyVector(int key)
	{
		Vector<String> returnVector = new Vector<>();

		switch(key)
		{
			case 1:
				for(ServiceOrderInterface order : orderList)
				{
					returnVector.add(order.getOwner());
					returnVector.add(String.valueOf(order.getOrderNum()));
					returnVector.add(order.getMakeModelYear());
				}
				break;
			case 2:
				for(ServiceOrderInterface order : ownerList)
				{
					returnVector.add(order.getOwner());
					returnVector.add(String.valueOf(order.getOrderNum()));
					returnVector.add(order.getMakeModelYear());	
				}
				break;
			case 3:
				for(ServiceOrderInterface order : makeList)
				{
					returnVector.add(order.getOwner());
					returnVector.add(String.valueOf(order.getOrderNum()));
					returnVector.add(order.getMakeModelYear());
				}
				break;
			default:
		}

		return returnVector;
	}

	public String outputFileFormat()
	{
		String returnString = "";
		for(ServiceOrderInterface order : orderList)
			returnString += order + "\n";
		return returnString;
	}
}