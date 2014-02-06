import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


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
	
	
	public SudokuPanel()
	{
		buildBoardPanel();
		buildInterfacePanel();
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
		
		for(JTextField[] t : cells)
		{
			for(JTextField f : t)
			{
				f = new JTextField(2);
				f.setEditable(false);
				f.setBackground(Color.WHITE);
				f.addMouseListener(new FieldListener());
				cellPanel.add(f);
			}
		}
		

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
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
				case 'e':
				case 'd':
				case 'n':
				case 'x':
					System.exit(0);
				default:
			}
		}
	}
	
	private class FieldListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			e.getComponent().setBackground(Color.YELLOW);
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			e.getComponent().setBackground(Color.WHITE);
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
