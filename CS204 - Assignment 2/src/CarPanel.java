import javax.swing.*;

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
		setPreferredSize(new Dimension(700,500));
		buildLeftPanel();
		buildRightPanel();
	}
	
	private void buildLeftPanel()
	{
		JPanel leftPanel = new JPanel();		
		JPanel addressPanel = new JPanel();
		JPanel sortPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		JScrollPane tableScroll = new JScrollPane();
		owner = new JRadioButton("Owner");
		order = new JRadioButton("Order #");
		makeModelYear = new JRadioButton("Make, Model, Year");
		table = new JTable(20,3);
		ButtonGroup group = new ButtonGroup();
		
		addressPanel.setBorder(BorderFactory.createTitledBorder("Adam's Autobody Shop"));
		addressPanel.add(new JLabel("123 Fake Sttreet College Park, MD 20740"));
		addressPanel.setPreferredSize(new Dimension(350,75));
		
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
		
		tablePanel.setBorder(BorderFactory.createTitledBorder("Car Table"));
		tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableScroll.setPreferredSize(new Dimension(350,200));
		table.setPreferredSize(new Dimension(350,200));
		tableScroll.add(table);
		tablePanel.add(tableScroll);
		
		leftPanel.setPreferredSize(new Dimension(400,300));
		leftPanel.add(addressPanel);
		leftPanel.add(sortPanel);
		leftPanel.add(tablePanel);
		
		add(leftPanel, BorderLayout.WEST);
	}
	
	private void buildRightPanel()
	{
		JPanel rightPanel = new JPanel();
		JPanel listPanel = new JPanel();
		JPanel detailPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		orderNumber = new JTextField(15);
		ownerName = new JTextField(15);
		make = new JTextField(15);
		model = new JTextField(15);
		year = new JTextField(15);
		start = new JButton("Start an Order");
		finish = new JButton("Finish an Order");
		exit = new JButton("Exit");
		list = new JTextArea(10,15);
		
		detailPanel.
		
		
		buttonPanel.add(start);
		buttonPanel.add(finish);
		buttonPanel.add(exit);
		rightPanel.add(listPanel);
		rightPanel.add(detailPanel);
		rightPanel.add(buttonPanel);
		add(rightPanel, BorderLayout.EAST);
	}
	
	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(e.getActionCommand().charAt(0))
			{
				case 'o':
					break;
				case 'r':
					break;
				case 'm':
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
				case 'f':
				case 'e':
					System.exit(0);
				default:
			}
		}
	}
	
}