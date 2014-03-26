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
	setSize(800,800);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }

}
