/* This is the settings page.
this is where the user can change the settings for their application.
 Currently we have some simulations for notifications on and off, and dark mode.
*/
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

public class SettingsFrame extends JFrame implements ActionListener{
    //instantiating all of our elements
    JFrame settingsFrame = new JFrame();
    static JLabel settingsLabel = new JLabel("Settings");
    JButton backButton = new JButton("Back");
    JButton profileButton = new JButton(new ImageIcon("C:/workspaces/SmartAss/Resources/profile-icon.png"));
    JLabel editProfileLabel = new JLabel("Edit Profile");
    JButton darkThemeButton = new JButton("Dark Theme");
    JButton changeLanguageButton = new JButton("Change Language");
    JButton dietTrackerSettingsButton = new JButton("Diet Tracker Settings");
    JToggleButton toggleNotificationsButton = new JToggleButton("Toggle Notifications");
    JButton logoutButton = new JButton("Log out");
    JButton deleteAccountButton = new JButton("Delete Account");

    static boolean darkThemeClicked = false; //ActionListener flag for sequential button clicking.
    static Color defaultBackground = UIManager.getColor("Panel.background");
    static String currentLanguage = "";
    static JLabel lbl = new JLabel();
    static JPanel panel;

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    SettingsFrame() {
        if (darkThemeClicked) {
            settingsFrame.getContentPane().setBackground(Color.DARK_GRAY);
            settingsLabel.setForeground(Color.WHITE);
            editProfileLabel.setForeground(Color.WHITE);
        }
        settingsFrame.setTitle("Settings");
        settingsFrame.setVisible(true);
        settingsFrame.setBounds(10, 10, 370, 600);
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
        settingsFrame.setLayout(null);
    }

    //Declares the size for the elements
    public void setLocationAndSize() {
        settingsLabel.setBounds(10,10,100,50);
        backButton.setBounds(280,25,70,20);
        profileButton.setBounds(140,100,70,70);
        editProfileLabel.setBounds(143,160,100,50);
        logoutButton.setBounds(85, 400, 180, 30);
        dietTrackerSettingsButton.setBounds(85, 360, 180, 30);
        changeLanguageButton.setBounds(85, 320, 180, 30);
        toggleNotificationsButton.setBounds(85, 280, 180, 30);
        darkThemeButton.setBounds(85, 240, 180, 30);
        deleteAccountButton.setBounds(220,500,130,20);
    }

    //Adds all the elements to the JFrame
    public void addComponentsToContainer() {
        settingsFrame.add(settingsLabel);
        settingsFrame.add(backButton);
        settingsFrame.add(profileButton);
        settingsFrame.add(editProfileLabel);
        settingsFrame.add(darkThemeButton);
        settingsFrame.add(changeLanguageButton);
        settingsFrame.add(toggleNotificationsButton);
        settingsFrame.add(dietTrackerSettingsButton);
        settingsFrame.add(logoutButton);
        settingsFrame.add(deleteAccountButton);
        settingsFrame.add(lbl);
    }

    //Adds an action listener to the buttons
    public void addActionEvent() {
        profileButton.addActionListener(this);
        backButton.addActionListener(this);
        darkThemeButton.addActionListener(this);
        logoutButton.addActionListener(this);
        toggleNotificationsButton.addActionListener(this);
        deleteAccountButton.addActionListener(this);
        changeLanguageButton.addActionListener(this);
        dietTrackerSettingsButton.addActionListener(this);
    }

    //Setting the action in which each button will do.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            settingsFrame.dispose();
            new FrameHome();
        }
        else if (e.getSource() == profileButton) {
            settingsFrame.dispose();
            new ProfilePage();
        }
        else if (e.getSource() == logoutButton) {
            settingsFrame.dispose();
            darkThemeClicked = false;
            new LoginFrame();
        }
        else if (e.getSource() == darkThemeButton) {
            darkTheme(settingsFrame);
        }
        else if (e.getSource() == toggleNotificationsButton) {
            toggleNotifications();
        }
        else if (e.getSource() == changeLanguageButton) {
            panel = (JPanel) lbl.getParent();
            JPopupMenu langMenu = new JPopupMenu("Supported Languages");
            if (currentLanguage.equals("")) {
                currentLanguage = "en";
            }
            langMenu.add(new JMenuItem(new AbstractAction("English") {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (currentLanguage.equals("ru")) {
                            translate(panel,"ru","en");
                        }
                        else if (currentLanguage.equals("fr")) {
                            translate(panel,"fr","en");
                        }
                        currentLanguage = "en";
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }));
            langMenu.add(new JMenuItem(new AbstractAction("French (Français)") {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (currentLanguage.equals("ru")) {
                            translate(panel,"ru","fr");
                        }
                        else if (currentLanguage.equals("en")) {
                            translate(panel,"en","fr");
                        }
                        currentLanguage = "fr";
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }));
            langMenu.add(new JMenuItem(new AbstractAction("Russian") {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (currentLanguage.equals("en")) {
                            translate(panel,"en","ru");
                        }
                        else if (currentLanguage.equals("fr")) {
                            translate(panel,"fr","ru");
                        }
                        currentLanguage = "ru";
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }));
            langMenu.setPopupSize(200,120);
            langMenu.setVisible(true);
            langMenu.show(settingsFrame,80,220);
        }
        else if (e.getSource() == dietTrackerSettingsButton) {
            settingsFrame.dispose();
            new DietTrackerSettingsFrame();
        }
        else if (e.getSource() == deleteAccountButton) {
            int result = JOptionPane.showConfirmDialog(this,"Are you sure?","Delete Account Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                Connection connection = DBConnection.DBC();
                DBQueries queries = new DBQueries();
                int userID = User.id;
                if (queries.deleteQuery(userID, connection)) {
                    JOptionPane.showMessageDialog(this, "Account Deleted. You will be redirected to the Login menu.");
                    settingsFrame.dispose();
                    new LoginFrame();
                }
            }
        }
    }

    public void darkTheme(JFrame frame) {
        if (!darkThemeClicked) {
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            settingsLabel.setForeground(Color.WHITE);
            editProfileLabel.setForeground(Color.WHITE);
            darkThemeClicked = true;
        }
        else {
            frame.getContentPane().setBackground(defaultBackground);
            settingsLabel.setForeground(Color.BLACK);
            editProfileLabel.setForeground(Color.BLACK);
            darkThemeClicked = false;
        }
    }

    public void toggleNotifications() {
        if (toggleNotificationsButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Notifications turned off");
        }
        else {
            JOptionPane.showMessageDialog(this, "Notifications turned on");
        }
    }

    public static void translate(JPanel panel, String langFrom, String langTo) throws IOException {
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setText(Translator.translate(langFrom,langTo, ((JLabel) c).getText()));
            }
            else if (c instanceof JButton) {
                ((JButton) c).setText(Translator.translate(langFrom,langTo, ((JButton) c).getText()));
            }
            else if (c instanceof JTextField) {
                ((JTextField) c).setText(Translator.translate(langFrom,langTo, ((JTextField) c).getText()));
            }
            else if (c instanceof JTextArea) {
                ((JTextArea) c).setText(Translator.translate(langFrom,langTo, ((JTextArea) c).getText()));
            }
        }
    }
}
//For testing the single page
//class Settings {
//    public static void main(String[] args) {
//        new SettingsFrame();
//    }
//}
