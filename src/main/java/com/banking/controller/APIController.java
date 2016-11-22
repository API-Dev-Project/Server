package com.banking.controller;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.persistence.PersistenceManager;

/**
 * Middleware layer that controls persistence and banking logic
 *
 * @author Graham Murray
 */
public class APIController {

    private PersistenceManager persistenceManager;
    private AuthController authController;

    public APIController() {
        persistenceManager = new PersistenceManager();
        authController = new AuthController();
    }

    public Customer getCustomer(String username, String password) {

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
        persistenceManager.start();
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
        persistenceManager.start();
        Account accountToUpdate = (Account) persistenceManager.find(account, account.getId());

        account.lodge(accountToUpdate.getOwner(), amount);

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
        Account accountToUpdate = (Account) persistenceManager.find(account, account.getId());

        account.withdraw(accountToUpdate.getOwner(), amount);

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
