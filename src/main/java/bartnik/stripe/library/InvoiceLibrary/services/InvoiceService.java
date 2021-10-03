package bartnik.stripe.library.InvoiceLibrary.services;

import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    public void createInvoice(){
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        Map<String, Object> params = new HashMap<>();
        params.put("customer", "cus_8epDebVEl8Bs2V");

        Invoice invoice = Invoice.create(params);
    }
}
