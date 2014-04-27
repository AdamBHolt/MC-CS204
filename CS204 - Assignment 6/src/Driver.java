import javax.swing.*;

public class Driver extends JFrame
{

    public Driver()
    {
	setTitle("Connections");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(new FriendPanel());
	setSize(800,610);
	setResizable(false);
	setVisible(true);
    }
    
    public static void main(String[] args)
    {
	new Driver();
    }
}