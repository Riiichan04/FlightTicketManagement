package model;

import utilities.JDialogCreator;
import view.AccountPanel;
import view.Observer;

import java.util.*;

public class Model implements Observable {
    List<Observer> listObserver;
    MainSystem mainSystem;
    public Model(MainSystem mainSystem) {
        this.listObserver = new LinkedList<>();
        this.mainSystem = mainSystem;
    }
    public List<Observer> getListObserver() {
        return listObserver;
    }

    public MainSystem getMainSystem() {
        return mainSystem;
    }

    @Override
    public void addObserver(Observer ob) {
        this.listObserver.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        this.listObserver.remove(ob);
    }

    @Override
    public void removeObserver(int i) {
        this.listObserver.remove(i);
    }

    @Override
    public void notifyObserver(JDialogCreator dialog) {
        for (Observer ob: listObserver) {
            ob.update(dialog);
        }
    }
    public boolean signIn(String username, String password, ListAccount listAccount) {
        JDialogCreator dialog = this.mainSystem.signIn(username, password, listAccount);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean createAccount(Account newAccount, ListAccount listAccount) {
        JDialogCreator dialog = this.mainSystem.createAccount(newAccount, listAccount);
        notifyObserver(dialog);
        dialog.setVisible(true);
        return dialog.status;
    }
    public boolean deleteAccount(String username, ListAccount listAccount) {
        JDialogCreator dialog = this.mainSystem.deleteAccount(username, listAccount);
        notifyObserver(dialog);
        dialog.setVisible(true);
        return dialog.status;
    }
    public boolean updateUsername(String username, ListAccount listAccount) {
        JDialogCreator dialog = this.mainSystem.updateUsername(username, listAccount);
        notifyObserver(dialog);
        dialog.setVisible(true);
        return dialog.status;
    }
    public boolean updatePassword(String passwd, String confirmPasswd, ListAccount listAccount) {
        JDialogCreator dialog = this.mainSystem.updatePassword(passwd, confirmPasswd, listAccount);
        notifyObserver(dialog);
        dialog.setVisible(true);
        return dialog.status;
    }
    public boolean addFlight(Flight flight, ListFlight listFlight) {
        JDialogCreator dialog = this.mainSystem.addFlight(flight, listFlight);
        dialog.setVisible(true);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean removeFlight(String flightId, ListFlight listFlight) {
        JDialogCreator dialog = this.mainSystem.removeFlight(flightId, listFlight);
        dialog.setVisible(true);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean updateFlight(Flight flight, ListFlight listFlight) {
        JDialogCreator dialog = this.mainSystem.updateFlight(flight, listFlight);
        dialog.setVisible(true);
        notifyObserver(dialog);
        return dialog.status;
    }
    public void displayFlight(ListFlight listFlight) {
        this.mainSystem.displayFlight(listFlight);
    }
    public void statistic(TicketDecorator decorator, ListFlight listFlight) {
        JDialogCreator dialog = this.mainSystem.statistic(decorator, listFlight);
        dialog.setVisible(true);
        notifyObserver(dialog);
    }
    public void displayFlightStatistic(TicketDecorator decorator, ListFlight listFlight) {
        this.mainSystem.displayFlightInclude(decorator, listFlight);
    }
    public String[][] displayTicket(String id, ListFlight listFlight) {
        return this.mainSystem.displayTicket(id, listFlight);
    }
}
