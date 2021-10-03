package bartnik.stripe.library.InvoiceLibrary;

import bartnik.stripe.library.InvoiceLibrary.services.InvoiceService;
import com.stripe.model.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<String> createInvoice(){
        return ResponseEntity.ok(invoiceService.createInvoice());
    }
}
