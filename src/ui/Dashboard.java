package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.DBConnection;
import detection.RiskAnalyzer;
import model.Transaction;

public class Dashboard {
    int userId;

    public Dashboard(int userId) {
        this.userId = userId;
        JFrame f = new JFrame("Money Laundering Detection - Dashboard");
        JLabel l1 = new JLabel("Amount:");
        JTextField tf1 = new JTextField();
        JLabel l2 = new JLabel("Location:");
        JTextField tf2 = new JTextField();
        JButton b1 = new JButton("Submit Transaction");

        l1.setBounds(50, 50, 100, 30);
        tf1.setBounds(150, 50, 150, 30);
        l2.setBounds(50, 100, 100, 30);
        tf2.setBounds(150, 100, 150, 30);
        b1.setBounds(100, 150, 180, 30);

        f.add(l1); f.add(tf1); f.add(l2); f.add(tf2); f.add(b1);
        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);

        b1.addActionListener(_ -> {
            double amt = Double.parseDouble(tf1.getText());
            String loc = tf2.getText();
            Transaction tx = new Transaction(userId, amt, loc);

            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO transactions (user_id, amount, location) VALUES (?, ?, ?)");
                ps.setInt(1, tx.userId);
                ps.setDouble(2, tx.amount);
                ps.setString(3, tx.location);
                ps.executeUpdate();
                if (RiskAnalyzer.isSuspicious(tx)) {
                    JOptionPane.showMessageDialog(f, "Suspicious Transaction Detected!");
                } else {
                    JOptionPane.showMessageDialog(f, "Transaction Recorded");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Error: " + ex.getMessage());
            }
        });
    }
}

