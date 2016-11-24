package com.banking.controller;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.persistence.PersistenceManager;

/**
 * Middleware layer that controls persistence and banking operations
 *
 * @author Graham Murray
 */
public class InteractionController {

    private PersistenceManager persistenceManager;
    private AuthController authController;

    public InteractionController() {
        persistenceManager = new PersistenceManager();
        authController = new AuthController(persistenceManager);
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

        return null;
    }

    /**
     * Adds a new customer who doesn't exist along with
     * persisting the new account
     *
     * @param account
     * @see Account
     */
    public void addNewCustomerWithAccount(Account account) {
        persistenceManager.persist(account.getOwner());
        persistenceManager.persist(account);
        persistenceManager.commit();
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

        account.withdraw(amount);
        persistenceManager.persist(account.getLastTransaction());
        persistenceManager.commit();
    }

    /**
     * Closes the database connection after all transactions
     * have taken place
     */
    public void teardown() {
        persistenceManager.close();
    }
}
