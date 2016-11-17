package com.banking.bank;

import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.mapping.MappingManager;
import com.banking.mapping.ReadMapping;
import com.banking.util.HashUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Graham Murray on 03/11/16.
 */

public class Customer extends Person {

    private int id;
    private List<Account> accounts;
    private String username;
    private String password;
    private MappingManager mappingManager;

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
                    String password) throws CustomerAlreadyExistsException {
        super(firstname, surname, email, address);

        id = 0;
        accounts = new ArrayList<>();
        mappingManager = new MappingManager();
        setUsername(username);
        setPassword(password);

        persist();
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

    private void persist() throws CustomerAlreadyExistsException {
        checkCustomerExists();
        mappingManager.getWriteMapping().addCustomer(this);
    }

    private void checkCustomerExists() throws CustomerAlreadyExistsException{
        ReadMapping readMapping = mappingManager.getReadMapping();

        if (readMapping != null && readMapping.doesCustomerExist(getEmail())) {
            throw new CustomerAlreadyExistsException();
        }
    }
}
