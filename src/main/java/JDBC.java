import com.banking.bank.Customer;
import com.banking.mapping.WriteMapping;

public class JDBC {

    public static void main(String[] args) {
        WriteMapping operation = new WriteMapping();
        Customer customer = new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "John.Doe", "123456");

        operation.addCustomer(customer);
    }
}