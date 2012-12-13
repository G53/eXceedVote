package g53.exceedvote.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Kamolporn Sanamlao ID5310545557
 * @Version 2012.12.14
 */

public class MenuUI {
	private JFrame frame;
	private JPanel pl;
	private JPanel defaultPanel;
	private JLabel label;
	private JButton vote_button;
	private JButton result_button;

	public MenuUI() {
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(300, 120);
		frame.setLocation(550, 250);
		frame.setResizable(false);
	}

	public void run() {
		frame.setVisible(true);
	}

	public void initComponents() {
		frame.setLayout(new BorderLayout());
		defaultPanel = new JPanel(new FlowLayout()); 
		pl = new JPanel(new FlowLayout());
		label = new JLabel("What would you like to do next?");
		vote_button = new JButton("Vote");
		vote_button.setPreferredSize(new Dimension(100,40));
		vote_button.addActionListener(new VoteListener());
		result_button = new JButton("See Result");
		result_button.setPreferredSize(new Dimension(100,40));
		result_button.addActionListener(new ResultListener());
		defaultPanel.add(label);
		pl.add(vote_button);
		pl.add(result_button);
		
		frame.add(defaultPanel, BorderLayout.NORTH);
		frame.add(pl, BorderLayout.CENTER);
		
	}
	
	class VoteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ResultListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
