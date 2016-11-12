package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InvalidAmountException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Graham Murray on 09/11/16.
 */
public class LodgementTest extends BaseTest{

    @Test
    public void testLodgeToCorrectAccount() throws InvalidAmountException, CustomerNotOwnerException {
        Customer customer = createCustomer();
        Account accountOne = createAccount(customer);

        accountOne.lodge(customer, 50);
        accountOne.lodge(customer, 50);
        accountOne.lodge(customer, 50);
        accountOne.lodge(customer, 50);

        assertEquals(200.00, accountOne.getBalance(), DELTA);
    }

    @Test(expected =  CustomerNotOwnerException.class)
    public void testLodgeToInCorrectAccount() throws InvalidAmountException, CustomerNotOwnerException {
        Customer customer = createCustomer();
        Customer incorrectCustomer = new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "Ireland", "John.Doe", "123456");
        Account account = createAccount(customer);

        account.lodge(incorrectCustomer, 50);
        assertEquals(0, account.getBalance(), DELTA);
    }

    @Test(expected =  InvalidAmountException.class)
    public void testLodgeInvalidAmount() throws InvalidAmountException, CustomerNotOwnerException {
        Customer customer = createCustomer();
        Account accountOne = createAccount(customer);

        accountOne.lodge(customer, -10);
        assertEquals(0, accountOne.getBalance(), DELTA);
    }
}
