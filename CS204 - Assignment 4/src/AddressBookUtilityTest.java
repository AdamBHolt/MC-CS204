import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AddressBookUtilityTest {
    private AddressBookUtility addressBook;
    private AddressBookUtility studentAddressBook;
    PersonInterface p1, p2, p3, p4, p5, p6;
    PersonInterface sp1, sp2, sp3, sp4, sp5, sp6;
    PrintWriter output;
    File newFile, outFile;

    @Before
    public void setUp() throws Exception {
	addressBook = new AddressBookUtility();
	p1 = new Person("John", "Smith", "(208)322-8822", "ID");
	p2 = new Person("Mary", "Arnold", "(240)944-3347", "VA");
	p3 = new Person("Eric", "Wiz", "(701)445-2333", "NY");
	p4 = new Person("Robert", "Kaznick", "(615)387-3935", "TN");
	p5 = new Person("Betty", "Hanson", "(714)273-3813", "CA");
	p6 = new Person("Steve", "Monty", "(801)553-8273", "UT");

	addressBook.add("John", "Smith", "(208)322-8822", "ID");
	addressBook.add("Mary", "Arnold", "(240)944-3347", "VA");
	addressBook.add("Eric", "Wiz", "(701)445-2333", "NY");
	addressBook.add("Robert", "Kaznick", "(615)387-3935", "TN");
	addressBook.add("Betty", "Hanson", "(714)273-3813", "CA");

	//STUDENT - create an additional address book object and add
	// Person objects that will be different from those above
	// use these in the STUDENT test below
	studentAddressBook = new AddressBookUtility();
	sp1 = new Person("Phillip", "Fry", "(201)555-5555", "NY");
	sp2 = new Person("Turanga", "Leela", "(978)123-1234", "MA");
	sp3 = new Person("Bender", "Rodriguez", "(303)565-4321", "CO");
	sp4 = new Person("Amy", "Wong", "(323)487-2326", "CA");
	sp5 = new Person("Hubert", "Farnsworth", "(240)420-0042", "MD");
	sp6 = new Person("John", "Zoidberg", "(703)333-4444", "VA");

	studentAddressBook.add("Phillip", "Fry", "(201)555-5555", "NY");
	studentAddressBook.add("Turanga", "Leela", "(978)123-1234", "MA");
	studentAddressBook.add("Bender", "Rodriguez", "(303)565-4321", "CO");
	studentAddressBook.add("Amy", "Wong", "(323)487-2326", "CA");
	studentAddressBook.add("Hubert", "Farnsworth", "(240)420-0042", "MD");

    }

    @After
    public void tearDown() throws Exception {
	addressBook = null;
	p1 = p2 = p3 = p4 = p5 = p6 = null;
	//STUDENT - teardown your address book objects and Person objects
	studentAddressBook = null;
	sp1 = sp2 = sp3 = sp4 = sp5 = sp6 = null;
    }

    @Test
    public void testContainsPersonInterface() {
	assertEquals(true, addressBook.contains(p1));
	assertEquals(true, addressBook.contains(p2));
	assertEquals(true, addressBook.contains(p3));
	assertEquals(true, addressBook.contains(p4));
	assertEquals(true, addressBook.contains(p5));
	assertEquals(false, addressBook.contains(p6));
    }

    @Test
    public void testContainsString() {
	try {
	    assertEquals(true, addressBook.contains("(208)322-8822"));
	    assertEquals(true, addressBook.contains("(240)944-3347"));
	    assertEquals(true, addressBook.contains("(701)445-2333"));
	    assertEquals(true, addressBook.contains("(615)387-3935"));
	    assertEquals(true, addressBook.contains("(714)273-3813"));
	    assertEquals(false, addressBook.contains("(801)553-8273"));
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    fail("This should not have raised an InvalidKeyException");
	}
	try {
	    addressBook.contains("801553-8273");
	    fail("This should have raised an InvalidKeyException ");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    // correct
	}
    }

    @Test
    public void testContainsStringSTUDENT() {
	try {
	    assertEquals(true, studentAddressBook.contains("(201)555-5555"));
	    assertEquals(true, studentAddressBook.contains("(978)123-1234"));
	    assertEquals(true, studentAddressBook.contains("(303)565-4321"));
	    assertEquals(true, studentAddressBook.contains("(323)487-2326"));
	    assertEquals(true, studentAddressBook.contains("(240)420-0042"));
	    assertEquals(false, studentAddressBook.contains("(703)333-4444"));
	} catch (InvalidKeyException e) {
	    fail("This should not have raised an InvalidKeyException");
	}
	try {
	    addressBook.contains("7033334444");
	    fail("This should have raised an InvalidKeyException ");
	} catch (InvalidKeyException e) {
	    // correct
	}
    }

    @Test
    public void testReverseLookup() {
	try {
	    assertEquals("Smith, John", addressBook.reverseLookup("(208)322-8822"));
	    assertEquals("Arnold, Mary", addressBook.reverseLookup("(240)944-3347"));
	    assertEquals("Wiz, Eric", addressBook.reverseLookup("(701)445-2333"));
	    assertEquals("Kaznick, Robert", addressBook.reverseLookup("(615)387-3935"));
	    assertEquals("Hanson, Betty", addressBook.reverseLookup("(714)273-3813"));
	    assertEquals(null, addressBook.reverseLookup("(801)553-8273"));
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    fail("Should not have raised an InvalidKeyException");
	}
	try {
	    assertEquals("Arnold, Mary", addressBook.reverseLookup("240)944-3347"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    // correct
	}
    }

    @Test
    public void testReadFile() {
	newFile = new File("hashTableTest");
	try {
	    output = new PrintWriter(newFile);
	    output.println("John Smith (208)322-8822 ID");
	    output.println("Mary Arnold (240)944-3347 VA");
	    output.println("Eric Wiz (701)445-2333 NY");
	    output.println("Robert Kaznick (615)387-3935 TN");
	    output.println("Betty Hanson (714)273-3813 CA");
	    output.close();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	addressBook = null;
	addressBook = new AddressBookUtility();
	addressBook.readFile(newFile.getAbsoluteFile());
	assertEquals(true, addressBook.contains(p1));
	assertEquals(true, addressBook.contains(p2));
	assertEquals(true, addressBook.contains(p3));
	assertEquals(true, addressBook.contains(p4));
	assertEquals(true, addressBook.contains(p5));
	assertEquals(false, addressBook.contains(p6));
    }

    @Test
    public void testAdd() {
	try {
	    assertEquals(true, addressBook.contains("(208)322-8822"));
	    assertEquals(true, addressBook.contains("(240)944-3347"));
	    assertEquals(true, addressBook.contains("(701)445-2333"));
	    assertEquals(true, addressBook.contains("(615)387-3935"));
	    assertEquals(true, addressBook.contains("(714)273-3813"));
	    assertEquals(false, addressBook.contains("(801)553-8273"));
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    fail("Should have not raised an InvalidKeyException");
	}
	try {
	    addressBook.add("Steve", "Monty", "(801)553-8273", "UT");
	    assertEquals(true, addressBook.contains("(208)322-8822"));
	    assertEquals(true, addressBook.contains("(240)944-3347"));
	    assertEquals(true, addressBook.contains("(701)445-2333"));
	    assertEquals(true, addressBook.contains("(615)387-3935"));
	    assertEquals(true, addressBook.contains("(714)273-3813"));
	    assertEquals(true, addressBook.contains("(801)553-8273"));
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    fail("Should not have raised an exception");
	} 

	try {
	    addressBook.add("Steve", "Monty", "801553-8273", "UT");
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    // correct
	} catch (KeyInUseException e) {
	    // TODO Auto-generated catch block
	    fail("Should have raised an InvalidKeyException");
	}

	try {
	    addressBook.add("Steve", "Monty", "(714)273-3813", "UT");
	    fail("Should have raised an KeyInUseException");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    fail("Should have raised an KeyInUseException");
	} catch (KeyInUseException e) {
	    // TODO Auto-generated catch block
	    // correct
	}
    }

    @Test
    public void testAddSTUDENT() {
	try {
	    assertEquals(true, studentAddressBook.contains("(201)555-5555"));
	    assertEquals(true, studentAddressBook.contains("(978)123-1234"));
	    assertEquals(true, studentAddressBook.contains("(303)565-4321"));
	    assertEquals(true, studentAddressBook.contains("(323)487-2326"));
	    assertEquals(true, studentAddressBook.contains("(240)420-0042"));
	    assertEquals(false, studentAddressBook.contains("(703)333-4444"));
	} catch (InvalidKeyException e) {
	    fail("Should have not raised an InvalidKeyException");
	}
	try {
	    studentAddressBook.add("John", "Zoidberg", "(703)333-4444", "VA");
	    assertEquals(true, studentAddressBook.contains("(201)555-5555"));
	    assertEquals(true, studentAddressBook.contains("(978)123-1234"));
	    assertEquals(true, studentAddressBook.contains("(303)565-4321"));
	    assertEquals(true, studentAddressBook.contains("(323)487-2326"));
	    assertEquals(true, studentAddressBook.contains("(240)420-0042"));
	    assertEquals(true, studentAddressBook.contains("(703)333-4444"));
	} catch (Exception e) {
	    fail("Should not have raised an exception");
	} 

	try {
	    studentAddressBook.add("John", "Zoidberg", "703-333-4444", "VA");
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // correct
	} catch (KeyInUseException e) {
	    fail("Should have raised an InvalidKeyException");
	}

	try {
	    studentAddressBook.add("John", "Zoidberg", "(703)333-4444", "VA");
	    fail("Should have raised an KeyInUseException");
	} catch (InvalidKeyException e) {
	    fail("Should have raised an KeyInUseException");
	} catch (KeyInUseException e) {
	    // correct
	}
    }

    @Test
    public void testIsValidKey() {
	try {
	    assertEquals(true, addressBook.isValidKey("(208)322-8822"));
	    assertEquals(true, addressBook.isValidKey("(240)944-3347"));
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    fail("Should have not raised an InvalidKeyException");
	}

	try {
	    assertEquals(true, addressBook.isValidKey("701)445-2333"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    // correct
	}
	try {
	    assertEquals(true, addressBook.contains("(615)3873935"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    // correct
	}
	try {
	    assertEquals(true, addressBook.contains("7142733813"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    // correct
	}
    }

    @Test
    public void testIsValidKeySTUDENT() {
	try {
	    assertEquals(true, studentAddressBook.isValidKey("(201)555-5555"));
	    assertEquals(true, studentAddressBook.isValidKey("(978)123-1234"));
	} catch (InvalidKeyException e) {
	    fail("Should have not raised an InvalidKeyException");
	}

	try {
	    assertEquals(true, studentAddressBook.isValidKey("(303)5654321"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // correct
	}
	try {
	    assertEquals(true, studentAddressBook.contains("(323)487-236"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // correct
	}
	try {
	    assertEquals(true, studentAddressBook.contains("2404200042"));
	    fail("Should have raised an InvalidKeyException");
	} catch (InvalidKeyException e) {
	    // correct
	}
    }

    @Test
    public void testWriteToFile() {
	// The following assures that the read To File works correctly
	newFile = new File("hashTableTest");
	try {
	    output = new PrintWriter(newFile);
	    output.println("John Smith (208)322-8822 ID");
	    output.println("Mary Arnold (240)944-3347 VA");
	    output.println("Eric Wiz (701)445-2333 NY");
	    output.println("Robert Kaznick (615)387-3935 TN");
	    output.println("Betty Hanson (714)273-3813 CA");
	    output.close();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	addressBook = null;
	addressBook = new AddressBookUtility();
	addressBook.readFile(newFile.getAbsoluteFile());
	if(addressBook.contains(p1) && addressBook.contains(p2) && addressBook.contains(p3) && addressBook.contains(p4) && addressBook.contains(p5))
	{
	    // we know that the readFile is working correctly
	    // now test the writeToFile
	    outFile = new File("hashTableWriteTest");
	    addressBook.writeToFile(outFile);
	    addressBook = null;
	    addressBook = new AddressBookUtility();
	    addressBook.readFile(outFile.getAbsoluteFile());

	    assertEquals(true, addressBook.contains(p1));
	    assertEquals(true, addressBook.contains(p2));
	    assertEquals(true, addressBook.contains(p3));
	    assertEquals(true, addressBook.contains(p4));
	    assertEquals(true, addressBook.contains(p5));
	    assertEquals(false, addressBook.contains(p6));

	}

    }

}
