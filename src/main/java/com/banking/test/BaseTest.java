package com.banking.test;

import com.banking.bank.Account;
import com.banking.bank.Customer;

/**
 * Created by graham on 07/11/16.
 */
public class BaseTest {

    protected Customer createCustomer() {
        return new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "Ireland", "John.Doe", "123456");
    }

    protected Account createAccount(Customer customer) {
        return new Account(1234, customer);
    }
}
