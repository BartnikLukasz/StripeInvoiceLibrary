package bartnik.stripe.library.InvoiceLibrary.services;

import bartnik.stripe.library.InvoiceLibrary.config.StripeConfiguration;
import bartnik.stripe.library.InvoiceLibrary.config.UserDetailsImpl;
import bartnik.stripe.library.InvoiceLibrary.model.UserEntity;
import com.stripe.Stripe;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class InvoiceServiceTest {

    @MockBean
    private InvoiceItemService invoiceItemService;

    @MockBean
    private StripeConfiguration stripeConfiguration;

    @MockBean
    private CustomerService customerService;

    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        invoiceService = new InvoiceService(customerService, invoiceItemService, stripeConfiguration);
    }

    @Test
    void createInvoice_should_create_invoice() throws StripeException {
        createInvoiceItemForTest();
        when(stripeConfiguration.getConfiguration()).thenReturn(Map.of("testKey", "sk_test_4eC39HqLyjWDarjtT1zdp7dc"));
        when(customerService.getCurrentCustomerId()).thenReturn("cus_KL8NFlYryCK2kD");
        invoiceService.createInvoice();
        verify(stripeConfiguration, times(1)).getConfiguration();
        verify(customerService, times(2)).getCurrentCustomerId();
        verify(invoiceItemService, times(1)).createInvoiceItem(anyString());
    }

    @Test
    void createInvoice_should_throw_InvalidRequestException() throws StripeException {
        when(stripeConfiguration.getConfiguration()).thenReturn(Map.of("testKey", "sk_test_4eC39HqLyjWDarjtT1zdp7dc"));
        when(customerService.getCurrentCustomerId()).thenReturn("cus_KL8NFlYryCK2kD");
        assertThrows(InvalidRequestException.class, () -> invoiceService.createInvoice());
    }

    @Test
    void downloadInvoice_should_return_link() throws StripeException {
        createInvoiceItemForTest();
        when(stripeConfiguration.getConfiguration()).thenReturn(Map.of("testKey", "sk_test_4eC39HqLyjWDarjtT1zdp7dc"));
        when(customerService.getCurrentCustomerId()).thenReturn("cus_KL8NFlYryCK2kD");
        invoiceService.createInvoice();
        invoiceService.downloadInvoice();
        verify(stripeConfiguration, times(2)).getConfiguration();
        verify(customerService, times(3)).getCurrentCustomerId();
        verify(invoiceItemService, times(1)).createInvoiceItem(anyString());
    }

    void createInvoiceItemForTest() throws StripeException {

        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";
        Map<String, Object> params = new HashMap<>();
        params.put("customer", "cus_KL8NFlYryCK2kD");
        params.put("amount", 1);
        params.put("currency", "usd");

        InvoiceItem.create(params);
    }
}