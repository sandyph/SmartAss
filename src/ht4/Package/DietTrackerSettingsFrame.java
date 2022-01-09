//frame for diet tracker settings from Settings tab
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietTrackerSettingsFrame extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame dietSettingsFrame = new JFrame();
    JLabel dietSettingsLabel = new JLabel("Diet Tracker Settings");
    JButton backButton = new JButton("Back");
    JButton setCalorieGoalButton = new JButton("Set Calorie Goal");

    //variables used for applying settings to whole project
    static JToggleButton displayCalorieTotalButton = new JToggleButton("Display Calorie Goal");
    static JButton displayTimestampsButton = new JButton("Show Meal Timestamps");
    static boolean calorieGoalSelected = false;
    static int calorieGoal = 0;

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    DietTrackerSettingsFrame() {
        if (SettingsFrame.darkThemeClicked) {
            dietSettingsFrame.getContentPane().setBackground(Color.DARK_GRAY);
            dietSettingsLabel.setForeground(Color.WHITE);
        }
        dietSettingsFrame.setTitle("SmartAss");
        dietSettingsFrame.setVisible(true);
        dietSettingsFrame.setBounds(10, 10, 370, 600);
        dietSettingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dietSettingsFrame.setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addHomeComponentsToContainer();
        addActionEvent();
    }
    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
        dietSettingsFrame.setLayout(null);
    }
    //Declares the size for the elements
    public void setLocationAndSize() {
        dietSettingsLabel.setBounds(10,10,120,50);
        backButton.setBounds(280,25,70,20);
        setCalorieGoalButton.setBounds(85,210,180,30);
        displayCalorieTotalButton.setBounds(85, 270, 180, 30);
        displayTimestampsButton.setBounds(85, 330, 180, 30);
    }
    //Adds all the elements to the JFrame
    public void addHomeComponentsToContainer() {
        dietSettingsFrame.add(dietSettingsLabel);
        dietSettingsFrame.add(backButton);
        dietSettingsFrame.add(setCalorieGoalButton);
        dietSettingsFrame.add(displayCalorieTotalButton);
        dietSettingsFrame.add(displayTimestampsButton);
    }
    //Adds an action listener to the buttons
    public void addActionEvent() {
        backButton.addActionListener(this);
        setCalorieGoalButton.addActionListener(this);
        displayCalorieTotalButton.addActionListener(this);
        displayTimestampsButton.addActionListener(this);
    }

    //This is where we have are buttons do something. in this case the button browse is opening the browse page
    //The logout button is logging the user out
    //the Settings button opens the settings page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dietSettingsFrame.dispose();
            new SettingsFrame();
        }
        else if (e.getSource() == setCalorieGoalButton) {
            boolean validCalGoal = false;
            while (!validCalGoal) {
                try {
                    String input = JOptionPane.showInputDialog(this, "Set Calorie Goal: ");
                    if (input == null || input.equals("")) {
                        break;
                    }
                    calorieGoal = Integer.parseInt(input);
                    calorieGoalSelected = true;
                    validCalGoal = true;
                } catch (Exception ex) {
                    JOptionPane.showInputDialog(this, "Input must be a positive integer. Try again.");
                }
            }
        }
        else if (e.getSource() == displayCalorieTotalButton) {
            if (!calorieGoalSelected) {
                boolean validCalGoal = false;
                while (!validCalGoal) {
                    try {
                        String input = JOptionPane.showInputDialog(this,"Set Calorie Goal: ");
                        if(input == null || input.equals("")) {
                            displayCalorieTotalButton.setSelected(false);
                            break;
                        }
                        calorieGoal = Integer.parseInt(input);
                        calorieGoalSelected = true;
                        validCalGoal = true;
                    } catch (Exception ex) {
                        JOptionPane.showInputDialog(this, "Input must be a positive integer. Try again.");
                    }
                }
            }
        }
        else if (e.getSource() == displayTimestampsButton) {
            JOptionPane.showMessageDialog(this,"Currently unavailable. Stay tuned!");
        }
    }
}
