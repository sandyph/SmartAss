/* This is the community page.
 * This class will show the user recommendations based on what other people are doing.
 * Right now it is just all hard coded for concept, but we would like for it to connect to an API of some sort
 * and show related topics. Such as articles and recipes  */
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.net.http.*;

public class CommunityFrame extends JFrame implements ActionListener {
    //declare necessary content within frame
    JFrame communityFrame = new JFrame();
    JLabel communityLabel = new JLabel("Community");
    JButton createPostButton = new JButton("Create Post");
    JLabel searchLabel = new JLabel("Search");
    JTextField searchField = new JTextField();
    JLabel newsFeedLabel = new JLabel("News Feed");
    JTextArea newsFeedArea = new JTextArea("7 Healthy Alternatives to Vegetable Oil\n\n" + "3 Ab Workouts to Burn Fat at Home");
    JLabel notificationLabel = new JLabel("Notifications (4)");
    JTextArea notificationArea = new JTextArea("- Friend Requests (1)\n\n" + "- 3 People interacted with your post (3 hr. ago)");
    JButton backButton = new JButton("Back");

    //Constructor to set window size and elements
    CommunityFrame() {
        if (SettingsFrame.darkThemeClicked) {
            communityFrame.getContentPane().setBackground(Color.DARK_GRAY);
            communityLabel.setForeground(Color.WHITE);
            searchLabel.setForeground(Color.WHITE);
            newsFeedLabel.setForeground(Color.WHITE);
            notificationLabel.setForeground(Color.WHITE);
        }
        communityFrame.setTitle("Community");
        communityFrame.setVisible(true);
        communityFrame.setBounds(10, 10, 370, 600);
        communityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        communityFrame.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    //create layout
    public void setLayoutManager() {
        communityFrame.setLayout(null);
    }

    //determine location of each element
    public void setLocationAndSize() {
        communityLabel.setBounds(10,10,100,50);
        createPostButton.setBounds(10,60,120,30);
        searchLabel.setBounds(150,60,50,30);
        searchField.setBounds(195,60,155,30);
        backButton.setBounds(280,25,70,20);
        newsFeedLabel.setBounds(10,100,100,50);
        newsFeedArea.setBounds(10,140,340,150);
        notificationLabel.setBounds(10,260,340,150);
        notificationArea.setBounds(10,350,340,150);
    }

    //add each element to the frame
    public void addComponentsToContainer() {
        communityFrame.add(communityLabel);
        communityFrame.add(backButton);
        communityFrame.add(createPostButton);
        communityFrame.add(searchLabel);
        communityFrame.add(searchField);
        communityFrame.add(newsFeedLabel);
        communityFrame.add(newsFeedArea);
        communityFrame.add(notificationLabel);
        communityFrame.add(notificationArea);
    }

    //declare actionEvents for necessary elements
    public void addActionEvent() {
        createPostButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    //corresponding actionEvent logic
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            communityFrame.dispose();
            new BrowsePage();
        }
        else if (e.getSource() == createPostButton) {
            JPanel postPanel = new JPanel(new BorderLayout());
            JTextArea writePostArea = new JTextArea();
            communityFrame.add(postPanel,BorderLayout.CENTER);
            postPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            postPanel.setBackground(SettingsFrame.defaultBackground);
            postPanel.setBounds(50,100,260,260);
            writePostArea.setBounds(10,10,240,240);
            postPanel.add(writePostArea);
            JPanel bottomPostPanel = new JPanel();
            bottomPostPanel.setBounds(40,150,260,50);
            JButton postButton = new JButton(new AbstractAction("Post") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(communityFrame,"This entry has been posted!");
                    for (Component c : postPanel.getParent().getComponents()) {
                        if (c instanceof JPanel) {
                            c.setVisible(false);
                        }
                    }
                    postPanel.getParent().remove(postPanel);
                }
            });
            postButton.setBounds(80,200,30,20);
            bottomPostPanel.add(postButton);
            postPanel.add(bottomPostPanel,BorderLayout.PAGE_END);
            postPanel.setVisible(true);
        }
    }
}

//class Community {
//    public static void main(String[] args) {
//        new CommunityFrame();
//
//    }
//}
