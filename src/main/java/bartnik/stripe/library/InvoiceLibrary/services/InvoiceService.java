package bartnik.stripe.library.InvoiceLibrary.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.net.ApiResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final CustomerService customerService;
    private final InvoiceItemService invoiceItemService;

    public String createInvoice() {

        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        Customer customer = customerService.createCustomer();
        invoiceItemService.createInvoiceItem(customer);

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

    public String downloadInvoice(String invoiceId){
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        Invoice invoice = null;

        try {
            invoice = Invoice.retrieve(invoiceId);
            return invoice.finalizeInvoice().getInvoicePdf();
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return "err";
    }
}
