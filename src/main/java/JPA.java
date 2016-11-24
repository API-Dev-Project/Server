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
        Customer customer = controller.getCustomer("jd@gmail.com ", "123456");

        if (customer != null) {
            Account account = customer.getAccount(7732621, 1234);
            controller.withdraw(account, 20000);
        }

        //controller.teardown();
    }
}
