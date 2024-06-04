package model;

import utilities.JDialogCreator;
import view.Observer;

public interface IModel {
    public boolean signIn(String username, String password, ListAccount listAccount);
    public boolean createAccount(Account newAccount, ListAccount listAccount);
    public boolean deleteAccount(String username, ListAccount listAccount);
    public boolean updateUsername(String username, ListAccount listAccount);
    public boolean updatePassword(String currentPassword, String passwd, String confirmPasswd, ListAccount listAccount);
    public boolean addFlight(Flight flight, ListFlight listFlight);
    public boolean removeFlight(String flightId, ListFlight listFlight);
    public boolean updateFlight(Flight flight, ListFlight listFlight);
    public void displayFlight(ListFlight listFlight);
    public void statistic(TicketDecorator decorator, ListFlight listFlight);
    public String[][] displayTicket(String id, ListFlight listFlight);
}
