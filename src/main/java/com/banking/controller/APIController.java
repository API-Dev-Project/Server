package com.banking.controller;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.mapping.MappingManager;

/**
 * Created by graham on 12/11/16.
 */
public class APIController {

    private MappingManager mappingManager;

    public APIController() {
        mappingManager = new MappingManager();
    }

    public void createCustomer(Customer customer) {
        mappingManager.getWriteMapping().addCustomer(customer);
    }

    public void createAccount(Account account) {
        mappingManager.getWriteMapping().addAccount(account);
    }
}
