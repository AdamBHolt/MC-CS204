import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is the gui for a program that manages a social network of Friends
 * It uses a DataManager to manage participants and their friends
 * @author Adam Holt
 * @date 4/27/14
 * @class CS204
 * @time 12:00 MW
 */
public class FriendPanel extends JPanel
{
    //Default serial ID
    private static final long serialVersionUID = 1L;
    //Data manger for the program
    private DataManager manager;
    //Components of the gui
    private JPanel centerPanel;
    private JComboBox<String> participants, addAFriend;
    private JTextField showFName, showLName, showHomeTown, addFName, addLName, addHomeTown;
    private JTextArea friends, fOfFriends;
    private JButton friendsOfFriends, addFriend, addProfile, exit;

    /**
     * Default constructor
     */
    public FriendPanel()
    {
	//Instantiate the manager
	manager = new DataManager();
	//Set the layout for the main panel
	setLayout(new BorderLayout());
	//Build and add the panels
	buildTopPanel();
	buildCenterPanel();
	buildBottomPanel();
	//Refresh the panel
	readParticipants();
	readFriends();
	refresh();
    }

    /**
     * Build and add the top panel
     */
    private void buildTopPanel()
    {
	//The panel
	JPanel topPanel = new JPanel();
	//Label to be added
	JLabel label = new JLabel("Connections");
	//Font for the label
	label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
	//Add the label to the panel
	topPanel.add(label, BorderLayout.NORTH);
	//Add the panel to the outer panel
	add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Build and add the center panel
     */
    private void buildCenterPanel()
    {
	//The panel
	centerPanel = new JPanel();
	//Build and add the left and right sub panels
	buildLeftPanel();
	buildRightPanel();
	//Add the center panel
	add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Build and add the left panel
     */
    private void buildLeftPanel()
    {
	//The panel and sub panels
	JPanel leftPanel = new JPanel();
	JPanel comboPanel = new JPanel();
	JPanel profilePanel = new JPanel(new GridLayout(3,1));
	JPanel fNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel lNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel homeTownPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel friendPanel = new JPanel();
	//Text fields
	showFName = new JTextField(15);
	showLName = new JTextField(15);
	showHomeTown = new JTextField(15);
	//The text area to show friends
	friends = new JTextArea(10,20);
	//Combo box to show participants
	participants = new JComboBox<String>();

	//Add an action listener to the combo box
	participants.addActionListener(new ComboListener());

	//Prevent the text area from being edited
	friends.setEditable(false);
	
	//Set the text fields to uneditable
	showFName.setEditable(false);
	showLName.setEditable(false);
	showHomeTown.setEditable(false);

	//Add labels and text fields to the sub panel
	fNamePanel.add(new JLabel("First Name    "));
	fNamePanel.add(showFName);
	lNamePanel.add(new JLabel("Last Name    "));
	lNamePanel.add(showLName);
	homeTownPanel.add(new JLabel("Homwtown   "));
	homeTownPanel.add(showHomeTown);

	//Add the sub panels
	profilePanel.add(fNamePanel);
	profilePanel.add(lNamePanel);
	profilePanel.add(homeTownPanel);

	//Set size and borders for the panels
	comboPanel.setPreferredSize(new Dimension(300,30));
	friends.setBorder(BorderFactory.createTitledBorder("Friends"));
	leftPanel.setBorder(BorderFactory.createTitledBorder("Choose a Profile"));
	leftPanel.setPreferredSize(new Dimension(325,350));

	//Add the sub panels
	comboPanel.add(participants);
	friendPanel.add(friends);
	leftPanel.add(comboPanel);
	leftPanel.add(profilePanel);
	leftPanel.add(friendPanel);	
	centerPanel.add(leftPanel);
    }

    /**
     * build and add the right panel
     */
    private void buildRightPanel()
    {
	//Panel and sub panels
	JPanel rightPanel = new JPanel();
	JPanel upperPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	//Action buttons
	friendsOfFriends = new JButton("Show Friends of Friends");
	addFriend = new JButton("Add Friend");
	//Text are to display friends of friends
	fOfFriends = new JTextArea(10,20);
	//Scroll pane for the text area
	JScrollPane scroll = new JScrollPane(fOfFriends);
	//Combo box to add friends
	addAFriend = new JComboBox<String>();

	//Set the action commands and add action listeners
	friendsOfFriends.setActionCommand("s");
	friendsOfFriends.addActionListener(new ButtonListener());
	addFriend.setActionCommand("f");
	addFriend.addActionListener(new ButtonListener());

	//Prevent the text are from being edited
	fOfFriends.setEditable(false);
	
	//Set the horizontal scroll policy for the scroll pane
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	//Set the borders and sizes of the panels
	upperPanel.setBorder(BorderFactory.createTitledBorder("Friends of Friends"));
	upperPanel.setPreferredSize(new Dimension(350,250));
	bottomPanel.setBorder(BorderFactory.createTitledBorder("Add a Friend"));
	bottomPanel.setPreferredSize(new Dimension(350,75));
	rightPanel.setPreferredSize(new Dimension(350,360));

	//Add items to the sub panels and add sub panels
	upperPanel.add(friendsOfFriends);
	upperPanel.add(scroll);
	bottomPanel.add(addAFriend);
	bottomPanel.add(addFriend);
	rightPanel.add(upperPanel);
	rightPanel.add(bottomPanel);
	centerPanel.add(rightPanel);
    }

    /**
     * Build and add the bottom panel
     */
    private void buildBottomPanel()
    {
	//Sub panels
	JPanel bottomPanel = new JPanel(new BorderLayout());
	JPanel createPanel = new JPanel();
	JPanel outerPanel = new JPanel();
	JPanel exitPanel = new JPanel();
	JPanel profilePanel = new JPanel(new GridLayout(3,1));
	JPanel fNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel lNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel homeTownPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	//Text fields
	addFName = new JTextField(15);
	addLName = new JTextField(15);
	addHomeTown = new JTextField(15);
	//Action buttons
	addProfile = new JButton("Add new Profile");
	exit = new JButton("Exit");

	//Add labels and text fields to the sub panel
	fNamePanel.add(new JLabel("First Name    "));
	fNamePanel.add(addFName);
	lNamePanel.add(new JLabel("Last Name    "));
	lNamePanel.add(addLName);
	homeTownPanel.add(new JLabel("Homwtown   "));
	homeTownPanel.add(addHomeTown);

	//Add the sub panels
	profilePanel.add(fNamePanel);
	profilePanel.add(lNamePanel);
	profilePanel.add(homeTownPanel);

	//Set action commands and add action listeners for the buttons
	addProfile.setActionCommand("p");
	addProfile.addActionListener(new ButtonListener());
	exit.setActionCommand("e");
	exit.addActionListener(new ButtonListener());

	//Set the borders and size for the sub panels
	createPanel.setBorder(BorderFactory.createTitledBorder("Create New Profile"));
	createPanel.setPreferredSize(new Dimension(675,125));

	//Add the sub panels
	createPanel.add(profilePanel);
	createPanel.add(addProfile);
	outerPanel.add(createPanel);
	exitPanel.add(exit);
	bottomPanel.add(outerPanel, BorderLayout.NORTH);
	bottomPanel.add(exitPanel, BorderLayout.SOUTH);
	add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Display friends of friends for the current participant
     */
    private void showFriendsOfFriends()
    {
	//Only proceed if something is selected in the combo box
	if(participants.getSelectedItem()!=null)
	{
	    //Get the list of friends of friends
	    ArrayList<String> fOF = manager.friendsOfFriends(participants.getSelectedItem().toString());
	    //Clear any existing text
	    fOfFriends.setText("");

	    //Add each friend to the text area
	    for(String s : fOF)
		fOfFriends.append(s + "\n");
	}
    }

    /**
     * Add a friend connection
     */
    private void addFriend()
    {
	//Only proceed if something is selected in each combo box
	if(participants.getSelectedItem()!=null && addAFriend.getSelectedItem()!=null)
	{
	    //Add a friend based on the selections in the combo boxes
	    manager.addFriend(participants.getSelectedItem().toString(), addAFriend.getSelectedItem().toString());
	    //Refresh the friends and profile
	    getFriends();
	    getProfile();
	}
    }

    /**
     * Add a new participant
     */
    private void addProfile()
    {
	//Only proceed if none of the text fields is empty
	if(!addFName.getText().equals("") && !addLName.getText().equals("") && !addHomeTown.getText().equals(""))
	{
	    //Add a new participant based on the names in the text fields
	    manager.addParticipant(addFName.getText(), addLName.getText(),addHomeTown.getText());
	    //Clear the text fields
	    addFName.setText("");
	    addLName.setText("");
	    addHomeTown.setText("");
	    //Refresh the screen
	    refresh();
	}
    }

    /**
     * Refresh the gui
     */
    private void refresh()
    {
	//A vector of the current participants in the graph
	Vector<String> part = manager.vectorOfParticipants();

	//Remove all items from the participant combo box
	participants.removeAllItems();
	//Check through each of the current participants
	for(String s : part)
	{
	    //Add each participant to the combo box
	    participants.addItem(s);
	    //If the current item is not the currently selected participant, also add it to the add friend combo box
	    if(!s.equals(participants.getSelectedItem().toString()))
		addAFriend.addItem(s);
	}
	//Refresh the profile and friends
	getProfile();
	getFriends();
	//Request focus for the first text field
	addFName.requestFocus();
    }

    /**
     * Get the profile of the current participant
     */
    private void getProfile()
    {
	//Only proceed if something is selected in the combo box
	if(participants.getSelectedItem()!=null && !participants.getSelectedItem().toString().equals(""))
	{
	    //Get the profile from the data manager
	    ArrayList<String> profile = manager.getProfile(participants.getSelectedItem().toString());
	    //Set the text fields to the results of the arraylist
	    showFName.setText(profile.get(0));
	    showLName.setText(profile.get(1));
	    showHomeTown.setText(profile.get(2));
	}
    }

    /**
     * Get the friends of the currrent participant
     */
    private void getFriends()
    {
	//Only proceed if something is selected in the combo box
	if(participants.getSelectedItem()!=null && !participants.getSelectedItem().toString().equals(""))
	{
	    //Get the list of friends from the data manager
	    ArrayList<String> friendList = manager.listFriends(participants.getSelectedItem().toString());
	    //Get a vector of the current participants from the data manager
	    Vector<String> part = manager.vectorOfParticipants();
	    //Clear any text in the text area
	    friends.setText("");
	    //Remove all items from the add a friend combo box
	    addAFriend.removeAllItems();

	    //Add each string for the friend list to the text area
	    for(String s : friendList)
		friends.append(s + "\n");

	    //Add each item from the participant vector to the add a friend combo box
	    //As long as it is not the same as the current participant
	    for(String s : part)
		if(!s.equals(participants.getSelectedItem().toString()))
		    addAFriend.addItem(s);

	}
    }

    /**
     * Read a file of participants
     */
    private void readParticipants()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select a List of Participants");

	//If the "open" option is chosen in the FileChooser
	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    //File object with the selected file
	    File selectedFile = chooser.getSelectedFile();

	    //Send the File to the AddressBookUtility
	    try
	    {
		//Populate the participants based on the file
		manager.populateParticipants(selectedFile);
	    }
	    catch (FileNotFoundException e){}
	}
    }

    /**
     * Read a file of friend connections
     */
    private void readFriends()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select a List of Friends");

	//If the "open" option is chosen in the FileChooser
	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    //File object with the selected file
	    File selectedFile = chooser.getSelectedFile();

	    //Send the File to the AddressBookUtility
	    try
	    {
		//Populate the friends based on the file
		manager.populateFriends(selectedFile);
	    }
	    catch (FileNotFoundException e){}
	}
    }

    /**
     * Action Listener for the action buttons
     */
    private class ButtonListener implements ActionListener
    {
	/**
	 * Action to perform when a button is clicked
	 */
	public void actionPerformed(ActionEvent e)
	{
	    //Switch based on the button's action command
	    switch(e.getActionCommand().charAt(0))
	    {
	    case 's':
		showFriendsOfFriends();
		break;
	    case 'f':
		addFriend();
		break;
	    case 'p':
		addProfile();
		break;
	    case 'e':
		System.exit(0);
	    }
	}
    }

    /**
     * Action Listener for the participants combo box
     */
    private class ComboListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    //Refresh the profile and friends
	    getProfile();
	    getFriends();
	    //Clear friend of friends text
	    fOfFriends.setText("");
	}
    }
}