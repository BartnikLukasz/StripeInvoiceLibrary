package bartnik.stripe.library.InvoiceLibrary.services;

import bartnik.stripe.library.InvoiceLibrary.config.UserDetailsImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CustomerService {

    public Customer createCustomer() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put(
                "description",
                "Test customer"
        );

        return Customer.create(params);
    }

    public String getCurrentCustomerId(){
        UserDetailsImpl userDetails = (UserDetailsImpl)  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userDetails.getCustomerId();
    }
}
