import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class provides a hash table used to store Person objects in an address book utility
 * The hash table is implemented as an array of LinkedLists that contain PersonInterface objects
 * Uses buckets to store objects with the same hash code
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class HashTableTest
{
    private HashTable<PersonInterface> table;
    PersonInterface p1, p2, p3, p4, p5, p6;

    @Before
    public void setUp() throws Exception
    {
	table = new HashTable<>();

	p1 = new Person("Phillip", "Fry", "(201)555-5555", "NY");
	p2 = new Person("Turanga", "Leela", "(978)123-1234", "MA");
	p3 = new Person("Bender", "Rodriguez", "(303)565-4321", "CO");
	p4 = new Person("Amy", "Wong", "(323)487-2326", "CA");
	p5 = new Person("Hubert", "Farnsworth", "(240)420-0042", "MD");
	p6 = new Person("John", "Zoidberg", "(703)333-4444", "VA");

	table.add(p1);
	table.add(p2);
	table.add(p3);
	table.add(p4);
    }

    @After
    public void tearDown() throws Exception
    {
	table=null;
	p1 = p2 = p3 = p4 = p5 = p6 = null;
    }

    @Test
    public void testAdd()
    {
	//Test the HashTable add method
	assertEquals(true, table.contains(p1));
	assertEquals(true, table.contains(p2));
	assertEquals(true, table.contains(p3));
	assertEquals(true, table.contains(p4));
	assertEquals(false, table.contains(p5));
	assertEquals(false, table.contains(p6));

	table.add(p5);
	table.add(p6);

	assertEquals(true, table.contains(p1));
	assertEquals(true, table.contains(p2));
	assertEquals(true, table.contains(p3));
	assertEquals(true, table.contains(p4));
	assertEquals(true, table.contains(p5));
	assertEquals(true, table.contains(p6));

    }

    @Test
    public void testContainsT()
    {
	//Test the contains(T) method
	assertEquals(true, table.contains(p1));
	assertEquals(true, table.contains(p2));
	assertEquals(true, table.contains(p3));
	assertEquals(true, table.contains(p4));
	assertEquals(false, table.contains(p5));
	assertEquals(false, table.contains(p6));
    }

    @Test
    public void testGetValue()
    {
	//Test the getValue method
	assertEquals(p1, table.getValue(p1.getKey()));
	assertEquals(p2, table.getValue(p2.getKey()));
	assertEquals(p3, table.getValue(p3.getKey()));
	assertEquals(p4, table.getValue(p4.getKey()));
	assertEquals(null, table.getValue(p5.getKey()));
	assertEquals(null, table.getValue(p6.getKey()));
    }

    @Test
    public void testContainsString()
    {
	//Test the contains(String) method
	assertEquals(true, table.contains("(201)555-5555"));
	assertEquals(true, table.contains("(978)123-1234"));
	assertEquals(true, table.contains("(303)565-4321"));
	assertEquals(true, table.contains("(323)487-2326"));
	assertEquals(false, table.contains("(240)420-0042"));
	assertEquals(false, table.contains("(703)333-4444"));
    }

    @Test
    public void testSort()
    {
	//Test the sort method
	ArrayList<PersonInterface> theArray = table.sort();
	assertEquals(p1, theArray.get(0));
	assertEquals(p3, theArray.get(1));
	assertEquals(p4, theArray.get(2));
	assertEquals(p2, theArray.get(3));
    }

    @Test
    public void testFourKPlus3()
    {
	//Test the fourKPlus3 method
	assertEquals(11, HashTable.fourKPlus3(10));
	assertEquals(23, HashTable.fourKPlus3(20));
	assertEquals(31, HashTable.fourKPlus3(30));
    }
}