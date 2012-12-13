package g53.exceedvote.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author	Guysit Koonrungruang 5310547185
 * @Version 2012.November.18
 */

public class SetVotingUI {
	private JFrame frame;
	private JComboBox hour;
	private JComboBox min;
	private JLabel colon;
	private JButton setTime;
	private JLabel head;
	private JLabel criteria;
	private JTextField criteriafield;
	private JButton criteriaSave;
	private JLabel projectName;
	private JTextField project;
	private JLabel teamName;
	private JTextField team;
	private JButton teamSave;
	private JLabel criteria2;
	private JList<String> criterialist;
	private JLabel projectt;
	private JList<String> projectlist;	
	private String[] houritem = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
	private String[] minitem = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
	
	
	public SetVotingUI(){
		frame = new JFrame();		
		frame.setTitle("Voting Configurating");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(500, 400);
		frame.setLocation(300, 100);
		frame.setResizable(false);
	}
	
	public void run() {
		frame.setVisible(true);
	}
	
	public void initComponents() {
		frame.setLayout(new BorderLayout());
		JPanel big = new JPanel();
		big.setLayout(new GridLayout(2,2));
		JPanel one = new JPanel();
		one.setLayout(new FlowLayout());
		JPanel one1 = new JPanel();
		one1.setLayout(new FlowLayout());
		JPanel one2 = new JPanel();
		one2.setLayout(new FlowLayout());
		JPanel two = new JPanel();
		two.setLayout(new FlowLayout());
		JPanel two1 = new JPanel();
		two1.setLayout(new FlowLayout());
		JPanel two2 = new JPanel();
		two2.setLayout(new FlowLayout());
		JPanel three = new JPanel();
		three.setLayout(new FlowLayout());
		JPanel four = new JPanel();
		four.setLayout(new FlowLayout());
		
		head = new JLabel("Set Voting");
		
		hour = new JComboBox(houritem);
		min = new JComboBox(minitem);
		
		colon = new JLabel(" : ");
		setTime = new JButton("Save");
		JLabel space = new JLabel("                  ");
		criteria = new JLabel("Criteria");
		criteriafield = new JTextField(10);
		criteriaSave = new JButton("Save");
		
		projectName = new JLabel("ProjectName");
		project = new JTextField(10);
		teamName = new JLabel("Team   Name");
		team = new JTextField(10);
		teamSave = new JButton("Save");
		
		criterialist = new JList<String>();
		projectlist = new JList<String>();
		
		three.add(criteria);
		three.add(criterialist);
		
		four.add(projectName);
		four.add(projectlist);
		
		two.add(projectName);
		two1.add(project);
		two.add(two1);
		two.add(teamName);
		two2.add(team);
		two.add(two2);
		two.add(teamSave);
		
		one1.add(hour);
		one1.add(colon);
		one1.add(min);
		one1.add(space);
		one1.add(setTime);
		one.add(one1);
		one2.add(criteria);
		one2.add(criteriafield);
		one2.add(criteriaSave);
		one.add(one2);
		
		big.add(one);
		big.add(two);
		big.add(three);
		big.add(four);
		frame.add(head,BorderLayout.PAGE_START);
		frame.add(big,BorderLayout.CENTER);
		
	}
}
