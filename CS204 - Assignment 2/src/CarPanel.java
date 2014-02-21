import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class CarPanel extends JPanel
{
	
	private JRadioButton owner, order, makeModelYear;
	private JTable table;
	private JTextArea list;
	private JTextField orderNumber, ownerName, make, model, year;
	private JButton start, finish, exit;
	
	public CarPanel()
	{
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(50, 50, 50, 50) );
		buildLeftPanel();
		buildRightPanel();
	}
	
	private void buildLeftPanel()
	{
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		JPanel addressPanel = new JPanel();
		JPanel sortPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		owner = new JRadioButton("Owner");
		order = new JRadioButton("Order #");
		makeModelYear = new JRadioButton("Make, Model, Year");
		ButtonGroup group = new ButtonGroup();
		table = new JTable(20,3);
		JScrollPane tableScroll = new JScrollPane(table);

		addressPanel.setBorder(BorderFactory.createTitledBorder("Adam's Autobody Shop"));
		addressPanel.add(new JLabel("123 Fake Street College Park, MD 20740"));
		addressPanel.setPreferredSize(new Dimension(390,75));
		
		group.add(owner);
		group.add(order);
		group.add(makeModelYear);
		
		owner.setActionCommand("o");
		order.setActionCommand("r");
		makeModelYear.setActionCommand("m");
		
		owner.addActionListener(new RadioListener());
		order.addActionListener(new RadioListener());
		makeModelYear.addActionListener(new RadioListener());
		
		sortPanel.setBorder(BorderFactory.createTitledBorder("Sort By"));
		sortPanel.setPreferredSize(new Dimension(300,75));
		sortPanel.add(owner);
		sortPanel.add(order);
		sortPanel.add(makeModelYear);
		
		table.setTableHeader(null);
		
		tablePanel.setBorder(BorderFactory.createTitledBorder("Car Table"));
		tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tableScroll.setPreferredSize(new Dimension(370,200));
		tablePanel.add(tableScroll);
		
		leftPanel.setPreferredSize(new Dimension(400,300));
		leftPanel.add(addressPanel);
		leftPanel.add(sortPanel);
		leftPanel.add(tablePanel);
		
		add(leftPanel, BorderLayout.WEST);
	}
	
	private void buildRightPanel()
	{
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel listPanel = new JPanel();
		JPanel detailPanel = new JPanel(new GridLayout(5,2));
		JPanel buttonPanel = new JPanel();
		list = new JTextArea(10,30);
		orderNumber = new JTextField(15);
		ownerName = new JTextField(15);
		make = new JTextField(15);
		model = new JTextField(15);
		year = new JTextField(15);
		start = new JButton("Start an Order");
		finish = new JButton("Finish an Order");
		exit = new JButton("Exit");
		
		listPanel.setBorder(BorderFactory.createTitledBorder("Car List"));
		listPanel.add(list);
		list.setEditable(false);
		
		detailPanel.setBorder(BorderFactory.createTitledBorder("Service Order Details"));
		detailPanel.setPreferredSize(new Dimension(420,200));
		detailPanel.add(new JLabel("Service Order Number"));
		detailPanel.add(orderNumber);
		detailPanel.add(new JLabel("Owner"));
		detailPanel.add(ownerName);
		detailPanel.add(new JLabel("Make"));
		detailPanel.add(make);
		detailPanel.add(new JLabel("Model"));
		detailPanel.add(model);
		detailPanel.add(new JLabel("Year"));
		detailPanel.add(year);
		
		start.setActionCommand("s");
		finish.setActionCommand("f");
		exit.setActionCommand("e");
		
		start.addActionListener(new ButtonListener());
		finish.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
		
		buttonPanel.add(start);
		buttonPanel.add(finish);
		buttonPanel.add(exit);
		
		rightPanel.setPreferredSize(new Dimension(440,400));
		rightPanel.add(listPanel);
		rightPanel.add(detailPanel);
		rightPanel.add(buttonPanel);
		add(rightPanel, BorderLayout.EAST);
	}
	
	private void sortOwner()
	{
		
	}
	
	private void sortOrder()
	{
		
	}
	
	private void sortMMY()
	{
		
	}
	
	private void startOrder()
	{
		
	}
	
	private void finishOrder()
	{
		
	}
	
	
	
	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
				case 'o':
					sortOwner();
					break;
				case 'r':
					sortOrder();
					break;
				case 'm':
					sortMMY();
					break;
				default:
			}
		}
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
				case 's':
					startOrder();
					break;
				case 'f':
					finishOrder();
					break;
				case 'e':
					System.exit(0);
				default:
			}
		}
	}
	
}