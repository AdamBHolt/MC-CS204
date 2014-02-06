import java.io.*;
import java.util.*;

public class SudokuBoardManager implements SudokuBoardManagerInterface
{
	private int[][] gameData;
	
	public SudokuBoardManager()
	{
		gameData = new int[9][9];
	}

	public void setValueAt(int r, int c, int v)
			throws InputOutOfRangeException, ValueNotValidException
	{
		gameData[r-1][c-1] = v;
	}

	public int getValueAt(int r, int c) throws InputOutOfRangeException
	{
		return gameData[r][c];
	}

	public int[] displayPossibleValues(int r, int c)
			throws InputOutOfRangeException
	{
		int[] invalidValues = new int[27];
		int[] validValues = new int[9];
		int j=0;
		
		for(int i=0; i<9; i++)
		{
			if(gameData[r-1][i] !=0)
				invalidValues[j++] = gameData[r-1][i];
		}
		
		for(int i=0; i<9; i++)
		{
			if(gameData[i][c-1] !=0)
				invalidValues[j++] = gameData[r-1][i];
		}
		
		for(int i=0, k=0; i<9; i++)
		{
			if(!findValue(i, invalidValues))
				validValues[k++] = i;
		}
		return validValues;
	}

	private boolean findValue(int v, int[] a)
	{
		for(int e : a)
			if(e==v)
				return true;
		return false;
	}
	
	public void newGame(File gameFile)
	{
		Scanner inputFile = null;
		StringTokenizer tokenizer;
		
		try {inputFile = new Scanner(gameFile);}
		catch (FileNotFoundException e)
			{e.printStackTrace();}
		
		for(int i=0; i<9; i++)
		{
			tokenizer = new StringTokenizer(inputFile.next(), ",");
			for(int j=0; j<9; j++)
			{
				gameData[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		inputFile.close();
	}
}