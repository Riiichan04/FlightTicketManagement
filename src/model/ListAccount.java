package model;

import utilities.FileLoader;

import java.util.*;

public class ListAccount {
    private Map<String, Account> listAccount;

    public void loadAccount() {
        try {
            this.listAccount = FileLoader.loadAccount();
        }
        catch (Exception e) {
            System.out.println("Không thể load danh sách tài khoản");
        }
    }

    Account findAccountByEmployee(Employee employee) {
        return this.listAccount.values().stream().filter(account -> account.info.equals(employee)).toList().getFirst();
    }
    public Map<String, Account> getListAccount() {
        return listAccount;
    }

}
