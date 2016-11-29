import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.controller.InteractionController;


/**
 * Created by Graham Murray on 19/11/16.
 */
public class JPA {
    public static void main(String args[]) throws CustomerAlreadyExistsException, CustomerNotOwnerException, InvalidAmountException, InsufficientFundsException {
        InteractionController controller = new InteractionController();

        Customer customer = new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "John.Doe", "123456");
        customer.addAccount(new Account(customer));

        controller.addNewCustomer(customer);

        //controller.teardown();
    }
}
