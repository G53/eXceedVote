package g53.exceedvote.ui;


import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import g53.exceedvote.persistence.DaoFactory;
import g53.exceedvote.persistence.VoterDao;



public class ResultUI {
	private JFrame frame;
	private VoterDao vote;
	
	public ResultUI() {
		frame = new JFrame("Vote Result");
		vote = DaoFactory.getInstance().getVoterDao();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(800, 500);
		frame.setLocation(280, 100);
	}
	public void initComponents() {
		try {
			JOptionPane.showMessageDialog(frame,new JScrollPane(new JTable(vote.voteResult(null))),"Vote Result",JOptionPane.INFORMATION_MESSAGE);
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		frame.setVisible(true);
	}
}
