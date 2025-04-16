package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener {

    JPasswordField t1, t2, t3; // t1 for old PIN, t2 for new PIN, t3 for re-enter new PIN
    JButton b1, b2;
    JLabel l1, l2, l3, l4;
    String pin;

    Pin(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 960, 1080);
        add(background);

        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(280, 330, 800, 35);
        background.add(l1);

        l2 = new JLabel("Old PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);
        l2.setBounds(180, 390, 150, 35);
        background.add(l2);

        l3 = new JLabel("New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);
        l3.setBounds(180, 440, 150, 35);
        background.add(l3);

        l4 = new JLabel("Re-Enter New PIN:");
        l4.setFont(new Font("System", Font.BOLD, 16));
        l4.setForeground(Color.WHITE);
        l4.setBounds(180, 490, 200, 35);
        background.add(l4);

        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        t1.setBounds(350, 390, 180, 25);
        background.add(t1);

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));
        t2.setBounds(350, 440, 180, 25);
        background.add(t2);

        t3 = new JPasswordField();
        t3.setFont(new Font("Raleway", Font.BOLD, 25));
        t3.setBounds(350, 490, 180, 25);
        background.add(t3);

        b1 = new JButton("CHANGE");
        b1.setBounds(390, 588, 150, 35);
        b1.addActionListener(this);
        background.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(390, 633, 150, 35);
        b2.addActionListener(this);
        background.add(b2);

        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String oldPin = t1.getText();
            String newPin = t2.getText();
            String reEnterPin = t3.getText();

            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from login where pin = '" + pin + "'");

            if (ae.getSource() == b1) {
                if (oldPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Old PIN");
                } else if (newPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                } else if (reEnterPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                } else {
                    if (rs.next()) {
                        String currentPin = rs.getString("pin");

                        if (!currentPin.equals(oldPin)) {
                            JOptionPane.showMessageDialog(null, "Old PIN does not match");
                        } else {
                            if (!newPin.equals(reEnterPin)) {
                                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                            } else {
                                // Update PIN in all relevant tables
                                String q1 = "update bank set pin = '" + newPin + "' where pin = '" + pin + "' ";
                                String q2 = "update login set pin = '" + newPin + "' where pin = '" + pin + "' ";
                                String q3 = "update signup3 set pin = '" + newPin + "' where pin = '" + pin + "' ";

                                c1.s.executeUpdate(q1);
                                c1.s.executeUpdate(q2);
                                c1.s.executeUpdate(q3);

                                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                                setVisible(false);
                                new Transactions(newPin).setVisible(true);
                            }
                        }
                    }
                }
            } else if (ae.getSource() == b2) {
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("").setVisible(true);
    }
}
