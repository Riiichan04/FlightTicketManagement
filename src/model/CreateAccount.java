package model;

import utilities.JDialogCreator;

public class CreateAccount implements AccountCommand {
    private Account account;
    private Account newAccount;

    public CreateAccount(Account account, Account newAccount) {
        this.account = account;
        this.newAccount = newAccount;
    }

    @Override
    public JDialogCreator execute() throws Exception {
        if (this.account.createAccount(newAccount)) {
            return new JDialogCreator("Cấp tài khoản thành công!");
        }
        else {
            if (this.account instanceof ManagerAccount) return new JDialogCreator("Tài khoản của nhân viên này đã tồn tại!");
            else return new JDialogCreator("Bạn không có quyền thực hiện tính năng này!");
        }
    }
}
