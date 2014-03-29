import javax.swing.*;

public class Driver extends JFrame
{

    public static void main(String[] args)
    {
	new Driver();

    }
    
    public Driver()
    {
	add(new AddressBookPanel());
	setSize(800,635);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }
}