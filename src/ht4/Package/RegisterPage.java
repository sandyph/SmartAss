/*  */
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class RegisterPage extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame frame = new JFrame();
    JLabel usernameLabel = new JLabel("USERNAME:");
    JLabel passwordLabel = new JLabel("PASSWORD:");
    JLabel cPasswordLabel = new JLabel("CONFIRM PASSWORD:");
    JLabel registerLabel = new JLabel("Register an account");
    JTextField usernameTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField cPasswordField = new JPasswordField();
    JButton createAccountButton = new JButton("Create Account");
    JButton backButton = new JButton("Back");

    //Grabbing image from folder and making it into a JLabel
    ImageIcon img = new ImageIcon(("C:/workspaces/SmartAss/src/main/resources/healtech1-removebg-preview.png"));
    JLabel jlPic = new JLabel(img);

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    RegisterPage() {
        if (SettingsFrame.darkThemeClicked) {
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            usernameLabel.setForeground(Color.WHITE);
            passwordLabel.setForeground(Color.WHITE);
            cPasswordLabel.setForeground(Color.WHITE);
            registerLabel.setForeground(Color.WHITE);
        }
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
        usernameLabel.setBounds(15, 325, 100, 30);
        passwordLabel.setBounds(15, 370, 100, 30);
        cPasswordLabel.setBounds(15, 415, 185, 30);
        usernameTextField.setBounds(150, 325, 150, 30);
        passwordField.setBounds(150, 370, 150, 30);
        cPasswordField.setBounds(150, 415, 150, 30);
        registerLabel.setBounds(150,300,150,20);

        createAccountButton.setBounds(150, 500, 160, 30);
        backButton.setBounds(10,10,80,30);
        jlPic.setBounds(20, 10, 325, 300);

    }
    //Adds all the elements to the JFrame
    public void addLoginComponentsToContainer() {
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(cPasswordLabel);
        frame.add(usernameTextField);
        frame.add(passwordField);
        frame.add(cPasswordField);
        frame.add(registerLabel);

        frame.add(createAccountButton);
        frame.add(backButton);
        frame.add(jlPic);
    }
    //Adds an action listener to the buttons
    public void addActionEvent() {

        createAccountButton.addActionListener(this);
        backButton.addActionListener(this);
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
        if(e.getSource() == backButton) {
            frame.dispose();
            new LoginFrame();
        }
        if (e.getSource() == createAccountButton) {
            String usernameText;
            String pwdText;
            String cpwdText;
            usernameText = usernameTextField.getText();
            pwdText = passwordField.getText();
            cpwdText = cPasswordField.getText();
            //Connection
            Connection conn = DBConnection.DBC();
            //Query
            DBQueries queries = new DBQueries();

            if (pwdText.equals(cpwdText) && usernameText.length() >= 6 && pwdText.length() >= 6) {
                queries.creatingUser(usernameText, pwdText, conn);
                JOptionPane.showMessageDialog(this, "Account has been created");
                frame.dispose();
                new LoginFrame();
            } else if(usernameText.length() >= 6 && pwdText.length() >= 6){
                JOptionPane.showMessageDialog(this, "Passwords don't match");
            } else if(usernameText.length() < 6){
                JOptionPane.showMessageDialog(this, "Username needs to be atleast 6 characters in length");
            } else if(pwdText.length() < 6 ){
                JOptionPane.showMessageDialog(this, "Password needs to be atleast 6 characters in length");
            }
        }
    }
}


