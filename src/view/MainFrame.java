package view;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() throws Exception {
        setTitle("Flight Ticket Management");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
        setLocationRelativeTo(null);

        add(new SignInPanel());

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new MainFrame();
    }
}
