package model;

import utilities.JDialogCreator;

public class UpdatePassword implements AccountCommand{
    private Account account;
    private String password;
    private String confirmPassword;

    public UpdatePassword(Account account, String password, String confirmPassword) {
        this.account = account;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public JDialogCreator execute() throws Exception {
        if (this.account.updatePassword(password, confirmPassword)) {
            return new JDialogCreator("Bạn đã cập nhật mật khẩu thành công");
        }
        else return new JDialogCreator("Mật khẩu và xác nhận mật khẩu không trùng khớp!");
    }
}
