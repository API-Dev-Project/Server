package com.banking.controller;

import com.banking.bank.Customer;
import com.banking.persistence.PersistenceManager;

/**
 * This controller deals with authentication prior
 * to any transactions taking place on a account
 *
 * @author Graham Murray
 */
public class AuthController {

    private PersistenceManager persistenceManager;

    public AuthController() {
        persistenceManager = new PersistenceManager();
    }

    public boolean isCredentialsValid(Customer customer) {
        persistenceManager.start();
        Customer customerToAuthenticate = (Customer) persistenceManager.find(customer, customer.getId());

        if (customer != null) {
            if(customer.getUsername().equals(customerToAuthenticate.getUsername())
                    && customer.getPassword().equals(customerToAuthenticate.getPassword())) {
                return true;
            }
        }

        return false;
    }

}
