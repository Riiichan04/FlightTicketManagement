package model;

import utilities.JDialogCreator;

public class DeleteAccount implements AccountCommand {
    private Account account;
    private String username;

    public DeleteAccount(Account account, String username) {
        this.account = account;
        this.username = username;
    }

    @Override
    public JDialogCreator execute() throws Exception {
        if (this.account.deleteAccount(username)) {
            return new JDialogCreator("Xóa tài khoản thành công!");
        }
        else if (this.account instanceof ManagerAccount) {
            return new JDialogCreator("Không tìm thấy tài khoản để xóa!");
        }
        else return new JDialogCreator("Bạn không có quyền thực hiện tính năng này!");
    }
}
