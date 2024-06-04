package model;

import utilities.JDialogCreator;

import java.awt.*;

public class CreateAccount implements AccountCommand {
    private Account account;
    private Account newAccount;

    public CreateAccount(Account account, Account newAccount) {
        this.account = account;
        this.newAccount = newAccount;
    }

    @Override
    public JDialogCreator execute(ListAccount listAccount){
        if (this.account.createAccount(newAccount, listAccount)) {
            return new JDialogCreator("Cấp tài khoản thành công! \nTài khoản mới có username mặc định là: "
                    + newAccount.info.getPosition() + newAccount.info.getId().substring(1) +
                    "\nTài khoản có mật khẩu là:" + newAccount.info.getId()
                    + "\nBạn có một lần đổi tên tài khoản và được thay đổi mật khẩu với số lần tùy ý"
            , new Dimension(600, 150));
        }
        else {
            if (this.account instanceof ManagerAccount) return new JDialogCreator("Tài khoản của nhân viên này đã tồn tại!");
            else return new JDialogCreator("Bạn không có quyền thực hiện tính năng này!");
        }
    }
}
