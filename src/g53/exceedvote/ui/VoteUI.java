package g53.exceedvote.ui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import g53.exceedvote.controller.Controller;
import g53.exceedvote.domain.*;
import g53.exceedvote.persistence.DaoFactory;
import g53.exceedvote.persistence.VoterDao;

/**
 * Class VoteUI show the interface for vote to user
 */

/**
 * @author Guysit Koonrungruang 5310547185
 * @Version 2012.November.18
 */
public class VoteUI extends RecordLog implements InterfaceUI{

	private JFrame frame; // frame attribute of this interface
	private JLabel question; // show label of question
	private String qname = "";
	private JButton summit;
	private JButton exit;
	private JComboBox<Question> patternList;
	private Font font = new Font("Tahoma", Font.BOLD, 16);
	private Font font2 = new Font("Tahoma", Font.PLAIN, 14);
	private ArrayList<Project> arrProject;
	private JRadioButton[] projectTeam;
	private ButtonGroup btg = new ButtonGroup();
	private JPanel pl;
	private String descrip;
	private Map<String, Project> map = new HashMap<String, Project>();
	private String s;
	private ArrayList<Question> listQues;
	private JTextPane tx = new JTextPane();
	private String temp;
	private Controller control;
	private ResourceBundle language;
	private ImageIcon images;
	
	public VoteUI(Controller control,ResourceBundle language) {
		this.control = control;
		this.language = language;
		frame = new JFrame();
		arrProject = control.getProject();
		listQues = control.getQuestion();
		pl = new JPanel(new GridLayout(10, 1));
		frame.setTitle(encode("Vote_Projects"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(800, 500);
		frame.setLocation(280, 100);
	}

	public void run() {
		frame.setVisible(true);
	}

	public void initComponents() {
		frame.setLayout(new BorderLayout());
		JPanel scllpl = new JPanel(new GridLayout(1, 2));
		JPanel questionPlane = new JPanel(new GridLayout(1, 2));
		questionPlane.setBorder(BorderFactory
				.createTitledBorder(encode("Question_Detail")));
		JPanel buttonPlane = new JPanel(new BorderLayout());
		JPanel insideButtonPlane = new JPanel(new FlowLayout());
		JScrollPane scrollPane = new JScrollPane(pl);
		scrollPane.setPreferredSize(new Dimension(700, 50));
		JScrollPane txscll = new JScrollPane(tx);
		txscll.setBorder(BorderFactory
				.createTitledBorder(encode("Project_Description")));
		scrollPane.setPreferredSize(new Dimension(700, 50));
		scrollPane.setBorder(BorderFactory
				.createTitledBorder(encode("Choose_Project_Team")));

		Question[] arrQ = new Question[listQues.size()];
		listQues.toArray(arrQ);
		patternList = new JComboBox<Question>(arrQ);
		patternList.setEditable(false);
		patternList.addActionListener(new QuestionListener());
		patternList.setFont(font2);
		summit = new JButton();
		summit.setFont(font2);
		summit.setText("Vote");
		summit.addActionListener(new VoteListener());
		exit = new JButton();
		exit.setFont(font2);
		exit.setText("Exit");
		exit.addActionListener(new ExitListener());
		question = new JLabel();
		question.setFont(font);
		question.setText(encode("Question") + patternList.getItemAt(0).getQuestion());

		tx.setFont(font2);
		descrip = encode("Project_Description")+"\n";
		tx.setText(descrip);
		tx.setEditable(false);

		questionPlane.add(question);
		questionPlane.add(patternList);

		createButton();
		insideButtonPlane.add(summit);
		insideButtonPlane.add(exit);
		buttonPlane.add(insideButtonPlane, BorderLayout.EAST);
		scllpl.add(scrollPane);
		scllpl.add(txscll);
		frame.add(scllpl, BorderLayout.CENTER);
		frame.add(questionPlane, BorderLayout.NORTH);
		frame.add(buttonPlane, BorderLayout.SOUTH);
		
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

	private void createButton() {
		projectTeam = new JRadioButton[arrProject.size()];
		for (int i = 0; i < arrProject.size(); i++) {
			map.put(arrProject.get(i).getProjectName(), arrProject.get(i));
			projectTeam[i] = new JRadioButton(arrProject.get(i)
					.getProjectName());
			projectTeam[i].addActionListener(new ShowDesListener());
			projectTeam[i].setActionCommand(arrProject.get(i).getProjectName());
			projectTeam[i].setFont(font);
			btg.add(projectTeam[i]);
			pl.add(projectTeam[i]);
		}

	}

	private void clearRadioButton() {
		btg.clearSelection();
	}

	class VoteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (btg.getSelection() == null) {
				JOptionPane.showMessageDialog(null,
						encode("Before_summit"));
			} else {
				java.util.Date date = new java.util.Date();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(
						date.getTime());
				if (control.canInsert(
						(int) control.getVoter().getId(),
						(int) map.get(btg.getSelection().getActionCommand())
								.getID(),
						(int) patternList.getItemAt(
								patternList.getSelectedIndex()).getQuestionID(),
						1, timestamp)) {
					temp = map.get(btg.getSelection().getActionCommand())
							.getTeamName();
					JOptionPane.showMessageDialog(null, encode("You_voted") +"\n"
							+ btg.getSelection().getActionCommand()
							+ "\".\nTeam Name: " + temp);
					record("Question : "
							+ patternList.getSelectedItem().toString()
							+ "| Vote - project : " + "" + temp);
					try {

						control.insertVoteDB(
								(int) control.getVoter().getId(),
								(int) map.get(
										btg.getSelection().getActionCommand())
										.getID(),
								(int) patternList.getItemAt(
										patternList.getSelectedIndex())
										.getQuestionID(), 1, timestamp);
						record("Vote have been added to database");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						record("Can't not add to database");
					}
				} else {
					temp = map.get(btg.getSelection().getActionCommand())
							.getTeamName();
					JOptionPane.showMessageDialog(null,
							encode("vote_same"),
							"Error!!", JOptionPane.ERROR_MESSAGE);
					record("You have vote the same team and question");
					record("Vote doesn't add to database");
				}

				clearRadioButton();
				frame.dispose();
				ResultUI resultUI = new ResultUI();
			}
		}
	}

	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
		}
	}

	class ShowDesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			s = e.getActionCommand();
			tx.setText(descrip + "This project is " + s + " made by " + map.get(s).getTeamName() + "\n");
			tx.insertIcon(map.get(s).getImage());
		}
	}

	class QuestionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!qname.equalsIgnoreCase(patternList.getSelectedItem()
					.toString())) {
				qname = (patternList.getItemAt(patternList.getSelectedIndex())
						.getQuestion());
				question.setText(encode("Question") + qname);
				clearRadioButton();
			}
		}
	}

	@Override
	public void close() {
		frame.dispose();
		
	}

	@Override
	public String encode(String key) {
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
