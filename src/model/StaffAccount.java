package model;

public class StaffAccount extends Account{
    public StaffAccount(String username, String password, Employee info, boolean isChangedUsername) {
        super(username, password, info, isChangedUsername);
    }

    @Override
    public boolean createAccount(Account account, ListAccount listAccount) {
        return false;
    }

    @Override
    public boolean deleteAccount(String username, ListAccount listAccount) {
        return false;
    }
}
