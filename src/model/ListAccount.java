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
        List<Account> list = this.listAccount.values().stream().filter(account -> account.info.equals(employee)).toList();
        if (list.size() > 0) return list.getFirst();
        else return null;
    }
    public Map<String, Account> getListAccount() {
        return listAccount;
    }

}
