import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SudokuPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JLabel[] columnLabels;
	private JLabel[] rowLabels;
	private JTextField[][] cells;
	private JTextField enterRow, enterColumn, enterValue;
	private JTextField displayRow, displayColumn, possibleValues;
	private JButton enter;
	private JButton clear;
	private JButton display;
	private JButton reset;
	private JButton newGame;
	private JButton exit;
	private SudokuBoardManager manager;
	private Font regularFont, boldFont;
	private JRootPane rootPane;
	
	public SudokuPanel(JRootPane j)
	{
		rootPane = j;
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
				cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				cells[i][j].setBackground(Color.WHITE);	

				
				//top, left, bottom, right
				
				if((i==0 || i==3 || i==6) && (j==0 || j==3 || j==6))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(2,2,1,1,Color.GRAY));
				if((i==0 || i==3 || i==6) && (j==1 || j==4 || j==7))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(2,1,1,1,Color.GRAY));
				if((i==0 || i==3 || i==6) && (j==2 || j==5 || j==8))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(2,1,1,2,Color.GRAY));
				if((i==2 || i==5 || i==8) && (j==0 || j==3 || j==6))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1,2,2,1,Color.GRAY));
				if((i==2 || i==5 || i==8) && (j==1 || j==4 || j==7))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1,1,2,1,Color.GRAY));
				if((i==2 || i==5 || i==8) && (j==2 || j==5 || j==8))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.GRAY));
				
				if((i==1 || i==4 || i==7) && (j==0 || j==3 || j==6))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1,2,1,1,Color.GRAY));
				if((i==1 || i==4 || i==7) && (j==1 || j==4 || j==7))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
				if((i==1 || i==4 || i==7) && (j==2 || j==5 || j==8))
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,2,Color.GRAY));

				if((i==0 || i==1 || i==2) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8) || 
						(i==6 || i==7 || i==8) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8) ||
						((i==3 || i==4 || i==5) && (j==3 || j==4 || j==5)))
					cells[i][j].setBackground(Color.YELLOW);

				cells[i][j].addMouseListener(new FieldListener());
				cells[i][j].setActionCommand("" + i + j);
				cellPanel.add(cells[i][j]);
			}
		}

		regularFont = new Font(cells[0][0].getFont().getName(), Font.PLAIN, cells[0][0].getFont().getSize());
		boldFont = new Font(cells[0][0].getFont().getName(), Font.BOLD+Font.ITALIC, cells[0][0].getFont().getSize());
		
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
		clear = new JButton ("Clear Cell");
		display = new JButton("Display Possible Values");
		reset = new JButton("Reset Game");
		newGame = new JButton("New Game");
		exit = new JButton("Exit");

		enterPanel.setPreferredSize(new Dimension(240,150));
		displayPanel.setPreferredSize(new Dimension(240,150));
		enterPanel.setLayout(new GridLayout(4,1));
		displayPanel.setLayout(new GridLayout(4,1));
		enterPanel.setBorder(BorderFactory.createTitledBorder("Enter Value"));
		displayPanel.setBorder(BorderFactory.createTitledBorder("Display Possible Values"));

		enter.addActionListener(new ButtonListener());
		clear.addActionListener(new ButtonListener());
		display.addActionListener(new ButtonListener());
		reset.addActionListener(new ButtonListener());
		newGame.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());

		enter.setActionCommand("e");
		clear.setActionCommand("c");
		display.setActionCommand("d");
		reset.setActionCommand("r");
		newGame.setActionCommand("n");
		exit.setActionCommand("x");
		
		enter.setMnemonic(KeyEvent.VK_E);
		clear.setMnemonic(KeyEvent.VK_C);
		display.setMnemonic(KeyEvent.VK_D);
		reset.setMnemonic(KeyEvent.VK_E);
		newGame.setMnemonic(KeyEvent.VK_N);
		exit.setMnemonic(KeyEvent.VK_X);
		
		enter.setToolTipText("Enter value in the specified cell");
		clear.setToolTipText("Clear the value in the specified cell");
		display.setToolTipText("Show valid values for the specified cell");
		reset.setToolTipText("Reset the current game");
		newGame.setToolTipText("Open a file to start a new game");
		exit.setToolTipText("Exit the program");
		
		rootPane.setDefaultButton(enter);
		
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
		enterControls[3].add(clear);

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

		bottomPanel.add(reset);
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

	private void enterNumber() throws NumberFormatException
	{
		try
		{
			if(!enterRow.getText().equals("") && !enterColumn.getText().equals("") && !enterValue.getText().equals(""))
			{
				manager.setValueAt(Integer.parseInt(enterRow.getText()), 
					Integer.parseInt(enterColumn.getText()),
					Integer.parseInt(enterValue.getText()));
				redrawBoard();
				clearFields();
			}
		}
		catch (NumberFormatException exception)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void clearNumber()
	{
		if(!enterRow.getText().equals("") && !enterColumn.getText().equals(""))
		{
			manager.clearValue(Integer.parseInt(enterRow.getText()), 
					Integer.parseInt(enterColumn.getText()));
			redrawBoard();
			clearFields();
		}
	}
	
	private void resetGame()
	{
		for(int i=1; i<=9; i++)
			for(int j=1; j<=9; j++)
				manager.clearValue(i, j);		
		redrawBoard();
		clearFields();
	}

	private void displayValues() throws NumberFormatException
	{
		try
		{
			if(!displayRow.getText().equals("") && !displayColumn.getText().equals(""))
			{
				int i=0;
				int[] valid = new int[10];
				String validString = "";
				valid = manager.displayPossibleValues(Integer.parseInt(displayRow.getText()),
							Integer.parseInt(displayColumn.getText()));

				while(valid[i]!=0)
					validString+=valid[i++] + " ";

				possibleValues.setText(validString);
			}
		}
		catch (NumberFormatException exception)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newGame()
	{
		manager = new SudokuBoardManager();
		File inFile = null;
		inFile = getFile();

		if(inFile!=null)
			manager.newGame(inFile);
		
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(manager.getValueAt(i, j)==0)
					cells[i][j].setFont(regularFont);
				else cells[i][j].setFont(boldFont);
		
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
			case 'c':
				clearNumber();
				break;
			case 'd':
				displayValues();
				break;
			case 'r':
				resetGame();
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
		private Border border;
		private int r, c;
		public void mouseClicked(MouseEvent e)
		{	
			setCoords(e);
			if(manager.getWriteStatus(r, c))
			{
				enterRow.setText("" + r);
				enterColumn.setText("" + c);
				enterValue.requestFocus();
			}
		}

		public void mouseEntered(MouseEvent e)
		{
			setCoords(e);

			border = ((JComponent) e.getComponent()).getBorder();
			((JComponent) e.getComponent()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			displayRow.setText("" + r);
			displayColumn.setText("" + c);
			displayValues();
		}

		public void mouseExited(MouseEvent e)
		{
			((JComponent) e.getComponent()).setBorder(border);	
			displayRow.setText("");
			displayColumn.setText("");
			possibleValues.setText("");
		}

		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		
		private void setCoords(MouseEvent e)
		{
			switch(e.getComponent().getX())
			{
			case 1:
				c=1;
				break;
			case 49:
				c=2;
				break;
			case 97:
				c=3;
				break;
			case 145:
				c=4;
				break;
			case 193:
				c=5;
				break;
			case 241:
				c=6;
				break;
			case 289:
				c=7;
				break;
			case 337:
				c=8;
				break;
			case 385:
				c=9;
				break;
			default:
				c=0;
			}
			
			switch(e.getComponent().getY())
			{
			case 1:
				r=1;
				break;
			case 23:
				r=2;
				break;
			case 45:
				r=3;
				break;
			case 67:
				r=4;
				break;
			case 89:
				r=5;
				break;
			case 111:
				r=6;
				break;
			case 133:
				r=7;
				break;
			case 155:
				r=8;
				break;
			case 177:
				r=9;
				break;
			default:
				r=0;
			}
		}
	}
}