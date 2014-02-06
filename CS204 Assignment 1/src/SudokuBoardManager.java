import java.io.File;


public class SudokuBoardManager implements SudokuBoardManagerInterface
{
	private int[][] data;
	
	public SudokuBoardManager()
	{
		data = new int[9][9];
	}

	public void setValueAt(int r, int c, int v)
			throws InputOutOfRangeException, ValueNotValidException {
		
	}

	public int getValueAt(int r, int c) throws InputOutOfRangeException {

		return 0;
	}

	public int[] displayPossibleValues(int r, int c)
			throws InputOutOfRangeException {

		return null;
	}

	public void newGame(File gameFile) {

		
	}
	
}