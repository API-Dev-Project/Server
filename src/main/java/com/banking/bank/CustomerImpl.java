package com.banking.bank;

import com.banking.util.Encyptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graham on 03/11/16.
 */
public class CustomerImpl extends PersonImpl implements Customer{

    private List<AccountImpl> accounts;
    private String username;
    private String password;

    public CustomerImpl(String firstname,
                        String surname,
                        String email,
                        String address,
                        String country,
                        String username,
                        String password) {
        super(firstname, surname, email, address, country);

        accounts = new ArrayList<>();
        setUsername(username);
        setPassword(password);
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPassword(String password) {
        this.password = Encyptor.sha256(password);
    }
}
