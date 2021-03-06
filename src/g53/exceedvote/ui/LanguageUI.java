package g53.exceedvote.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class VoteUI show the selection language interface to user
 */

/**
 * @author Guysit Koonrungruang 5310547185
 * @Version 2012.November.18
 */

public class LanguageUI {
	private static final String PIC_EN_JPG = "/pic/en.jpg";
	private static final String PIC_THAI_JPG = "/pic/thai.jpg";
	private JFrame frame;
	private JPanel pl;
	ImageIcon th = new ImageIcon(this.getClass().getResource(PIC_THAI_JPG));
	ImageIcon en = new ImageIcon(this.getClass().getResource(PIC_EN_JPG));
	private JLabel thai;
	private JLabel eng;
	private ResourceBundle language;
	public Boolean isChoose = false;

	public LanguageUI() {
		frame = new JFrame();
		frame.setTitle("Language User Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(300, 200);
		frame.setLocation(550, 250);
		frame.setResizable(false);
		language = ResourceBundle.getBundle("Language");
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

		// confirm exit
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String textYes = encode("textYes");
				String textNo = encode("textNo");
				String textTitle = encode("textTitle");
				String textMessage = encode("textMessage");
				Object[] options = { textYes, textNo };
				int result = JOptionPane
						.showOptionDialog(frame, textTitle, textMessage,
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

				if(result == JOptionPane.YES_OPTION) {
			           ((JFrame)e.getSource()).setDefaultCloseOperation(
			                   JFrame.EXIT_ON_CLOSE);
			        } else {
			           ((JFrame)e.getSource()).setDefaultCloseOperation(
			                   JFrame.DO_NOTHING_ON_CLOSE);
			        }
			}
		});
	}

	class ThaiSelectingListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			language = ResourceBundle.getBundle("Language_th");
			isChoose = true;
			// System.out.println("TH");
			frame.dispose();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class EngSelectingListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			language = ResourceBundle.getBundle("Language");
			isChoose = true;
			// System.out.println("EN");
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

	public ResourceBundle getLanguage() {
		return language;
	}
	
	/* (non-Javadoc)
	 * @see g53.exceedvote.ui.InterfaceUI#encode(java.lang.String)
	 */
	public String encode(String key){
		try {
			return new String(language.getString(key).getBytes("ISO8859-1"), "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
	}
}
