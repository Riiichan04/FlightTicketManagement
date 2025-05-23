package model;

import utilities.FileLoader;
import utilities.JDialogCreator;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MainSystem {
    private static MainSystem instance;
    private static AccountSystem accountSystem;
    private static FlightSystem flightSystem;
    private static TicketSystem ticketSystem;
    private Account currentAccount;
    private MainSystem() {
        accountSystem = new AccountSystem();
        flightSystem = new FlightSystem();
        ticketSystem = new TicketSystem();
    }

    public static MainSystem getInstance() {
        if (instance == null) {
            instance = new MainSystem();

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
    public Account getCurrentAccount() {
        return currentAccount;
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

    public JDialogCreator signIn(String username, String password, ListAccount listAccount) {
        JDialogCreator dialog;
        if (listAccount.getListAccount().get(username) == null) {
            dialog = new JDialogCreator("Tài khoản không tồn tại");
            dialog.setStatus(false);
        } else {
            Account account = listAccount.getListAccount().get(username);
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

    public JDialogCreator createAccount(Account newAccount, ListAccount listAccount) {
        accountSystem.setCreateAccount(new CreateAccount(this.currentAccount, newAccount));
        return accountSystem.createAccount(listAccount);
    }

    public JDialogCreator deleteAccount(String username, ListAccount listAccount) {
        accountSystem.setDeleteAccount(new DeleteAccount(this.currentAccount, username));
        return accountSystem.deleteAccount(listAccount);
    }

    public JDialogCreator updateUsername(String username, ListAccount listAccount) {
        accountSystem.setUpdateUsername(new UpdateUsername(this.currentAccount, username));
        return accountSystem.updateUsername(listAccount);
    }

    public JDialogCreator updatePassword(String currentPassword, String passwd, String confirmPasswd, ListAccount listAccount) {
        accountSystem.setUpdatePassword(new UpdatePassword(this.currentAccount, currentPassword, passwd, confirmPasswd));
        return accountSystem.updatePassword(listAccount);
    }

    public JDialogCreator addFlight(Flight flight, ListFlight listFlight) {
        flightSystem.setStrategy(new AddFlight());
        return flightSystem.execute(flight, listFlight);
    }

    public JDialogCreator removeFlight(String id, ListFlight listFlight) {
        if (currentAccount instanceof StaffAccount) {
            return new JDialogCreator("Bạn không có quyền hạn để thực hiện tính năng này!");
        } else {
            flightSystem.setStrategy(new RemoveFlight());
            Flight flight = FileLoader.findFlight(id);
            return flightSystem.execute(flight, listFlight);
        }
    }

    public JDialogCreator updateFlight(Flight flight, ListFlight listFlight) {
        if (this.currentAccount instanceof ManagerAccount) {
            flightSystem.setStrategy(new UpdateFlight());
            return flightSystem.execute(flight, listFlight);
        } else return new JDialogCreator("Bạn không có đủ quyền hạn để thực hiện tính năng này!");
    }

    public List<Flight> displayFlight(ListFlight listFlight) {
        return listFlight.getListFlight();
    }

    public JDialogCreator statistic(TicketDecorator decorator, ListFlight listFlight) {
        ticketSystem.setDecorator(decorator);
        int result = ticketSystem.statistic(listFlight);
        if (result == 0) return new JDialogCreator("Không tìm thấy chuyến bay nào!");
        return new JDialogCreator("Có tổng cộng " + result + " vé đã bán!");
    }

    public List<Flight> displayFlightInclude(TicketDecorator decorator, ListFlight listFlight) {
        ticketSystem.setDecorator(decorator);
        return ticketSystem.viewFlightIncluded(listFlight);
    }

    public String[][] displayTicket(String id, ListFlight listFlight) {
        return ticketSystem.displayTicket(id, listFlight);
    }

}
