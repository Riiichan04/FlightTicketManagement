package model;

import utilities.FileConverter;

public abstract class Account {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract boolean createAccount(Account account, ListAccount listAccount);
    public abstract boolean deleteAccount(String username, ListAccount listAccount);
    public boolean updateUsername(String username, ListAccount listAccount) {
        if (this.isChangedUsername || this.getUsername().equals("root")) return false;
        else {
            if (listAccount.getListAccount().containsKey(username)) return false;
            try {
                listAccount.getListAccount().remove(this.username);
                this.username = username;
                this.isChangedUsername = true;
                listAccount.getListAccount().put(this.username, this);
                FileConverter.updateAccount(this);
                System.out.println(this.getUsername());
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }
    public boolean updatePassword(String currentPassword, String password, String confirmPassword, ListAccount listAccount) {
        if (!this.getPassword().equals(currentPassword)) return false;
        if (!password.equals(confirmPassword)) return false;
        else {
            try {
                this.password = password;
                listAccount.getListAccount().get(this.username).setPassword(password);
                FileConverter.updateAccount(this);
                System.out.println(this.getPassword());
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }

    }
}