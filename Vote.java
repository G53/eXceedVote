/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Guy2jz
 */
public class Vote extends JFrame{

    private JLabel question;
    private JButton summit;
    private JButton exit;
    private JRadioButton team1;
    private JRadioButton team2;
    private JRadioButton team3;
    private JLabel teaml1;
    private JLabel teaml2;
    private JLabel teaml3;
    
    public Vote(){
        this.setTitle("Vote for Projects");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.pack();
    }
    public void run(){
        this.setVisible(true);
    }
    public void initComponents(){
        this.setLayout(new GridLayout(3,1));
        JPanel pl = new JPanel(new FlowLayout());
        JPanel pl1 = new JPanel(new FlowLayout());
        JPanel pl2 = new JPanel(new FlowLayout());
        summit = new JButton("summit") ;
        exit = new JButton("exit");
        question = new JLabel("What is your favorite project?");
        team1 = new JRadioButton();
        team2 = new JRadioButton();
        team3 = new JRadioButton();
        teaml1 = new JLabel("Project 1");
        teaml2 = new JLabel("Project 2");
        teaml3 = new JLabel("Project 3");
        pl.add(question);
        pl1.add(team1);
        pl1.add(teaml1);
        pl1.add(team2); 
        pl1.add(teaml2);
        pl1.add(team3);            
        pl1.add(teaml3);
        pl2.add(summit);
        pl2.add(exit);
        this.add(pl);
        this.add(pl1);
        this.add(pl2);
    }
}
