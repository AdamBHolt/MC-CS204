import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * This class provides a GUI for a program that checks the spelling of words against a dictionary of words
 * @author Adam Holt
 * @date 4/13/14
 * @class CS204
 * @time 12:00 MW
 */
public class SpellCheckerPanel extends JPanel
{
    //Member declarations
    private static final long serialVersionUID = 1L;		//Default serial ID
    private SpellCheckerManager manager;			//Manager for the data structure
    private JTextField input;					//Input field for words to check
    private JButton check, exit, add, list, write, clear;	//Action buttons
    private JPanel[] misspell;					//Array of panels to display misspelled words
    private JLabel[] errors;					//Array of labels to display misspelled words
    private JButton[] confirm;					//Array of buttons to add or ignore misspelled words
    private JRadioButton[] addToDictionary;			//Array or radio buttons to add words to the dictionary
    private JRadioButton[] ignore; 				//Array of radio buttons to ignore words
    private ButtonGroup[] group;				//Array of button groups for the radio buttons
    private final int ERRORS=5;					//Maximum number of misspelled words to display

    /**
     * Default constructor
     */
    public SpellCheckerPanel()
    {
	//SpellCheckManager to hold the dictionary of words
	manager = new SpellCheckerManager();
	//Set the panel layout to BorderLayout
	setLayout(new BorderLayout());
	//Build and add the top panel
	buildTopPanel();
	//Build and add the center panel
	buildCenterPanel();
	//Build and add the bottom panel
	buildBottomPanel();
	//Hide the misspelling panels
	hideMisspell();
    }   

    /**
     * Build and add the top panel to the outer panel
     */
    private void buildTopPanel()
    {
	//Top panel
	JPanel topPanel = new JPanel();
	//Field to get input of words to check
	input = new JTextField(50);
	//Button to check the input words
	check = new JButton("Spell-check");

	//Set the actionCommand and actionListener for the button
	check.setActionCommand("s");
	check.addActionListener(new ButtonListener());

	//Set the border and size of the panel
	topPanel.setBorder(BorderFactory.createTitledBorder("Enter Words to Check"));
	topPanel.setPreferredSize(new Dimension(100,100));

	//Add the field and button
	topPanel.add(input);
	topPanel.add(check);

	//Add the top panel to the north section of the main panel
	add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Build and add the center panel to the outer panel
     */
    private void buildCenterPanel()
    {
	//Outer center panel
	JPanel centerPanel = new JPanel(new GridLayout(ERRORS,1));
	//Panels to display misspelled words
	misspell = new JPanel[ERRORS];
	//Labels to display misspelled words
	errors = new JLabel[ERRORS];
	//Radio buttons to add words to the dictionary
	addToDictionary = new JRadioButton[ERRORS];
	//Radio buttons to ignore misspelled words
	ignore = new JRadioButton[ERRORS];
	//Button groups for the radio buttons
	group = new ButtonGroup[ERRORS];
	//Buttons to confirm adding or ignoring words
	confirm = new JButton[ERRORS];


	//Set up the misspell panels
	for(int i=0; i<ERRORS; i++)
	{
	    //Instantiate the panels and add the components to each panel
	    misspell[i] = new JPanel();
	    errors[i] = new JLabel("");
	    addToDictionary[i] = new JRadioButton("Add to Dictionary");
	    ignore[i] = new JRadioButton("Ignore");
	    group[i] = new ButtonGroup();
	    group[i].add(addToDictionary[i]);
	    group[i].add(ignore[i]);
	    confirm[i] = new JButton("Confirm...");
	    //Set the action command to indicate the index of the panel
	    confirm[i].setActionCommand("c" + i);
	    confirm[i].addActionListener(new ButtonListener());
	    misspell[i].add(errors[i]);
	    misspell[i].add(addToDictionary[i]);
	    misspell[i].add(ignore[i]);
	    misspell[i].add(confirm[i]);
	}

	//Add each misspell panel to the center panel
	for(JPanel panel : misspell)
	    centerPanel.add(panel);

	//Add the center panel to the main panel
	add(centerPanel, BorderLayout.WEST);
    }

    /**
     * Build and add the bottom panel to the main panel
     */
    private void buildBottomPanel()
    {
	//Bottom panel
	JPanel bottomPanel = new JPanel();
	//Action buttons
	exit = new JButton("Exit");
	add = new JButton("Add Dictionary");
	list = new JButton("List Dictionary");
	write = new JButton("Write Dictionary");
	clear = new JButton("Clear");

	//Set the actionCommands for the buttons
	exit.setActionCommand("e");
	add.setActionCommand("a");
	list.setActionCommand("l");
	write.setActionCommand("w");
	clear.setActionCommand("r");

	//Add actionListeners to the buttons
	exit.addActionListener(new ButtonListener());
	add.addActionListener(new ButtonListener());
	list.addActionListener(new ButtonListener());
	write.addActionListener(new ButtonListener());
	clear.addActionListener(new ButtonListener());

	//Add the buttons to the bottom panel
	bottomPanel.add(exit);
	bottomPanel.add(add);
	bottomPanel.add(list);
	bottomPanel.add(write);
	bottomPanel.add(clear);

	//Set the border and size of the bottom panel
	bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	bottomPanel.setPreferredSize(new Dimension(100,50));

	//Add the bottom panel to the main panel
	add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Hide the misspell panels and clear the radio buttons
     */
    private void hideMisspell()
    {
	//Set each panel to invisible
	for(JPanel panel : misspell)
	    panel.setVisible(false);
	//Set the text of each label to blank
	for(JLabel label : errors)
	    label.setText("");
	//Clear the selections of the button groups
	for(ButtonGroup g : group)
	    g.clearSelection();
    }

    /**
     * Show a specified panel and display the misspelled word
     * @param index Panel index to hide
     * @param label Misspelled word to set the label's text
     */
    private void showPanel(int index, String label)
    {
	//Set the label text
	errors[index].setText(label);
	//Set the panel to visible
	misspell[index].setVisible(true);
    }

    /**
     * Check the spelling of the words in the input field against the words in the dictionary
     */
    private void spellCheck()
    {
	//If the input text is not empty
	if(!input.getText().equals(""))
	{
	    //ArrayList of misspelled words
	    ArrayList<String> words;
	    //Flag to indicate whether all previous errors have been added or ignored
	    boolean clear = true;

	    //Iterate through each misspell panel and see if it is visible
	    for(int j=0; j<ERRORS; j++)
	    {
		//If any panel is visible set the flag to false
		if(misspell[j].isVisible())
		    clear = false;
	    }

	    //If the misspell panels are not clear display a message letting the user know
	    if(!clear)
		JOptionPane.showMessageDialog(null, "Choose to ignore or add existing words before performing a new check.");

	    //Otherwise, if all previously misspelled words have been addressed
	    else if(clear)
	    {
		//String to hold any misspelled words beyond the maximum
		String additionalErrors = "";

		try
		{
		    //Get an ArrayList of misspelled words from the data manager
		    words = manager.checkWords(input.getText().toLowerCase());

		    //If the ArrayList is null, all of the words are in the dictionary
		    if(words.equals(null))
			JOptionPane.showMessageDialog(null, "Found all words in Dictionary");
		    //Otherwise, at least one word is not in the dictionary
		    else
		    {
			//iterate through each word in the ArrayList until the maximum errors or length of the ArrayList is reached
			for(int i=0; i<ERRORS && i<words.size(); i++)
			    showPanel(i, words.get(i));
			//If there are any words beyond the maximum number of errors add them to a String
			for(int i=ERRORS; i<words.size(); i++)
			    additionalErrors += words.get(i) + "\n";
			//Display the additional misspelled words in a JOptionPane
			if(!additionalErrors.equals(""))
			    JOptionPane.showMessageDialog(null, "Additional words not found:\n" + additionalErrors);
		    }
		}
		catch (InvalidSpellingException e)
		{
		    //If a word is invalid display a message informing the user
		    JOptionPane.showMessageDialog(null, e.getMessage());
		}
		//Clear the input field
		input.setText("");
	    }
	}
    }

    /**
     * Allows the user to add words to the dictionary by reading a .txt or .bin file
     */
    private void addDictionary()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select a Dictionary File");

	//If the "open" option is chosen in the FileChooser
	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    //File object with the selected file
	    File selectedFile = chooser.getSelectedFile();

	    //Send the File to the manager
	    try
	    {
		manager.readDictionary(selectedFile);
	    }
	    catch (DuplicateWordException e)
	    {
		//If a word is already in the dictionary display a message informing the user
		JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	    catch (InvalidSpellingException e)
	    {
		//If a word is invalid display a message informing the user
		JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}
    }

    /**
     * Display the contents of the current dictionary in the console
     */
    private void listDictionary()
    {
	//Get the contents of the current dictionary and display it in the console
	System.out.println(manager.listDictionary());
    }

    /**
     * Write the current dictionary to a .txt or .bin file
     */
    private void writeDictionary()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Save Current Dictionary");

	//If the "save" option is chosen in the FileChooser send the File to the manager
	if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    try
	    {
		//Send the name of the selected file to the writeDictionary method of the manager
		manager.writeDictionary(new File(chooser.getSelectedFile().toString()));
	    }
	    catch (IOException e){}
	}
    }

    /**
     * Add a misspelled word to the existing dictionary
     * @param word Word to add to the dictionary
     */
    private void addWord(String word)
    {
	try
	{
	    //Send the word to be added
	    manager.addWord(word);
	}
	catch (DuplicateWordException e)
	{
	    //If a word is already in the dictionary display a message informing the user
	    JOptionPane.showMessageDialog(null, e.getMessage());
	}
	catch (InvalidSpellingException e)
	{
	    //If a word is invalid display a message informing the user
	    JOptionPane.showMessageDialog(null, e.getMessage());
	}
	//Display a message letting the user know that the word was added to the dictionary
	JOptionPane.showMessageDialog(null, "Added \"" + word + "\" to dictionary.");
    }

    /**
     * Clear the input field and hide all misspelled words
     */
    private void clearAll()
    {
	//Hide all misspelled words
	hideMisspell();
	//Set the input field to blank
	input.setText("");
    }

    /**
     * ActionListener for the buttons
     */
    private class ButtonListener implements ActionListener
    {
	/**
	 * Action to perform when a button is clicked
	 */
	public void actionPerformed(ActionEvent e)
	{
	    //Switch based on the first character of the actionCommand of the clicked button
	    switch(e.getActionCommand().charAt(0))
	    {
		//Spell Check button
		case 's':
		    spellCheck();
		    break;
		    //Exit button
		case 'e':
		    System.exit(0);
		    //Add Dictionary button
		case 'a':
		    addDictionary();
		    break;
		    //List Dictionary button
		case 'l':
		    listDictionary();
		    break;
		    //Write Dictionary button
		case 'w':
		    writeDictionary();
		    break;
		    //Clear All button
		case 'r':
		    clearAll();
		    break;
		    //Confirm buttons on misspelled words
		case 'c':
		    //Get the number that is the second character of the actionCommand - indicates the index of the panel
		    int index = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(1)));
		    //If "add to dictionary" is selected add the word to the dictionary
		    if(addToDictionary[index].isSelected())		 
			addWord(errors[index].getText());
		    //If either radio button is selected hide the panel and clear the button group
		    if(addToDictionary[index].isSelected() || ignore[index].isSelected())
		    {
			misspell[index].setVisible(false);
			errors[index].setText("");
			group[index].clearSelection();
		    }
		    break;
	    }
	}
    }
}