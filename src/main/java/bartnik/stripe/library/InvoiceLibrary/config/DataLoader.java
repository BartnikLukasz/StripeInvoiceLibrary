package bartnik.stripe.library.InvoiceLibrary.config;

import bartnik.stripe.library.InvoiceLibrary.model.UserEntity;
import bartnik.stripe.library.InvoiceLibrary.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new UserEntity("user", passwordEncoder.encode("password"), "cus_KL8NFlYryCK2kD"));
    }
}
