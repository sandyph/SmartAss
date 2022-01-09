/* This is the browse page.
 * This page will allow users to access all pages in the application.
 * The functionality of this class is to have a single location where most of the pages are accessible from */

package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BrowsePage extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame browseFrame = new JFrame();
    private JButton homeButton = new JButton("Home");
    private JButton browseButton = new JButton("Browse");
    private JButton stepCountButton = new JButton("Step Counter");
    private JButton journalButton = new JButton("Journal");
    private JButton dietButton = new JButton("Diet");
    private JButton progressButton = new JButton("Progress photos");
    private JButton communityButton = new JButton("Community");
    private JButton calendarButton = new JButton("Calendar");

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    BrowsePage() {
        if (SettingsFrame.darkThemeClicked) {
            browseFrame.getContentPane().setBackground(Color.DARK_GRAY);
            browseFrame.getContentPane().setForeground(Color.WHITE);
        }
        browseFrame.setTitle("SmartAss");
        browseFrame.setBounds(10, 10, 370, 600);
        browseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browseFrame.setVisible(true);
        browseFrame.setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addHomeComponentsToContainer();
        addActionEvent();
    }

    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {

        browseFrame.setLayout(null);
    }

    //Declares the size for the elements
    public void setLocationAndSize() {
        homeButton.setBounds(5, 510, 150, 30);
        browseButton.setBounds(205, 510, 150, 30);
        stepCountButton.setBounds(100, 350, 150, 30);
        journalButton.setBounds(100, 310, 150, 30);
        dietButton.setBounds(100, 270, 150, 30);
        progressButton.setBounds(100, 230, 150, 30);
        communityButton.setBounds(100, 190, 150, 30);
        calendarButton.setBounds(100, 390, 150, 30);
    }

    //Adds all the elements to the JFrame
    public void addHomeComponentsToContainer() {
        browseFrame.add(homeButton);
        browseFrame.add(browseButton);
        browseFrame.add(stepCountButton);
        browseFrame.add(journalButton);
        browseFrame.add(dietButton);
        browseFrame.add(progressButton);
        browseFrame.add(communityButton);
        browseFrame.add(calendarButton);
    }

    //Adds an action listener to the buttons
    public void addActionEvent() {
        homeButton.addActionListener(this);
        browseButton.addActionListener(this);
        stepCountButton.addActionListener(this);
        journalButton.addActionListener(this);
        dietButton.addActionListener(this);
        progressButton.addActionListener(this);
        communityButton.addActionListener(this);
        calendarButton.addActionListener(this);
    }

    // This code clears the JPanel
//    public void changePanel(JPanel panel) {
//        getContentPane().removeAll();
//        getContentPane().add(panel, BorderLayout.CENTER);
//        getContentPane().doLayout();
//        update(getGraphics());
//    }

    //Setting the action in which each button will do.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dietButton) {
            browseFrame.dispose();
            new DietPlan();
        }
        if (e.getSource() == homeButton) {
            browseFrame.dispose();
            new FrameHome();
        }
        if (e.getSource() == journalButton) {
            browseFrame.dispose();
            new JournalPage();
        }
        if (e.getSource() == progressButton) {
            browseFrame.dispose();
            new ProgressPic();
        }
        if (e.getSource() == calendarButton) {
            browseFrame.dispose();
            new CalendarPage();
        }
        if (e.getSource() == communityButton) {
            browseFrame.dispose();
            new CommunityFrame();
        }
        if (e.getSource() == stepCountButton) {
            //JOptionPane.showMessageDialog(this, "COMING SOON...");
            browseFrame.dispose();
            new StepPage();
        }
    }

    //This code is to run this single page for testing
//    public static void main(String[] args) {
//        BrowsePage frame = new BrowsePage();
//        frame.setBounds(10, 10, 370, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//    }
}

