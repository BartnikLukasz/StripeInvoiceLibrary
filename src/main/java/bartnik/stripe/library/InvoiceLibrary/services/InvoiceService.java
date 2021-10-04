package bartnik.stripe.library.InvoiceLibrary.services;

import bartnik.stripe.library.InvoiceLibrary.config.UserDetailsImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.net.ApiResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final CustomerService customerService;
    private final InvoiceItemService invoiceItemService;

    public String createInvoice() {

        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        UserDetailsImpl userDetails = (UserDetailsImpl)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        invoiceItemService.createInvoiceItem(userDetails.getCustomerId());

        Map<String, Object> params = new HashMap<>();
        params.put("customer", userDetails.getCustomerId());

        Invoice invoice = null;
        try {
            invoice = Invoice.create(params);
        } catch (StripeException e) {
            log.error(e.getMessage(), e);
        }

        return ApiResource.GSON.toJson(invoice);
    }

    public String downloadInvoice(){

        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        UserDetailsImpl userDetails = (UserDetailsImpl)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("customer", userDetails.getCustomerId());

        try {
            return Invoice.list(params).getData().get(0).getInvoicePdf();
        } catch (StripeException e) {
            log.error(e.getMessage(), e);
        }
        return "err";
    }
}
