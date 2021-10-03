package bartnik.stripe.library.InvoiceLibrary.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.net.ApiResource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceService {

    public String createInvoice() {

        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

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

        Map<String, Object> paramsItem = new HashMap<>();
        paramsItem.put("customer", customer.getId());
        paramsItem.put("amount", 1);
        paramsItem.put("currency", "usd");

        InvoiceItem item = null;

        try {
            item = InvoiceItem.create(paramsItem);
        } catch (StripeException e) {
            e.printStackTrace();
        }

        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        Map<String, Object> params = new HashMap<>();
        params.put("customer", customer.getId());

        Invoice invoice = null;
        try {
            invoice = Invoice.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return ApiResource.GSON.toJson(invoice);
    }
}
