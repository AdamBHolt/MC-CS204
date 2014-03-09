import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DocumentPanel extends JPanel
{
	private JLabel toRead, readerStatus, readerActivity;
	private JRadioButton enterStatus, exitStatus, lowPriority, normalPriority, highPriority; 
	private JButton sort, read, finish, add, readFile, exit;
	private JTextField document;
	private JList<String> inbox, urgent, normal, low;
	
	public DocumentPanel()
	{
		buildTopPanel();
		buildMiddlePanel();
		buildBottomPanel();
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
		exitStatus.doClick();
		
		statusPanel.add(new JLabel("ReaderStatus: "));
		statusPanel.add(readerStatus);
		
		buttonPanel.add(sort);
		buttonPanel.add(read);
		buttonPanel.add(finish);
		
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
		highPriority = new JRadioButton("High");
		add = new JButton("Add");
		inbox = new JList<String>();
		
		group.add(lowPriority);
		group.add(normalPriority);
		group.add(highPriority);
		
		namePanel.add(new JLabel("Document Name"));
		
		fieldPanel.add(document);
		
		priorityPanel.add(lowPriority);
		priorityPanel.add(normalPriority);
		priorityPanel.add(highPriority);
		priorityPanel.setBorder(BorderFactory.createTitledBorder("Priority"));
		priorityPanel.setPreferredSize(new Dimension(250,50));
		
		buttonPanel.add(add);
		
		leftPanel.add(namePanel);
		leftPanel.add(fieldPanel);
		leftPanel.add(priorityPanel);
		leftPanel.add(buttonPanel);
		
		leftPanel.setBorder(BorderFactory.createTitledBorder("Add Document"));
		leftPanel.setPreferredSize(new Dimension(275,190));
		
		inbox.add(sort, "Inbox is empty");
		rightPanel.add(inbox);
		
		rightPanel.setBorder(BorderFactory.createTitledBorder("Contents"));
		rightPanel.setPreferredSize(new Dimension(275,190));
		
		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		
		middlePanel.setBorder(BorderFactory.createTitledBorder("Inbox"));
		middlePanel.setPreferredSize(new Dimension(575,225));

		add(middlePanel);
	}
	
	private void buildBottomPanel()
	{
		JPanel bottomPanel = new JPanel();
		
		
		add(bottomPanel);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}