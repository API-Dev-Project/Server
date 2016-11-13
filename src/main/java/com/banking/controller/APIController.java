package com.banking.controller;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.mapping.MappingManager;

/**
 * Created by Graham Murray on 12/11/16.
 */
public class APIController {

    private MappingManager mappingManager;

    public APIController() {
        mappingManager = new MappingManager();
    }


    public void createCustomer(Customer customer) {
        //TODO
    }

    public void createAccount(Account account) {
        //TODO
    }

    /*
     * @param customerId
     * @return Customer
     * @description Returns a Customer object with all associated accounts and transactions
     */
    private Customer load(int customerId) {
        //TODO
        return null;
    }
}
