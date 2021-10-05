package bartnik.stripe.library.InvoiceLibrary.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "stripe")
public class StripeConfiguration {

    private Map<String, String> configuration;
}
