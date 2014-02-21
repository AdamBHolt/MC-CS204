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
		setSize(950,600);
		setResizable(false);
		setTitle("Adam's Autobody Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}