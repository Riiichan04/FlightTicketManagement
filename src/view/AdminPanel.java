package view;

import model.*;

import java.util.List;
import java.util.Map;

public class AdminPanel extends UserPanel {
    public AdminPanel(Account currentAccount, List<Flight> lf, Map<String, Account> la) throws Exception {
        super(currentAccount, lf, la);
    }

    public AdminPanel(Account currentAccount, IModel model) throws Exception {
        super(currentAccount, model);
    }
}
