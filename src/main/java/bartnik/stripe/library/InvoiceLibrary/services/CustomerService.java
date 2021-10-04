package bartnik.stripe.library.InvoiceLibrary.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CustomerService {

    public Customer createCustomer(){
        Map<String, Object> params = new HashMap<>();
        params.put(
                "description",
                "Test customer"
        );

        Customer customer = null;

        try {
            customer = Customer.create(params);
        } catch (StripeException e) {
            log.error(e.getMessage(), e);
        }

        return customer;
    }
}
