package bartnik.stripe.library.InvoiceLibrary.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.InvoiceItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceItemService {

    public void createInvoiceItem(String customerId){

        Map<String, Object> paramsItem = new HashMap<>();
        paramsItem.put("customer", customerId);
        paramsItem.put("amount", 1);
        paramsItem.put("currency", "usd");

        InvoiceItem item = null;

        try {
            item = InvoiceItem.create(paramsItem);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
