import javax.swing.*;

/**
 * This class is the driver for a program that checks the spelling of words against a dictionary of words
 * @author Adam Holt
 * @date 4/13/14
 * @class CS204
 * @time 12:00 MW
 */
public class Driver extends JFrame
{
    //Default serial ID
    private static final long serialVersionUID = 1L;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)
    {
	new Driver();
    }
    
    /**
     * Default constructor
     */
    public Driver()
    {
	//Set the frame title
	setTitle("Spell Checker");
	//Set the frame size
	setSize(600,450);
	//Prevent the frame from being resized
	setResizable(false);
	//Set the default close operation
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Add anonymous SpellcheckerPanel to the frame
	add(new SpellCheckerPanel());
	//Display the frame
	setVisible(true);
    }
}