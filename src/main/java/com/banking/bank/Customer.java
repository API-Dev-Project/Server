package com.banking.bank;

import com.banking.util.HashUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by graham on 03/11/16.
 */

public class Customer extends Person {

    private List<Account> accounts;
    private String username;
    private String password;
    private int id;

    public Customer() {
        username = new String();
        password = new String();
        id = 0;
        accounts = new ArrayList<>();
    }

    public Customer(String firstname,
                    String surname,
                    String email,
                    String address,
                    String username,
                    String password) {
        super(firstname, surname, email, address);

        id = 0;
        accounts = new ArrayList<>();
        setUsername(username);
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = HashUtil.sha256(password);
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getUsername() {
        return username;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public boolean isOwner(Account account) {
        for (Account tempAccount : accounts) {
            if (tempAccount.getOwner().getUsername().equals(this.getUsername())) {
                return true;
            }
        }

        return false;
    }

    //Could possibly be removed
    public Account getAccount(long accountNumber, int sortCode) {
        for (Account tempAccount : accounts) {
            if (tempAccount.getAccountNumber() == accountNumber && tempAccount.getSortCode() == sortCode) {
                return tempAccount;
            }
        }

        return null;
    }

}
