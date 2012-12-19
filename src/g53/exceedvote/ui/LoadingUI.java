package g53.exceedvote.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
 * Class LoadingUI show the simple animation to user during program try to connecting to database
 */

/**
 * @author	Metas Pongmetha 5310546529
 * @Version 2012.November.18
 */
public class LoadingUI implements InterfaceUI{

	private JFrame frame = new JFrame("Loading");
	private ImageIcon loading = new ImageIcon("pic/ajax-loader.gif");
	private ResourceBundle language;

	public LoadingUI(ResourceBundle language) {
		this.language = language;
		frame = new JFrame();
		frame.setTitle(encode("connecting"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(300, 200);
		frame.setLocation(550, 250);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see g53.exceedvote.ui.InterfaceUI#initComponents()
	 */
	@Override
	public void initComponents() {
		frame.setLayout(new BorderLayout());
		JPanel pn = new JPanel(new GridLayout(1, 1));	
		pn.add(new JLabel(encode("load"), loading, JLabel.CENTER));	
		frame.add(pn);
		
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
	
	/* (non-Javadoc)
	 * @see g53.exceedvote.ui.InterfaceUI#close()
	 */
	@Override
	public void close(){
		frame.dispose();
	}
	
	/* (non-Javadoc)
	 * @see g53.exceedvote.ui.InterfaceUI#encode(java.lang.String)
	 */
	@Override
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
