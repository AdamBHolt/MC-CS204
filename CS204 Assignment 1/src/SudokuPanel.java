import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


public class SudokuPanel extends JPanel
{
	private JLabel[] columnLabels;
	private JLabel[] rowLabels;
	private JTextField[][] cells;
	private JTextField enterRow, enterColumn, enterValue;
	private JTextField displayRow, displayColumn, possibleValues;
	private JButton enter;
	private JButton display;
	private JButton newGame;
	private JButton exit;
	private Border border;
	private Border[][] borders;
	private SudokuBoardManager manager;
	
	
	public SudokuPanel()
	{
		buildBoardPanel();
		buildInterfacePanel();
		newGame();
	}
	
	private void buildBoardPanel()
	{
		JPanel boardPanel = new JPanel();
		JPanel columnPanel = new JPanel();
		JPanel rowPanel = new JPanel();
		JPanel cellPanel = new JPanel();
		columnLabels = new JLabel[9];
		rowLabels = new JLabel[9];
		cells = new JTextField[9][9];
		borders = new Border[9][9];
		
		boardPanel.setLayout(new BorderLayout());
		
		columnPanel.setPreferredSize(new Dimension(475,20));
		columnPanel.setLayout(new GridLayout(1,10));
		rowPanel.setPreferredSize(new Dimension(40,200));
		rowPanel.setLayout(new GridLayout(9,1));
		cellPanel.setPreferredSize(new Dimension(435,200));
		cellPanel.setLayout(new GridLayout(9,9));
		
		
		
		for(int i=0; i<9; i++)
		{
			columnLabels[i] = new JLabel("    " + (i + 1));
		}
		
		columnPanel.add(new JLabel(""));
		
		for(JLabel l : columnLabels)
			columnPanel.add(l);
		
		for(int i=0; i<9; i++)
		{
			rowLabels[i] = new JLabel("Row " + (i + 1));
		}
		
		for(JLabel l : rowLabels)
			rowPanel.add(l);
		
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				cells[i][j] = new JTextField(2);
				cells[i][j].setEditable(false);
				cells[i][j].setBackground(Color.WHITE);	
				
				if((i==0 || i==1 || i==2) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8) || 
						(i==6 || i==7 || i==8) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8) ||
						((i==3 || i==4 || i==5) && (j==3 || j==4 || j==5)))
					cells[i][j].setBackground(Color.YELLOW);
				
				cells[i][j].addMouseListener(new FieldListener());
				cells[i][j].setActionCommand("" + i + j);
				borders[i][j] = cells[i][j].getBorder();
				cellPanel.add(cells[i][j]);
			}
		}
		
		border = cells[0][0].getBorder();
		boardPanel.add(columnPanel, BorderLayout.NORTH);
		boardPanel.add(rowPanel, BorderLayout.WEST);
		boardPanel.add(cellPanel, BorderLayout.CENTER);
		add(boardPanel);
	}
	
	private void buildInterfacePanel()
	{
		JPanel interfacePanel = new JPanel();
		JPanel enterPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel[] enterControls = new JPanel[4];
		JPanel[] displayControls = new JPanel[4];
		
		enterRow = new JTextField(5);
		enterColumn = new JTextField(5);
		enterValue = new JTextField(5);
		displayRow = new JTextField(5);
		displayColumn = new JTextField(5);
		possibleValues = new JTextField(10);
		enter = new JButton ("Enter");
		display = new JButton("Display Possible Values");
		newGame = new JButton("New Game");
		exit = new JButton("Exit");
		
		enterPanel.setPreferredSize(new Dimension(240,150));
		displayPanel.setPreferredSize(new Dimension(240,150));
		enterPanel.setLayout(new GridLayout(4,1));
		displayPanel.setLayout(new GridLayout(4,1));
		enterPanel.setBorder(BorderFactory.createTitledBorder("Enter Value"));
		displayPanel.setBorder(BorderFactory.createTitledBorder("Display Possible Values"));
		
		enter.addActionListener(new ButtonListener());
		display.addActionListener(new ButtonListener());
		newGame.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
		
		enter.setActionCommand("e");
		display.setActionCommand("d");
		newGame.setActionCommand("n");
		exit.setActionCommand("x");
		
		possibleValues.setEditable(false);
		
		for(int i=0; i<4; i++)
		{
			enterControls[i] = new JPanel();
		}
		
		for(int i=0; i<4; i++)
		{
			displayControls[i] = new JPanel();
		}
		
		enterControls[0].add(new JLabel("Row"));
		enterControls[0].add(enterRow);
		enterControls[1].add(new JLabel("Column"));
		enterControls[1].add(enterColumn);
		enterControls[2].add(new JLabel("Value"));
		enterControls[2].add(enterValue);
		enterControls[3].add(enter);
		
		displayControls[0].add(new JLabel("Row"));
		displayControls[0].add(displayRow);
		displayControls[1].add(new JLabel("Column"));
		displayControls[1].add(displayColumn);
		displayControls[2].add(possibleValues);
		displayControls[3].add(display);
		
		for(JPanel p : enterControls)
			enterPanel.add(p);
		
		for(JPanel p : displayControls)
			displayPanel.add(p);
		
		bottomPanel.add(newGame);
		bottomPanel.add(exit);
		
		interfacePanel.add(enterPanel);
		interfacePanel.add(displayPanel);
		add(interfacePanel);
		add(bottomPanel);
	}
	
	private void redrawBoard()
	{
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(manager.getValueAt(i, j)!=0)
					cells[i][j].setText(String.valueOf(manager.getValueAt(i, j)));
				else
					cells[i][j].setText("");
	}
	
	private void clearFields()
	{
		enterRow.setText("");
		enterColumn.setText("");
		enterValue.setText("");
		displayRow.setText("");
		displayColumn.setText("");
		possibleValues.setText("");
	}
	
	private void enterNumber()
	{
		if(!enterRow.getText().equals("") && !enterColumn.getText().equals("") && !enterValue.getText().equals(""))
			manager.setValueAt(Integer.parseInt(enterRow.getText()), 
					Integer.parseInt(enterColumn.getText()),
					Integer.parseInt(enterValue.getText()));
		redrawBoard();
		clearFields();
	}
	
	private void displayValues()
	{
		manager.displayPossibleValues(Integer.parseInt(enterRow.getText()),
				Integer.parseInt(enterColumn.getText()));
	}
	
	private void newGame()
	{
		manager = new SudokuBoardManager();
		File inFile = null;
		inFile = getFile();
		
		if(inFile!=null)
			manager.newGame(inFile);
		redrawBoard();
		clearFields();
	}
	
	private File getFile()
	{
		File selectedFile = null;
		//File chooser to select the file
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select a Game File");
		//If the "open" option is chosen in the FileChooser
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			//File object with the selected file
			selectedFile = chooser.getSelectedFile();
		return selectedFile;
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
				case 'e':
					enterNumber();
					break;
				case 'd':
					displayValues();
					break;
				case 'n':
					newGame();
					break;
				case 'x':
					System.exit(0);
				default:
			}
		}
	}
	
	private class FieldListener implements MouseListener
	{
		
		public void mouseClicked(MouseEvent e)
		{	
			
		}

		public void mouseEntered(MouseEvent e)
		{
			((JComponent) e.getComponent()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		}

		public void mouseExited(MouseEvent e)
		{
			((JComponent) e.getComponent()).setBorder(border);	
		}
		
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
	}
}