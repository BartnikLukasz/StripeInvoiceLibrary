package bartnik.stripe.library.InvoiceLibrary.config;

import bartnik.stripe.library.InvoiceLibrary.config.resources.MessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageProvider messageProvider;

    @org.springframework.web.bind.annotation.ExceptionHandler(ClassCastException.class)
    protected ResponseEntity<Object> handleClassCastException(ClassCastException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageProvider.getError().get("userNotAuthorized"));
    }
}
