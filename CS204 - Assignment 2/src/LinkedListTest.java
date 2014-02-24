import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the add method of three types of list that extend LinkedList
 * @author Adam Holt
 * @date 2/23/14
 * @class CS204
 * @time 12:00 MW
 */
public class LinkedListTest
{
	//LinkedLists to test
	private OrderList orderList;
	private OwnerList ownerList;
	private MakeList makeList;
	ServiceOrder order1, order2, order3;
	
	@Before
	public void setUp()
	{
		orderList = new OrderList();
		ownerList = new OwnerList();
		makeList = new MakeList();	
		order1 = new ServiceOrder(1001, "Prefect, Ford", "Ford", "Prefect", 1942);
		order2 = new ServiceOrder(1002, "Dent, Arthur", "Toyota", "Camry", 1990);
		order3 = new ServiceOrder(1003, "Beeblebrox, Zaphod", "Lexus", "Heart of Gold", 2014);
	}

	@After
	public void tearDown()
	{
		orderList = null;
		ownerList = null;
		makeList = null;
		order1 = order2 = order3 = null;
	}

	@Test
	public void orderListTest()
	{
		//Add the orders to the OrderLsit and check that they are being sorted correctly
		orderList.add(order3);
		assertEquals(String.valueOf(orderList.get(0)), "1003 Beeblebrox, Zaphod Lexus Heart of Gold 2014");
		orderList.add(order2);
		assertEquals(String.valueOf(orderList.get(0)), "1002 Dent, Arthur Toyota Camry 1990");
		orderList.add(order1);
		assertEquals(String.valueOf(orderList.get(0)), "1001 Prefect, Ford Ford Prefect 1942");
	}
	
	@Test
	public void ownerListTest()
	{
		//Add the orders to the OrderLsit and check that they are being sorted correctly
		ownerList.add(order3);
		assertEquals(String.valueOf(ownerList.get(0)), "1003 Beeblebrox, Zaphod Lexus Heart of Gold 2014");
		ownerList.add(order2);
		assertEquals(String.valueOf(ownerList.get(1)), "1002 Dent, Arthur Toyota Camry 1990");
		ownerList.add(order1);
		assertEquals(String.valueOf(ownerList.get(2)), "1001 Prefect, Ford Ford Prefect 1942");
	}
	
	@Test
	public void makeListTest()
	{
		//Add the orders to the OrderLsit and check that they are being sorted correctly
		makeList.add(order3);
		assertEquals(String.valueOf(makeList.get(0)), "1003 Beeblebrox, Zaphod Lexus Heart of Gold 2014");
		makeList.add(order2);
		assertEquals(String.valueOf(makeList.get(1)), "1002 Dent, Arthur Toyota Camry 1990");
		makeList.add(order1);
		assertEquals(String.valueOf(makeList.get(0)), "1001 Prefect, Ford Ford Prefect 1942");
	}
}