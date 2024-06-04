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

    public JDialogCreator createAccount(ListAccount listAccount) {
        return createAccount.execute(listAccount);
    }

    public JDialogCreator deleteAccount(ListAccount listAccount) {
        return deleteAccount.execute(listAccount);
    }

    public JDialogCreator updateUsername(ListAccount listAccount) {
        return updateUsername.execute(listAccount);
    }

    public JDialogCreator updatePassword(ListAccount listAccount) {
        return updatePassword.execute(listAccount);
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
