package model;

import utilities.FileConverter;

public abstract class Account extends ListAccount {
    protected String username;
    protected String password;
    protected Employee info;
    protected boolean isChangedUsername;

    public Account(String username, String password, Employee info, boolean isChangedUsername) {
        this.username = username;
        this.password = password;
        this.info = info;
        this.isChangedUsername = isChangedUsername;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        else {
            Account other = (Account) obj;
            return this.username.equals(other.username) && this.password.equals(other.password);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Employee getInfo() {
        return info;
    }

    public boolean isChangedUsername() {
        return isChangedUsername;
    }

    public abstract boolean createAccount(Account account);
    public abstract boolean deleteAccount(String username);
    public boolean updateUsername(String username) {
        if (this.isChangedUsername) return false;
        else {
            if (getListAccount().containsKey(username)) return false;
            try {
                this.username = username;
                this.isChangedUsername = true;
                FileConverter.updateAccount(this);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }
    public boolean updatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) return false;
        else {
            try {
                this.password = password;
                FileConverter.updateAccount(this);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }

    }
}