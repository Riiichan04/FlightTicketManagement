package view;

import javax.swing.*;

public class WorkFrame extends JFrame {
    public WorkFrame() {
        setTitle("Flight Ticket Management");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
