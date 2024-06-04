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
    public JDialogCreator execute(ListAccount listAccount) {
        if (this.account.getUsername().equals("root")) return new JDialogCreator("Bạn không thể thay đổi tên tài khoản root");
        else if (this.account.updateUsername(username, listAccount)) {
            return new JDialogCreator("Bạn đã thay đổi tên đăng nhập thành công!");
        }
        else if (this.account.isChangedUsername) return new JDialogCreator("Bạn chỉ có thể thay đổi tên đăng nhập một lần!");
        else return new JDialogCreator("Tên tài khoản mới đã tồn tại. Xin hãy chọn tên khác!");
    }
}
