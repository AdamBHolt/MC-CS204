import java.io.*;
import java.util.*;

import javax.swing.*;

/**
 * Manages the data for a sudoku board game
 * @author Adam Holt
 * @date 2/9/14
 * @class CS204
 * @time 12:00 MW
 */
public class SudokuBoardManager implements SudokuBoardManagerInterface
{

	private int[][] gameData;		//Two dimensional array to hold the game data
	private boolean[][] writable;	//Two dimensional array to establish the write status of each game cell

	/**
	 * Default constructor
	 */
	public SudokuBoardManager()
	{
		//Initialize the game data arrays
		gameData = new int[9][9];
		writable = new boolean[9][9];
	}

	/**
	 * Set the value of the specified location
	 * @param r Row location to set
	 * @param c Column location to set
	 * @param v Value to set
	 * @throws InputOutOfRangeException
	 * @throws ValueNotValidException
	 */
	public void setValueAt(int r, int c, int v)
			throws InputOutOfRangeException, ValueNotValidException
	{
		//Array of the values valid for the specified location
		int[] valid = new int[10];
		//Find the valid values for the specified location
		valid = displayPossibleValues(r, c);

		//If the row column specified are greater than 9 or less than 1 throw InputOutOfRangeException
		if(r<1 || r>9 || c<1 || c>9 || v<1 || v>9)
			throw new InputOutOfRangeException();
		//If the value to set is not valid for the specified location throw ValueNotValidException
		if(!findValue(v, valid))
			throw new ValueNotValidException();

		//If the specified location is writable set the value at the location
		if(writable[r-1][c-1])
			gameData[r-1][c-1] = v;
	}
	
	/**
	 * Set the value at a location to 0
	 * @param r Row location
	 * @param c Column location
	 * @throws InputOutOfRangeException
	 */
	public void clearValue(int r, int c) throws InputOutOfRangeException
	{
		if(r<1 || r>9 || c<1 || c>9)
		if(r<1 || r>9 || c<1 || c>9)
			throw new InputOutOfRangeException();
		
		//If the specified location is writable set the value at the location to 0
		if(writable[r-1][c-1])
			gameData[r-1][c-1] = 0;
	}

	/**
	 * Get the value of the specified location
	 * @param r Row location to get
	 * @param c Column location to get
	 * @throws InputOutOfRangeException
	 */
	public int getValueAt(int r, int c) throws InputOutOfRangeException
	{
		//If the row column specified are greater than 9 or less than 1 throw InputOutOfRangeException
		if(r<1 || r>9 || c<1 || c>9)
			throw new InputOutOfRangeException();
		//REturn the value at the specified location
		return gameData[r-1][c-1];
	}

	/**
	 * Displays the allowable values at the specified location
	 * @param r Row location
	 * @param c Column location
	 * @return Array containing valid values for the specified location
	 */
	public int[] displayPossibleValues(int r, int c) throws InputOutOfRangeException
	{
		int[] invalidValues = new int[27];		//Array of invalid values
		int[] validValues = new int[10];		//Array of valid values
		int j=0;								//Index for the invalid array
		int rMin, cMin;							//Row and column minimums for small box
		int rMax, cMax;							//Row and column maximum for small box

		//If the row column specified are greater than 9 or less than 1 throw InputOutOfRangeException
		if(r<1 || r>9 || c<1 || c>9)
			throw new InputOutOfRangeException();

		//If the specified location is not writable return an array of 0s
		if(!writable[r-1][c-1])
			return validValues;

		//Check each element in the specified location's row
		for(int i=0; i<9; i++)
			//If the value at the checked location is not 0 add that value to the invalid array and increment the invalid index
			if(gameData[r-1][i] !=0)
				invalidValues[j++] = gameData[r-1][i];

		//Check each element in the specified location's column
		for(int i=0; i<9; i++)
		{
			//If the value at the checked location is not 0 add that value to the invalid array and increment the invalid index
			if(gameData[i][c-1] !=0)
				invalidValues[j++] = gameData[i][c-1];
		}

		//Set the row/column minimum/maximum based on the index of the location passed
		//These values determine the smaller 3x3 box where the specified location is in the board
		if(r<=3)
		{
			rMin=0;
			rMax=2;
		}
		else if(r<=6)
		{
			rMin=3;
			rMax=5;
		}
		else
		{
			rMin=6;
			rMax=8;
		}
		if(c<=3)
		{
			cMin=0;
			cMax=2;
		}
		else if(c<=6)
		{
			cMin=3;
			cMax=5;
		}
		else
		{
			cMin=6;
			cMax=8;
		}

		//Check each element in the 3x3 box
		for(int i=rMin; i<=rMax; i++)
			for(int k=cMin; k<=cMax; k++)
				//If the value at the checked location is not 0 add that value to the invalid array and increment the invalid index
				if(gameData[i][k]!=0)
					invalidValues[j++] = gameData[i][k];

		//Increment from 1 to 9
		for(int i=1, k=0; i<=9; i++)
		{
			//If a number is not found in the invalid array
			//Place it in the current index of the valid array and increment the index
			if(!findValue(i, invalidValues))
				validValues[k++] = i;
		}
		//Return the valid array
		return validValues;
	}

	/**
	 * Linear search algorithm to locate a specific value in a passed array
	 * @param v Value to locate
	 * @param a Array to search
	 * @return True if the value is found, False if it is not
	 */
	private boolean findValue(int v, int[] a)
	{
		//Check each element of the array
		for(int e : a)
			//If the value is found return true
			if(e==v)
				return true;
		//If the value is not in the array, return false
		return false;
	}

	/**
	 * Determine whether the specified location is writable or not
	 * @param r Row location
	 * @param c Column location
	 * @return True if the location is writable, otherwise False
	 */
	public boolean getWriteStatus(int r, int c)
	{
		//Return the boolean value at the specified location
		return writable[r-1][c-1];
	}

	/**
	 * Create a new game
	 * @param gameFile Name of the sudoku game file
	 */
	public void newGame(File gameFile)
	{
		Scanner inputFile = null;	//Scanner to read the file
		StringTokenizer tokenizer;	//Tokenizer to parse the values

		//Attempt to create a new scanner based on the passed File object
		try {inputFile = new Scanner(gameFile);}
		catch (FileNotFoundException e)
		{e.printStackTrace();}

		//Iterate each row of the gameData array
		for(int i=1; i<=9; i++)
		{
			//Create tokens delimited by commas for each line in the file
			tokenizer = new StringTokenizer(inputFile.next(), ",");
			//Iterate each column of the gameData array
			for(int j=1; j<=9; j++)
			{
				//Place each token in the next location of the gameData array
				gameData[i-1][j-1] = Integer.parseInt(tokenizer.nextToken());
				//If the value placed is 0, set writable to true otherwise set it to false
				if(gameData[i-1][j-1]==0)
					writable[i-1][j-1] = true;
				else
					writable[i-1][j-1] = false;
			}
		}
		//Close the file
		inputFile.close();
	}

	/**
	 * Display a string representation of the sudoku board
	 * @return String representation of the game board
	 */
	public String toString()
	{
		//String to be returned
		String returnString = "";

		//Iterate through each element of the gameData aray and add its value to the String
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				returnString += gameData[i][j] + ",";
		//REturn the String
		return returnString;
	}
}