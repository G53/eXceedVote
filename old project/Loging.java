package test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Guy2jz
 */
public class Loging extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField inputField1;
    private JPasswordField inputField2;
    private JLabel user;
    private JLabel password;
    private JButton ok;
    private JButton cancel;
    private Font font = new Font("Tahoma", Font.BOLD, 16);
    private Font font2 = new Font("Tahoma", Font.PLAIN, 14);
    public String userName = null;
    public Boolean isClick = false;

    public Loging() {
        this.setTitle("Loging in ...");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.pack();
        this.setSize(400, 130);
        this.setLocation(500, 250);
        this.setResizable(false);
    }

    public void run() {
        this.setVisible(true);
    }

    public void close() {
        this.dispose();
    }

    public void initComponents() {
        this.setLayout(new BorderLayout());
        JPanel pl1 = new JPanel(new FlowLayout());
        JPanel pl2 = new JPanel(new FlowLayout());
        JPanel pl3 = new JPanel(new FlowLayout());
        inputField1 = new JTextField(15);
        inputField1.setPreferredSize(new Dimension(200, 20));
        inputField2 = new JPasswordField(15);
        inputField2.setPreferredSize(new Dimension(200, 20));
        ok = new JButton();
        ok.setFont(font2);
        ok.setText("Login");

        ok.addActionListener(new ButtonListener());
        cancel = new JButton();
        cancel.setFont(font2);
        cancel.setText("Cancel");
        cancel.addActionListener(new cancelListener());
        user = new JLabel();
        password = new JLabel();
        user.setFont(font);
        password.setFont(font);
        user.setText("Username : ");
        password.setText("Password  : ");
        inputField1.setFont(font2);
        inputField2.setFont(font2);
        pl1.add(user);
        pl1.add(inputField1);
        pl2.add(password);
        pl2.add(inputField2);
        pl3.add(ok);
        pl3.add(cancel);

        this.add(pl1, BorderLayout.NORTH);
        this.add(pl2, BorderLayout.CENTER);
        this.add(pl3, BorderLayout.SOUTH);
    }

    public String getName() {
        return userName;
    }

    public Boolean getClick() {
        return isClick;
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            userName = inputField1.getText();
            isClick = true;
            close();
        }
    }

    class cancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            close();
        }
    }
}
