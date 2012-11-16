package g53.exceedvote.ui;

import g53.exceedvote.persistence.DaoFactory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class VoteUI show the selection language interface to user
 */

/**
 * @author	Guysit Koonrungruang 5310547185
 * @Version 2012.November.16
 */

public class LanguageUI{
	private JFrame frame;
	private JPanel pl;
	ImageIcon th = new ImageIcon("ui/thai.jpg");	
	ImageIcon en = new ImageIcon("ui/en.jpg");
	private JLabel thai;
	private JLabel eng;
	public LanguageUI(){
		frame = new JFrame();		
		frame.setTitle("Language User Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(300, 200);
		frame.setLocation(550, 250);
	}
	public void run() {
		frame.setVisible(true);
	}
	
	public void initComponents() {
		frame.setLayout(new BorderLayout());
		pl = new JPanel(new GridLayout(1, 2));	
		thai = new JLabel(th);
		thai.addMouseListener(new ThaiSelectingListener());
		eng = new JLabel(en);
		eng.addMouseListener(new EngSelectingListener());
		pl.add(thai);
		pl.add(eng);
		frame.add(pl);
	}

	
	class ThaiSelectingListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			LoginUI logUI = new LoginUI();
			logUI.run();
			frame.dispose();
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
	class EngSelectingListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			LoginUI logUI = new LoginUI();
			logUI.run();
			frame.dispose();
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
}