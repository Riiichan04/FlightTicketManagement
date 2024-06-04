package model;

import utilities.JDialogCreator;

public class UpdatePassword implements AccountCommand{
    private Account account;
    private String currentPassword;
    private String password;
    private String confirmPassword;

    public UpdatePassword(Account account, String currentPassword, String password, String confirmPassword) {
        this.account = account;
        this.currentPassword = currentPassword;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public JDialogCreator execute(ListAccount listAccount) {
        if (!this.account.getPassword().equals(currentPassword)) return new JDialogCreator("Bạn đã nhập sai mật khẩu");
        else if (this.account.updatePassword(currentPassword, password, confirmPassword, listAccount)) {
            return new JDialogCreator("Bạn đã cập nhật mật khẩu thành công");
        }
        else return new JDialogCreator("Mật khẩu và xác nhận mật khẩu không trùng khớp!");
    }
}
