package com.banking.bank;


import com.banking.bank.exception.CustomerNotOwnerException;

public class Lodgement extends Transaction {

    public Lodgement(Customer customer, Account account, double amount) throws CustomerNotOwnerException {
        super(Type.CREDIT, amount);
        lodge(customer, account, amount);
    }

    private void lodge(Customer customer, Account account, double amount) throws CustomerNotOwnerException {
        if (customer.isOwner(account)) {
            account.updateBalance(account.getBalance() + amount);
            setBalanceAfter(account.getBalance());
        } else {
            throw new CustomerNotOwnerException();
        }
    }
}
