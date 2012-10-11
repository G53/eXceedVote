package temp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import RecordLog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Guy2jz
 */
public class LoginUI extends RecordLog {

	/**
	 * 
	 */
	private JFrame frame;
	private JTextField inputField1;
	private JPasswordField inputField2;
	private JLabel user;
	private JLabel password;
	private JButton ok;
	private JButton cancel;
	private String userName;
	private Boolean isLogin = false;
	private Votes vote;

	public LoginUI(Votes vote) {
		frame = new JFrame();
		this.vote = vote;
		frame.setTitle("Login for vote");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
	}

	public void run() {
		frame.setVisible(true);
		record("Start program : "+this.getClass().getName());
	}

	public void close() {
		frame.dispose();
	}

	public void initComponents() {
		frame.setLayout(new GridLayout(3, 1));
		JPanel pl = new JPanel(new FlowLayout());
		JPanel pl1 = new JPanel(new FlowLayout());
		JPanel pl2 = new JPanel(new FlowLayout());
		inputField1 = new JTextField("");
		inputField1.setPreferredSize(new Dimension(200, 20));
		inputField2 = new JPasswordField(18);
		inputField2.setPreferredSize(new Dimension(200, 20));
		ok = new JButton("ok");
		ok.addActionListener(new ButtonListener());
		cancel = new JButton("cancel");
		user = new JLabel("Username : ");
		password = new JLabel("Password : ");
		pl.add(user);
		pl.add(inputField1);
		pl1.add(password);
		pl1.add(inputField2);
		pl2.add(ok);
		pl2.add(cancel);
		frame.add(pl);
		frame.add(pl1);
		frame.add(pl2);
	}

	public String getName() {
		return userName;
	}

	public Boolean getSatus() {
		return isLogin;
	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			userName = inputField1.getText();
			if (isLogin = vote.logIn(userName, inputField2.getText())) {				
				JOptionPane.showMessageDialog(null, vote.getMessage());
				close();
				isLogin = true;
			} else {
				JOptionPane.showMessageDialog(null, vote.getMessage());
			}
			inputField2.setText(null);
		}
	}
}
