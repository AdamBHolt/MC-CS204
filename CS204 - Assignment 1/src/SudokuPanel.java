import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This is a graphical interface of a sudoku board game
 * @author Adam Holt
 * @date 2/9/14
 * @class CS204
 * @time 12:00 MW
 */
public class SudokuPanel extends JPanel
{

	//Class member declarations
	private static final long serialVersionUID = 1L;				//Default serialized ID
	private JLabel[] columnLabels;									//Labels for each column
	private JLabel[] rowLabels;										//Labels for each row
	private JTextField[][] cells;									//Game board cells
	private JTextField enterRow, enterColumn, enterValue;			//Fields to enter data
	private JTextField displayRow, displayColumn, possibleValues;	//Fields to get valid values
	private JButton enter;											//Button to enter a number
	private JButton clear;											//Button to clear a cell
	private JButton display;										//Button to display valid values
	private JButton reset;											//Button to reset the game
	private JButton newGame;										//Button to start a new game
	private JButton exit;											//Button to exit the program
	private SudokuBoardManager manager;								//Data manger object
	private Font regularFont, boldFont;								//Fonts for the cells
	private JRootPane rootPane;										//JFram root pane from Driver class
	
	/**
	 * Default constructor to construct the JPanel
	 * @param j Root pane passed from JFrame object
	 */
	public SudokuPanel(JRootPane j)
	{
		//Initialize rootPane
		rootPane = j;
		//Build and add the panel with the game board elements
		buildBoardPanel();
		//Build and add the panel with the control elements
		buildInterfacePanel();
		//Start a new game
		newGame();
	}

	/**
	 * Build the game board elements of the sudoku game and add them to the panel
	 */
	private void buildBoardPanel()
	{
		JPanel boardPanel = new JPanel();							//Panel for the board
		JPanel columnPanel = new JPanel();							//Panel for the column labels
		JPanel rowPanel = new JPanel();								//Panel for the row labels
		JPanel cellPanel = new JPanel();							//Panel to hold the game cells
		columnLabels = new JLabel[9];								//Array of JLabels for the columns
		rowLabels = new JLabel[9];									//Array of JLabels for the rows
		cells = new JTextField[9][9];								//Two dimensional array of JTextFields for the game cells

		//Create a new BorderLayout
		boardPanel.setLayout(new BorderLayout());

		//Set the sizes and layouts of the panels
		columnPanel.setPreferredSize(new Dimension(475,20));
		columnPanel.setLayout(new GridLayout(1,10));
		rowPanel.setPreferredSize(new Dimension(40,200));
		rowPanel.setLayout(new GridLayout(9,1));
		cellPanel.setPreferredSize(new Dimension(435,200));
		cellPanel.setLayout(new GridLayout(9,9));

		//Create the column labels and add them to the panel
		for(int i=0; i<9; i++)
			columnLabels[i] = new JLabel("    " + (i + 1));
		columnPanel.add(new JLabel(""));
		for(JLabel l : columnLabels)
			columnPanel.add(l);

		//Create the row labels and add them to the panel
		for(int i=0; i<9; i++)
			rowLabels[i] = new JLabel("Row " + (i + 1));
		for(JLabel l : rowLabels)
			rowPanel.add(l);
		
		//Create the game cells and add them to the panel
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				//Each cells is a JTextField wide enough to hold 2 characters
				cells[i][j] = new JTextField(2);
				//The cells are not directly editable
				cells[i][j].setEditable(false);
				//Center the text
				cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				//Set the default background color
				cells[i][j].setBackground(Color.WHITE);	
				
				//Set the borders to distinguish the various sections of the board
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

				//Set the background colors to distinguish the various sections of the board
				if((i==0 || i==1 || i==2) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8) || 
						(i==6 || i==7 || i==8) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8) ||
						((i==3 || i==4 || i==5) && (j==3 || j==4 || j==5)))
					cells[i][j].setBackground(Color.YELLOW);

				//Add a mouse listener to each cell
				cells[i][j].addMouseListener(new FieldListener());
				
				//Add each cell to the panel
				cellPanel.add(cells[i][j]);
			}
		}

		//Create fonts for the cells
		regularFont = new Font(cells[0][0].getFont().getName(), Font.PLAIN, cells[0][0].getFont().getSize());
		boldFont = new Font(cells[0][0].getFont().getName(), Font.BOLD+Font.ITALIC, cells[0][0].getFont().getSize());
		
		//Add each sub panel to the appropriate section of the outer panel
		boardPanel.add(columnPanel, BorderLayout.NORTH);
		boardPanel.add(rowPanel, BorderLayout.WEST);
		boardPanel.add(cellPanel, BorderLayout.CENTER);
		//Add the outer panel to the main panel
		add(boardPanel);
	}

	/**
	 * Build the input elements of the sudoku game and add them to the panel
	 */
	private void buildInterfacePanel()
	{
		JPanel interfacePanel = new JPanel();						//Panel to hold the interface elements
		JPanel enterPanel = new JPanel();							//Panel to hold the cell entry elements
		JPanel displayPanel = new JPanel();							//Panel to hold the valid value display elements
		JPanel bottomPanel = new JPanel();							//Bottom button panel
		JPanel[] enterControls = new JPanel[4];						//Sub panels for the entry elements
		JPanel[] displayControls = new JPanel[4];					//Sub panels for the display elements
		enterRow = new JTextField(5);								//Text field for the entry row
		enterColumn = new JTextField(5);							//Text field for the entry column
		enterValue = new JTextField(5);								//Text field for the value
		displayRow = new JTextField(5);								//Text field for the display row
		displayColumn = new JTextField(5);							//Text field for the display column
		possibleValues = new JTextField(10);						//Text field for the display values
		enter = new JButton ("Enter");								//Button to enter a value
		clear = new JButton ("Clear Cell");							//Button to clear a cell
		display = new JButton("Display Possible Values");			//Button to display valid values
		reset = new JButton("Reset Game");							//Button to reset the current game
		newGame = new JButton("New Game");							//Button to create a new game
		exit = new JButton("Exit");									//Button to exit the program

		//Set the sizes and layouts of the panels
		enterPanel.setPreferredSize(new Dimension(240,150));
		displayPanel.setPreferredSize(new Dimension(240,150));
		enterPanel.setLayout(new GridLayout(4,1));
		displayPanel.setLayout(new GridLayout(4,1));
		enterPanel.setBorder(BorderFactory.createTitledBorder("Enter Value"));
		displayPanel.setBorder(BorderFactory.createTitledBorder("Display Possible Values"));

		//Add action listeners to the buttons
		enter.addActionListener(new ButtonListener());
		clear.addActionListener(new ButtonListener());
		display.addActionListener(new ButtonListener());
		reset.addActionListener(new ButtonListener());
		newGame.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());

		//Set the action commands for the buttons
		enter.setActionCommand("e");
		clear.setActionCommand("c");
		display.setActionCommand("d");
		reset.setActionCommand("r");
		newGame.setActionCommand("n");
		exit.setActionCommand("x");
		
		//Set the mnemonics for the buttons
		enter.setMnemonic(KeyEvent.VK_E);
		clear.setMnemonic(KeyEvent.VK_C);
		display.setMnemonic(KeyEvent.VK_D);
		reset.setMnemonic(KeyEvent.VK_E);
		newGame.setMnemonic(KeyEvent.VK_N);
		exit.setMnemonic(KeyEvent.VK_X);
		
		//Set the tool tips for the buttons
		enter.setToolTipText("Enter value in the specified cell");
		clear.setToolTipText("Clear the value in the specified cell");
		display.setToolTipText("Show valid values for the specified cell");
		reset.setToolTipText("Reset the current game");
		newGame.setToolTipText("Open a file to start a new game");
		exit.setToolTipText("Exit the program");
		
		//Make "Enter" the default button when hitting the <Enter> key
		rootPane.setDefaultButton(enter);
		
		//The possible values field is not directly editable
		possibleValues.setEditable(false);

		//Create the text fields
		for(int i=0; i<4; i++)
			enterControls[i] = new JPanel();
		for(int i=0; i<4; i++)
			displayControls[i] = new JPanel();

		//Add the entry elements to the sub panels
		enterControls[0].add(new JLabel("Row"));
		enterControls[0].add(enterRow);
		enterControls[1].add(new JLabel("Column"));
		enterControls[1].add(enterColumn);
		enterControls[2].add(new JLabel("Value"));
		enterControls[2].add(enterValue);
		enterControls[3].add(enter);
		enterControls[3].add(clear);

		//Add the display elements to the sub panels
		displayControls[0].add(new JLabel("Row"));
		displayControls[0].add(displayRow);
		displayControls[1].add(new JLabel("Column"));
		displayControls[1].add(displayColumn);
		displayControls[2].add(possibleValues);
		displayControls[3].add(display);

		//Add the sub panels to the entry panel
		for(JPanel p : enterControls)
			enterPanel.add(p);

		//Add the sub panels to the display panel
		for(JPanel p : displayControls)
			displayPanel.add(p);

		//Add the buttons to the bottom panel
		bottomPanel.add(reset);
		bottomPanel.add(newGame);
		bottomPanel.add(exit);

		//Add each panel to the outer panel
		interfacePanel.add(enterPanel);
		interfacePanel.add(displayPanel);
		
		//Add the outer panel and bottom panel to the main panel
		add(interfacePanel);
		add(bottomPanel);
	}

	/**
	 * Redraws the current game board to update the cells
	 */
	private void redrawBoard()
	{
		//Get the value of each cell from the data manager and set the cells to the correct number
		//0 values are displayed as blanks
		for(int i=1; i<=9; i++)
		{
			for(int j=1; j<=9; j++)
			{
				if(manager.getValueAt(i, j)!=0)
					cells[i-1][j-1].setText(String.valueOf(manager.getValueAt(i, j)));
				else
					cells[i-1][j-1].setText("");
				//if(manager.getWriteStatus(i, j))
				  //cells[i-1][j-1].setToolTipText(getValues(i, j));
			}
		}
	}

	/**
	 * Clear all the entry fields
	 */
	private void clearFields()
	{
		//Set the value of each entry field to blank
		enterRow.setText("");
		enterColumn.setText("");
		enterValue.setText("");
		displayRow.setText("");
		displayColumn.setText("");
		possibleValues.setText("");
	}

	/**
	 * Enters a value at the specified cell
	 * @throws NumberFormatException
	 */
	private void enterNumber() throws NumberFormatException
	{
		//Attempt to enter the value at the specified cell
		try
		{
			//Check if any of the entry fields are blank
			if(!enterRow.getText().equals("") && !enterColumn.getText().equals("") && !enterValue.getText().equals(""))
			{
				//Call the .setValue method of the data manger using the Integer values of the entry fields
				manager.setValueAt(Integer.parseInt(enterRow.getText()), 
					Integer.parseInt(enterColumn.getText()),
					Integer.parseInt(enterValue.getText()));
				//Redraw the board and clear the entry fields
				redrawBoard();
				clearFields();
			}
		}
		//If the entry fields do not contain integers, display an error message
		catch (NumberFormatException exception){}
	}
	
	/**
	 * Clear the specified cell
	 */
	private void clearNumber() throws NumberFormatException
	{
		//Attempt to clear the value at the specified cell
		try
		{
			//Check if the row or column entry cells are empty
			if(!enterRow.getText().equals("") && !enterColumn.getText().equals(""))
			{
				//Call the clearValue method of the data manager using the Integer values of the row and column entry fields
				manager.clearValue(Integer.parseInt(enterRow.getText()), 
						Integer.parseInt(enterColumn.getText()));
				//Redraw the board and clear the entry fields
				redrawBoard();
				clearFields();
			}
		}
		//If the entry fields do not contain integers, display an error message
		catch (NumberFormatException exception){}
	}
	
	/**
	 * Clear all editable cells to restart the current game
	 */
	private void resetGame()
	{
		//Attempt to clear the value of every cell on the board
		for(int i=1; i<=9; i++)
			for(int j=1; j<=9; j++)
				manager.clearValue(i, j);
		//Redraw the board and clear the entry fields
		redrawBoard();
		clearFields();
	}

	/**
	 * Display the valid values for the specified cell
	 * @throws NumberFormatException
	 */
	private void displayValues() throws NumberFormatException
	{
		//Attempt to get the valid values for the specified cell
		try
		{
			//Check if either display fields are blank
			if(!displayRow.getText().equals("") && !displayColumn.getText().equals(""))
			{
				//Index to scan the returned array
				int i=0;
				//Array to refer to the passed array
				int[] valid;
				//String for the valid numbers
				String validString = "";
				//Call the displayPossibleValues method of the data manager using the Integer values of the display fields
				valid = manager.displayPossibleValues(Integer.parseInt(displayRow.getText()),
							Integer.parseInt(displayColumn.getText()));

				//Scan the array of valid values until a 0 is encountered
				while(valid[i]!=0)
					//Add each value in the array to the valid String
					validString+=valid[i++] + " ";
				//Set the possible values field to the valid String
				possibleValues.setText(validString);
			}
		}
		//If the display fields do not contain integers, display an error message
		catch (NumberFormatException exception)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	private String getValues(int r, int c)
	{
	    //Index to scan the returned array
	    int i=0;
	    //Array to refer to the passed array
	    int[] valid=null;
	    //String for the valid numbers
	    String validString = "";
		if(r!=0 || c!=0)
		{
			//Call the displayPossibleValues method of the data manager using the Integer values of the display fields
			valid = manager.displayPossibleValues(r, c);

			//Scan the array of valid values until a 0 is encountered
			while(valid[i]!=0)
				//Add each value in the array to the valid String
				validString+=valid[i++] + " ";	
	
		}
		return validString;
	}
	*/

	/**
	 * Start a new game of sudoku
	 */
	private void newGame()
	{
		//Create a new data manager
		manager = new SudokuBoardManager();
		//File object to pass to the data manager
		File inFile = null;
		inFile = getFile();
		//If a file is selected pass the File object to the manager's newGame method
		if(inFile!=null)
			manager.newGame(inFile);
		
		//Check each value in the data manager, if the value is not zero, set the bold font to indicate a non-editable cell
		//Otherwise set the normal font
		for(int i=1; i<=9; i++)
			for(int j=1; j<=9; j++)
			{
				if(manager.getValueAt(i, j)==0)
				{
					cells[i-1][j-1].setFont(regularFont);
					cells[i-1][j-1].setForeground(Color.GRAY);
				}
				else
				{
					cells[i-1][j-1].setFont(boldFont);
					cells[i-1][j-1].setForeground(Color.BLACK);
				}
			}
		
		//Redraw the board and clear the entry fields
		redrawBoard();
		clearFields();
	}

	/**
	 * Get the a file to start a new game of sudoku
	 * @return File object chosen by the user
	 */
	private File getFile()
	{
		//File object to be returned
		File selectedFile = null;
		//File chooser to select the file
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select a Game File");
		//If the "open" option is chosen in the FileChooser
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			//File object with the selected file
			selectedFile = chooser.getSelectedFile();
		//Return the selected file
		return selectedFile;
	}

	/**
	 * Button Listener for the control buttons on the panel
	 */
	private class ButtonListener implements ActionListener
	{
		/**
		 * Method to perform on button click
		 */
		public void actionPerformed(ActionEvent e)
		{
			//Switch based on the action command of the clicked button
			switch(e.getActionCommand().charAt(0))
			{
			case 'e':
				//enter a number
				try{enterNumber();}
				catch(InputOutOfRangeException ex) {}
				catch(ValueNotValidException ex) {};
				break;
			case 'c':
				//Clear the current cell
				try{clearNumber();}
				catch(InputOutOfRangeException ex) {};
				break;
			case 'd':
				//Display possible values
				try{displayValues();}
				catch(InputOutOfRangeException ex) {};
				break;
			case 'r':
				//Reset the current game
				resetGame();
				break;
			case 'n':
				//Start a new game
				newGame();
				break;
			case 'x':
				//Exit the program
				System.exit(0);
			default:
			}
		}
	}

	/**
	 * Mouse listener for the game cells
	 */
	private class FieldListener implements MouseListener
	{
		//Original border of the selected cell
		private Border border;
		//Row and column locations of the selected cell
		private int r, c;
		
		/**
		 * Method performed when the mouse button is clicked
		 */
		public void mouseClicked(MouseEvent e)
		{	
			//Get the row and column location of the current cell
			setCoords(e);
			//If the current cell is writable set the text of the data entry fields to the cell references and give the input field focus
			if(manager.getWriteStatus(r, c))
			{
				enterRow.setText("" + r);
				enterColumn.setText("" + c);
				enterValue.requestFocus();
			}
		}

		/**
		 * Method performed when the mouse enters a cell
		 */
		public void mouseEntered(MouseEvent e)
		{
			//Get the row and column location of the current cell
			setCoords(e);
			//Get the border of the current cell
			border = ((JComponent) e.getComponent()).getBorder();
			//Set the border of the current cell to solid black
			((JComponent) e.getComponent()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			//Set the row and column display fields to the current cell's reference
			displayRow.setText("" + r);
			displayColumn.setText("" + c);
			//Display the valid values for the currently selected cell
			displayValues();
		}

		/**
		 * Method performed when the mouse leaves a cell
		 */
		public void mouseExited(MouseEvent e)
		{
			//Reset the current cell's border to its previous state
			((JComponent) e.getComponent()).setBorder(border);	
			//Clear the display fields
			displayRow.setText("");
			displayColumn.setText("");
			possibleValues.setText("");
		}

		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		
		/**
		 * Set the row and column references of the current cell
		 * @param e MouseEvent object
		 */
		private void setCoords(MouseEvent e)
		{
			//Initial values of the column and row values
			c=1;
			r=1;
			//Get the x and y locations of the current cell
			int i = e.getComponent().getX();
			int j = e.getComponent().getY();
			
			//Determine the column and row number of the current cell
			//Subtract the width of each cell until the value is 1
			//The number of iterations to reach 1 determines the value of the column number
			while(i > 1)
			{
				c++;
				i-=48;
			}
			
			//Subtract the height of each cell until the value is 1
			//The number of iterations to reach 1 determines the value of the row number
			while(j > 1)
			{
				r++;
				j-=22;
			}
		}
	}
}