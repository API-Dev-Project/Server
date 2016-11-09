package com.banking.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Graham Murray on 09/11/16.
 */
public class Bank {

    private List<Account> accounts;
    private int sortCode;

    public Bank(int sortCode) {
        this.sortCode = sortCode;
        accounts = new ArrayList<>();
    }

    public int getSortCode() {
        return sortCode;
    }

    public Account getAccount(int sortCode, int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getSortCode() == sortCode) {
                return account;
            }
        }

        return null;
    }
}
