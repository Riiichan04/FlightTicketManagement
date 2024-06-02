package model;

import utilities.FileConverter;

public class ManagerAccount extends Account {
    public ManagerAccount(String username, String password, Employee info, boolean isChangedUsername) {
        super(username, password, info, isChangedUsername);
    }

    @Override
    public boolean createAccount(Account account) throws Exception {
        if (this.getListAccount().get(account.username) != null || findAccountByEmployee(account.info) != null) return false;
        else {
            FileConverter.convertAccountToTxt(account);
            return true;
        }
    }

    @Override
    public boolean deleteAccount(String username) throws Exception {
        if (this.getListAccount().get(username) == null) return false;
        else {
            FileConverter.deleteAccountInTxt(username);
            return true;
        }
    }

}
