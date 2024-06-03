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
    public Map<String, Account> getListAccount() {
        return this.mainSystem.getListAccount();
    }
    public boolean signIn(String username, String password) {
        JDialogCreator dialog = this.mainSystem.signIn(username, password);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean createAccount(Account newAccount) throws Exception {
        JDialogCreator dialog = this.mainSystem.createAccount(newAccount);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean deleteAccount(String username) throws Exception {
        JDialogCreator dialog = this.mainSystem.deleteAccount(username);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean updateUsername(String username) throws Exception {
        JDialogCreator dialog = this.mainSystem.updateUsername(username);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean updatePassword(String passwd, String confirmPasswd) throws Exception {
        JDialogCreator dialog = this.mainSystem.updatePassword(passwd, confirmPasswd);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean addFlight(Flight flight) throws Exception {
        JDialogCreator dialog = this.mainSystem.addFlight(flight);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean removeFlight(String flightId) throws Exception {
        JDialogCreator dialog = this.mainSystem.removeFlight(flightId);
        notifyObserver(dialog);
        return dialog.status;
    }
    public boolean updateFlight(Flight flight) throws Exception {
        JDialogCreator dialog = this.mainSystem.updateFlight(flight);
        notifyObserver(dialog);
        return dialog.status;
    }
    public void displayFlight() {
        this.mainSystem.displayFlight();
    }
    public void statistic(TicketDecorator decorator) {
        this.mainSystem.statistic(decorator);
    }
    public void displayFlightStatistic(TicketDecorator decorator) {
        this.mainSystem.displayFlightInclude(decorator);
    }
    public void displayTicket(String id) {
        this.mainSystem.displayTicket(id);
    }
}
