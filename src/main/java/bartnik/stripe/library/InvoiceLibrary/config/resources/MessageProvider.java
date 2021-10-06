package bartnik.stripe.library.InvoiceLibrary.config.resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@PropertySource("classpath:messages.properties")
@ConfigurationProperties(prefix = "message")
public class MessageProvider {

    private Map<String, String> error;
    private Map<String, String> success;
}