package g53.exceedvote.ui;
import g53.exceedvote.controller.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Controller controller;
	private ResourceBundle language;
	private String menuSelect;

	public MenuUI(Controller control,ResourceBundle language) {
		this.controller = control;
    	this.language = language;
		frame = new JFrame();
		frame.setTitle(encode("Menu"));
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
		label = new JLabel(encode("Main_menu_voter"));
		vote_button = new JButton(encode("Vote"));
		vote_button.setPreferredSize(new Dimension(100,40));
		vote_button.addActionListener(new VoteListener());
		result_button = new JButton(encode("See_Result"));
		result_button.setPreferredSize(new Dimension(100,40));
		result_button.addActionListener(new ResultListener());
		defaultPanel.add(label);
		pl.add(vote_button);
		pl.add(result_button);
		
		frame.add(defaultPanel, BorderLayout.NORTH);
		frame.add(pl, BorderLayout.CENTER);
		
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
	
	class VoteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			menuSelect = "vote";
			
		}
		
	}
	
	class ResultListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			menuSelect = "result";
			
		}
		
	}
	
	public void close(){
		frame.dispose();
	}
	
	public String getMenuSelect() {
		return menuSelect;
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
