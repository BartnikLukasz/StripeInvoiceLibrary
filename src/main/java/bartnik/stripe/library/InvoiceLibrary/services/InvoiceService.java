package bartnik.stripe.library.InvoiceLibrary.services;

import bartnik.stripe.library.InvoiceLibrary.config.MessageProvider;
import bartnik.stripe.library.InvoiceLibrary.config.StripeConfiguration;
import bartnik.stripe.library.InvoiceLibrary.config.UserDetailsImpl;
import com.stripe.Stripe;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.net.ApiResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final CustomerService customerService;
    private final InvoiceItemService invoiceItemService;
    private final StripeConfiguration stripeConfiguration;

    public String createInvoice() throws StripeException {

        Stripe.apiKey = stripeConfiguration.getConfiguration().get("testKey");

        invoiceItemService.createInvoiceItem(customerService.getCurrentCustomerId());

        Map<String, Object> params = new HashMap<>();
        params.put("customer", customerService.getCurrentCustomerId());

        Invoice invoice = Invoice.create(params);
        return invoice.getId();
    }

    public String downloadInvoice() throws StripeException {

        Stripe.apiKey = stripeConfiguration.getConfiguration().get("testKey");

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("customer", userDetails.getCustomerId());
        Invoice invoice = Invoice.list(params).getData().get(0);
        invoice = invoice.finalizeInvoice();
        System.out.println(invoice);
        return invoice.getInvoicePdf();
    }
}
