package bartnik.stripe.library.InvoiceLibrary;

import bartnik.stripe.library.InvoiceLibrary.services.InvoiceService;
import com.stripe.model.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

    @PostMapping
    public ResponseEntity<String> createInvoice(){
        return ResponseEntity.ok(invoiceService.createInvoice());
    }

    @GetMapping
    public ResponseEntity<?> downloadInvoice(){
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, invoiceService.downloadInvoice(null))
                .build();
    }
}
