import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DocumentPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JLabel toRead, readerStatus, readerActivity;
	private JRadioButton enterStatus, exitStatus, lowPriority, normalPriority, urgentPriority; 
	private JButton sort, read, finish, add, readFile, exit;
	private JTextField document;
	private JTextArea inbox, urgent, normal, low;
	private DocumentManager manager;
	private String priority;

	public DocumentPanel()
	{
		manager = new DocumentManager();
		buildTopPanel();
		buildMiddlePanel();
		buildBottomPanel();
		exitStatus.doClick();
	}

	private void buildTopPanel()
	{
		JPanel topPanel = new JPanel();
		JPanel toReadPanel = new JPanel();
		JPanel radioPanel = new JPanel();
		JPanel statusPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel activityPanel = new JPanel();

		toRead = new JLabel("0");
		readerStatus = new JLabel("Absent");
		readerActivity = new JLabel("None");
		ButtonGroup group = new ButtonGroup();
		enterStatus = new JRadioButton("Enter");
		exitStatus = new JRadioButton("Exit");
		sort = new JButton("Sort Inbox");
		read = new JButton("Read Document");
		finish = new JButton("Finish Document");

		group.add(enterStatus);
		group.add(exitStatus);

		toReadPanel.add(new JLabel("Documents to read: "));
		toReadPanel.add(toRead);

		radioPanel.add(enterStatus);
		radioPanel.add(exitStatus);
		radioPanel.setBorder(BorderFactory.createTitledBorder("Status"));
		radioPanel.setPreferredSize(new Dimension(300,50));

		enterStatus.addActionListener(new RadioListener());
		exitStatus.addActionListener(new RadioListener());

		enterStatus.setActionCommand("e");
		exitStatus.setActionCommand("x");

		statusPanel.add(new JLabel("ReaderStatus: "));
		statusPanel.add(readerStatus);

		buttonPanel.add(sort);
		buttonPanel.add(read);
		buttonPanel.add(finish);

		sort.addActionListener(new ButtonListener());
		read.addActionListener(new ButtonListener());
		finish.addActionListener(new ButtonListener());

		sort.setActionCommand("s");
		read.setActionCommand("r");
		finish.setActionCommand("f");

		activityPanel.add(new JLabel("Reader Activity: "));
		activityPanel.add(readerActivity);

		topPanel.setBorder(BorderFactory.createTitledBorder("Document Reader"));
		topPanel.setPreferredSize(new Dimension(375,225));

		topPanel.add(toReadPanel);
		topPanel.add(radioPanel);
		topPanel.add(statusPanel);
		topPanel.add(buttonPanel);
		topPanel.add(activityPanel);
		add(topPanel);
	}

	private void buildMiddlePanel()
	{
		JPanel middlePanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel namePanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		JPanel priorityPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		document = new JTextField(23);
		ButtonGroup group = new ButtonGroup();
		lowPriority = new JRadioButton("Low");
		normalPriority = new JRadioButton("Normal");
		urgentPriority = new JRadioButton("Urgent");
		add = new JButton("Add");
		inbox = new JTextArea();

		group.add(lowPriority);
		group.add(normalPriority);
		group.add(urgentPriority);

		lowPriority.addActionListener(new RadioListener());
		normalPriority.addActionListener(new RadioListener());
		urgentPriority.addActionListener(new RadioListener());

		lowPriority.setActionCommand("l");
		normalPriority.setActionCommand("n");
		urgentPriority.setActionCommand("u");

		namePanel.add(new JLabel("Document Name"));

		fieldPanel.add(document);

		priorityPanel.add(lowPriority);
		priorityPanel.add(normalPriority);
		priorityPanel.add(urgentPriority);
		priorityPanel.setBorder(BorderFactory.createTitledBorder("Priority"));
		priorityPanel.setPreferredSize(new Dimension(250,50));

		buttonPanel.add(add);
		add.addActionListener(new ButtonListener());
		add.setActionCommand("a");

		leftPanel.add(namePanel);
		leftPanel.add(fieldPanel);
		leftPanel.add(priorityPanel);
		leftPanel.add(buttonPanel);

		leftPanel.setBorder(BorderFactory.createTitledBorder("Add Document"));
		leftPanel.setPreferredSize(new Dimension(275,190));

		inbox.setText("Inbox is empty");
		inbox.setEditable(false);
		inbox.setBackground(rightPanel.getBackground());
		rightPanel.add(inbox);

		rightPanel.setBorder(BorderFactory.createTitledBorder("Contents"));
		rightPanel.setPreferredSize(new Dimension(275,190));

		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);

		middlePanel.setBorder(BorderFactory.createTitledBorder("Inbox"));
		middlePanel.setPreferredSize(new Dimension(575,225));

		add(middlePanel);
		normalPriority.doClick();
	}

	private void buildBottomPanel()
	{
		JPanel bottomPanel = new JPanel();
		JPanel urgentPanel = new JPanel();
		JPanel normalPanel = new JPanel();
		JPanel lowPanel = new JPanel();
		urgent = new JTextArea();
		normal = new JTextArea();
		low = new JTextArea();
		readFile = new JButton("Read File");
		exit = new JButton("Exit");

		urgentPanel.add(urgent);
		urgent.setText("Urgent Priority Queue is empty");
		urgent.setEditable(false);
		urgent.setBackground(urgentPanel.getBackground());
		urgentPanel.setBorder(BorderFactory.createTitledBorder("Urgent Priority"));
		urgentPanel.setPreferredSize(new Dimension(225,190));

		normalPanel.add(normal);
		normal.setText("Normal Priority Queue is empty");
		normal.setEditable(false);
		normal.setBackground(normalPanel.getBackground());
		normalPanel.setBorder(BorderFactory.createTitledBorder("Normal Priority"));
		normalPanel.setPreferredSize(new Dimension(225,190));

		lowPanel.add(low);
		low.setText("Low Priority Queue is empty");
		low.setEditable(false);
		low.setBackground(lowPanel.getBackground());
		lowPanel.setBorder(BorderFactory.createTitledBorder("Low Priority"));
		lowPanel.setPreferredSize(new Dimension(225,190));

		readFile.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());

		readFile.setActionCommand("d");
		exit.setActionCommand("e");

		bottomPanel.setBorder(BorderFactory.createTitledBorder("Priority Queues"));

		bottomPanel.add(urgentPanel);
		bottomPanel.add(normalPanel);
		bottomPanel.add(lowPanel);
		add(bottomPanel);
		add(readFile);
		add(exit);
	}

	private void sortDocuments()
	{
		manager.sortInbox();
		refresh();
	}

	private void readDocument()
	{
		manager.readDocument();
		refresh();
	}

	private void finishDocument()
	{
		manager.finishReadingDocument();
		refresh();
	}

	private void addDocument()
	{
		if(!document.getText().equals(""))
		{
			manager.addDocument(document.getText(), priority);
			document.setText("");
			document.requestFocus();
			refresh();

		}
	}

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
				//Start a new service order based on the next 5 lines in the text file
				manager.addDocument(inputFile.nextLine(), inputFile.nextLine());

			//Close the file
			inputFile.close();
			//Refresh the window
			refresh();
		}
	}

	private void readerEnter()
	{
		manager.enterReader();
		refresh();
	}

	private void readerExit()
	{
		sort.setEnabled(false);
		read.setEnabled(false);
		finish.setEnabled(false);
		manager.exitReader();
		refresh();
	}

	private void refresh()
	{
		Vector<String> documents = manager.currentInBoxStatus();

		inbox.setText("");
		for(String doc : documents)
			inbox.append(doc + "\n");

		documents = manager.currentUrgentQueueStatus();

		urgent.setText("");
		for(String doc : documents)
			urgent.append(doc + "\n");

		documents = manager.currentNormalQueueStatus();

		normal.setText("");
		for(String doc : documents)
			normal.append(doc + "\n");

		documents = manager.currentLowQueueStatus();

		low.setText("");
		for(String doc : documents)
			low.append(doc + "\n");
		
		toRead.setText("" + manager.getNumberDocuments());
		readerStatus.setText("" + manager.currentReaderStatus());
		readerActivity.setText("" + manager.currentReaderActivityStatus());
		
		//Set Button enabled state based on current reader status and activity
		if((!urgent.getText().equals("Urgent Priority Queue is empty\n") ||
			!normal.getText().equals("Normal Priority Queue is empty\n") || 
			!low.getText().equals("Low Priority Queue is empty\n")) &&
			manager.currentReaderStatus().equals(ReaderStatus.Present) &&
			!manager.currentReaderActivityStatus().equals(ReaderActivityStatus.Reading))
				read.setEnabled(true);
		else 
			read.setEnabled(false);
		
		if(!inbox.getText().equals("Inbox is empty\n") && manager.currentReaderStatus().equals(ReaderStatus.Present))
			sort.setEnabled(true);
		else
			sort.setEnabled(false);
		
		if(manager.currentReaderStatus().equals(ReaderStatus.Present) &&
			manager.currentReaderActivityStatus().equals(ReaderActivityStatus.Reading))
				finish.setEnabled(true);
		else
			finish.setEnabled(false);
		
		if(manager.currentReaderActivityStatus().equals(ReaderActivityStatus.Reading))
			exitStatus.setEnabled(false);
		else
			exitStatus.setEnabled(true);
	}


	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
			case 's':
				sortDocuments();
				break;
			case 'r':
				readDocument();
				break;
			case 'f':
				finishDocument();
				break;
			case 'a':
				addDocument();
				break;
			case 'd':
				try{readFile();}
				catch (FileNotFoundException ex){ex.printStackTrace();}
				break;
			case 'e':
				System.exit(0);
			}
		}
	}

	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
			case 'e':
				readerEnter();
				break;
			case 'x':
				readerExit();
				break;
			case 'l':
				priority = "low";
				break;
			case 'n':
				priority = "normal";
				break;
			case 'u':
				priority = "urgent";
				break;
			}
		}
	}
}