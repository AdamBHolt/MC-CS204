import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SpellCheckerPanel extends JPanel
{
    
    private SpellCheckerManager manager;
    private JTextField input;
    private JButton check, exit, add, list, write, clear;
    private JPanel[] misspell;
    private JLabel[] errors;
    private JButton[] confirm;
    private JRadioButton[] addToDictionary;
    private JRadioButton[] ignore; 
    private ButtonGroup[] group;
    private final int ERRORS=5;
    
    public SpellCheckerPanel()
    {
	manager = new SpellCheckerManager();
	setLayout(new BorderLayout());
	buildTopPanel();
	buildCenterPanel();
	buildBottomPanel();
	hideMisspell();
    }   
    
    private void buildTopPanel()
    {
	
	JPanel topPanel = new JPanel();
	input = new JTextField(50);
	check = new JButton("Spell-check");
	
	check.setActionCommand("s");
	check.addActionListener(new ButtonListener());
	
	topPanel.setBorder(BorderFactory.createTitledBorder("Enter Words to Check"));
	topPanel.setPreferredSize(new Dimension(100,100));
	topPanel.add(input);
	topPanel.add(check);
	add(topPanel, BorderLayout.NORTH);
    }
    
    private void buildCenterPanel()
    {
	JPanel centerPanel = new JPanel(new GridLayout(5,1));
	misspell = new JPanel[ERRORS];
	errors = new JLabel[ERRORS];
	addToDictionary = new JRadioButton[ERRORS];
	ignore = new JRadioButton[ERRORS];
	group = new ButtonGroup[ERRORS];
	confirm = new JButton[ERRORS];
	
	
	for(int i=0; i<ERRORS; i++)
	{
	    misspell[i] = new JPanel();
	    errors[i] = new JLabel("");
	    addToDictionary[i] = new JRadioButton("Add to Dictionary");
	    ignore[i] = new JRadioButton("Ignore");
	    group[i] = new ButtonGroup();
	    group[i].add(addToDictionary[i]);
	    group[i].add(ignore[i]);
	    confirm[i] = new JButton("Confirm...");
	    confirm[i].setActionCommand("c" + i);
	    confirm[i].addActionListener(new ButtonListener());
	    
	    misspell[i].add(errors[i]);
	    misspell[i].add(addToDictionary[i]);
	    misspell[i].add(ignore[i]);
	    misspell[i].add(confirm[i]);
	}
	
	for(JPanel panel : misspell)
	    centerPanel.add(panel);
	
	add(centerPanel, BorderLayout.WEST);
    }
    
    private void buildBottomPanel()
    {
	JPanel bottomPanel = new JPanel();
	exit = new JButton("Exit");
	add = new JButton("Add Dictionary");
	list = new JButton("List Dictionary");
	write = new JButton("Write Dictionary");
	clear = new JButton("Clear");
	
	exit.setActionCommand("e");
	add.setActionCommand("a");
	list.setActionCommand("l");
	write.setActionCommand("w");
	clear.setActionCommand("r");
	
	exit.addActionListener(new ButtonListener());
	add.addActionListener(new ButtonListener());
	list.addActionListener(new ButtonListener());
	write.addActionListener(new ButtonListener());
	clear.addActionListener(new ButtonListener());
	
	bottomPanel.add(exit);
	bottomPanel.add(add);
	bottomPanel.add(list);
	bottomPanel.add(write);
	bottomPanel.add(clear);
	
	bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	bottomPanel.setPreferredSize(new Dimension(100,50));
	add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void hideMisspell()
    {
	for(JPanel panel : misspell)
	    panel.setVisible(false);
	for(JLabel label : errors)
	    label.setText("");
	for(ButtonGroup g : group)
	    g.clearSelection();
    }
    
    private void shopwPanel(int index, String label)
    {
	errors[index].setText(label);
	misspell[index].setVisible(true);
    }
    
    private void spellCheck()
    {
	if(!input.getText().equals(""))
	{
	    int i=0;
	    try
	    {
		for(String word : manager.checkWords(input.getText().toLowerCase()))
		{
		    errors[i].setText(word);
		    misspell[i++].setVisible(true);
		}
		if(i==0)
		    JOptionPane.showMessageDialog(null, "Found all words in Dictionary");
	    }
	    catch (InvalidSpellingException e)
	    {
		JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	    input.setText("");
	}
    }
    
    private void addDictionary()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select a Dictionary");

	//If the "open" option is chosen in the FileChooser
	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    //File object with the selected file
	    File selectedFile = chooser.getSelectedFile();
	    
	    //Send the File to the AddressBookUtility
	    try
	    {
		manager.readDictionary(selectedFile);
	    } catch (DuplicateWordException e)
	    {
		JOptionPane.showMessageDialog(null, e.getMessage());
	    } catch (InvalidSpellingException e)
	    {
		JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}
    }
    
    private void listDictionary()
    {
	System.out.println(manager.listDictionary());
    }
    
    private void writeDictionary()
    {
	//File chooser to select the file
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Save Current Dictionary");

		//If the "save" option is chosen in the FileChooser send the File to the AddressBookUtility
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		    try
		    {
			manager.writeDictionary(new File(chooser.getSelectedFile().toString()));
		    } catch (IOException e)
		    {}
    }
    
    private void addWord(String word)
    {
	try
	{
	    manager.addWord(input.getText());
	} catch (DuplicateWordException e)
	{
	    JOptionPane.showMessageDialog(null, e.getMessage());
	} catch (InvalidSpellingException e)
	{
	    JOptionPane.showMessageDialog(null, e.getMessage());
	}
    }
    
    private void clearAll()
    {
	hideMisspell();
	input.setText("");
    }
    
    private class ButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    switch(e.getActionCommand().charAt(0))
	    {
		case 's':
		    spellCheck();
		    break;
		case 'e':
		    System.exit(0);
		case 'a':
		    addDictionary();
		    break;
		case 'l':
		    listDictionary();
		    break;
		case 'w':
		    writeDictionary();
		    break;
		case 'r':
		    clearAll();
		    break;
		case 'c':
		    int index = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(1)));
		    if(addToDictionary[index].isSelected())
			addWord(errors[index].getText());
		    if(addToDictionary[index].isSelected() || ignore[index].isSelected())
		    {
			misspell[index].setVisible(false);
			group[index].clearSelection();
		    }
		    break;
	    }
	}
    }
}