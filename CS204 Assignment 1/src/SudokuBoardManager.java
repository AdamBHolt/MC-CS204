import java.io.*;
import java.util.*;

import javax.swing.*;

public class SudokuBoardManager implements SudokuBoardManagerInterface
{
	private int[][] gameData;
	private boolean[][] writeable;
	
	public SudokuBoardManager()
	{
		gameData = new int[9][9];
		writeable = new boolean[9][9];
	}

	public void setValueAt(int r, int c, int v)
			throws InputOutOfRangeException, ValueNotValidException
	{
		int[] valid = new int[10];
		valid = displayPossibleValues(r, c);
		
		try
		{
			if(r<1 || r>9 || c<1 || c>9 || v<1 || v>9)
				throw new InputOutOfRangeException("Please enter a number 1 - 9");
			if(!findValue(v, valid))
				throw new ValueNotValidException("This is not a valid entry for this row and column");
			
			if(writeable[r-1][c-1])
				gameData[r-1][c-1] = v;
		}
				
		catch (InputOutOfRangeException exception)
		{
			//JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (ValueNotValidException exception)
		{
			JOptionPane.showMessageDialog(new JPanel(), "This is not a valid entry for this row and column", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int getValueAt(int r, int c) throws InputOutOfRangeException
	{
		try
		{
			if(r<0 || r>8 || c<0 || c>8)
				throw new InputOutOfRangeException("Please enter a number 1 - 9");
			return gameData[r][c];
		}
		catch(InputOutOfRangeException exception)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	public int[] displayPossibleValues(int r, int c) throws InputOutOfRangeException
	{
		int[] invalidValues = new int[27];
		int[] validValues = new int[10];
		int j=0;
		int rMin, cMin;
		int rMax, cMax;
		
		try
		{
			if(r<1 || r>9 || c<1 || c>9)
				throw new InputOutOfRangeException("Please enter a number 1 - 9");
		
			if(!writeable[r-1][c-1])
				return validValues;
		
			for(int i=0; i<9; i++)
			{
				if(gameData[r-1][i] !=0)
					invalidValues[j++] = gameData[r-1][i];
			}
		
			for(int i=0; i<9; i++)
			{
				if(gameData[i][c-1] !=0)
					invalidValues[j++] = gameData[i][c-1];
			}
		
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
		
			for(int i=rMin; i<=rMax; i++)
				for(int k=cMin; k<=cMax; k++)
					for(int l=1; l<=9; l++)
						if(gameData[i][k]==l)
							invalidValues[j++] = gameData[i][k];
		
			for(int i=1, k=0; i<=9; i++)
			{
				if(!findValue(i, invalidValues))
					validValues[k++] = i;
			}
			return validValues;
		}
		catch (InputOutOfRangeException exception)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Please enter a number 1 - 9", "Error", JOptionPane.ERROR_MESSAGE);
			return validValues;
		}
	}

	private boolean findValue(int v, int[] a)
	{
		for(int e : a)
			if(e==v)
				return true;
		return false;
	}
	
	public void clearValue(int r, int c)
	{
		if(writeable[r-1][c-1])
			gameData[r-1][c-1] = 0;
	}
	
	public boolean getWriteStatus(int r, int c)
	{
		return writeable[r-1][c-1];
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
				if(gameData[i][j]==0)
					writeable[i][j] = true;
				else
					writeable[i][j] = false;
			}
		}
		
		/*
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(gameData[i][j]);
			}
			System.out.print("\n");
		}
		
		System.out.print("\n");
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(writeable[i][j] + " ");
			}
			System.out.print("\n");
		}
		*/
		
		inputFile.close();
	}
}