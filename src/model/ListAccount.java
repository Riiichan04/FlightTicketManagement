package model;

import utilities.FileLoader;

import java.util.*;

public class ListAccount {
    private Map<String, Account> listAccount;

    void loadAccount() throws Exception {
        this.listAccount = FileLoader.loadAccount();
    }

    Account findAccountByEmployee(Employee employee) {
        return this.listAccount.values().stream().filter(account -> account.info.equals(employee)).toList().getFirst();
    }
    public Map<String, Account> getListAccount() {
        return listAccount;
    }

}
