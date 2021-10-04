package bartnik.stripe.library.InvoiceLibrary.config;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageProvider messageProvider;

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleStripeAuthenticationException(AuthenticationException ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.VARIANT_ALSO_NEGOTIATES).body(messageProvider.getError().get("invalidServerConfiguration"));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<Object> handleStripeInvalidRequestException(InvalidRequestException ex, WebRequest request){
        if(ex.getCode().equals("invoice_no_customer_line_items")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageProvider.getError().get("noItemsForCustomer"));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageProvider.getError().get("invalidCustomerId"));
    }
}
