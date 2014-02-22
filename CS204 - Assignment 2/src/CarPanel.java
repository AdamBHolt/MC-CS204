import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java .util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class CarPanel extends JPanel
{
	
	private ServiceOrderManager manager;
	private JRadioButton owner, order, makeModelYear;
	private JTable table;
	private JTextArea list;
	private JTextField orderNumber, ownerName, make, model, year;
	private JButton start, finish, exit;
	private int key;
	
	public CarPanel()
	{
		manager = new ServiceOrderManager();
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(50, 50, 50, 50) );
		buildLeftPanel();
		buildRightPanel();
		try{getOrders();}
		catch(IOException ex){};
		owner.doClick();
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
		
		owner.setActionCommand("2");
		order.setActionCommand("1");
		makeModelYear.setActionCommand("3");
		
		owner.addActionListener(new RadioListener());
		order.addActionListener(new RadioListener());
		makeModelYear.addActionListener(new RadioListener());
		
		sortPanel.setBorder(BorderFactory.createTitledBorder("Sort By"));
		sortPanel.setPreferredSize(new Dimension(300,75));
		sortPanel.add(owner);
		sortPanel.add(order);
		sortPanel.add(makeModelYear);
		
		table.setTableHeader(null);
		table.getSelectionModel().addListSelectionListener(new TableListener());
		
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
	
	private void redraw()
	{
		reset();
		sort();
	}
	
	private void sort()
	{
		String[][] aValues = manager.listByKeyTable(key);
		Vector<String> vValues = manager.listByKeyVector(key);
		int r = 0;
		
		reset();
		
		for(String[] row : aValues)
		{
			for(int c=0; c<3; c++)
				table.setValueAt(row[c], r, c);
			r++;
		}
		
		int i=1;
		
		for(String value : vValues)
		{
			list.append(value + " ");
			if(i++ % 3 == 0)
				list.append("\n");
		}
	}
	
	private void reset()
	{
		for(int i=0; i<20; i++)
			for(int j=0; j<3; j++)
				table.setValueAt("", i, j);
		list.setText("");
	}
	
	private void getOrders() throws IOException
	{
		//File chooser to select the file
		JFileChooser chooser = new JFileChooser();

		//If the "open" option is chosen in the FileChooser
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			//File object with the selected file
			File selectedFile = chooser.getSelectedFile();

			//Scanner to read from the selected file
			Scanner inputFile = new Scanner(selectedFile);

			//Read each line in the text file
			while(inputFile.hasNext())
					manager.startService(Integer.parseInt(inputFile.nextLine()), inputFile.nextLine(), inputFile.nextLine(), inputFile.nextLine(), Integer.parseInt(inputFile.nextLine()));

			//Close the file
			inputFile.close();
		}
	}
	
	public void startOrder()
	{
		if(!orderNumber.getText().equals("") &&
				!ownerName.getText().equals("") &&
				!make.getText().equals("") &&
				!model.getText().equals("") &&
				!year.getText().equals(""))
		{
			try
			{
			manager.startService(Integer.parseInt(orderNumber.getText()), ownerName.getText(), make.getText(), model.getText(), Integer.parseInt(year.getText()));
			}
			catch(ServiceOrderInUseException ex){};
			redraw();
		}
	}
	
	private void finishOrder()
	{
		if(!orderNumber.getText().equals(""))
		{
			try
			{
				manager.finishService(Integer.parseInt(orderNumber.getText()));
			}
			catch (ServiceOrderNotFoundException ex){};
		}
		redraw();
	}
	
	
	
	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			key = Integer.parseInt(e.getActionCommand());
			redraw();

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
	
	private class TableListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
		orderNumber.setText(String.valueOf(table.getValueAt(e.getFirstIndex(), 1)));
			
		}
	}
}