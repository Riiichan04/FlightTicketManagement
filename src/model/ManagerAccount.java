package model;

import utilities.FileConverter;

public class ManagerAccount extends Account {
    public ManagerAccount(String username, String password, Employee info, boolean isChangedUsername)  {
        super(username, password, info, isChangedUsername);
    }

    @Override
    public boolean createAccount(Account account, ListAccount listAccount) {
        if (listAccount.getListAccount().get(account.username) != null || listAccount.findAccountByEmployee(account.info) != null) return false;
        else {
            try {
                FileConverter.convertAccountToTxt(account);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public boolean deleteAccount(String username, ListAccount listAccount) {
        if (listAccount.getListAccount().get(username) == null || this.getUsername().equals(username)) return false;
        else {
            try {
                FileConverter.deleteAccountInTxt(username);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }

}
