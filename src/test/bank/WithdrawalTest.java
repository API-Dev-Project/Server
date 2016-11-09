package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficentFundsException;
import com.banking.bank.exception.InvalidAmountException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Graham Murray on 09/11/16.
 */
public class WithdrawalTest extends BaseTest{

    @Test
    public void testWithdraw() throws InvalidAmountException, CustomerNotOwnerException, InsufficentFundsException {
        Customer customer = createCustomer();
        Account account = createAccount(customer);

        account.lodge(customer, 500);
        account.withdraw(customer, 50);
        assertEquals(450, account.getBalance(), DELTA);
    }

    @Test(expected = InsufficentFundsException.class)
    public void testWithdrawWithZeroBalance() throws InvalidAmountException, CustomerNotOwnerException, InsufficentFundsException{
        Customer customer = createCustomer();
        Account account = createAccount(customer);

        account.withdraw(customer, 50);
        assertEquals(0, account.getBalance(), DELTA);
    }

    @Test(expected = CustomerNotOwnerException.class)
    public void testWithdrawCustomerNotOwner() throws InvalidAmountException, CustomerNotOwnerException, InsufficentFundsException{
        Customer customer = createCustomer();
        Customer incorrectCustomer = createCustomer();
        Account account = createAccount(customer);

        account.withdraw(incorrectCustomer, 50);
        assertEquals(0, account.getBalance(), DELTA);
    }
}

