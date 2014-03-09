import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.*;


public class DocumentManagerTest {
	public DocumentManager documentManager, studentManager;


	@Before
	public void setUp() {
		documentManager = new DocumentManager();
		documentManager.addDocument("Sales Report February 2012","normal");
		documentManager.addDocument("Expectations for Region 4","urgent");
		documentManager.addDocument("Employees 10 year Awards","low");
		documentManager.addDocument("Department Managers","low");
		documentManager.addDocument("Requirements for New Software","normal");
		documentManager.addDocument("Quarterly Report","urgent");
		
		// STUDENT
		// create your own DocumentManager
		// add documents to your DocumentManager
		// use this DocumentManager for STUDENT TESTS
		
		studentManager = new DocumentManager();
		studentManager.addDocument("Current Sales", "normal");
		studentManager.addDocument("FW: This is Hilarious", "low");
		studentManager.addDocument("Something is on Fire", "urgent");
		studentManager.addDocument("RE: Why is the Printer Covered in Blood?", "urgent");
		studentManager.addDocument("Quarterly Fridge Cleaning Log", "low");
		studentManager.addDocument("Annual Calendar of Events", "normal");
	}
	
	@After
	public void tearDown()
	{
		documentManager = null;
		
		// STUDENT
		// set your DocumentManager to null
		studentManager = null;
	}

	@Test
	public void testCurrentInBoxStatus() {
		Vector<String> inbox = documentManager.currentInBoxStatus();
		assertEquals(inbox.elementAt(0),"1. Quarterly Report - Urgent");
		assertEquals(inbox.elementAt(1), "2. Requirements for New Software - Normal");
		assertEquals(inbox.elementAt(2),"3. Department Managers - Low");
		assertEquals(inbox.elementAt(3),"4. Employees 10 year Awards - Low");
		assertEquals(inbox.elementAt(4),"5. Expectations for Region 4 - Urgent");
		assertEquals(inbox.elementAt(5),"6. Sales Report February 2012 - Normal");
	}
	
	@Test
	public void testCurrentInBoxStatusSTUDENT() {
		Vector<String> inbox = studentManager.currentInBoxStatus();
		assertEquals(inbox.elementAt(0),"1. Annual Calendar of Events - Normal");
		assertEquals(inbox.elementAt(1),"2. Quarterly Fridge Cleaning Log - Low");
		assertEquals(inbox.elementAt(2),"3. RE: Why is the Printer Covered in Blood? - Urgent");
		assertEquals(inbox.elementAt(3),"4. Something is on Fire - Urgent");
		assertEquals(inbox.elementAt(4),"5. FW: This is Hilarious - Low");
		assertEquals(inbox.elementAt(5),"6. Current Sales - Normal");
	}
	
	@Test
	public void testSortInBox() {
		documentManager.sortInbox();
		Vector<String> inbox = documentManager.currentInBoxStatus();
		assertEquals(inbox.elementAt(0),"Inbox is empty");
		Vector<String> urgentQueue = documentManager.currentUrgentQueueStatus();
		assertEquals(urgentQueue.elementAt(0), "1. Quarterly Report");
		assertEquals(urgentQueue.elementAt(1), "2. Expectations for Region 4");
		Vector<String> normalQueue = documentManager.currentNormalQueueStatus();
		assertEquals(normalQueue.elementAt(0), "1. Requirements for New Software");
		assertEquals(normalQueue.elementAt(1), "2. Sales Report February 2012");
		Vector<String> lowQueue = documentManager.currentLowQueueStatus();
		assertEquals(lowQueue.elementAt(0), "1. Department Managers");
		assertEquals(lowQueue.elementAt(1), "2. Employees 10 year Awards");
	}
	
	@Test
	public void testSortInBoxSTUDENT() {
		studentManager.sortInbox();
		Vector<String> inbox = studentManager.currentInBoxStatus();
		assertEquals(inbox.elementAt(0),"Inbox is empty");
		Vector<String> urgentQueue = studentManager.currentUrgentQueueStatus();
		assertEquals(urgentQueue.elementAt(0), "1. RE: Why is the Printer Covered in Blood?");
		assertEquals(urgentQueue.elementAt(1), "2. Something is on Fire");
		Vector<String> normalQueue = studentManager.currentNormalQueueStatus();
		assertEquals(normalQueue.elementAt(0), "1. Annual Calendar of Events");
		assertEquals(normalQueue.elementAt(1), "2. Current Sales");
		Vector<String> lowQueue = studentManager.currentLowQueueStatus();
		assertEquals(lowQueue.elementAt(0), "1. Quarterly Fridge Cleaning Log");
		assertEquals(lowQueue.elementAt(1), "2. FW: This is Hilarious");
	}
	
	@Test
	public void testReadDocument() {
		documentManager.sortInbox();
		assertEquals("Quarterly Report",documentManager.readDocument());
		assertEquals(ReaderActivityStatus.Reading, documentManager.currentReaderActivityStatus());
		documentManager.finishReadingDocument();
		assertEquals(ReaderActivityStatus.Idle, documentManager.currentReaderActivityStatus());
		assertEquals("Expectations for Region 4",documentManager.readDocument());
		documentManager.finishReadingDocument();
		assertEquals("Requirements for New Software",documentManager.readDocument());
		documentManager.finishReadingDocument();
		assertEquals("Sales Report February 2012",documentManager.readDocument());
		documentManager.finishReadingDocument();
		assertEquals("Department Managers",documentManager.readDocument());
		documentManager.finishReadingDocument();
		assertEquals("Employees 10 year Awards",documentManager.readDocument());
	}
	
	@Test
	public void testReadDocumentSTUDENT() {
		studentManager.sortInbox();
		assertEquals("RE: Why is the Printer Covered in Blood?",studentManager.readDocument());
		assertEquals(ReaderActivityStatus.Reading, studentManager.currentReaderActivityStatus());
		studentManager.finishReadingDocument();
		assertEquals(ReaderActivityStatus.Idle, studentManager.currentReaderActivityStatus());
		assertEquals("Something is on Fire",studentManager.readDocument());
		studentManager.finishReadingDocument();
		assertEquals("Annual Calendar of Events",studentManager.readDocument());
		studentManager.finishReadingDocument();
		assertEquals("Current Sales",studentManager.readDocument());
		studentManager.finishReadingDocument();
		assertEquals("Quarterly Fridge Cleaning Log",studentManager.readDocument());
		studentManager.finishReadingDocument();
		assertEquals("FW: This is Hilarious",studentManager.readDocument());
	}
	
	@Test
	public void testAddDocument() {
		documentManager.addDocument("New Document", "urgent");
		documentManager.addDocument("Another New Document", "normal");
		Vector<String> inbox = documentManager.currentInBoxStatus();
		System.out.println(inbox);
		assertEquals(inbox.elementAt(0),"1. Another New Document - Normal");
		assertEquals(inbox.elementAt(1),"2. New Document - Urgent");
		documentManager.sortInbox();
		assertEquals("New Document",documentManager.readDocument());
		assertEquals(ReaderActivityStatus.Reading, documentManager.currentReaderActivityStatus());
		documentManager.finishReadingDocument();
		assertEquals(ReaderActivityStatus.Idle, documentManager.currentReaderActivityStatus());
		assertEquals("Quarterly Report",documentManager.readDocument());
		documentManager.finishReadingDocument();
		assertEquals("Expectations for Region 4",documentManager.readDocument());
		documentManager.finishReadingDocument();
		assertEquals("Another New Document",documentManager.readDocument());
	}
	
	@Test
	public void testAddDocumentSTUDENT() {
		studentManager.addDocument("Please Stop Eating My Lunch From the Fridge", "urgent");
		studentManager.addDocument("Has Anyone Seen My Stapler?", "low");
		Vector<String> inbox = studentManager.currentInBoxStatus();
		System.out.println(inbox);
		assertEquals(inbox.elementAt(0),"1. Has Anyone Seen My Stapler? - Low");
		assertEquals(inbox.elementAt(1),"2. Please Stop Eating My Lunch From the Fridge - Urgent");
		studentManager.sortInbox();
		assertEquals("Please Stop Eating My Lunch From the Fridge",studentManager.readDocument());
		assertEquals(ReaderActivityStatus.Reading, studentManager.currentReaderActivityStatus());
		studentManager.finishReadingDocument();
		assertEquals(ReaderActivityStatus.Idle, studentManager.currentReaderActivityStatus());
		assertEquals("RE: Why is the Printer Covered in Blood?",studentManager.readDocument());
		studentManager.finishReadingDocument();
		assertEquals("Something is on Fire",studentManager.readDocument());
		studentManager.finishReadingDocument();
		assertEquals("Annual Calendar of Events",studentManager.readDocument());
	}

}
