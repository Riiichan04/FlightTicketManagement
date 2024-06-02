package view;

import model.Account;
import utilities.JDialogCreator;

import javax.swing.*;

public class SignInFrame extends JFrame implements View {
    public SignInFrame() throws Exception {
        setTitle("Flight Ticket Management");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
        setLocationRelativeTo(null);

        add(new SignInPanel());

        setVisible(true);
    }

    @Override
    public void update(JDialogCreator dialog) {

    }
}
