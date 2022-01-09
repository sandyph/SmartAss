/* This is the Login Page.
This page will accept a username and password and if they match a current user it will log them into the application.
 Currently the only username and password that we have is hard coded and it is USERNAME: joey | PASSWORD: 12345 */
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame frame = new JFrame();
    JLabel userLabel = new JLabel("USERNAME:");
    JLabel passwordLabel = new JLabel("PASSWORD:");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton registerButton = new JButton("Register");
    JCheckBox showPassword = new JCheckBox("Show Password");

    //Grabbing image from folder and making it into a JLabel
    ImageIcon img = new ImageIcon(("C:/workspaces/SmartAss/src/main/resources/healtech1-removebg-preview.png"));
    JLabel jlPic = new JLabel(img);

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    LoginFrame() {
        frame.setTitle("SmartAss");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addLoginComponentsToContainer();
        addActionEvent();
    }
    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
       frame.setLayout(null);
    }
    //Declares the size for the elements
    public void setLocationAndSize() {
        userLabel.setBounds(50, 325, 100, 30);
        passwordLabel.setBounds(50, 370, 100, 30);
        userTextField.setBounds(150, 325, 150, 30);
        passwordField.setBounds(150, 370, 150, 30);
        showPassword.setBounds(150, 400, 150, 30);
        loginButton.setBounds(150, 450, 100, 30);
        registerButton.setBounds(150, 500, 100, 30);
        jlPic.setBounds(20, 25, 325, 300);

    }
    //Adds all the elements to the JFrame
    public void addLoginComponentsToContainer() {
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(showPassword);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(jlPic);
    }
    //Adds an action listener to the buttons
    public void addActionEvent() {
        loginButton.addActionListener(this);
        showPassword.addActionListener(this);
        registerButton.addActionListener(this);
    }
    //This is to clear the JPanel
//    public void changePanel(FrameHome panel) {
//        getContentPane().removeAll();
//        getContentPane().add(panel, BorderLayout.CENTER);
//        getContentPane().doLayout();
//        update(getGraphics());
//    }

    // This is the action of clicking on the login button.
    // If the username and password are correct then it will take you to the home screen.
    //If the login is not correct it will tell you it's an invalid login
    @Override
    public void actionPerformed(ActionEvent e) {
        // Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            Connection conn = DBConnection.DBC();
            //Query
            DBQueries queries = new DBQueries();
            if (queries.selectingUserLogin(userText, pwdText, conn)) {
                new User(userText,pwdText);
                frame.dispose();
                new FrameHome();

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        if(e.getSource() == registerButton) {
            frame.dispose();
            new RegisterPage();

        }

        // Coding for show password with a Jcheckbox
        if (e.getSource() == showPassword)

        {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }
    }
}


