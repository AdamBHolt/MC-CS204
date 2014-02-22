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
		add(new CarPanel());
		setSize(950,600);
		setResizable(false);
		setTitle("Adam's Autobody Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}