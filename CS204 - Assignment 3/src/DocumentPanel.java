import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * The GUI interface for a program that manages the order of documents based on their priority for a document reader
 * @author Adam Holt
 * @date 3/09/14
 * @class CS204
 * @time 12:00 MW
 */
public class DocumentPanel extends JPanel
{

	//Class member declarations
	private static final long serialVersionUID = 1L;											//Default serialized ID
	private JLabel toRead, readerStatus, readerActivity;										//Labels to represent the Reader's status and documents to be read
	private JRadioButton enterStatus, exitStatus, lowPriority, normalPriority, urgentPriority; 	//Radio buttons
	private JButton sort, read, finish, add, readFile, exit;									//Action buttons for the GUI
	private JTextField document;																//Field to enter the name of a new Document
	private JTextArea inbox, urgent, normal, low;												//Inbox and reading queues to display Document names
	private DocumentManager manager;															//Data manager for the Documents, Reader, and inbox
	private String priority;																	//The priority of the Document being added

	/**
	 * Default constructor
	 */
	public DocumentPanel()
	{
		//Create a new data manager
		manager = new DocumentManager();
		//Build and add each of the panels to the main panel
		buildTopPanel();
		buildMiddlePanel();
		buildBottomPanel();
		//Click the "Exit" radio button to indicate that the reader is not currently present
		exitStatus.doClick();
		//Click the "Normal" radio button to set the default priority to normal
		normalPriority.doClick();
	}

	/**
	 * Build and add the top panel to the main frame
	 */
	private void buildTopPanel()
	{
		//Outer panel and sub panels
		JPanel topPanel = new JPanel();
		JPanel toReadPanel = new JPanel();
		JPanel radioPanel = new JPanel();
		JPanel statusPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel activityPanel = new JPanel();

		//Label indicating the number of documents to be read
		toRead = new JLabel("");
		//Label indicating the Reader's status
		readerStatus = new JLabel("");
		//LAbel indicating the Reader's activity
		readerActivity = new JLabel("");
		//Radio buttons to indicate that the Reader has entered or exited
		enterStatus = new JRadioButton("Enter");
		exitStatus = new JRadioButton("Exit");
		//Button group for the radio buttons
		ButtonGroup group = new ButtonGroup();
		//Button to sort the inbox
		sort = new JButton("Sort Inbox");
		//Button for the Reader to read the next Document
		read = new JButton("Read Document");
		//Button for the Reader to finish reading the current Document
		finish = new JButton("Finish Document");

		//Add the documents to be read label to the sub panel
		toReadPanel.add(new JLabel("Documents to read: "));
		toReadPanel.add(toRead);

		//Add the radio buttons to the button group
		group.add(enterStatus);
		group.add(exitStatus);

		//Add action listeners to the radio buttons
		enterStatus.addActionListener(new RadioListener());
		exitStatus.addActionListener(new RadioListener());
		//Set the action commands of the radio buttons
		enterStatus.setActionCommand("e");
		exitStatus.setActionCommand("x");

		//Add mnemonics to the radio buttons
		enterStatus.setMnemonic(KeyEvent.VK_E);
		exitStatus.setMnemonic(KeyEvent.VK_X);
		//Add tooltips to the radio buttons
		enterStatus.setToolTipText("Indicate the the reader is available to read documents");
		exitStatus.setToolTipText("Indicate the the reader is no longer available to read documents");
		//Prevent the radio buttons from being focused on
		enterStatus.setFocusable(false);
		exitStatus.setFocusable(false);

		//Add the radio buttons to the sub panel
		radioPanel.add(enterStatus);
		radioPanel.add(exitStatus);
		//Set the border and size of the sub panel
		radioPanel.setBorder(BorderFactory.createTitledBorder("Status"));
		radioPanel.setPreferredSize(new Dimension(300,50));

		//Add the reader status labels to the sub panel
		statusPanel.add(new JLabel("ReaderStatus: "));
		statusPanel.add(readerStatus);

		//Add the action buttons to the sub panel
		buttonPanel.add(sort);
		buttonPanel.add(read);
		buttonPanel.add(finish);

		//Add action listeners to the buttons
		sort.addActionListener(new ButtonListener());
		read.addActionListener(new ButtonListener());
		finish.addActionListener(new ButtonListener());
		//Set the action commands of the buttons
		sort.setActionCommand("s");
		read.setActionCommand("r");
		finish.setActionCommand("f");

		//Add mnemonics to the buttons
		sort.setMnemonic(KeyEvent.VK_S);
		read.setMnemonic(KeyEvent.VK_R);
		finish.setMnemonic(KeyEvent.VK_F);
		//Add tooltips to the buttons
		sort.setToolTipText("Sort the inbox into the reading queues");
		read.setToolTipText("Read the next document");
		finish.setToolTipText("Finish reading the current document");

		//Add the reader activity labels to the sub panel
		activityPanel.add(new JLabel("Reader Activity: "));
		activityPanel.add(readerActivity);

		//Set the border and size of the outher panel
		topPanel.setBorder(BorderFactory.createTitledBorder("Document Reader"));
		topPanel.setPreferredSize(new Dimension(375,225));

		//Add each of the sub panels to the outer panel
		topPanel.add(toReadPanel);
		topPanel.add(radioPanel);
		topPanel.add(statusPanel);
		topPanel.add(buttonPanel);
		topPanel.add(activityPanel);
		//Add the outer panel to the main panel
		add(topPanel);
	}

	private void buildMiddlePanel()
	{
		//Outer panel and sub panels
		JPanel middlePanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel namePanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		JPanel priorityPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		//Text field to enter a new Document name
		document = new JTextField(23);
		//Radio buttons to specify the priority of a new Document
		lowPriority = new JRadioButton("Low");
		normalPriority = new JRadioButton("Normal");
		urgentPriority = new JRadioButton("Urgent");
		//Button group for the radio buttons
		ButtonGroup group = new ButtonGroup();
		//Button to add a new Document
		add = new JButton("Add");
		//Text area to display the current contents of the inbox
		inbox = new JTextArea();

		//Add the radio buttons to the button group
		group.add(lowPriority);
		group.add(normalPriority);
		group.add(urgentPriority);

		//Add action listeners to the radio buttons
		lowPriority.addActionListener(new RadioListener());
		normalPriority.addActionListener(new RadioListener());
		urgentPriority.addActionListener(new RadioListener());

		//Set the action commands of the radio buttons
		lowPriority.setActionCommand("l");
		normalPriority.setActionCommand("n");
		urgentPriority.setActionCommand("u");

		//Add mnemonics to the radio buttons
		lowPriority.setMnemonic(KeyEvent.VK_L);
		normalPriority.setMnemonic(KeyEvent.VK_N);
		urgentPriority.setMnemonic(KeyEvent.VK_U);
		//Add tooltips to the radio buttons
		lowPriority.setToolTipText("Add a document with low priority");
		normalPriority.setToolTipText("Add a document with normal priority");
		urgentPriority.setToolTipText("Add a document with urgent priority");

		//Add the radio buttons to the sub panel
		priorityPanel.add(lowPriority);
		priorityPanel.add(normalPriority);
		priorityPanel.add(urgentPriority);
		//Set the border and size of the sub panel
		priorityPanel.setBorder(BorderFactory.createTitledBorder("Priority"));
		priorityPanel.setPreferredSize(new Dimension(250,50));

		//Add the document name label and text field to the sub panels
		namePanel.add(new JLabel("Document Name"));
		fieldPanel.add(document);


		//Add action listener and set action command for the add button
		add.addActionListener(new ButtonListener());
		add.setActionCommand("a");
		//Set the mnemonic and tooltip for the add button
		add.setMnemonic(KeyEvent.VK_A);
		add.setToolTipText("Add a new document to the inbox");

		//Add the add button to the sub panel
		buttonPanel.add(add);

		//Add the sub panels to the left panel
		leftPanel.add(namePanel);
		leftPanel.add(fieldPanel);
		leftPanel.add(priorityPanel);
		leftPanel.add(buttonPanel);

		//Set the border and size of the left panel
		leftPanel.setBorder(BorderFactory.createTitledBorder("Add Document"));
		leftPanel.setPreferredSize(new Dimension(275,190));

		//Prevent the inboc from being directly edited
		inbox.setEditable(false);
		//Set the background of the inbox to match the panel
		inbox.setBackground(rightPanel.getBackground());
		//Add the inbox to the right panel
		rightPanel.add(inbox);

		//Set the border and size of the right panel
		rightPanel.setBorder(BorderFactory.createTitledBorder("Contents"));
		rightPanel.setPreferredSize(new Dimension(275,190));

		//Add the left and right panels to the outer panel
		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);

		//Set the border and size of the outer panel
		middlePanel.setBorder(BorderFactory.createTitledBorder("Inbox"));
		middlePanel.setPreferredSize(new Dimension(575,225));

		//Add the outer panel to the main panel
		add(middlePanel);
	}

	private void buildBottomPanel()
	{
		//Outer panel and sub panels
		JPanel bottomPanel = new JPanel();
		JPanel urgentPanel = new JPanel();
		JPanel normalPanel = new JPanel();
		JPanel lowPanel = new JPanel();

		//Text areas to display the contents of the reaing queues
		urgent = new JTextArea();
		normal = new JTextArea();
		low = new JTextArea();
		//Button to read a file of documents
		readFile = new JButton("Read File");
		//Button to exit the program
		exit = new JButton("Exit");

		//Prevent the text areas from being directly edited
		urgent.setEditable(false);
		normal.setEditable(false);
		low.setEditable(false);

		//Set the backgrounds of the text areas to match the panel
		urgent.setBackground(urgentPanel.getBackground());
		normal.setBackground(normalPanel.getBackground());
		low.setBackground(lowPanel.getBackground());

		//Add the text areas to the sub panels
		urgentPanel.add(urgent);
		normalPanel.add(normal);
		lowPanel.add(low);

		//Set the borders and sizes of the sub panels
		urgentPanel.setBorder(BorderFactory.createTitledBorder("Urgent Priority"));
		normalPanel.setBorder(BorderFactory.createTitledBorder("Normal Priority"));
		lowPanel.setBorder(BorderFactory.createTitledBorder("Low Priority"));
		urgentPanel.setPreferredSize(new Dimension(225,190));
		normalPanel.setPreferredSize(new Dimension(225,190));
		lowPanel.setPreferredSize(new Dimension(225,190));

		//Add action listeners to the buttons
		readFile.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
		//Set the action commands of the buttons
		readFile.setActionCommand("d");
		exit.setActionCommand("e");
		
		//Add mnemonics to the buttons
		readFile.setMnemonic(KeyEvent.VK_D);
		exit.setMnemonic(KeyEvent.VK_T);
		//Add tooltips to the buttons
		readFile.setToolTipText("Add documents to the inbox from a file");
		exit.setToolTipText("Exit the program");

		//Set the border of the outer panel
		
		//Add the sub panels to the outer panel
		bottomPanel.add(urgentPanel);
		bottomPanel.add(normalPanel);
		bottomPanel.add(lowPanel);
		
		//Set the border of the outer panel
		bottomPanel.setBorder(BorderFactory.createTitledBorder("Priority Queues"));
		
		//Add the outer panel to the main panel
		add(bottomPanel);
		
		//Add the buttons to the main panel
		add(readFile);
		add(exit);
	}

	/**
	 * Sort the documents from the inbox into their respective queues based on priority
	 */
	private void sortDocuments()
	{
		//Call the manager's sortInbox method
		manager.sortInbox();
		//Refresh the GUI
		refresh();
	}

	/**
	 * Read the next document in the queue with the highest priority
	 */
	private void readDocument()
	{
		//Call the manager's readDocument method
		manager.readDocument();
		//Refresh the GUI
		refresh();
	}

	/**
	 * Finish reading the current document
	 */
	private void finishDocument()
	{
		//Call the manager's finishReadingDocument method
		manager.finishReadingDocument();
		//Refresh the GUI
		refresh();
	}

	/**
	 * Add a new document to the inbox
	 */
	private void addDocument()
	{
		//Only proceed if the document name field is not empty
		if(!document.getText().equals(""))
		{
			//Call the manager's addDocuemnt method passing the name and priority specified
			manager.addDocument(document.getText(), priority);
			//Clear the document name field
			document.setText("");
			//Set the focus to the document name field
			document.requestFocus();
			//Refresgh the GUI
			refresh();
		}
	}

	/**
	 * Read a file to fill the inbox with a list of document names and priorities
	 * @throws FileNotFoundException
	 */
	private void readFile() throws FileNotFoundException
	{
		//File chooser to select the file
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select a List of Documents");

		//If the "open" option is chosen in the FileChooser
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			//File object with the selected file
			File selectedFile = chooser.getSelectedFile();

			//Scanner to read from the selected file
			Scanner inputFile = new Scanner(selectedFile);

			//Read each line in the text file
			while(inputFile.hasNext())
				//Create a new Document in the manager based on the next two lines in the file
				manager.addDocument(inputFile.nextLine(), inputFile.nextLine());

			//Close the file
			inputFile.close();
			//Refresh the GUI
			refresh();
		}
	}

	/**
	 * Indicate that the Reader has entered and is available to read documents
	 */
	private void readerEnter()
	{
		//Call the manager's enterReader method
		manager.enterReader();
		//Refresh the GUI
		refresh();
	}

	/**
	 * Indicate that the Reader has exited and is no longer available to read documents
	 */
	private void readerExit()
	{
		//Disable the Reader's buttons
		sort.setEnabled(false);
		read.setEnabled(false);
		finish.setEnabled(false);
		//Call the manager's exitReader method
		manager.exitReader();
		//Refresh the GUI
		refresh();
	}

	/**
	 * Refresh the GUI
	 */
	private void refresh()
	{
		//Vector of Strings to represent the contents of the inbox and reading queues
		Vector<String> documents;
		
		//Get the contents of the inbox from the manager
		documents = manager.currentInBoxStatus();

		//Clear the inbox
		inbox.setText("");
		//Add each String representing the documents in the inbox to the inbox text area
		for(String doc : documents)
			inbox.append(doc + "\n");

		//Get the contents of the urgent queue from the manager
		documents = manager.currentUrgentQueueStatus();

		//Clear the urgent queue
		urgent.setText("");
		//Add each String representing the documents in the urgent queue to the urgent queue text area
		for(String doc : documents)
			urgent.append(doc + "\n");

		//Get the contents of the normal queue from the manager
		documents = manager.currentNormalQueueStatus();

		//Clear the normal queue
		normal.setText("");
		//Add each String representing the documents in the normal queue to the normal queue text area
		for(String doc : documents)
			normal.append(doc + "\n");

		//Get the contents of the low queue from the manager
		documents = manager.currentLowQueueStatus();

		//Clear the low queue
		low.setText("");
		//Add each String representing the documents in the low queue to the low queue text area
		for(String doc : documents)
			low.append(doc + "\n");

		//GEt the documents to be read and Reader statuses from the manger and reset the labels
		toRead.setText("" + manager.getNumberDocuments());
		readerStatus.setText("" + manager.currentReaderStatus());
		readerActivity.setText("" + manager.currentReaderActivityStatus());

		//Set Button enabled state based on current reader status and activity
		
		//If any of the queues is not empty, the Reader is present, and the Reader's activity is not Reading enable the read button, otherwise disable it 
		if((!urgent.getText().equals("Urgent Priority Queue is empty\n") ||
				!normal.getText().equals("Normal Priority Queue is empty\n") || 
				!low.getText().equals("Low Priority Queue is empty\n")) &&
				manager.currentReaderStatus().equals(ReaderStatus.Present) &&
				!manager.currentReaderActivityStatus().equals(ReaderActivityStatus.Reading))
			read.setEnabled(true);
		else 
			read.setEnabled(false);

		//If the inbox is not empty and the Reader is present enable the sort button, otherwise disable it
		if(!inbox.getText().equals("Inbox is empty\n") && manager.currentReaderStatus().equals(ReaderStatus.Present))
			sort.setEnabled(true);
		else
			sort.setEnabled(false);

		//If the Reader is present and the Reader's activity is Reading, enable the finish button, otherwise disable it
		if(manager.currentReaderStatus().equals(ReaderStatus.Present) &&
				manager.currentReaderActivityStatus().equals(ReaderActivityStatus.Reading))
			finish.setEnabled(true);
		else
			finish.setEnabled(false);

		//If the Reader's activity is Reading, disable the enterSTatus and exitStatus radio buttons
		if(manager.currentReaderActivityStatus().equals(ReaderActivityStatus.Reading))
		{
			enterStatus.setEnabled(false);
			exitStatus.setEnabled(false);
		}
		else
		{
			enterStatus.setEnabled(true);
			exitStatus.setEnabled(true);
		}
	}

	/**
	 * Action listener for the Buttons
	 */
	private class ButtonListener implements ActionListener
	{
		/**
		 * Action to be performed on button click
		 */
		public void actionPerformed(ActionEvent e)
		{
			//Switch based on the action command of the button
			switch(e.getActionCommand().charAt(0))
			{
			//Sort button
			case 's':
				//Sort the documents
				sortDocuments();
				break;
			//Read button
			case 'r':
				//Read the next document
				readDocument();
				break;
			//Finish button
			case 'f':
				//Finish reading the current document
				finishDocument();
				break;
			//Add button
			case 'a':
				//Add a new document
				addDocument();
				break;
			//Read File button
			case 'd':
				//Read new documents from a file
				try{readFile();}
				catch (FileNotFoundException ex){ex.printStackTrace();}
				break;
				//Exit the program
			//Exit button
			case 'e':
				System.exit(0);
			}
		}
	}

	/**
	 * Action listener for the Radio Buttons
	 */
	private class RadioListener implements ActionListener
	{
		/**
		 * Action to be performed when a radio button is selected
		 */
		public void actionPerformed(ActionEvent e)
		{
			//Switch based on the action command of the radio button
			switch(e.getActionCommand().charAt(0))
			{
			//Enter radio button
			case 'e':
				//Indicate the reader has entered
				readerEnter();
				break;
			//Exit radio button
			case 'x':
				//Indicate the reader has exited
				readerExit();
				break;
			//Low radio button
			case 'l':
				//Set the priority to low
				priority = "low";
				break;
			//Normal radio button
			case 'n':
				//Set the priority to normal
				priority = "normal";
				break;
			//Urgent radio button
			case 'u':
				//Set the priority to low
				priority = "urgent";
				break;
			}
		}
	}
}