import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;


public interface DocumentManagerInterface {

	
	  /**
	   * Returns the current Status of the Reader
	   * @return Status of the Reader - Absent or present
	   */
	public ReaderStatus currentReaderStatus();
	
	/**
	 * Returns the current Activity of the Reader
	 * @return current Activity of the Reader - None, Reading, Idle
	 */

	public ReaderActivityStatus currentReaderActivityStatus();
	
	/**
	 * returns the position in the inbox, document names and priority of documents in the inbox
	 * @return Vector of Strings, i.e. 1. Document name - document priority (top of inbox)
	 * 								   2. Document name - document priority (next in inbox)
	 * returns "Inbox is empty" if no documents in inbox
	 * 
	 * Example:
	 * 1. Quarterly Report - Urgent
	 * 2. Requirements for New Software - Normal
	 * 3. Department Managers - Low
	 * 4. Employees 10 year Awards - Low
	 * 5. Expectations for Region 4 - Urgent
	 * 6. Sales Report February 2012 - Normal
	 */
	
	public Vector<String> currentInBoxStatus();
	
	/**
	 * 
	 *returns the position in the UrgentQueue, document names and priority of documents in the UrgentQueue
	 * @return Vector of Strings, i.e. 1. Document name (front of UrgentQueue)
	 * 								   2. Document name (next in UrgentQueue)
	 * returns "Urgent Priority Queue is empty" if no documents in Queue
	 */
	
	public Vector<String> currentUrgentQueueStatus();
	
	/**
	 * 
	  *returns the position in the NormalQueue, document names and priority of documents in the NormalQueue
	 * @return Vector of Strings, i.e. 1. Document name (front of NormalQueue)
	 * 								   2. Document name (next in NormalQueue)
	 * returns "Normal Priority Queue is empty" if no documents in Queue
	 */
	
	public Vector<String> currentNormalQueueStatus();
	
	/**
	 * 
	 *returns the position in the LowQueue, document names and priority of documents in the LowQueue
	 * @return Vector of Strings, i.e. 1. Document name (front of LowQueue)
	 * 								   2. Document name (next in LowQueue)
	 * returns "Low Priority Queue is empty" if no documents in Queue
	 */

	public Vector<String> currentLowQueueStatus();
	
	/**
	 * Changes ReaderStatus to present
	 */
	
	public void enterReader();
	
	/**
	 * Changes ReaderStatus to absent
	 */
	
	public void exitReader();
	/**
	 * 
	 * @return number of documents in inbox
	 */
	
	public int getNumberDocuments();
	
	/**
	 * Add a document to the inbox
	 * @param name name of document
	 * @param priority priority of document
	 */
	
	public void addDocument(String name, String priority);
	
	/**
	 * Changes activity status of reader from Reading to Idle
	 */

	public void finishReadingDocument();
	
	/**
	 * Changes activity status of reader to Reading
	 * @return "false" if reader is not present
	 */
	
	public String readDocument();
	
	/**
	 * 
	 * @return false if reader is not Reading, true if reader is reading
	 */
		
	public boolean readStatus();
	
	/**
	 * Sorts all documents in inbox to the Urgent Normal and Low Queues
	 */

	public void sortInbox();
	
}
