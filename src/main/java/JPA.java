import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.persistence.PersistenceManager;

/**
 * Created by Graham Murray on 19/11/16.
 */
public class JPA {
    public static void main(String args[]) throws CustomerAlreadyExistsException, CustomerNotOwnerException, InvalidAmountException, InsufficientFundsException {
        PersistenceManager persistenceManager = new PersistenceManager();

        Customer customer = persistenceManager.criteria("jd@gmail.com");


    }
}
