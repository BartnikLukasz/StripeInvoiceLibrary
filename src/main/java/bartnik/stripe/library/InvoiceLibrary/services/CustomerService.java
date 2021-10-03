package bartnik.stripe.library.InvoiceLibrary.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    public Customer createCustomer(){
        Map<String, Object> paramsCus = new HashMap<>();
        paramsCus.put(
                "description",
                "My First Test Customer (created for API docs)"
        );

        Customer customer = null;

        try {
            customer = Customer.create(paramsCus);
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return customer;
    }
}
