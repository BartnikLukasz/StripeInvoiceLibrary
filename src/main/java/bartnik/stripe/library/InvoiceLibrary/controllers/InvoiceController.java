package bartnik.stripe.library.InvoiceLibrary.controllers;

import bartnik.stripe.library.InvoiceLibrary.config.resources.MessageProvider;
import bartnik.stripe.library.InvoiceLibrary.services.InvoiceService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final MessageProvider messageProvider;

    @PostMapping
    public ResponseEntity<String> createInvoice() {
        try {
            System.out.println();
            return ResponseEntity.ok(StringUtils.join(messageProvider.getSuccess().get("invoiceCreated"), invoiceService.createInvoice()));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.VARIANT_ALSO_NEGOTIATES).body(StringUtils.join(messageProvider.getError().get("stripeDefault"), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<String> downloadInvoice() {
        try {
            return ResponseEntity.ok(invoiceService.downloadInvoice());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.VARIANT_ALSO_NEGOTIATES).body(StringUtils.join(messageProvider.getError().get("stripeDefault"), e.getMessage()));
        }
    }
}
