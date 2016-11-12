import com.banking.bank.Customer;
import com.banking.persistence.Write;

public class JDBC {

    public static void main(String[] args) {
        Write operation = new Write();
        Customer customer = new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "John.Doe", "123456");

        operation.addCustomer(customer);
    }
}