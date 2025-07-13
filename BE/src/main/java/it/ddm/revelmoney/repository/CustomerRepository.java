package it.ddm.revelmoney.repository;

import it.ddm.revelmoney.model.entities.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByUsername(String username);
}
