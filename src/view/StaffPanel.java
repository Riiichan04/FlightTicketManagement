package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class StaffPanel extends UserPanel{


    public StaffPanel(Account currentAccount, List<Flight> lf, Map<String, Account> la) throws Exception {
        super(currentAccount, lf, la);
    }

    public StaffPanel(Account currentAccount, IModel model) throws Exception {
        super(currentAccount, model);
    }
}
