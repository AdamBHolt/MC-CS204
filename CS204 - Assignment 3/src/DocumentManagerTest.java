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
		fail("Test not yet implemented");
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
		fail("Test not yet implemented");
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
		fail("Test not yet implemented");
	}

}
