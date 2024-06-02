package model;

import utilities.FileLoader;
import utilities.JDialogCreator;

import java.util.List;

public class MainSystem extends ListAccount {
    private MainSystem instance;
    private AccountSystem accountSystem;
    private FlightSystem flightSystem;
    private TicketSystem ticketSystem;
    private Account currentAccount;

    private MainSystem() {
    }

    public MainSystem getInstance() {
        if (instance == null) {
            this.instance = new MainSystem();
            this.accountSystem = new AccountSystem();
            this.flightSystem = new FlightSystem();
            this.ticketSystem = new TicketSystem();
        }
        return instance;
    }

    public JDialogCreator signIn(String username, String password) {
        if (this.getListAccount().get(username) == null) {
            return new JDialogCreator("Tài khoản không tồn tại");
        } else {
            Account account = this.getListAccount().get(username);
            if (account.password.equals(password)) {
                this.currentAccount = account;
                return new JDialogCreator("Đăng nhập thành công");
            } else return new JDialogCreator("Mật khẩu bạn nhập không đúng");
        }
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
}
