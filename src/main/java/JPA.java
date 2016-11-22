import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.controller.APIController;


/**
 * Created by Graham Murray on 19/11/16.
 */
public class JPA {
    public static void main(String args[]) throws CustomerAlreadyExistsException, CustomerNotOwnerException, InvalidAmountException, InsufficientFundsException {
        APIController controller = new APIController();
        Customer customer = controller.getCustomer("jd@gmail.com ", "123456");


        if (customer != null) {
            Account account = customer.getAccount(2775439, 1234);
            controller.lodge(account, 200);
        }

        //controller.teardown();
    }
}
