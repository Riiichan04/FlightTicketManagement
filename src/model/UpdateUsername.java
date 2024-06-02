package model;

import utilities.JDialogCreator;

public class UpdateUsername implements AccountCommand {
    private Account account;
    private String username;

    public UpdateUsername(Account account, String username) {
        this.account = account;
        this.username = username;
    }

    @Override
    public JDialogCreator execute() throws Exception {
        if (this.account.updateUsername(username)) {
            return new JDialogCreator("Bạn đã thay đổi tên đăng nhập thành công!");
        }
        else return new JDialogCreator("Bạn chỉ có thể thay đổi tên đăng nhập một lần!");
    }
}