/* This is the user profile page.
This is where the user can see and edit their user information.
This information will be stored in the DB once we get one connected.*/
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ProfilePage extends JFrame implements ActionListener {
    //instantiating all of our elements

    boolean edit;
    String name = User.name;
    String phone = User.phone;
    String email = User.email;
    int age = User.age;
    int heightFT = User.heightFT;
    int heightIN = User.heightIN;
    int cWeight = User.currentWeight;
    int bmi = User.bmi;
    int gWeight = User.goalWeight;

    JFrame profileFrame = new JFrame();
    JLabel titleName = new JLabel("Name:");
    JLabel titlePhone = new JLabel("Phone:");
    JLabel titleEmail= new JLabel("Email:");
    JLabel titleAge = new JLabel("Age:");
    JLabel titleHeight = new JLabel("Height:");
    JLabel titleCWeight = new JLabel("Current Weight:");
    JLabel titleBMI = new JLabel("BMI:");
    JLabel titleGWeight = new JLabel("Goal Weight:");
    JLabel titleFT = new JLabel("ft");
    JLabel titleIN = new JLabel("in");

    JTextField nameText = new JTextField(name);
    JTextField phoneText = new JTextField(phone);
    JTextField emailText = new JTextField(email);
    JTextField ageText = new JTextField(String.valueOf(age));
    JTextField heightFTText = new JTextField(String.valueOf(heightFT));
    JTextField heightINText = new JTextField(String.valueOf(heightIN));
    JTextField cWeightText = new JTextField(String.valueOf(cWeight));;
    JTextField bmiText = new JTextField(String.valueOf(bmi));
    JTextField gWeightText = new JTextField(String.valueOf(gWeight));

    JButton exitButton = new JButton("Exit");
    JButton editButton = new JButton("edit");
    JButton updateButton = new JButton("Update");

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    ProfilePage() {
        if (SettingsFrame.darkThemeClicked) {
            profileFrame.getContentPane().setBackground(Color.DARK_GRAY);
            titleName.setForeground(Color.WHITE);
            titlePhone.setForeground(Color.WHITE);
            titleEmail.setForeground(Color.WHITE);
            titleAge.setForeground(Color.WHITE);
            titleHeight.setForeground(Color.WHITE);
            titleCWeight.setForeground(Color.WHITE);
            titleGWeight.setForeground(Color.WHITE);
            titleBMI.setForeground(Color.WHITE);
            titleFT.setForeground(Color.WHITE);
            titleIN.setForeground(Color.WHITE);
        }
        profileFrame.setTitle("SmartAss");
        profileFrame.setBounds(10, 10, 370, 600);
        profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profileFrame.setVisible(true);
        profileFrame.setResizable(false);
        edit = false;

        setLayoutManager();
        setLocationAndSize();
        addHomeComponentsToContainer();
        addActionEvent();
        editable();

    }

    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {

        profileFrame.setLayout(null);
    }
    public void editable() {
        nameText.setEditable(edit);
        phoneText.setEditable(edit);
        emailText.setEditable(edit);
        ageText.setEditable(edit);
        heightFTText.setEditable(edit);
        heightINText.setEditable(edit);
        cWeightText.setEditable(edit);
        bmiText.setEditable(edit);
        gWeightText.setEditable(edit);
    }
    //Declares the size for the elements
    public void setLocationAndSize() {

        exitButton.setBounds(0, 20, 80, 25);

        nameText.setBounds(155, 65, 120,25);
        titleName.setBounds(5, 65, 80,30);

        phoneText.setBounds(155, 95, 120,25);
        titlePhone.setBounds(5, 95, 80,30);

        emailText.setBounds(155, 125, 120,25);
        titleEmail.setBounds(5, 125, 80,30);

        ageText.setBounds(155, 155, 120,25);
        titleAge.setBounds(5, 155, 80,30);

        heightFTText.setBounds(155, 185, 30,25);
        titleFT.setBounds(190, 185, 15,25);
        heightINText.setBounds(210, 185, 30,25);
        titleIN.setBounds(245, 185, 15,25);
        titleHeight.setBounds(5, 185, 80,30);

        cWeightText.setBounds(155, 215, 120,25);
        titleCWeight.setBounds(5, 215, 80,30);

        bmiText.setBounds(155, 245, 120,25);
        titleBMI.setBounds(5, 245, 80,30);

        gWeightText.setBounds(155, 275, 120,25);
        titleGWeight.setBounds(5, 275, 80,30);

        editButton.setBounds(270,20,80,25);
        updateButton.setBounds(155, 500, 80, 30);
    }

    //Adds all the elements to the JFrame
    public void addHomeComponentsToContainer() {
        profileFrame.add(titleName);
        profileFrame.add(titlePhone);
        profileFrame.add(titleEmail);
        profileFrame.add(titleAge);
        profileFrame.add(titleHeight);
        profileFrame.add(titleCWeight);
        profileFrame.add(titleBMI);
        profileFrame.add(titleGWeight);
        profileFrame.add(titleFT);
        profileFrame.add(titleIN);

        profileFrame.add(exitButton);
        profileFrame.add(editButton);
        profileFrame.add(updateButton);

        profileFrame.add(nameText);
        profileFrame.add(phoneText);
        profileFrame.add(emailText);
        profileFrame.add(ageText);
        profileFrame.add(heightFTText);
        profileFrame.add(heightINText);
        profileFrame.add(cWeightText);
        profileFrame.add(bmiText);
        profileFrame.add(gWeightText);

    }

    //Adds an action listener to the buttons
    public void addActionEvent() {

        exitButton.addActionListener(this);
        editButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    //Setting the action in which each button will do.
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateButton){
            boolean rightAge = true;
            name = nameText.getText();
            phone = phoneText.getText();
            email = emailText.getText();
            age = Integer.valueOf(ageText.getText());
            heightFT = Integer.valueOf(heightFTText.getText());
            heightIN = Integer.valueOf(heightINText.getText());
            cWeight = Integer.valueOf(cWeightText.getText());
            bmi = Integer.valueOf(bmiText.getText());
            gWeight = Integer.valueOf(gWeightText.getText());
            Connection conn = DBConnection.DBC();
            DBQueries queries = new DBQueries();
            if(age < 1 || age > 120){
                rightAge = false;
                JOptionPane.showMessageDialog(this, "Invalid age entered. Enter a valid age (1 - 120.");
            }
            if(queries.updatingUserInfo(conn, name, phone, email, age, heightFT, heightIN, cWeight, bmi, gWeight)
                    && rightAge) {
                edit = false;
                editable();

            }
        }
        if(e.getSource() == editButton) {
            edit = true;
            editable();
        }
        if (e.getSource() == exitButton) {
            profileFrame.dispose();
            new SettingsFrame();
        }
    }
}
