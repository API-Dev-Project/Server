import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.mapping.MappingManager;
import com.banking.mapping.ReadMapping;
import com.banking.mapping.WriteMapping;

import java.util.Map;

public class JDBC {

    public static void main(String[] args) {
        //Customer customer = new Customer("John", "Doe", "jd@gmail.com", "44 Some Road", "John.Doe", "123456");

        MappingManager manager = new MappingManager();
        System.out.println(manager.getReadMapping().doesCustomerExist("j@gmail.com"));
        System.out.println(manager.getReadMapping().doesAccountExist(2531219));
    }
}