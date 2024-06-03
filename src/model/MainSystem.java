package model;

import utilities.FileLoader;
import utilities.JDialogCreator;

import java.util.*;

public class MainSystem {
    private static MainSystem instance;
    private static AccountSystem accountSystem;
    private static FlightSystem flightSystem;
    private static TicketSystem ticketSystem;
    private Account currentAccount;
    private ListAccount listAccount;

    private MainSystem()  {
    }

    public static MainSystem getInstance()  {
        if (instance == null) {
            instance = new MainSystem();
            accountSystem = new AccountSystem();
            flightSystem = new FlightSystem();
            ticketSystem = new TicketSystem();
        }
        return instance;
    }
    public AccountSystem getAccountSystem() {
        return accountSystem;
    }

    public FlightSystem getFlightSystem() {
        return flightSystem;
    }

    public TicketSystem getTicketSystem() {
        return ticketSystem;
    }

    public Map<String, Account> getListAccount() {
        return listAccount.getListAccount();
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }
    public void setListAccount(ListAccount la) {
        this.listAccount = la;
    }
    public void setAccountSystem(AccountSystem accountSystem) {
        MainSystem.accountSystem = accountSystem;
    }

    public void setFlightSystem(FlightSystem flightSystem) {
        MainSystem.flightSystem = flightSystem;
    }

    public void setTicketSystem(TicketSystem ticketSystem) {
        MainSystem.ticketSystem = ticketSystem;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public JDialogCreator signIn(String username, String password) {
        JDialogCreator dialog;
        if (this.getListAccount().get(username) == null) {
            dialog = new JDialogCreator("Tài khoản không tồn tại");
            dialog.setStatus(false);
        } else {
            Account account = this.getListAccount().get(username);
            if (account.password.equals(password)) {
                this.currentAccount = account;
                dialog = new JDialogCreator("Đăng nhập thành công");
                dialog.setStatus(true);
            } else {
                dialog = new JDialogCreator("Mật khẩu bạn nhập không đúng");
                dialog.setStatus(false);
            }
        }
        return dialog;
    }

    public JDialogCreator createAccount(Account newAccount) throws Exception {
        this.accountSystem.setCreateAccount(new CreateAccount(this.currentAccount, newAccount));
        return this.accountSystem.createAccount();
    }

    public JDialogCreator deleteAccount(String username) throws Exception {
        this.accountSystem.setDeleteAccount(new DeleteAccount(this.currentAccount, username));
        return this.accountSystem.deleteAccount();
    }

    public JDialogCreator updateUsername(String username) throws Exception {
        this.accountSystem.setDeleteAccount(new UpdateUsername(this.currentAccount, username));
        return this.accountSystem.updateUsername();
    }

    public JDialogCreator updatePassword(String passwd, String confirmPasswd) throws Exception {
        this.accountSystem.setUpdatePassword(new UpdatePassword(this.currentAccount, passwd, confirmPasswd));
        return this.accountSystem.updatePassword();
    }

    public JDialogCreator addFlight(Flight flight) throws Exception {
        this.flightSystem.setStrategy(new AddFlight());
        return this.flightSystem.execute(flight);
    }

    public JDialogCreator removeFlight(String id) throws Exception {
        this.flightSystem.setStrategy(new RemoveFlight());
        Flight flight = FileLoader.findFlight(id);
        return this.flightSystem.execute(flight);
    }

    public JDialogCreator updateFlight(Flight flight) throws Exception {
        if (this.currentAccount instanceof ManagerAccount) {
            this.flightSystem.setStrategy(new UpdateFlight());
            return this.flightSystem.execute(flight);
        }
        else return new JDialogCreator("Bạn không có đủ quyền hạn để thực hiện tính năng này!");
    }

    public List<Flight> displayFlight() {
        return this.flightSystem.getListFlight();
    }

    public void statistic(TicketDecorator decorator) {
        this.ticketSystem.setDecorator(decorator);
        this.ticketSystem.statistic();
    }

    public List<Flight> displayFlightInclude(TicketDecorator decorator) {
        this.ticketSystem.setDecorator(decorator);
        return this.ticketSystem.viewFlightIncluded();
    }

    public void displayTicket(String id) {
        this.ticketSystem.displayTicket(id);
    }

    public List<Flight> getListFlight() {
        return this.flightSystem.getListFlight();
    }

}
