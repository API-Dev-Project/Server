package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;

/**
 * Created by Graham Murray on 07/11/16.
 */
public class BaseTest {

    protected static final double DELTA = 1e-15;

    protected Customer createCustomer() {
        try {
            return new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "John.Doe", "123456");
        } catch (CustomerAlreadyExistsException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected Account createAccount(Customer customer) {
        return new Account(1234, customer);
    }
}
