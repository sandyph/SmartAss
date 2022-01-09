/* This is the home page of the project.
This is where the user can get to the settings page and the browse page, and logout of their account.*/
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameHome extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame frameHome = new JFrame();
    private JButton homeButton = new JButton("Home");
    private JButton browseButton = new JButton("Browse");
    private JButton logoutButton = new JButton("Logout");
    private JButton settingsButton = new JButton("Settings");

    //Grabbing image from folder and making it into a JLabel
    ImageIcon img = new ImageIcon(("C:/workspaces/SmartAss/src/main/resources/healtech1-removebg-preview.png"));
    JLabel jlPic = new JLabel(img);

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    FrameHome() {
        if (SettingsFrame.darkThemeClicked) {
            frameHome.getContentPane().setBackground(Color.DARK_GRAY);
            frameHome.getContentPane().setForeground(Color.WHITE);
        }
        frameHome.setTitle("SmartAss");
        frameHome.setVisible(true);
        frameHome.setBounds(10, 10, 370, 600);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addHomeComponentsToContainer();
        addActionEvent();
    }
    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
        frameHome.setLayout(null);
    }
    //Declares the size for the elements
    public void setLocationAndSize() {
        homeButton.setBounds(5, 510, 150, 30);
        browseButton.setBounds(205, 510, 150, 30);
        jlPic.setBounds(20, 25, 325, 300);
        logoutButton.setBounds(5, 10, 85, 30);
        settingsButton.setBounds(265, 10, 85, 30);
    }
    //Adds all the elements to the JFrame
    public void addHomeComponentsToContainer() {
        frameHome.add(homeButton);
        frameHome.add(browseButton);
        frameHome.add(jlPic);
        frameHome.add(logoutButton);
        frameHome.add(settingsButton);
    }
    //Adds an action listener to the buttons
    public void addActionEvent() {
        homeButton.addActionListener(this);
        browseButton.addActionListener(this);
        logoutButton.addActionListener(this);
        settingsButton.addActionListener(this);
    }
//    //This is to clear the JPanel
//    public void changePanel(JPanel panel) {
//        getContentPane().removeAll();
//        getContentPane().add(panel, BorderLayout.CENTER);
//        getContentPane().doLayout();
//        update(getGraphics());
//    }

    //This is where we have are buttons do something. in this case the button browse is opening the browse page
    //The logout button is logging the user out
    //the Settings button opens the settings page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            frameHome.dispose();
            new BrowsePage();
        }
        if (e.getSource() == logoutButton) {
            frameHome.dispose();
            SettingsFrame.darkThemeClicked = false;
            new LoginFrame();
        }
        if (e.getSource() == settingsButton) {
            frameHome.dispose();
            new SettingsFrame();
        }
    }
}

