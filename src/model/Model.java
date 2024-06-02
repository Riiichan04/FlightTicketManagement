package model;

import utilities.JDialogCreator;
import view.Observer;

import java.util.*;

public class Model implements Observable {
    List<Observer> listObserver;
    MainSystem mainSystem;

    @Override
    public void addObserver(Observer ob) {
        this.listObserver.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        this.listObserver.remove(ob);
    }

    @Override
    public void notifyObserver(JDialogCreator dialog) {
        for (Observer ob: listObserver) {
            ob.update(dialog);
        }
    }

    public void signIn(String username, String password) {
        notifyObserver(this.mainSystem.signIn(username, password));
    }
    public void createAccount(Account newAccount) throws Exception {
        notifyObserver(this.mainSystem.createAccount(newAccount));
    }
    public void deleteAccount(String username) throws Exception {
        notifyObserver(this.mainSystem.deleteAccount(username));
    }
    public void updateUsername(String username) throws Exception {
        notifyObserver(this.mainSystem.updateUsername(username));
    }
    public void updatePassword(String passwd, String confirmPasswd) throws Exception {
        notifyObserver(this.mainSystem.updatePassword(passwd, confirmPasswd));
    }
    public void addFlight(Flight flight) throws Exception {
        notifyObserver(this.mainSystem.addFlight(flight));
    }
    public void removeFlight(String flightId) throws Exception {
        notifyObserver(this.mainSystem.removeFlight(flightId));
    }
    public void updateFlight(Flight flight) throws Exception {
        notifyObserver(this.mainSystem.updateFlight(flight));
    }
    public void displayFlight() {
        this.mainSystem.displayFlight();
    }
    public void statistic(TicketDecorator decorator) {
        this.mainSystem.statistic(decorator);
    }
    public void displayTicket(String id) {
        this.mainSystem.displayTicket(id);
    }
}
