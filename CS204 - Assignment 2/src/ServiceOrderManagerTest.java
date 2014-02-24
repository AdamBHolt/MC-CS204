import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for Service Order Manager
 * @author Professor Myers
 *
 */

public class ServiceOrderManagerTest {

	public static ServiceOrderManagerInterface serviceOrderManager;
	public static ServiceOrderManagerInterface studentManager;
	
	@Before
	public void setUp() {
		try {
			serviceOrderManager = new ServiceOrderManager();
			serviceOrderManager.startService(1001, "Melville, George", "Mercedes Benz", "E320", 2002);
			serviceOrderManager.startService(1002, "Rowling, June", "Ford", "Mustang", 2007);
			serviceOrderManager.startService(1003, "Milne, Randall", "Toyota", "Camry", 1998);
			serviceOrderManager.startService(1005, "Patterson, Jimmy", "Chrysler", "Town and Country", 2004);
			serviceOrderManager.startService(1006, "Dickson, Richard", "Chrysler", "Sebring", 2002);
			serviceOrderManager.startService(1007, "Carle, Erica", "Toyota", "Sequoia", 2003);
			serviceOrderManager.startService(1010, "Wiz, Eric", "Toyota", "Celica", 2007);
		} catch (ServiceOrderInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			studentManager = new ServiceOrderManager();
			studentManager.startService(1001, "Prefect, Ford", "Ford", "Prefect", 1942);
			studentManager.startService(1002, "Dent, Arthur", "Toyota", "Camry", 1990);
			studentManager.startService(1003, "Beeblebrox, Zaphod", "Lexus", "Heart of Gold", 2014);
			studentManager.startService(1006, "McMillian, Trisha", "Honda", "Civic", 2006);
			studentManager.startService(1007, "Android, Marvin", "Dodge", "Dart", 1975);
			
		} catch (ServiceOrderInUseException ex)
		{
			ex.printStackTrace();
		}
		
		
	}
		
	@After
	public void tearDown()
	{
		serviceOrderManager = null;
	}

	@Test
	//Test the startService(int orderNum, String owner, String make, String model, int year) method
	public void testStartServiceForUser() {
		Vector<String> result = null;
		result = serviceOrderManager.listByKeyVector(1); // ordered by ordernumber
		// Order Number 1007 is at index 5
		assertEquals(result.elementAt(5),"1007 Carle, Erica Toyota Sequoia 2003");
		// Order Number 1010 is at index 6
		assertEquals(result.elementAt(6),"1010 Wiz, Eric Toyota Celica 2007");
		// Add order number 1008
		try {
			serviceOrderManager.startService(1008, "Skywalker, Luke", "Galatica", "Speeder", 2520);
		} catch (ServiceOrderInUseException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a ServiceOrderInUseException");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
		
		result = serviceOrderManager.listByKeyVector(1);
		// Now 1008 should be at index 6 and 1010 should be at index 7
		assertEquals(result.elementAt(5),"1007 Carle, Erica Toyota Sequoia 2003");
		assertEquals(result.elementAt(6),"1008 Skywalker, Luke Galatica Speeder 2520");
		assertEquals(result.elementAt(7),"1010 Wiz, Eric Toyota Celica 2007");
		//Now try adding a service order that is already being used - 1007
		try {
			serviceOrderManager.startService(1007, "Jones, Johnny", "Kia", "Sorento", 2010);
			fail("This statement should have thrown a ServiceOrderInUseException");
		} catch (ServiceOrderInUseException e) {
			// TODO Auto-generated catch block
			System.out.println("Service Order In Use Exeception");
		} catch (Exception e) {
			fail("This statement should have thrown a ServiceOrderInUseException");
		}
	}

	@Test
	//Test the startService(int orderNum, String owner, String make, String model, int year) method
	public void testStartServiceSTUDENT() {
		Vector<String> result = null;
		result = studentManager.listByKeyVector(1);
		assertEquals(result.elementAt(1),"1002 Dent, Arthur Toyota Camry 1990");
		assertEquals(result.elementAt(3),"1006 McMillian, Trisha Honda Civic 2006");
		
		//Try to start new orders
		try {
			studentManager.startService(1005, "Though, Deep", "Magrathean", "Planet", 1900);
			result = studentManager.listByKeyVector(1);
			assertEquals(result.elementAt(1),"1002 Dent, Arthur Toyota Camry 1990");
			assertEquals(result.elementAt(3),"1005 Though, Deep Magrathean Planet 1900");
		} catch (ServiceOrderInUseException ex) {
			fail("This statement should not have thrown a ServiceOrderInUseException");
		} catch (Exception ex) {
			fail("This statement should have thrown an Exception");
		}
		
		try {
			studentManager.startService(1004, "Way, Mille", "Doomed", "Restaurant", 3000);
			result = studentManager.listByKeyVector(1);
			assertEquals(result.elementAt(1),"1002 Dent, Arthur Toyota Camry 1990");
			assertEquals(result.elementAt(3),"1004 Way, Mille Doomed Restaurant 3000");
		} catch (ServiceOrderInUseException ex) {
			fail("This statement should not have thrown a ServiceOrderInUseException");
		} catch (Exception ex) {
			fail("This statement should have thrown an Exception");
		}
		
		//Try to start new orders with previously used order numbers
		try {
			studentManager.startService(1005, "Though, Deep", "Magrathean", "Planet", 1900);
			fail("This statement should have thrown a ServiceOrderInUseException");
		} catch (ServiceOrderInUseException ex) {
			System.out.println("Service Order In Use Exeception");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
		
		try {
			studentManager.startService(1004, "Way, Mille", "Doomed", "Restaurant", 3000);
			fail("This statement should have thrown a ServiceOrderInUseException");
		} catch (ServiceOrderInUseException ex) {
			System.out.println("Service Order In Use Exeception");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
	}
	
	@Test
	// Test the startService(ServiceOrderInterface order) method
	public void testStartServiceForTesting() {
		ServiceOrder order = new ServiceOrder(1009, "Jones, Johnny", "Kia", "Sorento", 2010);
		Vector<String> result = null;
		result = serviceOrderManager.listByKeyVector(1); // ordered by ordernumber
		assertEquals(result.elementAt(5),"1007 Carle, Erica Toyota Sequoia 2003");
		assertEquals(result.elementAt(6),"1010 Wiz, Eric Toyota Celica 2007");
		try {
			serviceOrderManager.startService(order);
			result = serviceOrderManager.listByKeyVector(1); // ordered by ordernumber
			assertEquals(result.elementAt(6),"1009 Jones, Johnny Kia Sorento 2010");
			assertEquals(result.elementAt(7),"1010 Wiz, Eric Toyota Celica 2007");
		} catch (ServiceOrderInUseException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a ServiceOrderInUseException");
		} catch (Exception exception){
			fail("This statement should not have thrown an Exception");
		}
	
		//Now try adding a service order that is already being used - 1007
		order = new ServiceOrder(1007, "Jones, Johnny", "Kia", "Sorento", 2010);
		try {
			serviceOrderManager.startService(order);
			fail("This statement should have thrown a ServiceOrderInUseException");
		} catch (ServiceOrderInUseException e) {
			// TODO Auto-generated catch block
			System.out.println("Service Order In Use Exeception");
		} catch (Exception exception){
			fail("This statement should have thrown a ServiceOrderInUseException");
		}
	}

	@Test
	public void testFinishService() {
		Vector<String> result = null;
		result = serviceOrderManager.listByKeyVector(1); // ordered by ordernumber
		assertEquals(result.elementAt(4),"1006 Dickson, Richard Chrysler Sebring 2002");
		assertEquals(result.elementAt(5),"1007 Carle, Erica Toyota Sequoia 2003");
		assertEquals(result.elementAt(6),"1010 Wiz, Eric Toyota Celica 2007");
		//Now finish service for 1007
		try {
			serviceOrderManager.finishService(1007);
			result = serviceOrderManager.listByKeyVector(1); // ordered by ordernumber
			// 1007 should no longer be in the list
			assertEquals(result.elementAt(4),"1006 Dickson, Richard Chrysler Sebring 2002");
			assertEquals(result.elementAt(5),"1010 Wiz, Eric Toyota Celica 2007");
		} catch (ServiceOrderNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a ServiceOrderNotFoundException");
		} catch (Exception e) {
			fail("This statement should not have thrown an Exception");
		}
		
		try {
			serviceOrderManager.finishService(1011);
			fail("This statement should have thrown a ServiceOrderNotFoundException");
		} catch (ServiceOrderNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Service Order Not Found Exception");
		} catch (Exception e){
			fail("This statement should have thrown a ServiceOrderNotFoundException");
		}
	}
	
	@Test
	public void testFinishServiceSTUDENT() {
		Vector<String> result = null;
		result = studentManager.listByKeyVector(1);
		assertEquals(result.elementAt(1),"1002 Dent, Arthur Toyota Camry 1990");
		assertEquals(result.elementAt(2),"1003 Beeblebrox, Zaphod Lexus Heart of Gold 2014");
		
		//Try to finish existing orders
		try {
			studentManager.finishService(1003);
			result = studentManager.listByKeyVector(1);
			assertEquals(result.elementAt(1),"1002 Dent, Arthur Toyota Camry 1990");
			assertEquals(result.elementAt(2),"1006 McMillian, Trisha Honda Civic 2006");
		} catch(ServiceOrderNotFoundException ex) {
			fail("This statement should not have thrown a ServiceOrderNotFoundException");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
		
		try {
			studentManager.finishService(1002);
			result = studentManager.listByKeyVector(1);
			assertEquals(result.elementAt(1),"1006 McMillian, Trisha Honda Civic 2006");
			assertEquals(result.elementAt(2),"1007 Android, Marvin Dodge Dart 1975");
		} catch(ServiceOrderNotFoundException ex) {
			fail("This statement should not have thrown a ServiceOrderNotFoundException");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
		
		//Try to finish previously finished orders
		try {
			studentManager.finishService(1003);
			fail("This statement should have thrown a ServiceOrderNotFoundException");
		} catch(ServiceOrderNotFoundException ex) {
			System.out.println("Service Order Not Found Exception");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
		
		try {
			studentManager.finishService(1002);
			fail("This statement should have thrown a ServiceOrderNotFoundException");
		} catch(ServiceOrderNotFoundException ex) {
			System.out.println("Service Order Not Found Exception");
		} catch (Exception e) {
			fail("This statement should have thrown an Exception");
		}
		
	}

	@Test
	public void testListByKeyTable() {
		String[][] result = null;
		result = serviceOrderManager.listByKeyTable(1); // ordered by ordernumber
		assertEquals(result[5][0],"1007");
		assertEquals(result[6][0],"1010");
		result = serviceOrderManager.listByKeyTable(2); // ordered by owner
		assertEquals(result[5][0],"Rowling, June");
		assertEquals(result[6][0],"Wiz, Eric");
		result = serviceOrderManager.listByKeyTable(3); // ordered by make, model, year
		assertEquals(result[5][0],"Toyota Celica 2007");
		assertEquals(result[6][0],"Toyota Sequoia 2003");
	}
	
	@Test
	public void testListByKeyTableSTUDENT(){
		String[][] result = null;
		//sorted by order
		result = studentManager.listByKeyTable(1); 
		assertEquals(result[2][0],"1003");
		assertEquals(result[4][0],"1007");
		//sorted by owner
		result = studentManager.listByKeyTable(2); 
		assertEquals(result[2][0],"Dent, Arthur");
		assertEquals(result[4][0],"Prefect, Ford");
		//sorted by make, model, year
		result = studentManager.listByKeyTable(3); 
		assertEquals(result[2][0],"Honda Civic 2006");
		assertEquals(result[4][0],"Toyota Camry 1990");
	}

	@Test
	public void testListByKeyVector() {
		Vector<String> result = null;
		result = serviceOrderManager.listByKeyVector(1); // ordered by ordernumber
		assertEquals(result.elementAt(5),"1007 Carle, Erica Toyota Sequoia 2003");
		assertEquals(result.elementAt(6),"1010 Wiz, Eric Toyota Celica 2007");
		result = serviceOrderManager.listByKeyVector(2); // ordered by owner
		assertEquals(result.elementAt(5),"Rowling, June 1002 Ford Mustang 2007");
		assertEquals(result.elementAt(6),"Wiz, Eric 1010 Toyota Celica 2007");
		result = serviceOrderManager.listByKeyVector(3); // ordered by make, model, year
		assertEquals(result.elementAt(5),"Toyota Celica 2007 1010 Wiz, Eric");
		assertEquals(result.elementAt(6),"Toyota Sequoia 2003 1007 Carle, Erica");
	}

	@Test
	public void testOutputFileFormat() {
		Scanner scan;
		String result = null;
		result = serviceOrderManager.outputFileFormat();
		scan = new Scanner(result);
		assertEquals(scan.nextLine().substring(0,4),"1001");
		scan.nextLine(); //owner
		scan.nextLine(); //make
		scan.nextLine(); //model
		scan.nextLine(); //year
		assertEquals(scan.nextLine().substring(0,4),"1002");
		
	}
	
}
