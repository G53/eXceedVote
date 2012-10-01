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
import javax.swing.JTextField;

/**
 *
 * @author Guy2jz
 */
public class Login extends JFrame{
    
    private JTextField inputField1;
    private JTextField inputField2;
    private JLabel user;
    private JLabel password;
    private JButton ok;
    private JButton cancel;
    
    public Login(){
        this.setTitle("Loging in ...");
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
        inputField1 = new JTextField("");
        inputField1.setPreferredSize(new Dimension(200, 20));
        inputField2 = new JTextField("");
        inputField2.setPreferredSize(new Dimension(200, 20));
        ok = new JButton("ok") ;
        cancel = new JButton("cancel");
        user = new JLabel("Username : ");
        password = new JLabel("Password : ");
        pl.add(user);
        pl.add(inputField1);
        pl1.add(password);
        pl1.add(inputField2);
        pl2.add(ok);
        pl2.add(cancel);
        this.add(pl);
        this.add(pl1);
        this.add(pl2);
    }
}
