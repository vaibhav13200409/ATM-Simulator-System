package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3, b4;

    Login() {
        setTitle("AUTOMATED TELLER MACHINE");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/un.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 10, 100, 100);
        add(l11);

        l1 = new JLabel("WELCOME TO ATM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200, 40, 450, 40);
        add(l1);

        l2 = new JLabel("Card No/Username:");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(95, 110, 675, 90);
        add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(300, 150, 230, 30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.setBounds(125, 220, 375, 30);
        add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300, 220, 230, 30);
        add(pf2);

        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        b4 = new JButton("FORGOT PASSWORD");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);

        setLayout(null);

        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300, 300, 100, 30);
        add(b1);

        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430, 300, 100, 30);
        add(b2);

        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300, 350, 230, 30);
        add(b3);
        
        b4.setFont(new Font("Arial", Font.BOLD, 14));
        b4.setBounds(300, 400, 230, 30);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setLocation(550, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                Conn c1 = new Conn();
                String cardnoOrUsername = tf1.getText();
                String pin = pf2.getText();
                String q = "select * from login where (cardno = '" + cardnoOrUsername + "' or username = '" + cardnoOrUsername + "') and pin = '" + pin + "'";

                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number/Username or PIN");
                }
            } else if (ae.getSource() == b2) {
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) {
                setVisible(false);
                new Signup().setVisible(true);
            } else if (ae.getSource() == b4) {
                // Action for "Forgot Password" button
                setVisible(false);
                new ForgotPassword().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}

class ForgotPassword extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField tf1;
    JButton b1, b2;

    ForgotPassword() {
        setTitle("FORGOT PASSWORD");

        l1 = new JLabel("Enter your registered email:");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(100, 50, 400, 40);
        add(l1);

        tf1 = new JTextField(15);
        tf1.setBounds(100, 100, 300, 30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);

        b1 = new JButton("RESET PASSWORD");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(100, 150, 200, 30);
        add(b1);

        b2 = new JButton("BACK");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(310, 150, 100, 30);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        setSize(500, 300);
        setLocation(600, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            // Code to reset password using the email provided in tf1
            String email = tf1.getText();
            // Perform the necessary actions to reset the password
            // This could involve sending a reset link to the email
            JOptionPane.showMessageDialog(null, "Password reset link sent to " + email);
        } else if (ae.getSource() == b2) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new ForgotPassword().setVisible(true);
    }
}
