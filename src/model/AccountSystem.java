package model;

import utilities.JDialogCreator;

public class AccountSystem {
    AccountCommand createAccount;
    AccountCommand deleteAccount;
    AccountCommand updateUsername;
    AccountCommand updatePassword;

    public AccountSystem() {}

    public AccountSystem(AccountCommand createAccount, AccountCommand deleteAccount, AccountCommand updateUsername, AccountCommand updatePassword) {
        this.createAccount = createAccount;
        this.deleteAccount = deleteAccount;
        this.updateUsername = updateUsername;
        this.updatePassword = updatePassword;
    }

    public JDialogCreator createAccount() throws Exception {
        return createAccount.execute();
    }

    public JDialogCreator deleteAccount() throws Exception {
        return deleteAccount.execute();
    }

    public JDialogCreator updateUsername() throws Exception {
        return updatePassword.execute();
    }

    public JDialogCreator updatePassword() throws Exception {
        return updatePassword.execute();
    }

    public void setCreateAccount(AccountCommand createAccount) {
        this.createAccount = createAccount;
    }

    public void setDeleteAccount(AccountCommand deleteAccount) {
        this.deleteAccount = deleteAccount;
    }

    public void setUpdateUsername(AccountCommand updateUsername) {
        this.updateUsername = updateUsername;
    }

    public void setUpdatePassword(AccountCommand updatePassword) {
        this.updatePassword = updatePassword;
    }
}
