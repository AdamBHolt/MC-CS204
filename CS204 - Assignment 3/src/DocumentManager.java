import java.util.*;

/**
 * The data manager for a program that manages the order of documents based on their priority
 * Manages instances of the Reader and Document classes using a Stack and 3 ArrayDeques as data structures
 * @author Adam Holt
 * @date 3/09/14
 * @class CS204
 * @time 12:00 MW
 */
public class DocumentManager implements DocumentManagerInterface
{
	//Stack that acts as an unsorted inbox for Documents
	private Stack<Document> inbox;
	//Reading Queues that are used to sort the Documents from the inbox based on priority
	private ArrayDeque<Document> urgentQueue, normalQueue, lowQueue;
	//THe Reader of the Documents
	private Reader reader;

	/**
	 * Default constructor
	 */
	public DocumentManager()
	{
		//Instantiate inbox Stack
		inbox = new Stack<>();
		
		//Instantiate the reading Queues
		urgentQueue = new ArrayDeque<>();
		normalQueue = new ArrayDeque<>();
		lowQueue = new ArrayDeque<>();
		
		//Create a new Reader for the Documents
		reader = new Reader(ReaderStatus.Present, ReaderActivityStatus.None);
	}

	/**
	 * Get the status of the Reader
	 * @return Current status of the Reader
	 */
	public ReaderStatus currentReaderStatus()
	{
		//Return the status of the Reader
		return reader.getStatus();
	}

	/**
	 * Get the activity of the Reader
	 * @return Current activity of the Reader
	 */
	public ReaderActivityStatus currentReaderActivityStatus()
	{
		//Return the activity of the Reader
		return reader.getActivity();
	}

	/**
	 * Get the contents of the inbox
	 * @return Vector of Strings for each Document currently in the inbox
	 */
	public Vector<String> currentInBoxStatus()
	{
		//Vector of Strings to be returned
		Vector<String> returnVector = new Vector<>();
		//Temporary Stack to hold the contents of the inbox as they are popped off the main Stack
		Stack<Document> tempStack = new Stack<>();
		//Document object to refer to each object from the Stack
		Document doc = null;
		//Iterator to set the Document number
		int i = 1;

		//If the inbox has 0 elements return a Vector containing the String representing that it is empty
		if(inbox.size() == 0)
		{
			returnVector.add("Inbox is empty");
			return returnVector;
		}

		//Repeat until the inbox Stack is empty
		while(!inbox.isEmpty())
		{
			//Set doc equal to each Document as it is popped off the Stack
			doc = inbox.pop();
			//Push the Document onto the temporary Stack
			tempStack.push(doc);
			//Add a String representing the Document and its order to the Vector
			returnVector.add("" + i++ + ". " + doc);
		}

		//Pop each Document off the temporary Stack and push it back onto the inbox Stack
		while(!tempStack.isEmpty())
			inbox.push(tempStack.pop());

		//Return the Vector
		return returnVector;
	}

	/**
	 * Get the contents of the urgent priority queue
	 * @return Vector of Strings for each Document currently in the urgent priority queue
	 */
	public Vector<String> currentUrgentQueueStatus()
	{
		//Vector of Strings to be returned
		Vector<String> returnVector = new Vector<>();
		//Temporary Queue to hold the contents of the urgent Queue as they are dequeued from the urgent Queue
		ArrayDeque<Document> tempQueue = new ArrayDeque<>();
		//Document object to refer to each object from the Queue
		Document doc = null;
		//Iterator to set the Document number
		int i = 1;
		
		//If the urgent Queue has 0 elements return a Vector containing the String representing that it is empty
		if(urgentQueue.size() == 0)
		{
			returnVector.add("Urgent Priority Queue is empty");
			return returnVector;
		}

		//Repeat until the urgent Queue is empty
		while(!urgentQueue.isEmpty())
		{
			//Set doc equal to each Document as it is dequeued from the urgent Queue
			doc = urgentQueue.removeLast();
			//Enqueue the Document to the temporary Queue
			tempQueue.addFirst(doc);
			//Add a String representing the Document and its order to the Vector
			returnVector.add("" + i++ + ". " + doc.getName());
		}

		//Dequeue each Document from the temporary Queue and enqueue it to the urgent Queue
		while(!tempQueue.isEmpty())
			urgentQueue.addFirst(tempQueue.removeLast());

		//Return the Vector
		return returnVector;
	}

	/**
	 * Get the contents of the normal priority Queue
	 * @return Vector of Strings for each Document currently in the normal priority Queue
	 */
	public Vector<String> currentNormalQueueStatus()
	{
		//Vector of Strings to be returned
		Vector<String> returnVector = new Vector<>();
		//Temporary Queue to hold the contents of the normal Queue as they are dequeued from the normal Queue
		ArrayDeque<Document> tempQueue = new ArrayDeque<>();
		//Document object to refer to each object from the Queue
		Document doc = null;
		//Iterator to set the Document number
		int i = 1;

		//If the normal Queue has 0 elements return a Vector containing the String representing that it is empty
		if(normalQueue.size() == 0)
		{
			returnVector.add("Normal Priority Queue is empty");
			return returnVector;
		}

		//Repeat until the normal Queue is empty
		while(!normalQueue.isEmpty())
		{
			//Set doc equal to each Document as it is dequeued from the normal Queue
			doc = normalQueue.removeLast();
			//Enqueue the Document to the temporary Queue
			tempQueue.addFirst(doc);
			//Add a String representing the Document and its order to the Vector
			returnVector.add("" + i++ + ". " + doc.getName());
		}

		//Dequeue each Document from the temporary Queue and enqueue it to the normal Queue
		while(!tempQueue.isEmpty())
			normalQueue.addFirst(tempQueue.removeLast());

		//Return the Vector
		return returnVector;
	}

	/**
	 * Get the contents of the low priority queue
	 * @return Vector of Strings for each Document currently in the low priority queue
	 */
	public Vector<String> currentLowQueueStatus()
	{
		//Vector of Strings to be returned
		Vector<String> returnVector = new Vector<>();
		//Temporary Queue to hold the contents of the low Queue as they are dequeued from the low Queue
		ArrayDeque<Document> tempQueue = new ArrayDeque<>();
		//Document object to refer to each object from the Queue
		Document doc = null;
		//Iterator to set the Document number
		int i = 1;

		//If the low Queue has 0 elements return a Vector containing the String representing that it is empty
		if(lowQueue.size() == 0)
		{
			returnVector.add("Low Priority Queue is empty");
			return returnVector;
		}

		//Repeat until the low Queue is empty
		while(!lowQueue.isEmpty())
		{
			//Set doc equal to each Document as it is dequeued from the low Queue
			doc = lowQueue.removeLast();
			//Enqueue the Document to the temporary Queue
			tempQueue.addFirst(doc);
			//Add a String representing the Document and its order to the Vector
			returnVector.add("" + i++ + ". " + doc.getName());
		}

		//Dequeue each Document from the temporary Queue and enqueue it to the low Queue
		while(!tempQueue.isEmpty())
			lowQueue.addFirst(tempQueue.removeLast());

		//Return the Vector
		return returnVector;
	}

	/**
	 * Set the status of the Reader to Present and the activity to Idle
	 */
	public void enterReader()
	{
		//Set the status of the Reader to Present
		reader.setStatus(ReaderStatus.Present);
		//Set the activity of the Reader to Idle
		reader.setActivity(ReaderActivityStatus.Idle);
	}

	/**
	 * Set the status of the Reader to Absent and activity to None
	 */
	public void exitReader()
	{
		//Set the status of the Reader to Absent
		reader.setStatus(ReaderStatus.Absent);
		//Set the activity of the Reader to None
		reader.setActivity(ReaderActivityStatus.None);
	}

	/**
	 * Get the number of Documents remaining to be read
	 * @return Total number of Documents remaining to be read
	 */
	public int getNumberDocuments()
	{
		//Return the number of elements of the inbox and the three priority queues combined
		return inbox.size() + urgentQueue.size() + normalQueue.size() + lowQueue.size();
	}

	/**
	 * Add a Document to the inbox
	 * @param name Name of the Document
	 * @param priority Priority of the Document
	 */
	public void addDocument(String name, String priority)
	{
		//Push a new Document object with the passed parameters onto the inbox Stack
		inbox.push(new Document(name, priority));
	}

	/**
	 * Set the Reader activity to Idle
	 */
	public void finishReadingDocument()
	{
		//Set the Reader activity to Idle
		reader.setActivity(ReaderActivityStatus.Idle);
	}

	/**
	 * Indicate that the Reader is reading the next Document in the queues
	 * Choose documents starting from the highest priority Queue and working down
	 * @return String Name of the Document currently being read, or "false" if the Reader is Absent
	 */
	public String readDocument()
	{
		//The Document currently being read
		Document doc = null;

		//If the Reader's status is Absent, return "false"
		if(reader.getStatus().equals(ReaderStatus.Absent))
			return "false";
		
		//Set the Reader's activity to Reading
		reader.setActivity(ReaderActivityStatus.Reading);

		//If the urgent Queue is not empty, dequeue the next Document refer doc to it
		if(!urgentQueue.isEmpty())
			doc = urgentQueue.removeLast();
		//Otherwise, if the normal Queue is not empty, dequeue the next Document refer doc to it
		else if(!normalQueue.isEmpty())
			doc = normalQueue.removeLast();
		//Otherwise, if the low Queue is not empty, dequeue the next Document refer doc to it
		else if(!lowQueue.isEmpty())
			doc = lowQueue.removeLast();

		//Return the name of the Document
		return doc.getName();
	}

	/**
	 * Determine whether the Reader's status is currently Reading
	 * @return True if the Reader's status is Reading, otherwise, false
	 */
	public boolean readStatus()
	{
		//Return the boolean representation of whether the Reader's status is equal to Reading
		return reader.getActivity().equals(ReaderActivityStatus.Reading);
	}

	/**
	 * Sort the inbox Stack into the priority Queues
	 */
	public void sortInbox()
	{
		//A Document object
		Document doc = null;
		
		//Repeat until the inbox Stack is empty
		while(!inbox.isEmpty())
		{
			//Pop the first Document off the Stack and refer doc to it
			doc = inbox.pop();

			//If the priority of the Document is "urgent" enqueue it to the urgent priority Queue
			if(doc.getPriority().equals("urgent"))
				urgentQueue.addFirst(doc);
			//Otherwise, if the priority of the Document is "normal" enqueue it to the normal priority Queue
			else if(doc.getPriority().equals("normal"))
				normalQueue.addFirst(doc);
			//Otherwise, enqueue the Document to the low priority Queue
			else
				lowQueue.addFirst(doc);
		}
	}
}