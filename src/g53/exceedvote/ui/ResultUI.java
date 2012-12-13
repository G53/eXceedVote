package g53.exceedvote.ui;


import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	private String qname = "";
	private int qid; 
	
	public ResultUI(Controller control,ResourceBundle language) {
		controller = control;
    	this.language = language;
		frame = new JFrame("Vote Result");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	    initComponents();
		frame.pack();
		frame.setSize(800, 500);
		frame.setLocation(280, 100);
	}
	public void initComponents() {
		questions = new Question[controller.getQuestion().size()];
	    controller.getQuestion().toArray(questions);
		questionslist = new JComboBox<Question>(questions);
		scrollPane = new JScrollPane();
		questionslist.addActionListener( new QuestionListener());
		frame.add(questionslist,BorderLayout.NORTH);
		frame.add(scrollPane,BorderLayout.CENTER);
	}
	public void run() {
		frame.setVisible(true);
	}
	class QuestionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			qid = (int) (questionslist.getItemAt(questionslist.getSelectedIndex()).getQuestionID());
			try {
				scrollPane.add( new JTable(controller.voteResult(null, qid)));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		LanguageUI languageUI = new LanguageUI();
		ResourceBundle language = languageUI.getLanguage();
		Controller con = new Controller();
		con.connect();
		ResultUI ui = new ResultUI(con, language);
		ui.run();
	}
}
