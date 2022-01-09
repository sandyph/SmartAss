/* This is the Diet Plan Page.
 * This page allows users to enter what they have eaten for that day of the week.
 * We aren't currently connected to a DB so until we get connected we won't be able to save the data. */
package ht4.Package;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DietPlan extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame dietFrame = new JFrame();
    JButton backButton = new JButton("Back");

    JComboBox<String> monList;
    JComboBox<String> tueList;
    JComboBox<String> wedList;
    JComboBox<String> thuList;
    JComboBox<String> friList;
    JComboBox<String> satList;
    JComboBox<String> sunList;

    JScrollPane monMenu;
    JScrollPane tueMenu;
    JScrollPane wedMenu;
    JScrollPane thuMenu;
    JScrollPane friMenu;
    JScrollPane satMenu;
    JScrollPane sunMenu;

    //array to hold labels for each day
    JLabel[] days = {new JLabel("Monday"), new JLabel("Tuesday"), new JLabel("Wednesday"), new JLabel("Thursday"), new JLabel("Friday"), new JLabel("Saturday"), new JLabel("Sunday")};

    JLabel calorieGoalLabel = new JLabel("Calorie Goal: " + DietTrackerSettingsFrame.calorieGoal);

    JTextArea monCal = new JTextArea("Calories: ");
    JTextArea tueCal = new JTextArea("Calories: ");
    JTextArea wedCal = new JTextArea("Calories: ");
    JTextArea thuCal = new JTextArea("Calories: ");
    JTextArea friCal = new JTextArea("Calories: ");
    JTextArea satCal = new JTextArea("Calories: ");
    JTextArea sunCal = new JTextArea("Calories: ");


    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    DietPlan() {
        //dark theme from settings check
        if (SettingsFrame.darkThemeClicked) {
            dietFrame.getContentPane().setBackground(Color.DARK_GRAY);
            for (JLabel day : days) {
                day.setForeground(Color.WHITE);
            }
            calorieGoalLabel.setForeground(Color.WHITE);
        }
        dietFrame.setTitle("Diet Plan");
        dietFrame.setVisible(true);
        dietFrame.setBounds(10, 10, 370, 600);
        dietFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dietFrame.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addPlanComponentsToContainer();
        addActionEvent();
    }
    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
        dietFrame.setLayout(null);
    }

    //Declares the size for the elements
    public void setLocationAndSize() {
        //DietPlan.setBounds(10, 10, 100, 30);
        backButton.setBounds(265, 0, 80, 22);
        for (int i=0; i<7; i++) {
            days[i].setBounds(10,80*i,250,25);
        }

        try {
            monList = new JComboBox<>(fileToArray());
            tueList = new JComboBox<>(fileToArray());
            wedList = new JComboBox<>(fileToArray());
            thuList = new JComboBox<>(fileToArray());
            friList = new JComboBox<>(fileToArray());
            satList = new JComboBox<>(fileToArray());
            sunList = new JComboBox<>(fileToArray());

            monMenu = new JScrollPane(monList);
            tueMenu = new JScrollPane(tueList);
            wedMenu = new JScrollPane(wedList);
            thuMenu = new JScrollPane(thuList);
            friMenu = new JScrollPane(friList);
            satMenu = new JScrollPane(satList);
            sunMenu = new JScrollPane(sunList);

        } catch (IOException e) {
            //itemList = new JList<>();
            monList = new JComboBox<>();
            tueList = new JComboBox<>();
            wedList = new JComboBox<>();
            thuList = new JComboBox<>();
            friList = new JComboBox<>();
            satList = new JComboBox<>();
            sunList = new JComboBox<>();
        }

        monMenu.setBounds(10, 25, 250, 50);
        tueMenu.setBounds(10, 105, 250, 50);
        wedMenu.setBounds(10, 185, 250, 50);
        thuMenu.setBounds(10, 265, 250, 50);
        friMenu.setBounds(10, 345, 250, 50);
        satMenu.setBounds(10, 425, 250, 50);
        sunMenu.setBounds(10, 505, 250, 50);

        monCal.setBounds(265, 25, 250, 50);
        tueCal.setBounds(265, 105, 250, 50);
        wedCal.setBounds(265, 185, 250, 50);
        thuCal.setBounds(265, 265, 250, 50);
        friCal.setBounds(265, 345, 250, 50);
        satCal.setBounds(265, 425, 250, 50);
        sunCal.setBounds(265, 505, 250, 50);

    }
    //Adds all the elements to the JFrame
    public void addPlanComponentsToContainer() {
        dietFrame.add(backButton);
        for (JLabel day: days) {
            dietFrame.add(day);
        }

        //adds calorie goal display if enabled in settings, right justifies text along ComboBox)
        if (DietTrackerSettingsFrame.displayCalorieTotalButton.isSelected()) {
            for (JLabel day: days) {
                if (day.getText().contains("Wednesday")) {
                    day.setText(day.getText() + "                        Calorie Goal: " + DietTrackerSettingsFrame.calorieGoal);
                    continue;
                }
                day.setText(day.getText() + "                            Calorie Goal: " + DietTrackerSettingsFrame.calorieGoal);
            }
        }

        dietFrame.add(monMenu);
        dietFrame.add(tueMenu);
        dietFrame.add(wedMenu);
        dietFrame.add(thuMenu);
        dietFrame.add(friMenu);
        dietFrame.add(satMenu);
        dietFrame.add(sunMenu);

        dietFrame.add(monCal);
        dietFrame.add(tueCal);
        dietFrame.add(wedCal);
        dietFrame.add(thuCal);
        dietFrame.add(friCal);
        dietFrame.add(satCal);
        dietFrame.add(sunCal);
    }
    //Adds an action listener to the buttons
    public void addActionEvent() {
        backButton.addActionListener(this);
        monList.addActionListener(this);
        tueList.addActionListener(this);
        wedList.addActionListener(this);
        thuList.addActionListener(this);
        friList.addActionListener(this);
        satList.addActionListener(this);
        sunList.addActionListener(this);
        //int totalCalo = getTotalCalories();
        //monCal.append(String.valueOf(totalCalo));

    }

    // This is the action of clicking on the back button.
    // It will drive the user back to Home page
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dietFrame.dispose();
            new BrowsePage();
        }

        if (e.getSource() == monList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) monList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            monCal.append(String.valueOf(caloSum) + "\n");
        }
        if (e.getSource() == tueList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) tueList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            tueCal.append(String.valueOf(caloSum) + "\n");
        }

        if (e.getSource() == wedList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) wedList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            wedCal.append(String.valueOf(caloSum) + "\n");
        }

        if (e.getSource() == thuList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) thuList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            thuCal.append(String.valueOf(caloSum) + "\n");
        }

        if (e.getSource() == friList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) friList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            friCal.append(String.valueOf(caloSum) + "\n");
        }

        if (e.getSource() == satList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) satList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            satCal.append(String.valueOf(caloSum) + "\n");
        }

        if (e.getSource() == sunList) {
            int calories = 0;
            int caloSum = 0;
            String str = (String) sunList.getSelectedItem();
            String[] string = str.split(",");
            calories = Integer.parseInt(string[1].trim());
            caloSum += calories;
            sunCal.append(String.valueOf(caloSum) + "\n");
        }

    }

    private String[] fileToArray() throws IOException {
        Path filePath = new File("C:/workspaces/SmartAss/FoodList.txt").toPath();
        Charset charset = Charset.defaultCharset();
        List<String> stringList = Files.readAllLines(filePath, charset);
        return stringList.toArray(new String[] {});
    }
}

