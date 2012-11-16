package g53.exceedvote.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingUI {

	private JFrame frame = new JFrame("Loading");
	private ImageIcon loading = new ImageIcon("pic/ajax-loader.gif");

	public LoadingUI() {

		frame = new JFrame();
		frame.setTitle("Connecting to database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(300, 200);
		frame.setLocation(550, 250);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void initComponents() {
		frame.setLayout(new BorderLayout());
		JPanel pn = new JPanel(new GridLayout(1, 1));	
		pn.add(new JLabel("loading... ", loading, JLabel.CENTER));
		frame.add(pn);
	}
	
	public void close(){
		frame.dispose();
	}
}
