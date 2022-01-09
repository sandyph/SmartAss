/* This is the Calendar page. This class current has an accurate calendar in it,
 and allows the user to navigate through it. We would like to make it so the user is able to schedule stuff on the
  calendar*/
package ht4.Package;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarPage extends JFrame implements ActionListener {
    JFrame calFrame = new JFrame();
    Calendar cal = new GregorianCalendar();
    JLabel label;
    DefaultTableModel model;
    JButton exitButton = new JButton("Exit");

    CalendarPage() {
        calFrame.setTitle("Calendar");
        calFrame.setBounds(10, 10, 370, 600);
        calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calFrame.setVisible(true);
        calFrame.setResizable(false);

        //exitButton.setBounds(0, 0, 80, 25);
        exitButton.setBounds(145, 530, 85, 25);
        addActionEvent();


        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton leftArrow = new JButton("<-");
        leftArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
                updateMonth();
            }
        });

        JButton rightArrow = new JButton("->");
        rightArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
                updateMonth();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(leftArrow,BorderLayout.WEST);
        panel.add(label,BorderLayout.CENTER);
        panel.add(rightArrow,BorderLayout.EAST);

        JPanel panel2 = new JPanel();
        panel2.add(exitButton, BorderLayout.SOUTH);


        String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        model = new DefaultTableModel(null,columns);
        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);

        calFrame.add(panel, BorderLayout.NORTH);
        calFrame.add(pane, BorderLayout.CENTER);
        calFrame.add(panel2, BorderLayout.SOUTH);
        this.updateMonth();
        if (SettingsFrame.darkThemeClicked) {
            calFrame.setBackground(Color.DARK_GRAY);
            panel.setBackground(Color.DARK_GRAY);
            panel2.setBackground(Color.DARK_GRAY);
            label.setForeground(Color.WHITE);
            table.setBackground(Color.DARK_GRAY);
            table.setForeground(Color.WHITE);
            pane.setBackground(Color.DARK_GRAY);
        }
    }

    void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay-1;
        for(int day=1;day<=numberOfDays;day++){
            model.setValueAt(day, i/7 , i%7 );
            i = i + 1;
        }

    }
    public void addActionEvent() {
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            calFrame.dispose();
            new BrowsePage();
        }
    }

//    public static void main(String args[]){
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        new CalendarPage();
//    }

}
