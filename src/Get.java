import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import dao.CustomerDAO;

public class Get extends JFrame implements ActionListener {

	JMenuItem back;
	JMenuItem exit;
	
	public Get() {
		// TODO Auto-generated constructor stub
		initMenuBar();
		initFrame();
	}
	
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu actionBar = new JMenu("Actions");
		back = new JMenuItem("Back to Menu");
		back.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		
		actionBar.add(back);
		actionBar.add(exit);
		menuBar.add(actionBar);
		
		setJMenuBar(menuBar);
	}
	
	public void initFrame() {
		setTitle("Get");
		setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 200);
        setLayout(null);
        setResizable(false);
        initTable();
	}

	private void initTable() {
//		String column[] = {"Name", "Phone", "City", "Gender"};
//		String row[][] = {
//				{"Budi", "084234234", "Bogor", "Male"},
//				{"Andi", "0823523523", "Jakarta", "Male"}
//		};
		Vector<String> columns = new Vector<>();
		columns.add("ID");
		columns.add("Name");
		columns.add("Phone");
		columns.add("City");
		columns.add("Gender");
		
		CustomerDAO customerDAO = new CustomerDAO();
		
		JTable table = new JTable(customerDAO.getCustomerData(), columns) {
			//biar gabisa diganti datanya
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		//biar bisa di scroll
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 0, 500, 500);
		add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(back)) {
			new MainMenu();
			setVisible(false);
		}else if(e.getSource().equals(exit)) {
			System.exit(0);
		}
	}

}
