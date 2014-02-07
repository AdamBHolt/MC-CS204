import javax.swing.*;

public class Driver extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
    {
    	new Driver();
    }
    
    public Driver()
    {
    	add(new SudokuPanel(getRootPane()));
    	setSize(500, 470);
    	setResizable(false);
    	setTitle("Sudoku");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    }
}