import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;

public class UpdateForm extends JFrame implements ActionListener, MouseListener{

	JMenuItem exit;
	JMenuItem back;
	JTextField txtname, txtphone;
	JComboBox cmbCity;
	ButtonGroup buttongroup;
	JRadioButton male, female;
	JButton update;
	JTable table;
	String id = "";
	
	public UpdateForm() {
		initMenuBar();
		initFrame();
	}
	
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu actionBar = new JMenu("Actions");
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		back = new JMenuItem("Back to Menu");
		back.addActionListener(this);

		actionBar.add(back);
		actionBar.add(exit);
		menuBar.add(actionBar);
		
		setJMenuBar(menuBar);
	}
	
	public void initFrame() {
		setTitle("Update");
		setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 200);
        setLayout(new GridLayout(3, 1));
        setResizable(false);
        initTable();
        initComponent();
        addButtonUpdate();
	}
	
	private void initTable() {
		Vector<String> columns = new Vector<>();
		columns.add("ID");
		columns.add("Name");
		columns.add("Phone");
		columns.add("City");
		columns.add("Gender");
		
		CustomerDAO customerDAO = new CustomerDAO();
		
		table = new JTable(customerDAO.getCustomerData(), columns) {
			//biar gabisa diganti datanya
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		//biar bisa di scroll
		table.addMouseListener(this);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 0, 500, 500);
		add(sp);
	}
	
	public void initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		JLabel name = new JLabel("Name: ");
		txtname = new JTextField();
		panel.add(name);
		panel.add(txtname);
		
		JLabel phone = new JLabel("Phone: ");
		txtphone = new JTextField();
		panel.add(phone);
		panel.add(txtphone);
		
		JLabel city = new JLabel("City: ");
		cmbCity = new JComboBox(initCity());
		panel.add(city);
		panel.add(cmbCity);
		
		JLabel gender = new JLabel("Gender: ");
		panel.add(gender);
		panel.add(addRadioButton());
		
		add(panel);

	}
	
	private Vector<String> initCity(){
		Vector<String> city = new Vector<>();
		city.add("Select City");
		city.add("Jakarta");
		city.add("Bandung");
		city.add("Bogor");
		return city;
	}
	
	private JPanel addRadioButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		male = new JRadioButton("Male");
		male.setActionCommand("Male");
		
		female = new JRadioButton("Female");
		female.setActionCommand("Female");
		
		panel.add(male);
		panel.add(female);
		add(panel);
		
		//biar gabisa pilih 2 radio button
		buttongroup = new ButtonGroup();
		buttongroup.add(male);
		buttongroup.add(female);
		
		return panel;
	}
	
	private void addButtonUpdate() {
		update = new JButton("Update Data");
		update.addActionListener(this);
		add(update);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(exit)) {
			System.exit(0);
		}else if(e.getSource().equals(back)) {
			new MainMenu();
			setVisible(false);
		}else if(e.getSource().equals(update)) {
			if(id.equals("")){
				JOptionPane.showMessageDialog(null, "Select item you want to update!");
			}else {
				CustomerDAO customerDAO = new CustomerDAO();
				customerDAO.updateData(id, txtname.getText(), txtphone.getText(), cmbCity.getSelectedItem().toString(), buttongroup.getSelection().getActionCommand().toString());
				JOptionPane.showMessageDialog(null, "Success Update!");
				new UpdateForm();
				setVisible(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectRowIndex = table.getSelectedRow();
		id = table.getValueAt(selectRowIndex, 0).toString();
		txtname.setText(table.getValueAt(selectRowIndex, 1).toString());
		txtphone.setText(table.getValueAt(selectRowIndex, 2).toString());
		cmbCity.setSelectedItem(table.getValueAt(selectRowIndex, 3).toString());
		String gender = table.getValueAt(selectRowIndex, 4).toString();
		if(gender.equals("Male")) {
			male.setSelected(true);
		}else {
			female.setSelected(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
