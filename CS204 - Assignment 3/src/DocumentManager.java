import java.util.*;

public class DocumentManager implements DocumentManagerInterface
{
	private Stack<Document> inbox;
	private ArrayDeque<Document> urgentQueue, normalQueue, lowQueue;
	private Reader reader;

	public DocumentManager()
	{
		inbox = new Stack<>();
		urgentQueue = new ArrayDeque<>();
		normalQueue = new ArrayDeque<>();
		lowQueue = new ArrayDeque<>();
		reader = new Reader(ReaderStatus.Present, ReaderActivityStatus.None);
	}

	public ReaderStatus currentReaderStatus()
	{
		return reader.getStatus();
	}

	public ReaderActivityStatus currentReaderActivityStatus()
	{
		return reader.getActivity();
	}

	public Vector<String> currentInBoxStatus()
	{
		Vector<String> returnVector = new Vector<>();
		
		Stack<Document> tempStack = new Stack<>();
		Document doc = null;
		int i = 1;

		if(inbox.size() == 0)
		{
			returnVector.add("Inbox is empty");
			return returnVector;
		}

		while(!inbox.isEmpty())
		{
			doc = inbox.pop();
			tempStack.push(doc);
			returnVector.add("" + i++ + ". " + doc);
		}

		while(!tempStack.isEmpty())
			inbox.push(tempStack.pop());

		return returnVector;
	}

	public Vector<String> currentUrgentQueueStatus()
	{
		Vector<String> returnVector = new Vector<>();
		ArrayDeque<Document> tempQueue = new ArrayDeque<>();
		Document doc = null;
		int i = 1;

		if(urgentQueue.size() == 0)
		{
			returnVector.add("Urgent Priority Queue is empty");
			return returnVector;
		}

		while(!urgentQueue.isEmpty())
		{
			doc = urgentQueue.removeLast();
			tempQueue.addFirst(doc);
			returnVector.add("" + i++ + ". " + doc.getName());
		}

		while(!tempQueue.isEmpty())
			urgentQueue.addFirst(tempQueue.removeLast());

		return returnVector;
	}

	public Vector<String> currentNormalQueueStatus()
	{
		Vector<String> returnVector = new Vector<>();
		ArrayDeque<Document> tempQueue = new ArrayDeque<>();
		Document doc = null;
		int i = 1;

		if(normalQueue.size() == 0)
		{
			returnVector.add("Normal Priority Queue is empty");
			return returnVector;
		}

		while(!normalQueue.isEmpty())
		{
			doc = normalQueue.removeLast();
			tempQueue.addFirst(doc);
			returnVector.add("" + i++ + ". " + doc.getName());
		}

		while(!tempQueue.isEmpty())
			normalQueue.addFirst(tempQueue.removeLast());

		return returnVector;
	}

	public Vector<String> currentLowQueueStatus()
	{
		Vector<String> returnVector = new Vector<>();
		ArrayDeque<Document> tempQueue = new ArrayDeque<>();
		Document doc = null;
		int i = 1;

		if(lowQueue.size() == 0)
		{
			returnVector.add("Low Priority Queue is empty");
			return returnVector;
		}

		while(!lowQueue.isEmpty())
		{
			doc = lowQueue.removeLast();
			tempQueue.addFirst(doc);
			returnVector.add("" + i++ + ". " + doc.getName());
		}

		while(!tempQueue.isEmpty())
			lowQueue.addFirst(tempQueue.removeLast());

		return returnVector;
	}

	public void enterReader()
	{
		reader.setStatus(ReaderStatus.Present);
	}

	public void exitReader()
	{
		reader.setStatus(ReaderStatus.Absent);
	}

	public int getNumberDocuments()
	{
		return inbox.size() + urgentQueue.size() + normalQueue.size() + lowQueue.size();
	}

	public void addDocument(String name, String priority)
	{
		inbox.push(new Document(name, priority));
	}

	public void finishReadingDocument()
	{
		reader.setActivity(ReaderActivityStatus.Idle);
	}

	public String readDocument()
	{
		Document doc = null;

		if(reader.getStatus().equals(ReaderStatus.Absent))
			return "false";
		
		reader.setActivity(ReaderActivityStatus.Reading);

		if(!urgentQueue.isEmpty())
			doc = urgentQueue.removeLast();
		else if(!normalQueue.isEmpty())
			doc = normalQueue.removeLast();
		else if(!lowQueue.isEmpty())
			doc = lowQueue.removeLast();

		return doc.getName();
	}

	public boolean readStatus()
	{
		return reader.getActivity().equals(ReaderActivityStatus.Reading);
	}

	public void sortInbox()
	{
		Document doc;
		while(!inbox.isEmpty())
		{
			doc = inbox.pop();

			if(doc.getPriority().equals("urgent"))
				urgentQueue.addFirst(doc);
			else if(doc.getPriority().equals("normal"))
				normalQueue.addFirst(doc);
			else
				lowQueue.addFirst(doc);
		}
	}
}
