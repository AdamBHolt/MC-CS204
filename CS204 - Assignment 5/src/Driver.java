import javax.swing.*;

public class Driver extends JFrame
{

    public static void main(String[] args)
    {
	new Driver();
    }
    
    public Driver()
    {
	setTitle("Spell Checker");
	setSize(600,450);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(new SpellCheckerPanel());
	setVisible(true);
    }
}