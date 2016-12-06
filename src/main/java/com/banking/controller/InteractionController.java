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

    public Account getAccount(int accountNumber) {
        TypedQuery accountQuery = Query.getAccount(persistenceManager, accountNumber);
        Account account = (Account) persistenceManager.getSingleResult(accountQuery);

        return account;
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

        return customer;
    }

    public Account addAccount(int customerId) {
        persistenceManager.start();

        Customer customer = (Customer) persistenceManager.find(Customer.class, customerId);

        if (customer != null) {
            Account account = generateAccount(customer);

            persistenceManager.persist(account);
            persistenceManager.commit();

            return account;
        }

        return null;
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

    private Account generateAccount(Customer customer) {
        Account account = new Account(customer);
        boolean exists = doesAccountExist(account.getAccountNumber());

        if (exists) {
            return new Account(customer);
        }

        return account;
    }

    private boolean doesAccountExist(int accountNumber) {
        TypedQuery customerQuery = Query.getAccount(persistenceManager, accountNumber);
        Account account = (Account) persistenceManager.getSingleResult(customerQuery);

        return account != null;
    }

    /**
     * Updates a customer and persists it
     *
     * @see Customer
     */
    public Customer updateCustomer(Customer customer) {
        persistenceManager.start();
        Customer customerToUpdate = (Customer) persistenceManager.find(Customer.class, customer.getId());
        customerToUpdate.setFirstname(customer.getFirstname());
        customerToUpdate.setSurname(customer.getSurname());
        customerToUpdate.setAddress(customer.getAddress());
        persistenceManager.persist(customerToUpdate);
        persistenceManager.commit();

        return customerToUpdate;

    }
}
