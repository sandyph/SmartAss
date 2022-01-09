/* This page is the progress page.
 Which will allow the user to upload progress pictures and track their physical progress over time.
  Currently the page allows the user to cycle through multiple pictures.*/
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ProgressPic extends JFrame implements ActionListener{
    //instantiating all of our elements
    JFrame progressPic = new JFrame();
    ImageIcon img = new ImageIcon(("C:/workspaces/SmartAss/src/main/resources/healtech1-removebg-preview.png"));
    JLabel jlPic = new JLabel(img);
    int indexy=0;
    private JButton homeButton = new JButton("Home");
    private JButton browseButton = new JButton("Browse");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");

    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    ProgressPic() {
        if (SettingsFrame.darkThemeClicked) {
            progressPic.getContentPane().setBackground(Color.DARK_GRAY);
            progressPic.getContentPane().setForeground(Color.WHITE);
        }
        progressPic.setTitle("SmartAss");
        progressPic.setVisible(true);
        progressPic.setBounds(10, 10, 370, 600);
        progressPic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        progressPic.setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addHomeComponentsToContainer();
        addActionEvent();
        showImage();
    }

    //Grabbing the images from the resources folder
    public String[] getImages()
    {
        File directoryPath=new File("C:/workspaces/SmartAss/Resources/ProgressPictures/"+User.username);
        if(!directoryPath.exists())
        {
            directoryPath.mkdirs();
        }
        String[] imagesList=directoryPath.list();
        return imagesList;
    }
    // Creating an array to cycle through the images
    public void showImage()
    {
        String [] imageList=getImages();
        if(imageList.length==0)
        {
            ImageIcon icon = new ImageIcon("C:/workspaces/SmartAss/Resources/ProgressPictures/NoMore.png");
            Image image = icon.getImage().getScaledInstance(jlPic.getWidth(), jlPic.getHeight(), Image.SCALE_SMOOTH);
            jlPic.setIcon(new ImageIcon(image));
        }
        else
        {
            if (indexy > imageList.length - 1)
            {
                indexy = imageList.length-1;
                ImageIcon icon = new ImageIcon("C:/workspaces/SmartAss/Resources/ProgressPictures/NoMore.png");
                Image image = icon.getImage().getScaledInstance(jlPic.getWidth(), jlPic.getHeight(), Image.SCALE_SMOOTH);
                jlPic.setIcon(new ImageIcon(image));
            }

            else
            {
                String imageName = imageList[indexy];
                ImageIcon icon = new ImageIcon("C:/workspaces/SmartAss/Resources/ProgressPictures/"+User.username+"/" + imageName);
                Image image = icon.getImage().getScaledInstance(jlPic.getWidth(), jlPic.getHeight(), Image.SCALE_SMOOTH);
                jlPic.setIcon(new ImageIcon(image));
            }
        }
    }
    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
        progressPic.setLayout(null);
    }

    //Declares the size for the elements
    public void setLocationAndSize() {
        homeButton.setBounds(5, 510, 150, 30);
        browseButton.setBounds(205, 510, 150, 30);
        previousButton.setBounds(5, 400, 100, 50);
        nextButton.setBounds(250, 400, 100, 50);
        jlPic.setBounds(20, 50, 325, 300);
    }

    //Adds all the elements to the JFrame
    public void addHomeComponentsToContainer() {
        progressPic.add(homeButton);
        progressPic.add(browseButton);
        progressPic.add(previousButton);
        progressPic.add(nextButton);
        progressPic.add(jlPic);
    }

    //Adds an action listener to the buttons
    public void addActionEvent() {
        homeButton.addActionListener(this);
        browseButton.addActionListener(this);
        nextButton.addActionListener(this);
        previousButton.addActionListener(this);
    }

    //Setting the action in which each button will do.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            progressPic.dispose();
            new BrowsePage();
        }
        if (e.getSource() == homeButton) {
            progressPic.dispose();
            new FrameHome();
        }
        //Cycles the image to the right
        if(e.getSource()==nextButton)
        {
            indexy++;
            showImage();
        }
        //Cycles the image to the left
        if(e.getSource()==previousButton)
        {
            if(indexy==0)
                ;
            else
            {
                indexy--;
                showImage();
            }
        }
    }
}
