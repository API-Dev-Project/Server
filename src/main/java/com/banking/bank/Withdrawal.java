package com.banking.bank;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Graham Murray on 09/11/16.
 */
@Entity
@DiscriminatorValue(value = "Debit")
public class Withdrawal extends Transaction {

    public Withdrawal() {
        super();
    }

    public Withdrawal(Account account, double amount) {
        super(amount, account);
    }

    @Override
    protected void doTransaction() {
        getAccount().updateBalance(getAccount().getBalance() - getAmount());
        setPostBalance(getAccount().getBalance());
    }
}
