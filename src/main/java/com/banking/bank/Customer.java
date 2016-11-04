package com.banking.bank;

import com.banking.util.Encyptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graham on 03/11/16.
 */
public class Customer extends Person {

    private List<Account> accounts;
    private String username;
    private String password;

    public Customer(String firstname,
                    String surname,
                    String email,
                    String address,
                    String country,
                    String username,
                    String password,
                    Account account) {
        super(firstname, surname, email, address, country);

        accounts = new ArrayList<>();
        accounts.add(account);
        setUsername(username);
        setPassword(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = Encyptor.sha256(password);
    }

    public boolean isCustomerOwner(Account account) {
        return true;
    }
}
