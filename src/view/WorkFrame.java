package view;

import model.Employee;
import model.ListAccount;
import model.ListFlight;
import model.ManagerAccount;
import utilities.JDialogCreator;

import javax.swing.*;

public class WorkFrame extends JFrame implements View {
    UserPanel currentPanel;
    public WorkFrame(UserPanel currentPanel) {
        this.currentPanel = currentPanel;
        setTitle("Flight Ticket Management");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(this.currentPanel);
        setVisible(true);
    }

    @Override
    public void update(JDialogCreator dialog) {

    }
}
