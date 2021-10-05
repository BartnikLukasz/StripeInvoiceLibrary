package bartnik.stripe.library.InvoiceLibrary.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.InvoiceItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class InvoiceItemService {

    public void createInvoiceItem(String customerId) throws StripeException {

        Map<String, Object> params = new HashMap<>();
        params.put("customer", customerId);
        params.put("amount", 1);
        params.put("currency", "usd");

        InvoiceItem.create(params);
    }
}
