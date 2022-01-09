/* This will be the step counter page.
 * This page will simulate the amount of steps tracked by the user.
*/
package ht4.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class StepPage extends JFrame implements ActionListener {
    //instantiating all of our elements
    JFrame stepFrame = new JFrame();
    JLabel chartComment = new JLabel("You're on track to meet your goal!");
    //private ChartPanel chartPanel = new ChartPanel();
    private JButton exitButton = new JButton("Exit");


    //Creating the constructor and setting the size of the JFrame along with calling our helper methods
    StepPage() {
        if (SettingsFrame.darkThemeClicked) {
            stepFrame.getContentPane().setBackground(Color.DARK_GRAY);
            stepFrame.getContentPane().setForeground(Color.WHITE);
        }
        stepFrame.setTitle("Step Tracker");
        stepFrame.setBounds(10, 10, 370, 600);
        stepFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stepFrame.setVisible(true);
        stepFrame.setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addActionEvent();

        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton, BorderLayout.SOUTH);
        //chartPanel.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel();
        chartPanel.addColumn("Sun", 350);
        chartPanel.addColumn("Mon", 690);
        chartPanel.addColumn("Tue", 510);
        chartPanel.addColumn("Wed", 570);
        chartPanel.addColumn("Thu", 180);
        chartPanel.addColumn("Fri", 504);
        chartPanel.addColumn("Sat", 484);
        chartPanel.chartLayout();

        stepFrame.add(chartPanel);
        stepFrame.add(chartComment);
        stepFrame.add(exitButton);

    }


    //Setting layout to null, which ends up just using the default layout
    public void setLayoutManager() {
        stepFrame.setLayout(null);
    }

    public void setLocationAndSize() {
        chartComment.setBounds(35, 245, 50, 25);
        exitButton.setBounds(145, 530, 85, 25);

    }

    public void addActionEvent() {
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            stepFrame.dispose();
            new BrowsePage();
        }
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value) {
            this.label = label;
            this.value = value;
            this.color = Color.LIGHT_GRAY;
        }

        public String getLabel() {
            return label;
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }
    }

    private class BarIcon implements Icon {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public BarIcon(int width, int height)
        {
            this.color = Color.LIGHT_GRAY;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }
        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    private class ChartPanel extends JPanel{
        private int chartHeight = 200;
        private int barWidth = 50;
        private int barGap = 5;
        private JPanel barPanel;
        private JPanel labelPanel;
        private List<Bar> bars = new ArrayList<>();

        public ChartPanel() {
            // create the bar graph
            barPanel = new JPanel( new GridLayout(1, 0, barGap, 0));
            Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
            Border inner = new EmptyBorder(10, 10, 0, 10);
            Border compound = new CompoundBorder(outer, inner);
            barPanel.setBorder(compound);

            labelPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
            labelPanel.setBorder(new EmptyBorder(5, 10, 0, 10));

            add(barPanel, BorderLayout.CENTER);
            add(labelPanel, BorderLayout.PAGE_END);

            //ChartPanel panel = new ChartPanel();


        }

        public void addColumn(String label, int value) {
            Bar bar = new Bar(label, value);
            bars.add( bar );
        }

        public void chartLayout()
        {
            barPanel.removeAll();
            labelPanel.removeAll();

            int maxValue = 0;

            for (Bar bar: bars)
                maxValue = Math.max(maxValue, bar.getValue());

            for (Bar bar: bars)
            {
                JLabel label = new JLabel(bar.getValue() + "");
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.TOP);
                label.setVerticalAlignment(JLabel.BOTTOM);
                int barHeight = (bar.getValue() * chartHeight) / maxValue;
                Icon icon = new BarIcon(barWidth, barHeight);
                label.setIcon(icon);
                barPanel.add(label);

                JLabel barLabel = new JLabel( bar.getLabel() );
                barLabel.setHorizontalAlignment(JLabel.CENTER);
                labelPanel.add(barLabel);
            }
        }


    }


}

