package g53.exceedvote.ui;


import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sun.launcher.resources.launcher;
import sun.org.mozilla.javascript.internal.annotations.JSConstructor;

import g53.exceedvote.controller.Controller;
import g53.exceedvote.domain.Question;
import g53.exceedvote.persistence.DaoFactory;
import g53.exceedvote.persistence.VoterDao;


/**
 * Class
 */

/**
 * @author 	Guysit Koonrungruang 5310547185
 * @Version 2012.November.15
 */
public class ResultUI {
	private JFrame frame;
	private Controller controller;
	private ResourceBundle language;
	private JComboBox<Question> questionslist;
	private Question[] questions;
	private JScrollPane scrollPane;
	private JTable resultTable;
	private JPanel panel;
	private String qname = "";
	private int qid; 
	private boolean isClose = false;
	
	public ResultUI(Controller control,ResourceBundle language) {
		this.controller = control;
    	this.language = language;
		frame = new JFrame(encode("Vote_Result"));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	    initComponents();
		frame.pack();
		frame.setLocation(280, 100);
	}
	public void initComponents() {
		questions = new Question[controller.getQuestion().size()];
	    controller.getQuestion().toArray(questions);
		questionslist = new JComboBox<Question>(questions);
		panel = new JPanel();
		try {
			resultTable	= new JTable(controller.voteResult(null, 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane = new JScrollPane();
		questionslist.addActionListener( new QuestionListener());
		panel.add(resultTable);
		panel.add(scrollPane);
		frame.add(questionslist,BorderLayout.NORTH);
		frame.add(panel,BorderLayout.CENTER);
		
		
		
		// confirm exit
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	public void run() {
		frame.setVisible(true);
	}
	
	public void close(){
		isClose = true;
		frame.dispose();
	}
	
	public boolean isClose(){
		return isClose;
	}
	class QuestionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			qid = (int) (questionslist.getItemAt(questionslist.getSelectedIndex()).getQuestionID());
			try {
				resultTable.setModel(controller.voteResult(null, qid));
				scrollPane.repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
//	public static void main(String[] args) {
//		Controller con = new Controller();
//		con.connect();
//		LanguageUI ui = new LanguageUI();
//		ResourceBundle language = ui.getLanguage();
//		ResultUI rui = new ResultUI(con, language);
//		rui.run();
//	}
}
