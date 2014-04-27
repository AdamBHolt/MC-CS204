import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FriendPanel extends JPanel
{
    private JPanel centerPanel;
    private JComboBox<String> participants, addAFriend;
    private JTextField showFName, showLName, showHomeTown, addFName, addLName, addHomeTown;
    private JTextArea friends, fOfFriends;
    private JButton friendsOfFriends, addFriend, addProfile, exit;
    
    
    public FriendPanel()
    {
	setLayout(new BorderLayout());
	buildTopPanel();
	buildCenterPanel();
	buildBottomPanel();
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
	JPanel profilePanel = new JPanel(new GridLayout(3,1));
	JPanel fNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel lNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel homeTownPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel friendPanel = new JPanel();
	
	showFName = new JTextField(15);
	showLName = new JTextField(15);
	showHomeTown = new JTextField(15);
	friends = new JTextArea(10,20);
	
	String[] temp = {"First Entry", "Second Entry"};
	participants = new JComboBox<String>(temp);
	

	
	fNamePanel.add(new JLabel("First Name    "));
	fNamePanel.add(showFName);
	lNamePanel.add(new JLabel("Last Name    "));
	lNamePanel.add(showLName);
	homeTownPanel.add(new JLabel("Homwtown   "));
	homeTownPanel.add(showHomeTown);
	
	profilePanel.add(fNamePanel);
	profilePanel.add(lNamePanel);
	profilePanel.add(homeTownPanel);
	
	friendPanel.add(friends);
	friends.setBorder(BorderFactory.createTitledBorder("Friends"));
	
	leftPanel.setBorder(BorderFactory.createTitledBorder("Choose a Profile"));
	
	leftPanel.setPreferredSize(new Dimension(325,350));
	
	leftPanel.add(participants);
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
	
	String[] temp = {"First Entry", "Second Entry"};
	addAFriend = new JComboBox<String>(temp);
	
	fOfFriends.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	upperPanel.add(friendsOfFriends);
	upperPanel.add(fOfFriends);
	
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
	
	createPanel.setBorder(BorderFactory.createTitledBorder("Create New Profile"));
	
	createPanel.setPreferredSize(new Dimension(675,125));
	//bottomPanel.setPreferredSize(new Dimension(650,300));
	
	createPanel.add(profilePanel);
	createPanel.add(addProfile);
	outerPanel.add(createPanel);
	exitPanel.add(exit);
	bottomPanel.add(outerPanel, BorderLayout.NORTH);
	bottomPanel.add(exitPanel, BorderLayout.SOUTH);
	add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private class ButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    
	}
    }
}