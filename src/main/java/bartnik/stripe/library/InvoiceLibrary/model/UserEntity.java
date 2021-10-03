package bartnik.stripe.library.InvoiceLibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    Long id;

    String username;

    String password;

    String customerId;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String customerId) {
        this.username = username;
        this.password = password;
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomerId() {
        return customerId;
    }
}
