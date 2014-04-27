import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FriendPanel extends JPanel
{
    private DataManager manager;
    private JPanel centerPanel;
    private JComboBox<String> participants, addAFriend;
    private JTextField showFName, showLName, showHomeTown, addFName, addLName, addHomeTown;
    private JTextArea friends, fOfFriends;
    private JButton friendsOfFriends, addFriend, addProfile, exit;


    public FriendPanel()
    {
	manager = new DataManager();
	setLayout(new BorderLayout());
	buildTopPanel();
	buildCenterPanel();
	buildBottomPanel();
	readParticipants();
	readFriends();
	refresh();
    }

    private void buildTopPanel()
    {
	JPanel topPanel = new JPanel();
	JLabel label = new JLabel("Connections");
	label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
	topPanel.add(label, BorderLayout.NORTH);
	add(topPanel, BorderLayout.NORTH);
    }

    private void buildCenterPanel()
    {
	centerPanel = new JPanel();
	buildLeftPanel();
	buildRightPanel();
	add(centerPanel, BorderLayout.CENTER);
    }


    private void buildLeftPanel()
    {
	JPanel leftPanel = new JPanel();
	JPanel comboPanel = new JPanel();
	JPanel profilePanel = new JPanel(new GridLayout(3,1));
	JPanel fNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel lNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel homeTownPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel friendPanel = new JPanel();
	showFName = new JTextField(15);
	showLName = new JTextField(15);
	showHomeTown = new JTextField(15);
	friends = new JTextArea(10,20);
	participants = new JComboBox<String>();

	participants.addActionListener(new ComboListener());

	showFName.setEditable(false);
	showLName.setEditable(false);
	showHomeTown.setEditable(false);

	fNamePanel.add(new JLabel("First Name    "));
	fNamePanel.add(showFName);
	lNamePanel.add(new JLabel("Last Name    "));
	lNamePanel.add(showLName);
	homeTownPanel.add(new JLabel("Homwtown   "));
	homeTownPanel.add(showHomeTown);

	profilePanel.add(fNamePanel);
	profilePanel.add(lNamePanel);
	profilePanel.add(homeTownPanel);

	comboPanel.setPreferredSize(new Dimension(300,30));
	friends.setBorder(BorderFactory.createTitledBorder("Friends"));
	leftPanel.setBorder(BorderFactory.createTitledBorder("Choose a Profile"));
	leftPanel.setPreferredSize(new Dimension(325,350));

	comboPanel.add(participants);
	friendPanel.add(friends);
	leftPanel.add(comboPanel);
	leftPanel.add(profilePanel);
	leftPanel.add(friendPanel);	
	centerPanel.add(leftPanel);
    }

    private void buildRightPanel()
    {
	JPanel rightPanel = new JPanel();
	JPanel upperPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	friendsOfFriends = new JButton("Show Friends of Friends");
	addFriend = new JButton("Add Friend");
	fOfFriends = new JTextArea(10,20);
	JScrollPane scroll = new JScrollPane(fOfFriends);
	addAFriend = new JComboBox<String>();

	friendsOfFriends.setActionCommand("s");
	friendsOfFriends.addActionListener(new ButtonListener());
	addFriend.setActionCommand("f");
	addFriend.addActionListener(new ButtonListener());

	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


	upperPanel.add(friendsOfFriends);
	upperPanel.add(scroll);

	bottomPanel.add(addAFriend);
	bottomPanel.add(addFriend);

	upperPanel.setBorder(BorderFactory.createTitledBorder("Friends of Friends"));
	upperPanel.setPreferredSize(new Dimension(350,250));
	bottomPanel.setBorder(BorderFactory.createTitledBorder("Add a Friend"));
	bottomPanel.setPreferredSize(new Dimension(350,75));
	rightPanel.setPreferredSize(new Dimension(350,360));

	rightPanel.add(upperPanel);
	rightPanel.add(bottomPanel);
	centerPanel.add(rightPanel);
    }

    private void buildBottomPanel()
    {
	JPanel bottomPanel = new JPanel(new BorderLayout());
	JPanel createPanel = new JPanel();
	JPanel outerPanel = new JPanel();
	JPanel exitPanel = new JPanel();
	JPanel profilePanel = new JPanel(new GridLayout(3,1));
	JPanel fNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel lNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel homeTownPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	addFName = new JTextField(15);
	addLName = new JTextField(15);
	addHomeTown = new JTextField(15);
	addProfile = new JButton("Add new Profile");
	exit = new JButton("Exit");

	fNamePanel.add(new JLabel("First Name    "));
	fNamePanel.add(addFName);
	lNamePanel.add(new JLabel("Last Name    "));
	lNamePanel.add(addLName);
	homeTownPanel.add(new JLabel("Homwtown   "));
	homeTownPanel.add(addHomeTown);

	profilePanel.add(fNamePanel);
	profilePanel.add(lNamePanel);
	profilePanel.add(homeTownPanel);

	addProfile.setActionCommand("p");
	addProfile.addActionListener(new ButtonListener());
	exit.setActionCommand("e");
	exit.addActionListener(new ButtonListener());

	createPanel.setBorder(BorderFactory.createTitledBorder("Create New Profile"));
	createPanel.setPreferredSize(new Dimension(675,125));

	createPanel.add(profilePanel);
	createPanel.add(addProfile);
	outerPanel.add(createPanel);
	exitPanel.add(exit);
	bottomPanel.add(outerPanel, BorderLayout.NORTH);
	bottomPanel.add(exitPanel, BorderLayout.SOUTH);
	add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showFriendsOfFriends()
    {
	if(participants.getSelectedItem()!=null)
	{
	    ArrayList<String> fOF = manager.friendsOfFriends(participants.getSelectedItem().toString());
	    fOfFriends.setText("");

	    for(String s : fOF)
		fOfFriends.append(s + "\n");
	}
    }

    private void addFriend()
    {
	if(participants.getSelectedItem()!=null && addAFriend.getSelectedItem()!=null)
	{
	    manager.addFriend(participants.getSelectedItem().toString(), addAFriend.getSelectedItem().toString());
	    getFriends();
	    getProfile();
	}
    }

    private void addProfile()
    {
	if(!addFName.getText().equals("") && !addLName.getText().equals("") && !addHomeTown.getText().equals(""))
	{
	    manager.addParticipant(addFName.getText(), addLName.getText(),addHomeTown.getText());
	    refresh();
	    addFName.setText("");
	    addLName.setText("");
	    addHomeTown.setText("");
	    refresh();
	}
    }

    private void refresh()
    {
	Vector<String> part = manager.vectorOfParticipants();

	participants.removeAllItems();
	for(String s : part)
	{
	    participants.addItem(s);
	    if(!s.equals(participants.getSelectedItem().toString()))
		addAFriend.addItem(s);
	}
	getProfile();
	getFriends();
	addFName.requestFocus();
    }

    private void getProfile()
    {
	if(participants.getSelectedItem()!=null && !participants.getSelectedItem().toString().equals(""))
	{
	    ArrayList<String> profile = manager.getProfile(participants.getSelectedItem().toString());
	    showFName.setText(profile.get(0));
	    showLName.setText(profile.get(1));
	    showHomeTown.setText(profile.get(2));
	}
    }

    private void getFriends()
    {
	if(participants.getSelectedItem()!=null && !participants.getSelectedItem().toString().equals(""))
	{
	    ArrayList<String> friendList = manager.listFriends(participants.getSelectedItem().toString());
	    Vector<String> part = manager.vectorOfParticipants();
	    friends.setText("");
	    addAFriend.removeAllItems();

	    for(String s : friendList)
		friends.append(s + "\n");

	    for(String s : part)
		if(!s.equals(participants.getSelectedItem().toString()))
		    addAFriend.addItem(s);

	}
    }

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
		manager.populateParticipants(selectedFile);
	    }
	    catch (FileNotFoundException e){}
	}
    }

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
		manager.populateFriends(selectedFile);
	    }
	    catch (FileNotFoundException e){}
	}
    }

    private class ButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
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

    private class ComboListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    getProfile();
	    getFriends();
	}
    }
}