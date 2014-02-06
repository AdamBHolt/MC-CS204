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

		return null;
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