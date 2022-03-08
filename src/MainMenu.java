import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{

	JButton button1 = new JButton("Create something");
	JButton button2 = new JButton("Read something");
	JButton button3 = new JButton("Update something");
	JButton button4 = new JButton("Delete something");
	
	JButton startButton = new JButton("Start");
	JButton exitButton = new JButton("Exit");
	
	public MainMenu() {
		initFrame();
	}
	
	private void initFrame() {
		setTitle("Main Menu");
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(400,200);
		setLayout(new BorderLayout());
		setResizable(false);
		addText();
		addMenus();
		addBottomButton();
	}
	
	public void addText() {
		JLabel text1 = new JLabel("This is my GUI");
		text1.setHorizontalAlignment(JLabel.CENTER);
		text1.setFont(new Font(null, Font.BOLD, 20));
		
		add(text1, BorderLayout.NORTH);
	}
	
	public void addMenus() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		add(panel);
	}
	
	public void addBottomButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		startButton.setBackground(Color.green);
		startButton.setForeground(Color.white);
		startButton.addActionListener(this);

		exitButton.setBackground(Color.red);
		exitButton.setForeground(Color.white);
		exitButton.addActionListener(this);
		
		panel.add(startButton);
		panel.add(exitButton);
		
		add(panel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button1)) {
			new Create();
			setVisible(false);
		}else if(e.getSource().equals(button2)) {
			new Get();
			setVisible(false);
		}else if(e.getSource().equals(button3)) {
			new UpdateForm();
			setVisible(false);
		}else if(e.getSource().equals(button4)) {
			new Delete();
			setVisible(false);
		}else if(e.getSource().equals(startButton)) {
			JOptionPane.showMessageDialog(null, "Start");
		}else if(e.getSource().equals(exitButton)) {
			System.exit(0);
		}
	}

}
