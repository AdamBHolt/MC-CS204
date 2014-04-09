import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class SpellCheckerPanel extends JPanel
{
    
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
    }
    
    private void spellCheck()
    {
	
    }
    
    private void addDictionary()
    {
	
    }
    
    private void listDictionary()
    {
	
    }
    
    private void writeDictionary()
    {
	
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
		case 'c':
		    char index = e.getActionCommand().charAt(1);
		    break;
	    }
	}
    }
}