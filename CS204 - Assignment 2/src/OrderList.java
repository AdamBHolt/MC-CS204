import java.util.*;

public class OrderList extends LinkedList<ServiceOrderInterface>
{

	public boolean add(ServiceOrderInterface order)
	{
		ListIterator<ServiceOrderInterface> i = listIterator();
		
		while(i.hasNext())
		{

			if(order.compareTo(i.next(), 1) < 0)
			{
				i.previous();
				i.add(order);
				return true;
			}
			
		}
		
		i.add(order);
		return true;
	}
}