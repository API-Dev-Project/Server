package com.banking.controller;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.persistence.PersistenceManager;
import com.banking.persistence.Query;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

/**
 * Middleware layer that controls persistence and banking operations
 *
 * @author Graham Murray
 */
public class InteractionController {

    private PersistenceManager persistenceManager;
    private AuthController authController;
    private Map exceptions;

    public InteractionController() {
        persistenceManager = new PersistenceManager();
        authController = new AuthController(persistenceManager);
        exceptions = new HashMap();
    }

    /**
     * Returns a Customer based on email and password if they exist
     * otherwise null is return
     *
     * @param email
     * @param password
     * @return Customer
     */
    public Customer getCustomer(String email, String password) {
        return authController.getAuthenticatedCustomer(email, password);
    }

    public Customer getCustomerById(int id, String email, String password) {
        Customer customer = (Customer) persistenceManager.find(Customer.class, id);

        if (customer != null) {
            boolean isValidCustomer = authController.isCredentialsValid(customer, email, password);

            if (isValidCustomer) {
                return customer;
            }
        }

        addException(InvalidAmountException.class);

        return null;
    }

    /**
     * Adds a new customer who doesn't exist along with
     * persisting the new account
     *
     * @param customer
     * @see Customer
     */
    public Customer addNewCustomer(Customer customer) {
        persistenceManager.start();
        persistenceManager.persist(customer);
        persistenceManager.commit();

        return getCustomer(customer.getEmail(), customer.getPassword());
    }

    /**
     * Lodges an amount to an account an persists it
     *
     * @param account
     * @param amount
     * @throws CustomerNotOwnerException
     * @throws InvalidAmountException
     */
    public void lodge(Account account, double amount) throws CustomerNotOwnerException, InvalidAmountException {

        persistenceManager.start();
        account.lodge(amount);
        persistenceManager.persist(account.getLastTransaction());
        persistenceManager.commit();
    }

    /**
     * Withdraws an amount from an account an persists it
     *
     * @param account
     * @param amount
     * @throws InsufficientFundsException
     * @throws CustomerNotOwnerException
     * @throws InvalidAmountException
     */
    public void withdraw(Account account, double amount) throws InsufficientFundsException,
            CustomerNotOwnerException,
            InvalidAmountException {

        persistenceManager.start();
        account.withdraw(amount);
        persistenceManager.persist(account.getLastTransaction());
        persistenceManager.commit();
    }

    public void addException(Object value) {
        exceptions.put("Error", value);
    }

    /**
     * Closes the database connection after all transactions
     * have taken place
     */
    public void teardown() {
        persistenceManager.close();
    }
}
