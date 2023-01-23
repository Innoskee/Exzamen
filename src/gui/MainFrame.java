package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        new Dimension(500, 500);
        setTitle("Swing Functions");

        pack();
        repaint();
    }
}
