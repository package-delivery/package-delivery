package GUI;

import javax.swing.*;
import java.awt.*;

public class GuiMain extends JFrame {
    private JPanel mainPanel;
    private JPanel panel1;

    public GuiMain(String appName){
        super(appName);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        JButton button = new JButton("Press");
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        button.setPreferredSize(new Dimension(40, 40));
        frame.setVisible(true);
    }
}