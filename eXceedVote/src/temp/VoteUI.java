package temp;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Guy2jz
 */
public class VoteUI extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel question;
    private JButton summit;
    private JButton exit;
    private JRadioButton team1;
    private JRadioButton team2;
    private JRadioButton team3;
    private JLabel teaml1;
    private JLabel teaml2;
    private JLabel teaml3;
    public String teamName = "team1";
    public Boolean isSummit = false;
    
    public VoteUI(){
        this.setTitle("Vote for Projects");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.pack();
    }
    public void run(){
        this.setVisible(true);
    }
    
    public String getVoteTeam(){
    	return teamName;
    }
    
    public void close(){
    	this.dispose();
    }
    public void initComponents(){
        getContentPane().setLayout(new GridLayout(3,1));
        JPanel pl = new JPanel(new FlowLayout());
        JPanel pl1 = new JPanel(new FlowLayout());
        JPanel pl2 = new JPanel(new FlowLayout());
        summit = new JButton("summit") ;
        summit.addActionListener(new ButtonListener());
        exit = new JButton("exit");
        question = new JLabel("What is your favorite project?");
        team1 = new JRadioButton();
        team1.setActionCommand("team1");
        team1.setSelected(true);
        team2 = new JRadioButton();
        team1.setActionCommand("team2");
        team3 = new JRadioButton();
        team1.setActionCommand("team3");
        teaml1 = new JLabel("Project 1");
        teaml2 = new JLabel("Project 2");
        teaml3 = new JLabel("Project 3");
        team1.addActionListener(new RadioButtonListener());
        team2.addActionListener(new RadioButtonListener());
        team3.addActionListener(new RadioButtonListener());
        ButtonGroup group = new ButtonGroup();
        group.add(team1);
        group.add(team2);
        group.add(team3);
        pl.add(question);
        pl1.add(team1);
        pl1.add(teaml1);
        pl1.add(team2); 
        pl1.add(teaml2);
        pl1.add(team3);            
        pl1.add(teaml3);
        pl2.add(summit);
        pl2.add(exit);
        getContentPane().add(pl);
        getContentPane().add(pl1);
        getContentPane().add(pl2);
    }
    
    class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			isSummit = true;
			close();
			JOptionPane.showMessageDialog(null,"You voted."+teamName+"\n Thank you");
		}
    }
    
    class RadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			teamName = arg0.getActionCommand().toString();
		}
    }
}
