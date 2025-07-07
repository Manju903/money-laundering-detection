package main;
import javax.swing.JOptionPane;

import auth.Login;
import auth.Register;
import ui.Dashboard;

public class Main {
    public static void main(String[] args) {
        String[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to Money Laundering Detection System", "Select Option",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 1) {
            String username = JOptionPane.showInputDialog("Choose username:");
            String password = JOptionPane.showInputDialog("Choose password:");
            if (Register.registerUser(username, password)) {
                JOptionPane.showMessageDialog(null, "Registration Successful!");
            } else {
                JOptionPane.showMessageDialog(null, "User Already Exists or Error!");
            }
        }

        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        int userId = Login.authenticateUser(username, password);
        if (userId != -1) {
            new Dashboard(userId);
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed!");
        }
    }
}
