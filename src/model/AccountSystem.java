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

    public JDialogCreator createAccount() {
        return createAccount.execute();
    }

    public JDialogCreator deleteAccount() {
        return deleteAccount.execute();
    }

    public JDialogCreator updateUsername() {
        return updatePassword.execute();
    }

    public JDialogCreator updatePassword() {
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
