import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import dao.CustomerDAO;

public class Create extends JFrame implements ActionListener {

	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	JMenuItem exit;
	
	JTextField txtname, txtphone;
	JComboBox cmbCity;
	ButtonGroup buttongroup;
	JRadioButton male, female;
	
	public Create()  {
		// TODO Auto-generated constructor stub
		initMenuBar();
		initFrame();
	}

	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu actionBar = new JMenu("Actions");
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);

		actionBar.add(exit);
		menuBar.add(actionBar);
		
		setJMenuBar(menuBar);
	}
	
	public void initFrame() {
		setTitle("Create");
		setSize(300, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 200);
        setLayout(new GridLayout(0, 2));
        setResizable(false);
        initComponent();
	}
	
	public void initComponent() {
		JLabel name = new JLabel("Name: ");
		txtname = new JTextField();
		add(name);
		add(txtname);
		
		JLabel phone = new JLabel("Phone: ");
		txtphone = new JTextField();
		add(phone);
		add(txtphone);
		
		JLabel city = new JLabel("City: ");
		cmbCity = new JComboBox(initCity());
		add(city);
		add(cmbCity);
		
		JLabel gender = new JLabel("Gender: ");
		add(gender);
		addRadioButton();
		
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		add(save);
		add(cancel);
	}
	
	private Vector<String> initCity(){
		Vector<String> city = new Vector<>();
		city.add("Select City");
		city.add("Jakarta");
		city.add("Bandung");
		city.add("Bogor");
		return city;
	}
	
	private void addRadioButton() {
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
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(save)) {
			CustomerDAO customerDAO = new CustomerDAO();
			customerDAO.insertData(txtname.getText(), txtphone.getText(), cmbCity.getSelectedItem().toString(), buttongroup.getSelection().getActionCommand().toString());
			JOptionPane.showMessageDialog(null, "Success Input Data");
		}else if(e.getSource().equals(cancel)) {
			new MainMenu();
			setVisible(false);
		}else if(e.getSource().equals(exit)) {
			System.exit(0);
		}
	}

}
