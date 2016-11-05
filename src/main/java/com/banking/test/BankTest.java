package com.banking.test;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import org.glassfish.jersey.message.internal.AcceptableLanguageTag;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by graham on 05/11/16.
 */
public class BankTest {

    @Test
    public void testCreateCustomer() {
        Customer customer = createCustomer();

        assertEquals("John", customer.getFirstname());
        assertEquals("Doe", customer.getSurname());
        assertEquals("jd@gmail.com", customer.getEmail());
        assertEquals("44 Some Road", customer.getAddress());
        assertEquals("Ireland", customer.getCountry());
        assertEquals("John.Doe", customer.getUsername());
    }

    @Test
    public void testEncryptPassword() {
        String cipherText = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        Customer customer = createCustomer();

        assertEquals(cipherText, customer.getPassword());
    }

    @Test
    public void testCreateAccount() {
        Customer customer = createCustomer();
        Account account = new Account(1234, customer);

        assertEquals(account.getOwner(), customer);
    }

    @Test
    public void testCustomerIsOwner() {
        Customer customer = createCustomer();
        Account account = new Account(1234, customer);

        assertTrue(customer.isOwner(account));
    }

    @Test
    public void testCustomerWithTwoAccounts() {
        Customer customer = createCustomer();
        Account accountOne = new Account(1234, customer);
        Account accountTwo = new Account(1234, customer);

        assertTrue(customer.isOwner(accountOne));
        assertNotNull(customer.getAccount(accountTwo.getAccountNumer(), accountTwo.getSortCode()));
    }

    @Test
    public void testLodgeToCorrectAccount() {
        Customer customer = createCustomer();
        Account accountOne = new Account(1234, customer);

    }

    private Customer createCustomer() {
        return new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "Ireland", "John.Doe", "123456");
    }
}
