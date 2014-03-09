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
		setSize(700,700);
		add(new DocumentPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}