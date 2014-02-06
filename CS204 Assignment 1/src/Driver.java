import javax.swing.*;

public class Driver extends JFrame
{
    public static void main(String[] args)
    {
    	new Driver();
    }
    
    public Driver()
    {
    	add(new SudokuPanel());
    	setSize(500, 470);
    	setTitle("Sudoku");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setVisible(true);
    }
}