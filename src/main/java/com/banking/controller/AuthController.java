package com.banking.controller;

import com.banking.bank.Customer;
import com.banking.persistence.PersistenceManager;
import com.banking.util.HashUtil;

/**
 * This controller deals with authentication prior
 * to any transactions taking place on a account
 *
 * @author Graham Murray
 */
public class AuthController {

    private PersistenceManager persistenceManager;
    private Customer customer;

    public AuthController(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * Takes a email and password and returns the customer
     * associated with if credentials are valid
     *
     * @param email
     * @param password
     * @return Customer
     */
    public Customer getAuthenticatedCustomer(String email, String password) {
        persistenceManager.start();
        customer = persistenceManager.getCustomerByEmail(email);
        persistenceManager.commit();

        if (customer != null && isPasswordCorrect(password)) {
            return customer;
        }

        return null;
    }

    private boolean isPasswordCorrect(String password) {
        String encryptedPassword = HashUtil.sha256(password);

        return encryptedPassword.equals(customer.getPassword()) ? true : false;
    }
}
