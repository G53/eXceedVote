/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Guy2jz
 */
public class Vote extends JFrame {

    private JLabel question;
    private String qname = " : Please select the question";
    private JButton summit;
    private JButton exit;
    private JRadioButton team1;
    private JRadioButton team2;
    private JRadioButton team3;
    private JLabel teaml1;
    private JLabel teaml2;
    private JLabel teaml3;
    private JComboBox patternList;
    private Font font = new Font("Tahoma", Font.BOLD, 16);
    private Font font2 = new Font("Tahoma", Font.PLAIN, 14);

    private TextArea tx = new TextArea();

    public Vote() {
        this.setTitle("Vote for Projects");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.pack();
        this.setSize(800, 500);
        this.setLocation(280, 100);
    }

    public void run() {
        this.setVisible(true);
    }

    public void initComponents() {
        this.setLayout(new BorderLayout());
        JPanel pl = new JPanel(new GridLayout(10, 1));
        JPanel scllpl = new JPanel(new GridLayout(1, 2));       
        JPanel questionPlane = new JPanel(new GridLayout(1, 2));
        questionPlane.setBorder(BorderFactory.createTitledBorder("Question Detail"));
        JPanel buttonPlane = new JPanel(new BorderLayout());
        JPanel insideButtonPlane = new JPanel(new FlowLayout());
        JScrollPane scrollPane = new JScrollPane(pl);
        scrollPane.setPreferredSize(new Dimension(700, 50));
        JScrollPane txscll = new JScrollPane(tx);
        txscll.setBorder(BorderFactory.createTitledBorder("Project Description"));
        scrollPane.setPreferredSize(new Dimension(700, 50));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Choose Project Team"));
        String[] patternExamples = {
            "Best Performance",
            "Best Graphic",
            "Best Design",
            "Best Creation",
            "Overall"
        };
        patternList = new JComboBox(patternExamples);
        patternList.setEditable(true);
        patternList.addActionListener(new questionListener());
        patternList.setFont(font2);
        
        ButtonGroup btg = new ButtonGroup();

        summit = new JButton();
        summit.setFont(font2);
        summit.setText("summit");        
        exit = new JButton();
        exit.setFont(font2);
        exit.setText("exit");
        exit.addActionListener(new exitListener());
        question = new JLabel();
        question.setFont(font);
        question.setText("Question" + qname);
        team1 = new JRadioButton();
        team1.setFont(font);
        team1.setText("Project 1");
        team2 = new JRadioButton();
        team2.setFont(font);
        team2.setText("Project 2");
        team3 = new JRadioButton();
        team3.setFont(font);
        team3.setText("Project 3");
        tx.setFont(font2);
        tx.setText("Project Description");
        tx.setEditable(false);
        
        team1.addActionListener(new showDesListener());
        team2.addActionListener(new showDesListener());
        team3.addActionListener(new showDesListener());
        questionPlane.add(question);
        questionPlane.add(patternList);
        pl.add(team1);
        pl.add(team2);
        pl.add(team3);
        btg.add(team1);
        btg.add(team2);
        btg.add(team3);
        insideButtonPlane.add(summit);
        insideButtonPlane.add(exit);
        buttonPlane.add(insideButtonPlane  ,BorderLayout.EAST);
        scllpl.add(scrollPane);
        scllpl.add(txscll);
        this.add(scllpl , BorderLayout.CENTER);
        this.add(questionPlane , BorderLayout.NORTH);
        this.add(buttonPlane , BorderLayout.SOUTH);
    }
    
    class exitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            dispose(); 
        }
    }
    
    class showDesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            tx.setText("This is a " + s + " project description");
        }
    }
    
    class questionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            qname = (" : " + patternList.getSelectedItem().toString());
            question.setText("Question "+qname);
        }
    }
}
