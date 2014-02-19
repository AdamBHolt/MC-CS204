import javax.swing.*;

public class Driver extends JFrame
{
	
	public static void main(String[] args)
	{
		new Driver();
	}
	
	public Driver()
	{
		add(new CarPanel());
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}