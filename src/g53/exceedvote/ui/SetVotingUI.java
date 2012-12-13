package g53.exceedvote.ui;

import g53.exceedvote.controller.Controller;
import g53.exceedvote.domain.Project;
import g53.exceedvote.domain.Question;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorListener;

import sun.font.EAttribute;

/**
 * @author	Guysit Koonrungruang 5310547185
 * @Version 2012.November.18
 */

public class SetVotingUI
{
	private DefaultListModel<Project> modelproject;
	private DefaultListModel<Question> modelcriteria;
	private JFrame frame;
	private JComboBox hour;
	private JComboBox min;
	private JLabel colon;
	private JButton setTime;
	private JLabel time,criteria;
	private JTextField criteriafield;
	private JButton criteriaSave;
	private JLabel projectName;
	private JTextField project;
	private JLabel teamName;
	private JTextField team;
	private JButton teamSave;
	private JLabel criteria2;
	private JList<Question> criterialist;
	private JLabel projectt;
	private JList<Project> projectlist;
	private JButton addQuestion;
	private JButton addProject;
	private Controller control;
	private ResourceBundle language;
	private ArrayList<Project> projects;
	private ArrayList<Question> questions;
	private JScrollPane scrollPane;
	private JButton browse;
	private JButton savepic;
	private String dir = null;
	private Image m = null;
	private ImageIcon img = new ImageIcon();
	private String[] houritem = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	private String[] minitem = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	
	
	public SetVotingUI(Controller control, ResourceBundle language){
		this.control = control;
    	this.language = language;
		frame = new JFrame();		
		frame.setTitle("Voting Configurating");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		frame.pack();
		frame.setSize(1024, 768);
		frame.setLocation(280, 100);
		frame.setResizable(false);
	}
	
	public void run() {
		frame.setVisible(true);
	}
	
	public void initComponents() {
		frame.setLayout(new BorderLayout());
		JPanel big = new JPanel();
		big.setLayout(new GridLayout(3,2));
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
		JPanel five = new JPanel();
		five.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel six = new JLabel(img);
		six.setLayout(new FlowLayout());
		projects = control.getProject();
		modelproject = new DefaultListModel<Project>();
		for (Project p : projects) {
			modelproject.addElement(p);
		}
		projectlist = new JList<Project>(modelproject);
		projectlist.setVisibleRowCount(6);  
	    projectlist.setFixedCellHeight(30);  
	    projectlist.setFixedCellWidth(200);  
	    projectlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questions = control.getQuestion();
		modelcriteria = new DefaultListModel<Question>();
		for (Question q : questions) {
			modelcriteria.addElement(q);
		}
		criterialist = new JList<Question>(modelcriteria);
		criterialist.setVisibleRowCount(6);  
		criterialist.setFixedCellHeight(30);  
		criterialist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		time = new JLabel("Set Timing");
		hour = new JComboBox<String>(houritem);
		min = new JComboBox<String>(minitem);
		
		colon = new JLabel(" : ");
		setTime = new JButton("Save");
		criteria = new JLabel("Criteria");
		criteriafield = new JTextField(10);
		criteriaSave = new JButton("Save");
		
		projectName = new JLabel("ProjectName");
		project = new JTextField(10);
		teamName = new JLabel("Team   Name");
		team = new JTextField(10);
		teamSave = new JButton("Save");
		scrollPane = new JScrollPane(projectlist);
		browse = new JButton("Upload Team's Picture");
		browse.addActionListener(new axnListener());
		c.anchor = GridBagConstraints.LAST_LINE_START;
		five.add(browse);
		savepic = new JButton("Save Picture");
		c.anchor = GridBagConstraints.LAST_LINE_END;
		five.add(savepic);
		three.add(criteria);
		three.add(new JScrollPane(criterialist));
		three.setBorder(BorderFactory.createTitledBorder("Criteria (In Database) :"));
		
		four.add(projectName);
		four.add(scrollPane);
		four.setBorder(BorderFactory.createTitledBorder("Project (In Database) :"));
		
		two1.add(projectName);
		two1.add(project);
		two.add(two1);
		two2.add(teamName);
		two2.add(team);
		two2.add(teamSave);
		two.add(two2);
		
		one1.add(time);
		one1.add(hour);
		one1.add(colon);
		one1.add(min);
		one1.add(setTime);
		one.add(one1);
		one2.add(criteria);
		one2.add(criteriafield);
		one2.add(criteriaSave);
		one.add(one2);
		
		big.add(one);
		big.add(three);
		big.add(two);
		big.add(four);
		big.add(six);
		big.add(five);
		frame.add(big,BorderLayout.CENTER);
		
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
	public void loadImage()
	  {
	    FileDialog dlg = new FileDialog(new JFrame(), "Choose Image", FileDialog.LOAD);
	    //set current directory
	    if(dir != null){
	      dlg.setDirectory(dir);
	    }
	    dlg.setVisible(true);
	    //get image name and path
	    String imgFile = dlg.getDirectory()+dlg.getFile();
	    dir = dlg.getDirectory();
	    //create image using filename
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    m = tk.getImage(imgFile);
	    //call repaint to draw image
	    m.flush();
	  }

	  //inner class to listen menu actions
	  class axnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e){
	      if(e.getActionCommand().equalsIgnoreCase("Upload Team's Picture")){
	        loadImage();
	        img.setImage(m);
	      }
	      else System.exit(0);
	    }
	  }
	public static void main(String[] args) {
		LanguageUI languageUI = new LanguageUI();
		ResourceBundle language = languageUI.getLanguage();
		Controller control = new Controller();
		control.connect();
		SetVotingUI ui = new SetVotingUI(control, language);
		ui.run();
	}
}
