package model;

import utilities.JDialogCreator;
import view.Observer;

 public interface IModel extends Observable {
     boolean signIn(String username, String password, ListAccount listAccount);
     boolean createAccount(Account newAccount, ListAccount listAccount);
     boolean deleteAccount(String username, ListAccount listAccount);
     boolean updateUsername(String username, ListAccount listAccount);
     boolean updatePassword(String currentPassword, String passwd, String confirmPasswd, ListAccount listAccount);
     boolean addFlight(Flight flight, ListFlight listFlight);
     boolean removeFlight(String flightId, ListFlight listFlight);
     boolean updateFlight(Flight flight, ListFlight listFlight);
     void displayFlight(ListFlight listFlight);
     void statistic(TicketDecorator decorator, ListFlight listFlight);
     String[][] displayTicket(String id, ListFlight listFlight);
     MainSystem getMainSystem();
}
