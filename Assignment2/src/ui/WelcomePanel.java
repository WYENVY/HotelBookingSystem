package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel 
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Main mainFrame;

    public WelcomePanel(Main mainFrame) 
    {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(5, 1));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterAction());

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(registerButton);
        add(loginButton);
    }

    private class RegisterAction implements ActionListener 
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = username + "@HM.com";
        User newUser = new User(0, username, password, email, "user");
        mainFrame.getUserService().registerUser(newUser);
        JOptionPane.showMessageDialog(WelcomePanel.this, "Registration successful!");
    }
}


    private class LoginAction implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = mainFrame.getUserService().login(username, password);
            if (user != null)
            {
                mainFrame.setCurrentUser(user);
                mainFrame.showPanel("UserMenu");
            } 
            else 
            {
                JOptionPane.showMessageDialog(WelcomePanel.this, "Invalid credentials, please try again.");
            }
        }
    }
}
