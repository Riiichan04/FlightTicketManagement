package view;

import model.Account;
import model.ListAccount;
import model.ListFlight;
import model.IModel;
import utilities.JDialogCreator;

import javax.swing.*;

public class SignInFrame extends JFrame implements View {
    public SignInFrame(IModel model, ListAccount listAccount, ListFlight listFlight) throws Exception {
        setTitle("Flight Ticket Management");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new SignInPanel(model, listAccount, listFlight));

        setVisible(true);
    }

    @Override
    public void update(JDialogCreator dialog) {
        dialog.setVisible(true);
    }
}