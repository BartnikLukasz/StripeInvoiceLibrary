package bartnik.stripe.library.InvoiceLibrary;

import bartnik.stripe.library.InvoiceLibrary.config.MessageProvider;
import bartnik.stripe.library.InvoiceLibrary.services.InvoiceService;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private MessageProvider messageProvider;


    @Test
    void createInvoice_should_return_error_message() throws Exception {
        when(messageProvider.getError()).thenReturn(Map.of("stripeDefault", "Error occurred during connection with Stripe API. Message: "));
        when(messageProvider.getSuccess()).thenReturn(Map.of("invoiceCreated", "Invoice has been successfully created with id: "));
        when(invoiceService.createInvoice()).thenThrow(new InvalidRequestException("Exception message", null, null, null, null, null));

        this.mockMvc.perform(post("/api"))
                .andExpect(status().isVariantAlsoNegotiates())
                .andExpect(content().string("Error occurred during connection with Stripe API. Message: Exception message"));
    }
    
    @Test
    void createInvoice_should_return_success_message() throws Exception {
        when(messageProvider.getError()).thenReturn(Map.of("stripeDefault", "Error occurred during connection with Stripe API. Message: "));
        when(messageProvider.getSuccess()).thenReturn(Map.of("invoiceCreated", "Invoice has been successfully created with id: "));
        when(invoiceService.createInvoice()).thenReturn("Invoice id");

        this.mockMvc.perform(post("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice has been successfully created with id: Invoice id"));
    }

    @Test
    void downloadInvoice_should_return_error_message() throws Exception {
        when(messageProvider.getError()).thenReturn(Map.of("stripeDefault", "Error occurred during connection with Stripe API. Message: "));
        when(messageProvider.getSuccess()).thenReturn(Map.of("invoiceCreated", "Invoice has been successfully created with id: "));
        when(invoiceService.downloadInvoice()).thenThrow(new InvalidRequestException("Exception message", null, null, null, null, null));

        this.mockMvc.perform(get("/api"))
                .andExpect(status().isVariantAlsoNegotiates())
                .andExpect(content().string("Error occurred during connection with Stripe API. Message: Exception message"));
    }

    @Test
    void downloadInvoice_should_return_success_message() throws Exception {
        when(messageProvider.getError()).thenReturn(Map.of("stripeDefault", "Error occurred during connection with Stripe API. Message: "));
        when(messageProvider.getSuccess()).thenReturn(Map.of("invoiceCreated", "Invoice has been successfully created with id: "));
        when(invoiceService.downloadInvoice()).thenReturn("Download url");

        this.mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("Download url"));
    }
}